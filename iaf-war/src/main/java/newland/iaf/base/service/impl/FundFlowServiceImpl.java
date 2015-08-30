package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.FundFlowDao;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.trans.model.HcTransLog;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("fundFlowService")
@Transactional(readOnly = false)
public class FundFlowServiceImpl implements FundFlowService {
	@Resource(name = "fundFlowDao")
	private FundFlowDao fundFlowDao;
	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	@Resource (name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	@Override
	public void save(FundFlow fundFlow) {
		if (StringUtils.isBlank(fundFlow.getTraceNo()))
			fundFlow.setTraceNo(this.serialNoService.genFundFlowNo());
		this.fundFlowDao.save(fundFlow);
	}

	@Override
	public void update(FundFlow fundFlow, FundFlowStat stat) {
		fundFlow.setStatus(stat);
		this.fundFlowDao.update(fundFlow);
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowType type, FundFlowStat stat) {
		FundFlow ff = new FundFlow();
		ff.setType(type);
		ff.setStatus(stat);
		return this.fundFlowDao.queryFundFlowBy(ff);
	}

	@Override
	public FundFlow auditCredit(FundFlow fundFlow) throws Exception {
		fundFlow.setStatus(FundFlowStat.SUCCESS);
		fundFlow.setUpdTime(new Date());
		this.fundFlowDao.update(fundFlow);
		return fundFlow;
	}

	@Override
	public FundFlow auditRefund(FundFlow fundFlow) throws Exception {
		fundFlow.setStatus(FundFlowStat.SUCCESS);
		fundFlow.setUpdTime(new Date());
		this.fundFlowDao.update(fundFlow);
		return fundFlow;
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowType type,
			Set<FundFlowStat> status) {
		FundFlowCondition cond = new FundFlowCondition();
		cond.setType(type);
		cond.setStatus(status);
		return this.fundFlowDao.queryByCondition(cond);
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowCondition cond) {
		return this.fundFlowDao.queryByCondition(cond);
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowCondition cond, Page page) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("ifundFlow"));
		cond.setOrders(orderList);
		return this.fundFlowDao.queryByCondition(cond, page);
	}

	@Override
	public FundFlow cancel(FundFlow fundFlow) throws Exception {
		fundFlow.setStatus(FundFlowStat.EXPIRY);
		this.fundFlowDao.update(fundFlow);
		return fundFlow;
	}

	@Override
	public boolean haveFundFlow(Long iloanOrd, FundFlowType type) {
		return this.fundFlowDao.haveFundFlow(iloanOrd, type);
	}

	@Override
	public List<FundFlow> queryByIloanOrdAndType(Long iloanOrd,
			FundFlowType type) {
		// TODO Auto-generated method stub
		return this.fundFlowDao.queryByIloanOrdAndType(iloanOrd, type);
	}

	@Override
	public FundFlow queryByIloanOrdPlan(Long iLoanOrdPlan) {
		// TODO Auto-generated method stub
		return this.fundFlowDao.queryByIloanOrdPlan(iLoanOrdPlan);
	}
	
	@Override
	public List<Object> queryTrade(final FundFlowStat stat, final Object[] type) {
		List<Object> objList = (List<Object>) this.fundFlowDao.queryBy(
				new CriteriaExecutor<List<Object>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("amount"))
										.add(Projections.rowCount()))
								.add(Restrictions.eq("status", stat))
								.add(Restrictions.in("type", type));
						return expr.list();
					}
				}, FundFlow.class);
		return objList;
	}

	@Override
	public List<Object> queryCredit(final Long iinst, final FundFlowStat stat,
			final FundFlowType type) {
		List<Object> objList = (List<Object>) this.fundFlowDao.queryBy(
				new CriteriaExecutor<List<Object>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("amount"))
										.add(Projections.rowCount()))
								.add(Restrictions.eq("iinst", iinst))
								.add(Restrictions.eq("status", stat))
								.add(Restrictions.eq("type", type));
						return expr.list();
					}
				}, FundFlow.class);
		return objList;
	}
}
