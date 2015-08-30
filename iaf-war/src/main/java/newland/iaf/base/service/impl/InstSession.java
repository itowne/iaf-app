package newland.iaf.base.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.servlet.LoginFlag;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstAuthType;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.utils.DateUtils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 机构session
 * 
 * @author rabbit
 * 
 */
public class InstSession extends AbsSession {

	private Inst inst;
	private InstBusiInfo instBusiInfo;
	private InstLegalPers instLegalPers;

	private LoanStatistics statistics;

	private BigInteger loanPdtCount;

	private BigInteger debitBidCount;

	/**
	 * 机构所有权限
	 */
	private List<InstAuth> auths;

	/**
	 * 用户角色
	 */
	private List<InstRole> roles;

	/**
	 * 用户权限
	 */
	private List<InstAuth> allowAuths;

	/**
	 * 
	 */
	private InstUser instUsr;

	@Override
	public String flag() {
		return LoginFlag.INST_LOGIN;
	}

	public List<InstAuth> getAuths() {
		return auths;
	}

	public Map<Long, MenuNode> getMenuMap() {
		Map<Long, MenuNode> nodes = new TreeMap<Long, MenuNode>();
		add(nodes, allowAuths, null);
		return nodes;
	}

	public List<MenuNode> getMenuList() {
		Map<Long, MenuNode> menuMap = getMenuMap();
		List<MenuNode> menu = new ArrayList<MenuNode>();
		menu.addAll(menuMap.values());
		return menu;
	}

	public void add(Map<Long, MenuNode> nodes, List<InstAuth> auths,
			Long piinstAuth) {
		for (InstAuth ia : auths) {
			if (!ObjectUtils.equals(ia.getParentIinstAuth(), piinstAuth)) {
				continue;
			}
			if (!ObjectUtils.equals(ia.getAuthType(), InstAuthType.menu)) {
				continue;
			}
			Long iia = ia.getiInstAuth();
			if (!nodes.containsKey(iia)) {
				MenuNode n = new MenuNode();
				n.setInstAuth(ia);
				TreeMap<Long, MenuNode> subNodes = new TreeMap<Long, MenuNode>();
				n.setSubNodeMap(subNodes);
				nodes.put(iia, n);
				add(subNodes, auths, iia);
			}
		}
	}

	public boolean hasInstAuth(String authCode) {
		for (InstAuth ia : allowAuths) {
			if (ObjectUtils.equals(ia.getAuthCode(), authCode)) {
				return true;
			}
		}
		return false;
	}
	
	public void setAuths(List<InstAuth> auths) {
		this.auths = auths;
		Set<String> authUrlSet = new HashSet<String>();
		for (InstAuth ia : auths) {
			String url = ia.getUrl();
			if (!StringUtils.isBlank(url)) {
				authUrlSet.add(ia.getUrl());
			}
		}
		this.setAuthUrls(authUrlSet);
	}

	public List<InstRole> getRoles() {
		return roles;
	}

	public void setRoles(List<InstRole> roles) {
		this.roles = roles;
	}

	public List<InstAuth> getAllowAuths() {
		return allowAuths;
	}

	public void setAllowAuths(List<InstAuth> allowAuths) {
		this.allowAuths = allowAuths;
		Set<String> allowAuthUrlSet = new HashSet<String>();
		for (InstAuth ia : allowAuths) {
			String url = ia.getUrl();
			if (!StringUtils.isBlank(url)) {
				allowAuthUrlSet.add(url);
			}
		}
		this.setAllowUrls(allowAuthUrlSet);

	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}

	public InstLegalPers getInstLegalPers() {
		return instLegalPers;
	}

	public void setInstLegalPers(InstLegalPers instLegalPers) {
		this.instLegalPers = instLegalPers;
	}

	public InstUser getInstUsr() {
		return instUsr;
	}

	public void setInstUsr(InstUser instUsr) {
		this.instUsr = instUsr;
	}

	public LoanStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(LoanStatistics statistics) {
		this.statistics = statistics;
	}

	public BigInteger getLoanPdtCount() {
		return loanPdtCount;
	}

	public void setLoanPdtCount(BigInteger loanPdtCount) {
		this.loanPdtCount = loanPdtCount;
	}

	public BigInteger getDebitBidCount() {
		return debitBidCount;
	}

	public void setDebitBidCount(BigInteger debitBidCount) {
		this.debitBidCount = debitBidCount;
	}

	public String getAmOrPm() {
		return DateUtils.getAM_PM_Chinese(new Date());
	}

}
