package newland.iaf.base.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.TransStat;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.SettleService;
import newland.iaf.base.trans.model.HcTransLog;
import newland.iaf.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("settleService")
public class SettleServiceImpl implements SettleService {

	@Resource(name = "transMsgDao")
	private TransMsgDao transMsgDao;

	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;

	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;

	@Resource(name = "operLogService")
	private OperLogService operLogService;

	@Resource(name = "hicardPaymentService")
	private HicardPaymentService paymentService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public List<String> queryOrdersBy(Date date) {
		return this.transMsgDao.queryBeforeDate(date, TransStat.SUCCESS);
	}

	@Override
	@Transactional
	public void settle(HcTransLog hcTransLog) throws Exception {
		// TransMsg transMsg = this.transMsgDao.findByOthSysNo(orderId, amount);
		String orderNo = hcTransLog.getOrderNo().substring(4, 20);
		List<TransMsg> transMsgList = transMsgDao.queryByOrderNo(orderNo);// 交易返回汇卡订单号是16位,ftp文件给出的汇卡订单号是20位,

		if (CollectionUtils.isEmpty(transMsgList)) {
			logger.debug("[transLog] 交易订单号：[" + hcTransLog.getOrderNo()
					+ "],未查询到相关交易信息！");
		} else {

			for (TransMsg transMsg : transMsgList) {// 汇卡提供清算文件orderId不唯一.
				FundFlowCondition cond = new FundFlowCondition();
				cond.setOtherSysNo(transMsg.getOrderNo());

				List<FundFlow> list = this.fundFlowService
						.queryFundFlowBy(cond);
				if (CollectionUtils.isEmpty(list)) {
					logger.debug("未找到对应资金记录 [fundflow] , [transLog] 订单号：["
							+ hcTransLog.getOrderNo() + "], 汇卡清算号：["
							+ hcTransLog.getOrderNo() + "]");
					continue;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (FundFlow fundFlow : list) {
					if (fundFlow.getType() == FundFlowType.CREDIT) {// 放款
						if (fundFlow.getStatus() == FundFlowStat.SUCCESS
								|| fundFlow.getStatus() == FundFlowStat.EXPIRY)
							continue;
						LoanOrd loanOrd = this.loanOrdService
								.queryLoanOrdById(fundFlow.getIloanOrd());
						if (loanOrd == null) {
							logger.debug("贷款放款订单不存在：["
									+ fundFlow.getLoanOrdId() + "]");
							continue;
						}
						// 汇卡提供清算号不唯一 根据交易时间 金额 商户号 清算号判断
						String hcTrans_time = sdf.format(hcTransLog
								.getTransDate());
						String transMsg_time = sdf.format(transMsg
								.getTransDate());
						boolean transTime = hcTrans_time.equals(transMsg_time);
						//BigDecimal hcAmt = hcTransLog.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UNNECESSARY);
						int amt = hcTransLog.getAmt().compareTo(
								transMsg.getOrderAmount());

						if (fundFlow.getInstId()
								.equals(hcTransLog.getMerchNo())
								&& fundFlow.getOtherSysTraceNo().equals(
										transMsg.getOrderNo())
								&& transTime
								&& amt == 0) {
							fundFlowService.auditCredit(fundFlow);

							// OrdOperLog log = this.genOrdOperLog(loanOrd,
							// OperType.AUDIT_CREDIT);
							// log.setOperResult("放款已到账");
							// log.setOperStat(OperStat.SUCCESS);
							// operLogService.save(log);
							loanOrdService.auditCredit(loanOrd);
						}else{
							logger.debug("放款清算失败!汇卡清算号:["+hcTransLog.getOrderNo()+"],汇融易交易订单号：["+transMsg.getOrderNo()+"],汇融易订单号:["+loanOrd.getLoanOrdId()+"]");
							continue;
						}
					} else if (fundFlow.getType() == FundFlowType.REFUND) {// 还款
						if (fundFlow.getStatus() == FundFlowStat.SUCCESS
								|| fundFlow.getStatus() == FundFlowStat.EXPIRY)
							continue;
						LoanOrd loanOrd = this.loanOrdService
								.queryLoanOrdById(fundFlow.getIloanOrd());
						if (loanOrd == null) {
							logger.debug("贷款还款订单不存在：["
									+ fundFlow.getLoanOrdId() + "]");
							continue;
						}

						// 汇卡提供清算号不唯一 根据交易时间 金额 商户号 清算号判断
						String hcTrans_time = sdf.format(hcTransLog
								.getTransDate());
						String transMsg_time = sdf.format(transMsg
								.getTransDate());
						boolean transTime = hcTrans_time.equals(transMsg_time);
						//BigDecimal hcAmt = hcTransLog.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UNNECESSARY);
						int amt = hcTransLog.getAmt().compareTo(
								transMsg.getOrderAmount());

						if (fundFlow.getMerchId().equals(
								hcTransLog.getMerchNo())
								&& fundFlow.getOtherSysTraceNo().equals(
										transMsg.getOrderNo())
								&& transTime
								&& amt == 0) {
							fundFlowService.auditRefund(fundFlow);

							// OrdOperLog log = this.genOrdOperLog(loanOrd,
							// OperType.AUDIT_CREDIT);
							// log.setOperResult("第" + fundFlow.getPeriod()
							// + "期还款已到账");
							// log.setOperStat(OperStat.SUCCESS);
							// operLogService.save(log);
							loanOrdService.auditRefund(loanOrd);
						}else{
							logger.debug("还款清算失败!汇卡清算号:["+hcTransLog.getOrderNo()+"],汇融易交易订单号：["+transMsg.getOrderNo()+"],汇融易订单号:["+loanOrd.getLoanOrdId()+"]");
							continue;
						}
					}
				}
			}

		}

	}

	@Override
	@Transactional
	public void synchronize(Date date) {
//		Date beginDate = DateUtils.roundDate(date, Calendar.DATE);
//		Date endDate = DateUtils.roundDate(new Date(), Calendar.DATE);
//		endDate = DateUtils.rollDate(endDate, Calendar.DATE, 1);
//		List<TransMsg> list = this.transMsgDao.queryBy(beginDate, endDate,
//				TransStat.UNCONFIRMED);
//		if (CollectionUtils.isEmpty(list) == false) {
//			for (TransMsg trans : list) {
//				try {
//					this.paymentService.synchronize(trans);
//				} catch (Throwable e) {
//					logger.error("同步出错", e);
//				}
//			}
//		}
	}

}
