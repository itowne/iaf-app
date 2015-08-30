package newland.iaf.base.service;

import java.util.List;
import java.util.Map;

import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;

/**
 * 
 * @author rabbit
 * 
 */
public interface SysParamService {

	List<SysParam> findSysParamByType(SysParamType type);

	Map<SysParamName, SysParam> findSysParamMapByType(SysParamType type);

	void updateSysParam(SysParam param);

	SysParam getSysParam(SysParamType type, SysParamName paramName);
	
	/**
	 * 批量更新参数信息
	 * @param sysParamList
	 */
	void batchUpdateSysParam(Map<SysParamName, SysParam> sysParamMap);
}
