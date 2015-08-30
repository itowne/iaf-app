package newland.iaf.backstage.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.StringUtils;
import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.CreditExcel;
import newland.iaf.base.model.Freeze;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.RefundExcel;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.ApplyReqService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.file.FileParser;
import newland.iaf.utils.service.SerialNoService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service("backStageLoanService")
@Transactional(readOnly = false)
public class BackStageLoanServiceImpl implements BackStageLoanService {
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	@Resource (name = "operLogService")
	private OperLogService operLogService;
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	@Resource (name = "serialNoService")
	private SerialNoService serialNoService;
	@Resource (name = "com.newland.iaf.merchService")
	private MerchService merchService;
	@Resource (name = "com.newland.iaf.instService")
	private InstService instService;
	@Resource(name = "creditFileParser")
	private FileParser<List<CreditExcel>> creditFileParser;
	@Resource (name = "refundFileParser")
	private FileParser<List<RefundExcel>> refundFileParser;
	@Resource (name = "applyReqService")
	private ApplyReqService applyReqService;
	@Resource (name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	@Resource (name = "onceCreditFileParser")
	private FileParser<List<LoanOrd>> onceCreditFileParser;
	@Resource (name = "applyRequestFileParser")
	private FileParser<List<Freeze>> applyRequestFileParser;
	
	@Resource(name="transMsgService")
	private TransMsgService transMsgService;
	
	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowType type, FundFlowStat stat) throws Exception {
		if (type != FundFlowType.CREDIT && type != FundFlowType.REFUND)
			throw new Exception("不支持平台外对账查询");
		List<FundFlow> list = this.fundFlowService.queryFundFlowBy(type, stat);
		return list;
	}
	
	enum NameType{
		ID,
		NAME;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd auditCredit(FundFlow fundFlow, IafConsoleSession session,
			String memo, String ipaddr) throws Exception {
		if (fundFlow.getStatus() == FundFlowStat.SUCCESS 
				||fundFlow.getStatus() == FundFlowStat.EXPIRY) throw new Exception("该笔流水已处理！");
		LoanOrd loanOrd = this.loanOrdService.queryLoanOrdById(fundFlow.getIloanOrd());
		if (loanOrd == null) throw new Exception("订单不存在");
		this.loanOrdService.auditCredit(loanOrd);
		fundFlow.setCheckMemo(memo);
		this.fundFlowService.auditCredit(fundFlow);
		
		//日志
		this.operLogService.iafOrdLog(session, loanOrd, ipaddr, memo, OperType.AUDIT_CREDIT, OperStat.SUCCESS);
		
		return loanOrd;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd auditRefund(FundFlow fundFlow, IafConsoleSession session,
			String memo, String ipaddr) throws Exception {
		if (fundFlow.getStatus() == FundFlowStat.SUCCESS 
				||fundFlow.getStatus() == FundFlowStat.EXPIRY) throw new Exception("该笔流水已处理！");
		LoanOrd loanOrd = this.loanOrdService.queryLoanOrdById(fundFlow.getIloanOrd());
		if (loanOrd == null) throw new Exception("订单不存在");
		fundFlow.setCheckMemo(memo);
		this.fundFlowService.auditRefund(fundFlow);
		this.loanOrdService.auditRefund(loanOrd);
		
		//日志
		this.operLogService.iafOrdLog(session, loanOrd, ipaddr, memo, OperType.AUDIT_CREDIT, OperStat.SUCCESS);
		
		return loanOrd;
	}

	@Override
	public File genFlowsFile(List<FundFlow> flows, FundFlowType type) throws Exception {
			if (type == FundFlowType.CREDIT){
				List<CreditExcel> ceList = new ArrayList<CreditExcel>();
				if (CollectionUtils.isEmpty(flows) == false){
					for (FundFlow ff : flows) {
						CreditExcel  ce = new CreditExcel();
						ce.setTraceNo(ff.getTraceNo());
						if(StringUtils.isNotBlank(ff.getOtherSysTraceNo())){
							ce.setOtherSysTraceNo(ff.getOtherSysTraceNo());
						}else{
							//ce.setOtherSysTraceNo("平台外放款");
							continue;
						}
						ce.setLoanOrdId(ff.getLoanOrdId());
						Inst inst = instService.findInstById(ff.getIinst());
						if(inst!=null){
							ce.setMerchNo(inst.getInstId());
							ce.setInstName(inst.getInstName());
						}
						Merch merch = merchService.findMerchById(ff.getImerch());
						MerchBusiInfo mbi = merchService.getMerchBusiInfoByImerch(ff.getImerch());
						if(merch!=null){
							ce.setReceiveMerchNo(merch.getMerchNo());
							ce.setReceiveMerchName(merch.getMerchName());
						}
						if(mbi!=null){
							ce.setAccountBankNo(mbi.getBankCode());
							ce.setAccountName(mbi.getAccountName());
							ce.setAccountNo(mbi.getAccountNo());
							ce.setAcountBankName(mbi.getBank());
						}
						ce.setCreditQuota(ff.getAmount());
						BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
						BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
						BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
						Double r = tradeRate.doubleValue()/100;
						BigDecimal rate = ff.getAmount().multiply(new BigDecimal(r.toString()));
						rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
						if(rate.compareTo(minRate)==-1){
							rate=minRate;
						}
						if(rate.compareTo(maxRate)==1){
							rate=maxRate;
						}
						ce.setTradeCharge(rate);
						TransMsg tm = transMsgService.findById(ff.getOtherSysTraceNo());
						if(tm!=null){
							ce.setTradeQuota(tm.getOrderAmount());
							ce.setCreditDate(tm.getTransDate());
						}
						if(ff.getStatus()==FundFlowStat.AUDIT){
							ce.setStat("未对账");
						}else{
							ce.setStat(ff.getStatus().getDesc());
						}
						ceList.add(ce);
					}
				}
				
				return this.creditFileParser.convertToFile(ceList);
			}else if (type == FundFlowType.REFUND){
				
				List<RefundExcel> reList = new ArrayList<RefundExcel>();
				if (CollectionUtils.isEmpty(flows) == false){
					for (FundFlow ff : flows) {
						RefundExcel  re = new RefundExcel();
						re.setTraceNo(ff.getTraceNo());
						if(StringUtils.isNotBlank(ff.getOtherSysTraceNo())){
							re.setOtherSysTraceNo(ff.getOtherSysTraceNo());
						}else{
							//re.setOtherSysTraceNo("平台外还款");
							continue;
						}
						re.setLoanOrdId(ff.getLoanOrdId());
						re.setPeriods(ff.getPeriod().toString());
						Merch merch = merchService.findMerchById(ff.getImerch());
						if(merch!=null){
							re.setMerchNo(merch.getMerchNo());
							re.setMerchName(merch.getMerchName());
						}
						Inst inst = instService.findInstById(ff.getIinst());
						if(inst!=null){
							re.setReceiveMerchNo(inst.getInstId());
							re.setReceiveMerchName(inst.getInstName());
						}
						InstBusiInfo ibi = instService.findInstBusiInfoByiinst(ff.getIinst());
						if(ibi!=null){
							re.setAccountBankNo(ibi.getBankCode());
							re.setAccountName(ibi.getRepaymentName());
							re.setAccountNo(ibi.getRepaymentNo());
							re.setAcountBankName(ibi.getRepaymentBank());
						}
						re.setCreditQuota(ff.getAmount());
						BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
						BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
						BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
						Double r = tradeRate.doubleValue()/100;
						BigDecimal rate = ff.getAmount().multiply(new BigDecimal(r.toString()));
						rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
						if(rate.compareTo(minRate)==-1){
							rate=minRate;
						}
						if(rate.compareTo(maxRate)==1){
							rate=maxRate;
						}
						re.setTradeCharge(rate);
						TransMsg tm = transMsgService.findById(ff.getOtherSysTraceNo());
						if(tm!=null){
							re.setTradeQuota(tm.getOrderAmount());
							re.setRefundDate(tm.getTransDate());
						}
						if(ff.getStatus()==FundFlowStat.AUDIT){
							re.setStat("未对账");
						}else{
							re.setStat(ff.getStatus().getDesc());
						}
						reList.add(re);
					}
				}
				return this.refundFileParser.convertToFile(reList);
			}
		
		throw new Exception("查询无数据");
	}

	@Override
	public List<ApplyRequest> queryApplyRequest(ApplyType type)
			throws Exception {
		return this.applyReqService.queryBy(type);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class, readOnly = false)
	public void processApply(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception {
		if (req.getStat() == ApplyStat.SUCCESS) throw new Exception("该 请求已处理！");
		if (req.getApplyType() == ApplyType.FREEZE){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.auditFreeze(ord, plans);
			
			req.setResult(memo);
			req.setUpdTime(new Date());
			this.applyReqService.process(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.AUDIT_FREEZE, OperStat.SUCCESS);
			
		}else if (req.getApplyType() == ApplyType.THAW){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.auditThaw(ord, plans);
			
			req.setResult(memo);
			req.setUpdTime(new Date());
			this.applyReqService.process(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.AUDIT_THAW, OperStat.SUCCESS);
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class, readOnly = false)
	public void unFreezenPayed(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception {
		if (req.getStat() == ApplyStat.SUCCESS) throw new Exception("该 请求已处理！");
		if (req.getApplyType() == ApplyType.FREEZE){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.auditFreeze(ord, plans);
			req.setResult(memo);
			req.setUpdTime(new Date());
			this.applyReqService.unFreezenPayed(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.AUDIT_FREEZE, OperStat.SUCCESS);
		}else if (req.getApplyType() == ApplyType.THAW){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.auditThaw(ord, plans);
			req.setResult(memo);
			req.setUpdTime(new Date());
			this.applyReqService.unFreezenPayed(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.AUDIT_THAW, OperStat.SUCCESS);
		}
		
	}
	
	private List<LoanOrdPlan> renderToPlan(Long iloanOrd, String periods){
		String[] temp = StringUtils.split(periods, ",");
		List<LoanOrdPlan> plans = new ArrayList<LoanOrdPlan>();
		if (temp != null && temp.length > 0){
			for (String str: temp){
				if (StringUtils.isNotBlank(str)){
					Integer period = Integer.valueOf(str);
					LoanOrdPlan plan = this.loanOrdPlanService.queryBy(iloanOrd, period);
					if (plan != null){
						plans.add(plan);
					}
				}
			}
		}
		return plans;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void refuseApply(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception {
		if (req.getApplyType() == ApplyType.FREEZE){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.refuseFreeze(ord, plans);
			req.setResult(memo);
			this.applyReqService.refuse(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.REFUSE_FREEZE, OperStat.FAIL);
		}else if (req.getApplyType() == ApplyType.THAW){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.refuseThaw(ord, plans);
			req.setResult(memo);
			this.applyReqService.refuse(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.REFUSE_THAW, OperStat.FAIL);
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void unFreezenUnPay(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception {
		if (req.getApplyType() == ApplyType.FREEZE){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.refuseFreeze(ord, plans);
			req.setResult(memo);
			this.applyReqService.unFreezenUnPayed(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.REFUSE_FREEZE, OperStat.FAIL);

		}else if (req.getApplyType() == ApplyType.THAW){
			List<LoanOrdPlan> plans = this.renderToPlan(req.getIloanOrd(), req.getPeriods());
			if (CollectionUtils.isEmpty(plans)) throw new Exception ("未查询到计划列表！");
			LoanOrd ord = this.loanOrdService.findLoanOrdById(req.getIloanOrd());
			if (ord == null) throw new Exception("订单不存在！");
			this.loanOrdService.refuseThaw(ord, plans);
			req.setResult(memo);
			this.applyReqService.unFreezenUnPayed(req);
			
			//日志
			this.operLogService.iafOrdLog(session, ord, ipaddr, memo, OperType.REFUSE_THAW, OperStat.FAIL);

		}
		
	}
	
	@Override
	public LoanOrd queryLoanById(Long iloanOrd) {
		return this.loanOrdService.queryLoanOrdById(iloanOrd);
	}

	@Override
	public File genOnceCreditFile(List<LoanOrd> list) throws Exception {
		if (CollectionUtils.isEmpty(list) == false){
			return this.onceCreditFileParser.convertToFile(list);
		}
		throw new Exception("查询无数据");
	}

	@Override
	public List<ApplyRequest> queryApplyRequest(ApplyRequestCondition cond) {
		return this.applyReqService.queryBy(cond);
	}

	@Override
	public LoanOrdPlan queryPlan(Long iloanOrd, Integer i) {
		LoanOrdPlan plan = new LoanOrdPlan();
		plan.setIloanOrd(iloanOrd);
		plan.setPeriod(i);
		List<LoanOrdPlan> list = this.loanOrdPlanService.queryBy(plan);
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@Override
	public File genApplyFile(List<Freeze> requestList) {
		return this.applyRequestFileParser.convertToFile(requestList);
	}

	@Override
	public List<LoanOrd> queryLoanOrds(LoanOrdCondition cond, Page page) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		return this.loanOrdService.queryOrdByCon(cond, page);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void shield(LoanOrd loanOrd, IafConsoleSession userSession,
			String ipaddr) throws Exception {
		loanOrd.setShield(new Integer(1));
		this.loanOrdService.updateLoanOrd(loanOrd);
	}
	
	/**
	 * 取消屏蔽
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void reShield(LoanOrd loanOrd, IafConsoleSession userSession,
			String ipaddr) throws Exception {
		loanOrd.setShield(new Integer(0));
		this.loanOrdService.updateLoanOrd(loanOrd);
	}

	@Override
	public List<LoanOrdPlan> queryPlan(Long iloanOrd) throws Exception {
		return this.loanOrdPlanService.queryPlanById(iloanOrd);
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowType refund, Long iloanOrd) {
		FundFlowCondition cond = new FundFlowCondition();
		cond.setType(refund);
		cond.setIloanOrd(iloanOrd);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("genTime"));
		cond.setOrders(orderList);
		List<FundFlow> list = this.fundFlowService.queryFundFlowBy(cond);
		return list;
	}

	@Override
	public List<FundFlow> queryFundFlowBy(FundFlowCondition cond, Page page) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("ifundFlow"));
		cond.setOrders(orderList);
		return this.fundFlowService.queryFundFlowBy(cond, page);
	}

	@Override
	public List<FundFlow> queryFundFlowBy(Set<FundFlowType> types, Long iloanOrd) {
		FundFlowCondition cond = new FundFlowCondition();
		cond.setTypes(types);
		cond.setIloanOrd(iloanOrd);
		Set<FundFlowStat> status = new HashSet<FundFlowStat>();
		status.add(FundFlowStat.AUDIT);
		status.add(FundFlowStat.SUCCESS);
		cond.setStatus(status);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("genTime"));
		cond.setOrders(orderList);
		cond.setOrders(orderList);
		List<FundFlow> list = this.fundFlowService.queryFundFlowBy(cond);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd cancelCredit(FundFlow fundFlow, IafConsoleSession session,
			String memo, String ipaddr) throws Exception {
		if (fundFlow.getStatus() == FundFlowStat.SUCCESS 
				|| fundFlow.getStatus() == FundFlowStat.EXPIRY) throw new Exception("该笔流水已处理！");
		LoanOrd loanOrd = this.loanOrdService.queryLoanOrdById(fundFlow.getIloanOrd());
		if (loanOrd == null) throw new Exception("订单不存在");
		this.loanOrdService.cancelCredit(loanOrd);
		this.fundFlowService.cancel(fundFlow);
		
		//日志
		this.operLogService.iafOrdLog(session, loanOrd, ipaddr, memo, OperType.CANCEL_CREDIT, OperStat.FAIL);

		return loanOrd;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd cancelRefund(FundFlow fundFlow, IafConsoleSession session,
			String memo, String ipaddr) throws Exception {
		if (fundFlow.getStatus() == FundFlowStat.SUCCESS 
				|| fundFlow.getStatus() == FundFlowStat.EXPIRY) throw new Exception("该笔流水已处理！");
		LoanOrd loanOrd = this.loanOrdService.queryLoanOrdById(fundFlow.getIloanOrd());
		if (loanOrd == null) throw new Exception("订单不存在");
		LoanOrdPlan plan = this.loanOrdPlanService.queryByPlanId(fundFlow.getIloanOrdPlan());
		if (plan == null) throw new Exception("计划不存在！");
		this.fundFlowService.cancel(fundFlow);
		this.loanOrdService.cancelRefund(loanOrd, plan);
		
		//日志
		this.operLogService.iafOrdLog(session, loanOrd, ipaddr, memo, OperType.CANCEL_REFUND, OperStat.FAIL);

		return loanOrd;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void expiryTrans(TransMsg msg) throws Exception{
		FundFlowCondition cond = new FundFlowCondition();
		cond.setOtherSysNo(msg.getOrderNo());
		List<FundFlow> list = this.fundFlowService.queryFundFlowBy(cond);
		if (CollectionUtils.isEmpty(list) == false){
			if (msg.getType() == FundFlowType.CREDIT){
				for (FundFlow ff: list){
					this.fundFlowService.update(ff, FundFlowStat.EXPIRY);
					LoanOrd ord = this.loanOrdService.queryLoanOrdById(ff.getIloanOrd());
					if (ord == null) throw new Exception("订单不存在！资金流水号：[" + ff.getTraceNo() + "]");
					this.loanOrdService.cancelCredit(ord);
					this.operLogService.deleteByFundFlowTraceNo(ff.getTraceNo());
				}
			}else if (msg.getType() == FundFlowType.REFUND){
				for (FundFlow ff: list){
					this.fundFlowService.update(ff, FundFlowStat.EXPIRY);
					LoanOrd ord = this.loanOrdService.queryLoanOrdById(ff.getIloanOrd());
					if (ord == null) throw new Exception("订单不存在！资金流水号：[" + ff.getTraceNo() + "]");
					LoanOrdPlan plan = this.loanOrdPlanService.queryByPlanId(ff.getIloanOrdPlan());
					this.loanOrdService.cancelRefund(ord, plan);
					this.operLogService.deleteByFundFlowTraceNo(ff.getTraceNo());
				}
			}
		}
	}

	@Override
	public List<ApplyRequest> queryApplyRequest(ApplyRequestCondition cond,
			Page page) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("updTime"));
		cond.setOrders(orderList);
		return this.applyReqService.queryByPage(cond, page);
	}

}
