package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanOrdTrackService;
import newland.iaf.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

//import newland.iaf.utils.DateUtils;

/**
 * 定单状态跟踪服务实现
 * 
 * @author new
 * 
 */
@Service("loanOrdTrackService")
public class LoanOrdTrackServiceImpl implements LoanOrdTrackService {
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	private Logger logger = LoggerFactory
			.getLogger(LoanOrdTrackServiceImpl.class);

	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;

	// @Override
	// @Transactional(propagation=Propagation.REQUIRED,
	// rollbackFor=Throwable.class)
	// public void trackOrdAndPlanStat(LoanOrd loanOrd) {
	// if (loanOrd != null
	// && loanOrd.getOrdStat().ordinal()>= OrdStat.REFUNDING.ordinal()
	// && loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()) {
	// Long loanOrdId = loanOrd.getIloanOrd();
	// List<LoanOrdPlan> list = null;
	// try {
	// list = this.loanOrdPlanService.queryPlanById(loanOrdId);
	// } catch (Exception e) {
	// logger.error("跟踪订单状态出现错误", e);
	// }
	// if (CollectionUtils.isEmpty(list) == false){
	// boolean expired = false;
	// for (LoanOrdPlan plan : list){
	// if (DateUtils.isBoforeToday(plan.getRefundDate())){
	// switch(plan.getStat()){
	// case BALANCE:
	// expired = true;
	// plan.setStat(PlanStat.DELIN_QUENCY);
	// logger.info("订单号：" + loanOrd.getIloanOrd() + " 还款期号：" + plan.getPeriod()
	// + "已逾期");
	// break;
	// case FREEZING:
	// case BALANCE_FREEZE:
	// case DELIN_QUENCY:
	// expired = true;
	// break;
	// case PAID_UP_LOAN:
	// }
	// }
	// this.loanOrdPlanService.update(plan);
	// }
	// if (expired){
	// //loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
	// logger.info("订单号：" + loanOrd.getIloanOrd() + " 逾期中。");
	// }else{
	// if (this.loanOrdPlanService.isPaidUp(loanOrd.getIloanOrd())){
	// //loanOrd.setOrdStat(OrdStat.PAID_UP_LOAN);
	// }else{
	// //loanOrd.setOrdStat(OrdStat.REFUND);
	// }
	// }
	// try {
	// this.loanOrdService.updateLoanOrd(loanOrd);
	// } catch (Exception e) {
	// logger.error("跟踪订单状态时出错", e);
	// }
	// }
	// }
	//
	// }

	private void trackPlanStat(LoanOrd loanOrd) {
		if (loanOrd == null) {
			return;
		}
		OrdStat ordStat = loanOrd.getOrdStat();
		switch (ordStat) {
		case APPLY:
		case ACCEPT:
		case AUDIT:
		case CREDITING:
		case PAID_UP_LOAN:
		case AUDIT_REFUSE:
		case ACCEPT_OVERDUE:
		case ACCEPT_REFUSE:
		case APPLY_OVERDUE:
		case CANCEL:
			return;
		default:
			break;
		}

		switch (ordStat) {
		case REFUNDING:
		case REFUND:
		case DELIN_QUENCY:
			break;
		default:
			return;
		}

		Long loanOrdId = loanOrd.getIloanOrd();
		List<LoanOrdPlan> list = null;
		try {
			list = this.loanOrdPlanService.queryPlanById(loanOrdId);
		} catch (Exception e) {
			throw new RuntimeException(
					"定时器查询订单计划订单是否逾期出错!  [订单号]:" + loanOrdId, e);
		}

		if (CollectionUtils.isEmpty(list)) {
			return;
		}

		for (LoanOrdPlan plan : list) {
			if (DateUtils.isBoforeToday(plan.getRefundDate())) {
				switch (plan.getStat()) {
				case BALANCE:
					plan.setStat(PlanStat.DELIN_QUENCY);
					logger.info("订单号：" + loanOrd.getIloanOrd() + " 还款期号："
							+ plan.getPeriod() + "已逾期");
					break;
				case FREEZING:
					break;
				case BALANCE_FREEZE:
					break;
				case DELIN_QUENCY:
					break;
				}
			}

			this.loanOrdPlanService.update(plan);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void trackOrdAndPlanStat(LoanOrd loanOrd) {
		trackPlanStat(loanOrd);
		trackOrdStat(loanOrd);
	}

	private void trackOrdStat(LoanOrd loanOrd) {
		if (loanOrd == null) {
			return;
		}

		OrdStat ordStat = loanOrd.getOrdStat();

		switch (ordStat) {
		case APPLY:
		case ACCEPT:
		case AUDIT:
		case CREDITING:
		case PAID_UP_LOAN:
		case AUDIT_REFUSE:
		case ACCEPT_OVERDUE:
		case ACCEPT_REFUSE:
		case APPLY_OVERDUE:
		case CANCEL:
			return;
		default:
			break;
		}

		switch (ordStat) {
		case REFUNDING:
		case REFUND:
		case DELIN_QUENCY:
			break;
		default:
			return;
		}

		Long loanOrdId = loanOrd.getIloanOrd();
		try {
			List<LoanOrdPlan> plans = this.loanOrdPlanService
					.queryPlanById(loanOrdId);
			if (CollectionUtils.isEmpty(plans)) {
				return;
			}

			// 计划状态为逾期，更新订单状态为逾期
			for (LoanOrdPlan loanOrdPlan : plans) {
				if (loanOrdPlan.getStat() == PlanStat.DELIN_QUENCY) {
					logger.info("[订单编号]:" + loanOrd.getLoanOrdId() + "[原状态]:"
							+ loanOrd.getOrdStat().getDesc());
					loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
					logger.info("[订单编号]:" + loanOrd.getLoanOrdId() + "[更新状态]:"
							+ loanOrd.getOrdStat().getDesc());
					this.loanOrdService.updateLoanOrd(loanOrd);
					return;
				}
			}

			// 如果计划状态没有逾期
//			for (LoanOrdPlan loanOrdPlan : plans) {

//				if (loanOrdPlan.getStat() == PlanStat.DELIN_QUENCY) {
//					continue;
//				}
//				if (loanOrdPlan.getStat() == PlanStat.FREEZING) {
//					continue;
//				}
//				if (loanOrdPlan.getStat() == PlanStat.BALANCE_FREEZE) {
//					continue;
//				}
//				if (loanOrdPlan.getStat() == PlanStat.THAW_APPLY) {
//					continue;
//				}
//				
//				if(loanOrd.getOrdStat()==OrdStat.REFUNDING){
//					int count =0;
//					List<LoanOrdPlan> list = this.loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
//					if(CollectionUtils.isEmpty(list)){
//						return;
//					}
//					for (LoanOrdPlan p : list) {
//						if(p.getStat()==PlanStat.PAID_UP_LOAN){
//							count++;
//						}
//					}
//					if(list.size()==count){
//						return;
//					}
//				}
//				FundFlow ff = fundFlowService.queryByIloanOrdPlan(loanOrdPlan
//						.getIlanOrdPlan());
//				if (ff == null) {
//					logger.info("[订单编号]:" + loanOrd.getLoanOrdId() + "[计划编号]:"
//							+ loanOrdPlan.getIlanOrdPlan() + "[原订单状态]:"
//							+ loanOrd.getOrdStat().getDesc() + "获取还款fundflow错误");
//					continue;
//				}
//				logger.info("[订单编号]:" + loanOrd.getLoanOrdId() + "[原状态]:"
//						+ loanOrd.getOrdStat().getDesc());
//				if (ff.getStatus() == FundFlowStat.SUCCESS) {
//					loanOrd.setOrdStat(OrdStat.REFUND);
//				} else if (ff.getStatus() == FundFlowStat.AUDIT) {
//					loanOrd.setOrdStat(OrdStat.REFUNDING);
//				} else {
//					continue;
//				}
//				logger.info("[订单编号]:" + loanOrd.getLoanOrdId() + "[更新状态]:"
//						+ loanOrd.getOrdStat().getDesc());
//				this.loanOrdService.updateLoanOrd(loanOrd);
			//}
		} catch (Exception e) {
			throw new RuntimeException("更新订单状态出错!如果计划有逾期则订单状态也显示逾期. [订单编号]:"
					+ loanOrd.getLoanOrdId(), e);
		}
	}

	@Override
	public void trackLoanOrdExpire(LoanOrd loanOrd) throws Exception {
		logger.info("检查订单是否过期, 订单编号:[" + loanOrd.getLoanOrdId() + "]"
				+ "[当前状态为]:" + loanOrd.getOrdStat().getDesc());
		if (this.loanOrdService.isExpire(loanOrd)) {
			this.loanOrdService.expireOrd(loanOrd);
		}
	}

}
