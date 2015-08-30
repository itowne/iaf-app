package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import newland.iaf.inst.model.InstAuth;

/**
 * 菜单
 * 
 * @author rabbit
 * 
 */
public class MenuNode {
	private InstAuth instAuth;

	private Map<Long, MenuNode> subNodeMap;

	public InstAuth getInstAuth() {
		return instAuth;
	}

	public void setInstAuth(InstAuth instAuth) {
		this.instAuth = instAuth;
	}

	public Map<Long, MenuNode> getSubNodeMap() {
		return subNodeMap;
	}

	public void setSubNodeMap(Map<Long, MenuNode> subNodes) {
		this.subNodeMap = subNodes;
	}

	public List<MenuNode> getSubNodeList() {
		List<MenuNode> ret = new ArrayList<MenuNode>();
		ret.addAll(subNodeMap.values());
		return ret;
	}

}
