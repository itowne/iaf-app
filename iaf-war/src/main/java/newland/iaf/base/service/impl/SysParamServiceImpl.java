package newland.iaf.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.dao.SysParamDao;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.sysParamService")
@Transactional
public class SysParamServiceImpl implements SysParamService {

	@Resource(name = "com.newland.iaf.sysParamDao")
	private SysParamDao sysParamDao;

	@Override
	public List<SysParam> findSysParamByType(SysParamType type) {
		return sysParamDao.findSysParamDaoByType(type);
	}

	@Override
	public void updateSysParam(SysParam param) {
		sysParamDao.updateSysParam(param);
	}

	@Override
	public SysParam getSysParam(SysParamType type, SysParamName paramName) {
		return sysParamDao.getSysParam(type, paramName);
	}

	@Override
	public Map<SysParamName, SysParam> findSysParamMapByType(SysParamType type) {
		List<SysParam> list = this.findSysParamByType(type);
		Map<SysParamName, SysParam> map = new HashMap<SysParamName, SysParam>();
		for (SysParam sysParam : list) {
			map.put(sysParam.getParamName(), sysParam);
		}
		return map;
	}
	
	/**
	 * 批量更新参数信息
	 * @param sysParamList
	 */
	public void batchUpdateSysParam(Map<SysParamName, SysParam> sysParamMap){
		this.sysParamDao.batchUpdateSysParam(sysParamMap);
	}

}
