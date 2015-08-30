package newland.iaf.base.dao;

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
public interface SysParamDao {

	List<SysParam> findSysParamDaoByType(SysParamType type);

	void updateSysParam(SysParam param);

	SysParam getSysParam(SysParamType type, SysParamName paramName);
	
	List<SysParam> getSysParamList(SysParamType type, SysParamName paramName);
	
	/**
	 * 批量更新参数信息
	 * @param sysParamList
	 */
	void batchUpdateSysParam(Map<SysParamName, SysParam> sysParamMap);

}
