package newland.iaf.inst.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.inst.dao.InstCollDao;
import newland.iaf.inst.model.InstCollMerch;
import newland.iaf.inst.model.InstCollOrd;

/**
 * 机构关注daoimpl
 * 
 * @author Mr.Towne
 * 
 */
@Repository("com.newland.iaf.instCollDao")
public class InstCollDaoImpl extends BaseDao implements InstCollDao {

	/**
	 * 新增机构关注订单
	 */
	@Override
	public void saveInstCollOrd(InstCollOrd instCollOrd) {
		this.getSession().save(instCollOrd);
	}

	/**
	 * 删除机构关注订单
	 */
	@Override
	public void deleteInstCollOrd(InstCollOrd instCollOrd) {
		this.getSession().delete(instCollOrd);
	}

	/**
	 * 新增机构关注商户
	 */
	@Override
	public void saveInstCollMerch(InstCollMerch instCollMerch) {
		this.getSession().save(instCollMerch);
	}

	/**
	 * 删除机构关注商户
	 */
	@Override
	public void deleteInstCollMerch(InstCollMerch instCollMerch) {
		this.getSession().delete(instCollMerch);
	}

	/**
	 * 根据机构内部编号查询机构关注商户
	 */
	@Override
	public List<InstCollMerch> queryCollMerchByIinst(Long iInst) {
		InstCollMerch instCollMerch = new InstCollMerch();
		instCollMerch.setiInst(iInst);
		return this.getHibernateDao().findByExample(instCollMerch);
	}

	@Override
	public InstCollMerch getInstCollMerch(Long iInstCollMerch) {
		return (InstCollMerch) this.getSession().get(InstCollMerch.class, iInstCollMerch);
	}

	@Override
	public List<InstCollOrd> queryCollOrdByIInst(Long iInst) {
		InstCollOrd instCollOrd = new InstCollOrd();
		instCollOrd.setiInst(iInst);
		return this.getHibernateDao().findByExample(instCollOrd);
	}

	@Override
	public InstCollMerch queryByIinstAndImerch(Long iInst, Long iMerch) {
		InstCollMerch ic = new InstCollMerch();
		ic.setiInst(iInst);
		ic.setiMerch(iMerch);
		return this.getHibernateDao().getFirstOneByExample(ic);
	}

	@Override
	public InstCollOrd queryInstCollOrdByIinstAndImerchAndIdebitBid(Long iInst, Long iMerch, Long idebitBid) {
		InstCollOrd ico = new InstCollOrd();
		ico.setiInst(iInst);
		ico.setiMerch(iMerch);
		ico.setIdebitBid(idebitBid);
		return this.getHibernateDao().getFirstOneByExample(ico);
	}
}
