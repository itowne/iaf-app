/**
 * 
 */
package newland.iaf.inst.dao.impl;

import java.util.ArrayList;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.inst.dao.InstDao;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstBusiInfoCondition;
import newland.iaf.inst.model.condition.InstCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.Towne
 * 
 */
@Repository("com.newland.iaf.instDao")
public class InstDaoImpl extends BaseDao implements InstDao {

	/**
	 * 新增机构
	 */
	@Override
	public void saveInst(Inst inst) {
		this.getSession().save(inst);
	}

	/**
	 * 更改机构
	 */
	@Override
	public void updateInst(Inst inst) {
		this.getSession().update(inst);
	}

	@Override
	public Inst getInst(Long iinst) {
		return (Inst) this.getSession().get(Inst.class, iinst);
	}
	
	/**
	 * 根据id查找机构集合
	 * @param iInst
	 * @return
	 */
	public List<Inst> getInstList(List<LoanOrd> iInst){
		List<Inst> instList = new ArrayList<Inst>();
		for (LoanOrd loanOrd : iInst) {
			Inst inst=this.getInst(loanOrd.getIinst());
			instList.add(inst);
		}
		return instList;
	}

	// -------------------

	@Override
	public void saveInstBusiInfo(InstBusiInfo instbusiinfo) {
		this.getSession().save(instbusiinfo);
	}

	@Override
	public void updateInstBusiInfo(InstBusiInfo instbusiinfo) {
		this.getSession().update(instbusiinfo);
	}

	// ------------------
	@Override
	public void saveInstLegalPers(InstLegalPers instlegalpers) {
		this.getSession().save(instlegalpers);
	}

	@Override
	public void updateInstLegalPers(InstLegalPers instlegalpers) {
		this.getSession().update(instlegalpers);
	}

	@Override
	public InstLegalPers getInstLegalPersIinst(Long iInstLegalPers) {
		return (InstLegalPers) this.getSession().get(InstLegalPers.class, iInstLegalPers);
	}

	@Override
	public InstBusiInfo getInstBusiInfoByIinst(Long iinst) {
		InstBusiInfo i = new InstBusiInfo();
		i.setiInst(iinst);
		return this.getHibernateDao().getFirstOneByExample(i);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getInstBusiInfoByAcceptRegion(String acceptRegion) {
		InstBusiInfoCondition instBusiInfoCon = new InstBusiInfoCondition();
		instBusiInfoCon.setAcceptRegion(acceptRegion);
		List<InstBusiInfo> instBusiInfoList= this.getHibernateDao().findByCriteriaEx(instBusiInfoCon);
		List<Long> iinstList = new ArrayList<Long>();
		for(InstBusiInfo ibi : instBusiInfoList){
			if(ibi != null){
				iinstList.add(ibi.getiInst());
			}
		}
		return iinstList;
	}
	
	@Override
	public InstLegalPers getInstLegalPersByIinst(Long iinst) {
		InstLegalPers i = new InstLegalPers();
		i.setiInst(iinst);
		return this.getHibernateDao().getFirstOneByExample(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inst> queryInstByCon(InstCondition instCondition, Page page) throws Exception {
		return this.getHibernateDao().findByCriteriaEx(instCondition, page, true);
	}

	/**
	 * 查询全部机构
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Inst> queryInst() {
		return this.getHibernateDao().find(Inst.class);
	}

	@Override
	public Inst queryByInstId(String instId) {
		Inst inst = new Inst();
		inst.setInstId(instId);
		return this.getHibernateDao().getFirstOneByExample(inst);
	}

	@Override
	public InstUser queryUserByIinst(Long iinst) {
		InstUser iu = new InstUser();
		iu.setIinst(iinst);
		return this.getHibernateDao().getFirstOneByExample(iu);
	}
}
