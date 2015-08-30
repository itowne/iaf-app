package newland.iaf.base.dao.impl;

import java.util.List;
import java.util.Map;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.SysParamDao;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;

import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.sysParamDao")
public class SysParamDaoImpl extends BaseDao implements SysParamDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysParam> findSysParamDaoByType(SysParamType type) {
		SysParam p = new SysParam();
		p.setParamType(type);
		return this.getHibernateDao().findByExample(p);
	}

	@Override
	public void updateSysParam(SysParam param) {
		this.getSession().update(param);
	}

	@Override
	public SysParam getSysParam(SysParamType type, SysParamName paramName) {
		SysParam p = new SysParam();
		p.setParamType(type);
		p.setParamName(paramName);
		return this.getHibernateDao().getFirstOneByExample(p);
	}

	@Override
	public List<SysParam> getSysParamList(SysParamType type,
			SysParamName paramName) {
		SysParam p = new SysParam();
		p.setParamType(type);
		p.setParamName(paramName);
		return this.getHibernateDao().findByExample(p);
	}
	
	/**
	 * 批量更新参数信息
	 * @param sysParamList
	 */
	public void batchUpdateSysParam(Map<SysParamName, SysParam> sysParamMap){
		for (Object o : sysParamMap.values()) {
			this.getSession().update(o);
		}
	}

}
