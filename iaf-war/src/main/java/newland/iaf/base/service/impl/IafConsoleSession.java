package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.servlet.LoginFlag;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstAuthType;

/**
 * 汇卡管理后台session
 * 
 * @author rabbit
 * 
 */
public class IafConsoleSession extends AbsSession {

	@Override
	public String flag() {
		return LoginFlag.IAF_LOGIN;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 汇卡用户编号
	 */
	public static final Long HICARD_INST = new Long(0);
	/**
	 * 汇卡机构编码
	 */
	public static final String HICARD_INST_ID = "00000000";
	/**
	 * 后台管理用户
	 */
	BackStageUser user;
	/**
	 * 后台管理角色列表
	 */
	//List<BackStageRole> roles;
	
	private List<BackStageAuth> auths;
	
	/**
	 * 用户权限
	 */
	private List<BackStageAuth> allowAuths;
	
	String roles;
	
	public Map<Long, IafMenuNode> getMenuMap() {
		Map<Long, IafMenuNode> nodes = new TreeMap<Long, IafMenuNode>();
		add(nodes, allowAuths, null);
		return nodes;
	}

	public List<IafMenuNode> getMenuList() {
		Map<Long, IafMenuNode> menuMap = getMenuMap();
		List<IafMenuNode> menu = new ArrayList<IafMenuNode>();
		menu.addAll(menuMap.values());
		return menu;
	}
	
	public void add(Map<Long, IafMenuNode> nodes, List<BackStageAuth> auths,
			Long piinstAuth) {
		for (BackStageAuth ia : auths) {
			if (!ObjectUtils.equals(ia.getParentIBsAuth(), piinstAuth)) {
				continue;
			}
			if (!ObjectUtils.equals(ia.getAuthType(), InstAuthType.menu)) {
				continue;
			}
			Long iia = ia.getiBsAuth();
			if (!nodes.containsKey(iia)) {
				IafMenuNode n = new IafMenuNode();
				n.setBackStageAuth(ia);
				TreeMap<Long, IafMenuNode> subNodes = new TreeMap<Long, IafMenuNode>();
				n.setSubNodeMap(subNodes);
				nodes.put(iia, n);
				add(subNodes, auths, iia);
			}
		}
	}
	
	public boolean hasHcAuth(String authCode) {
		for (BackStageAuth bsa : allowAuths) {
			if (ObjectUtils.equals(bsa.getAuthCode(), authCode)) {
				return true;
			}
		}
		return false;
	}
	
	public BackStageUser getUser() {
		return user;
	}

	public void setUser(BackStageUser user) {
		this.user = user;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<BackStageAuth> getAuths() {
		return auths;
	}

	public void setAuths(List<BackStageAuth> auths) {
		this.auths = auths;
		Set<String> authUrlSet = new HashSet<String>();
		for (BackStageAuth ia : auths) {
			String url = ia.getUrl();
			if (!StringUtils.isBlank(url)) {
				authUrlSet.add(ia.getUrl());
			}
		}
		this.setAuthUrls(authUrlSet);
	}

	public List<BackStageAuth> getAllowAuths() {
		return allowAuths;
	}

	public void setAllowAuths(List<BackStageAuth> allowAuths) {
		this.allowAuths = allowAuths;
		Set<String> allowAuthUrlSet = new HashSet<String>();
		for (BackStageAuth ia : allowAuths) {
			String url = ia.getUrl();
			if (!StringUtils.isBlank(url)) {
				allowAuthUrlSet.add(url);
			}
		}
		this.setAllowUrls(allowAuthUrlSet);
	}

}
