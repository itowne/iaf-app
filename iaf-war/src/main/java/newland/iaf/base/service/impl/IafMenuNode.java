package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import newland.iaf.backstage.model.BackStageAuth;

public class IafMenuNode {
	private BackStageAuth backStageAuth;

	private Map<Long, IafMenuNode> subNodeMap;

	public BackStageAuth getBackStageAuth() {
		return backStageAuth;
	}

	public void setBackStageAuth(BackStageAuth backStageAuth) {
		this.backStageAuth = backStageAuth;
	}

	public Map<Long, IafMenuNode> getSubNodeMap() {
		return subNodeMap;
	}

	public void setSubNodeMap(Map<Long, IafMenuNode> subNodes) {
		this.subNodeMap = subNodes;
	}

	public List<IafMenuNode> getSubNodeList() {
		List<IafMenuNode> ret = new ArrayList<IafMenuNode>();
		ret.addAll(subNodeMap.values());
		return ret;
	}



}
