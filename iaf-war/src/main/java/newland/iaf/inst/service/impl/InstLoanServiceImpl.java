package newland.iaf.inst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.StringSplitParser;
import newland.iaf.IafApplication;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.PdtOperLog;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.condition.OrdOperLogCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.YesOrNoType;
import newland.iaf.base.service.ApplyReqService;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.FreezeResult;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.DateUtils;
import newland.iaf.utils.file.FileParser;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("instLoanService")
@Transactional(readOnly = false)
public class InstLoanServiceImpl implements InstLoanService {
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	@Resource(name = "loanOrdPlanFileParser")
	private FileParser<List<LoanOrdPlan>> fileParser;
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	@Resource(name = "operLogService")
	private OperLogService operLogService;
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	@Resource(name = "applyReqService")
	private ApplyReqService applyReqService;
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	@Resource(name="com.newland.iaf.instService")
	private InstService instService;
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;
	@Resource(name = "loanOrdDao")
	private LoanOrdDao loanOrdDao;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	

	/**
	 * 受理
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd accept(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception {
		this.loanOrdService.acceptOrd(loanOrd);
		// 如果是竞投申请则需要更新竞投申请的受理次数
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {
			DebitBid bebitbid = debitBidService.getDebitBidById(loanOrd
					.getIloanPdt());
			debitBidService.updateDebitBidAcceptTotal(bebitbid);
		}
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.ACCEPT, OperStat.SUCCESS);
		return loanOrd;
	}

	/**
	 * 审核
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd audit(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception {

		this.loanOrdService.auditOrd(loanOrd);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.AUDIT, OperStat.SUCCESS);

		return loanOrd;
	}

	/**
	 * 放款
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd credit(LoanOrd loanOrd, TransMsg transCfg,
			InstSession session, String memo, String ipaddr,FundFlow fundFlow) throws Exception {
		if (StringUtils.isEmpty(transCfg.getOrderNo())) {
			logger.error("交易跟踪号为空");
			throw new Exception("参数错误");
		}
		loanOrd.setCreditDate(new Date());
		//this.loanOrdService.creditOrd(loanOrd);
		//FundFlow fundFlow = genFundFlow(loanOrd, transCfg, FundFlowType.CREDIT,
				//FundFlowStat.AUDIT);
		if (transCfg.getAttachment() == null) transCfg.setAttachment("");
		Merch merch = this.merchService.findMerchById(loanOrd.getImerch());
		if (merch == null) throw new Exception("商户不存在!放款附加信息缺失");
		Inst inst = instService.findInstById(loanOrd.getIinst());
//		Merch instBankCode = merchService.queryByMerchNo(inst.getInstId());
//		if(instBankCode==null){
//			throw new Exception("付款人银行行号不存在![机构]");
//		}
		InstBusiInfo instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
		if(instBusiInfo==null)throw new Exception("机构不存在!放款附加信息缺失");
		StringSplitParser parser = new StringSplitParser("#");
		parser.append(fundFlow.getReciveAcctNo(), 0);//收款人银行卡号
		parser.append(fundFlow.getReciveAcctName(), 1);//收款人开户名
		//parser.append(fundFlow.getReciveBankCode(), 2);//行号
		if(StringUtils.isEmpty(merch.getBankCode())){
			throw new Exception("放款---收款人银行行号为空,无法进行交易!");
		}
		parser.append(merch.getBankCode(), 2);//行号
		parser.append(fundFlow.getReiciveBankName(), 3);//收款银行名称
		
		//付款人银行卡号
		if(StringUtils.isEmpty(instBusiInfo.getLoanAccountNo())){
			throw new Exception("放款---请填写付款人银行卡号!");
		}
		parser.append(instBusiInfo.getLoanAccountNo(),4);
		//付款人开户名
		if(StringUtils.isEmpty(instBusiInfo.getLoanName())){
			throw new Exception("放款---请填写付款人开户名!");
		}
		parser.append(instBusiInfo.getLoanName(),5);
		//付款人行号
		if(StringUtils.isEmpty(instBusiInfo.getLoanBankCode())){
			throw new Exception("放款---付款人银行行号为空,无法进行交易!");
		}
		parser.append(instBusiInfo.getLoanBankCode(),6);
		//付款人银行名称
		if(StringUtils.isEmpty(instBusiInfo.getRepaymentBank())){
			throw new Exception("放款---请填写付款人银行名称!");
		}
		parser.append(instBusiInfo.getRepaymentBank(),7);
		
		parser.append("贷款", 8);//放贷标志
		
		BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
		BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
		BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
		Double r = tradeRate.doubleValue()/100;
		BigDecimal rate = loanOrd.getQuota().multiply(new BigDecimal(r.toString()));
		rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(rate.compareTo(minRate)==-1){
			rate=minRate;
		}
		if(rate.compareTo(maxRate)==1){
			rate=maxRate;
		}
		
		parser.append(rate.toString(), 9);
		
		transCfg.setAttachment(transCfg.getAttachment() + parser.toString());
		//this.fundFlowService.save(fundFlow);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.CREDIT_APPLY, OperStat.SUCCESS);
		return loanOrd;
	}

	public FundFlow genFundFlow(LoanOrd loanOrd, TransMsg transCfg,
			FundFlowType type, FundFlowStat fundFlowStat) throws Exception {
		MerchBusiInfo busi = this.merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
		if (busi == null) throw new Exception("未登记商户业务信息！");
		if(StringUtils.isBlank(busi.getAccountNo())) throw new Exception("商户还款账户未设置！");
		if(StringUtils.isBlank(busi.getAccountName())) throw new Exception("商户还款账户名称未设置");
		if (StringUtils.isBlank(busi.getBank())) throw new Exception("商户还款账户银行名称未设置");
		if (StringUtils.isBlank(busi.getBankCode())) throw new Exception("商户还款款账户银行编号未设置");
		FundFlow fundFlow = new FundFlow();
		fundFlow.setReciveAcctName(busi.getAccountName());
		fundFlow.setReciveAcctNo(busi.getAccountNo());
		fundFlow.setReiciveBankName(busi.getBank());
		fundFlow.setReciveBankCode(busi.getBankCode());
		fundFlow.setAmount(loanOrd.getQuota());
		fundFlow.setCapital(loanOrd.getQuota());
		fundFlow.setGenTime(new Date());
		fundFlow.setIinst(loanOrd.getIinst());
		fundFlow.setIloanOrd(loanOrd.getIloanOrd());
		fundFlow.setImerch(loanOrd.getImerch());
		fundFlow.setLoanOrdId(loanOrd.getLoanOrdId());
		if (transCfg != null) {
			fundFlow.setInstId(transCfg.getMerId());//放款清算比对商户号
			fundFlow.setOtherSysTraceNo(transCfg.getOrderNo());
			fundFlow.setTransType(GateWayType.getTransType(transCfg.getGateId()));
		}
		fundFlow.setStatus(fundFlowStat);
		fundFlow.setType(type);
		;
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setUpdTime(new Date());
		this.fundFlowService.save(fundFlow);
		return fundFlow;
	}

	public FundFlow othGenFundFlow(LoanOrd loanOrd, TransMsg transCfg,
			FundFlowType type, FundFlowStat fundFlowStat) throws Exception {
		MerchBusiInfo busi = this.merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
		if (busi == null){
			busi = new MerchBusiInfo();
			logger.debug("平台外放款,未登记商户业务信息！");
		}
		//if(StringUtils.isBlank(busi.getAccountNo())) throw new Exception("商户还款账户未设置！");
		//if(StringUtils.isBlank(busi.getAccountName())) throw new Exception("商户还款账户名称未设置");
		//if (StringUtils.isBlank(busi.getBank())) throw new Exception("商户还款账户银行名称未设置");
		//if (StringUtils.isBlank(busi.getBankCode())) throw new Exception("商户还款款账户银行编号未设置");
		FundFlow fundFlow = new FundFlow();
		fundFlow.setReciveAcctName(busi.getAccountName()==null?"0000":busi.getAccountName());
		fundFlow.setReciveAcctNo(busi.getAccountNo()==null?"0000":busi.getAccountNo());
		fundFlow.setReiciveBankName(busi.getBank()==null?"0000":busi.getBank());
		fundFlow.setReciveBankCode(busi.getBankCode()==null?"0000":busi.getBankCode());
		fundFlow.setAmount(loanOrd.getQuota());
		fundFlow.setCapital(loanOrd.getQuota());
		fundFlow.setGenTime(new Date());
		fundFlow.setIinst(loanOrd.getIinst());
		fundFlow.setIloanOrd(loanOrd.getIloanOrd());
		fundFlow.setImerch(loanOrd.getImerch());
		fundFlow.setLoanOrdId(loanOrd.getLoanOrdId());
		if (transCfg != null) {
			fundFlow.setInstId(transCfg.getMerId());//放款清算比对商户号
			fundFlow.setOtherSysTraceNo(transCfg.getOrderNo());
			fundFlow.setTransType(GateWayType.getTransType(transCfg.getGateId()));
		}
		fundFlow.setStatus(fundFlowStat);
		fundFlow.setType(type);
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setUpdTime(new Date());
		this.fundFlowService.save(fundFlow);
		return fundFlow;
	}
	
	/**
	 * 平台外放款
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd othCredit(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception {
		this.loanOrdService.othCreditOrd(loanOrd,memo);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.CREDIT, OperStat.SUCCESS);
		FundFlow fundFlow = othGenFundFlow(loanOrd, null, FundFlowType.OTH_CREDIT,
				FundFlowStat.SUCCESS);
		this.fundFlowService.save(fundFlow);
		return loanOrd;
	}

	/**
	 * 拒决
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd refuse(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception {

		//日志
		OrdStat stat = this.loanOrdService.refuseOrd(loanOrd);
		OperType operType;
		if (stat == OrdStat.AUDIT_REFUSE) {
			operType = OperType.REFUSE_ACCEPT;
		} else {
			operType = OperType.REFUSE_APPLY;
		}
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, operType, OperStat.FAIL);
		return loanOrd;
	}

	/**
	 * 撤销
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd cancel(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception {
		/**
		 * 撤销前要先清除资金流水与冻结请求 前提：在放款之后与完成之前
		 */
		if (loanOrd.getOrdStat().ordinal() >= OrdStat.CREDITING.ordinal()
				&& loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN
						.ordinal()) {
			cancelFundFlow(loanOrd);
			cancalApplyReq(loanOrd);
		}
		this.loanOrdService.cancelOrd(loanOrd);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.CANCEL, OperStat.FAIL);

		return loanOrd;
	}

	private void cancalApplyReq(LoanOrd loanOrd) {
		ApplyRequestCondition acond = new ApplyRequestCondition();
		acond.setIloanOrd(loanOrd.getIloanOrd());
		acond.setStat(ApplyStat.APPLY);
		acond.setApplyType(ApplyType.FREEZE);
		List<ApplyRequest> alist = this.applyReqService.queryBy(acond);
		if (CollectionUtils.isEmpty(alist) == false) {
			for (ApplyRequest req : alist) {
				this.applyReqService.cancel(req);
			}
		}
	}

	private void cancelFundFlow(LoanOrd loanOrd) {
		FundFlowCondition cond = new FundFlowCondition();
		cond.setIloanOrd(loanOrd.getIloanOrd());
		Set<FundFlowStat> set = new HashSet<FundFlowStat>();
		set.add(FundFlowStat.AUDIT);
		set.add(FundFlowStat.TRANSFER);
		cond.setStatus(set);
		List<FundFlow> list = this.fundFlowService.queryFundFlowBy(cond);
		if (CollectionUtils.isEmpty(list) == false) {
			for (FundFlow ff : list) {
				this.fundFlowService.update(ff, FundFlowStat.CANCEL);
			}
		}
	}

	/**
	 * 冻结
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd freeze(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception {
		FreezeResult res = this.loanOrdService.freezeOrd(loanOrd, plans);
		ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),res.getPeriods());
		if(req!=null){
			req.setGenTime(new Date());
			req.setApplyType(ApplyType.FREEZE);
			req.setStat(ApplyStat.FREEZE_APPLY);
			req.setUpdTime(new Date());
			req.setReason(memo);
			req.setFreezeAcceptMemo("");
			req.setFreezeAcceptTime(null);
			req.setFreezeSuccessMemo("");
			req.setFreezeSuccessTime(null);
			req.setUnFreezeAcceptMemo("");
			req.setUnFreezeAcceptTime(null);
			req.setUnFreezeApplyMemo("");
			req.setUnFreezeApplyTime(null);
			req.setUnFreezeSuccessMemo("");
			req.setUnFreezeSuccessTime(null);
			req.setiFile(null);
			if(loanOrd.getOverdue()!=null){
				req.setAmount(res.getAmount().add(loanOrd.getOverdue()));
			}
			req.setPeriods(res.getPeriods());
			TFile tfile=  tfileService.quueryFreeze(loanOrd.getIloanOrd(), "UNFREEZE", res.getPeriods());
			if(tfile!=null){
				tfileService.deleteByIfile(tfile.getIfile());
			}
			this.applyReqService.update(req);
			if(!CollectionUtils.isEmpty(plans)){
				for (LoanOrdPlan loanOrdPlan : plans) {
					loanOrdPlan.setIsUplod("0");
					loanOrdPlanService.update(loanOrdPlan);
				}
			}
		}else{
			req = new ApplyRequest();
			req.setApplyType(ApplyType.FREEZE);
			req.setIinst(loanOrd.getIinst());
			req.setIloanOrd(loanOrd.getIloanOrd());
			req.setImerch(loanOrd.getImerch());
			req.setLoanOrdId(loanOrd.getLoanOrdId());
			req.setReason(memo);
			req.setPeriods(res.getPeriods());
			if(loanOrd.getOverdue()!=null){
				req.setAmount(res.getAmount().add(loanOrd.getOverdue()));
			}else{
				req.setAmount(res.getAmount());
			}
			req.setStat(ApplyStat.FREEZE_APPLY);
			req.setInstName(loanOrd.getInstName());
			req.setMerchName(loanOrd.getMerchName());
			req.setGenTime(new Date());
			req.setUpdTime(new Date());
			Merch merch =merchService.findMerchById(loanOrd.getImerch());
			req.setMerchNo(merch.getMerchNo());
			Inst inst = instService.findInstById(loanOrd.getIinst());
			req.setInstNo(inst.getInstId());
			//req.setReason("逾期未还款");
			req.setReason(memo);
			this.applyReqService.apply(req);
		}
		
		return loanOrd;
	}

	/**
	 * 解冻
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd thaw(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception {

		FreezeResult res = this.loanOrdService.thaw(loanOrd, plans);
		ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),res.getPeriods());
		req.setApplyType(ApplyType.THAW);
		req.setStat(ApplyStat.UNFREEZE_APPLY);
		req.setUnFreezeApplyTime(new Date());
		req.setUnFreezeApplyMemo(memo);
		
//		req.setIinst(loanOrd.getIinst());
//		req.setIloanOrd(loanOrd.getIloanOrd());
//		req.setImerch(loanOrd.getImerch());
//		req.setLoanOrdId(loanOrd.getLoanOrdId());
//		req.setReason(memo);
//		req.setPeriods(res.getPeriods());
//		req.setAmount(res.getAmount());
//		req.setStat(ApplyStat.APPLY);
//		req.setInstName(loanOrd.getInstName());
//		req.setMerchName(loanOrd.getMerchName());
//		req.setGenTime(new Date());
//		req.setUpdTime(new Date());
		//req.setReason("请求解冻");
		this.applyReqService.update(req);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.THAW_APPLY, OperStat.SUCCESS);

		return loanOrd;
	}

	/**
	 * 上传还款计划
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void uploadLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception {

		this.loanOrdService.uploadPlan(loanOrd, plans);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.UPLOAD_PLAN, OperStat.SUCCESS);

	}
	
	/**
	 * 修改还款计划
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void modifyLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception {

		this.loanOrdService.uploadPlan(loanOrd, plans);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.MODIFY_PLAN, OperStat.SUCCESS);

	}

	@Override
	public LoanOrd queryLoanOrdById(Long iLoanOrd) throws Exception {
		return this.loanOrdService.findLoanOrdById(iLoanOrd);
	}

	@Override
	public LoanOrdPlan queryPlanById(Long iLoanOrdPlan) throws Exception {
		return this.loanOrdService.queryPlanById(iLoanOrdPlan);
	}

	@Override
	public List<LoanOrdPlan> queryPlan(LoanOrd loanOrd) throws Exception {
		return this.loanOrdService.queryPlan(loanOrd.getIloanOrd());
	}


	@Override
	public void uploadLoanOrdPlan(LoanOrd loanOrd, File file,
			InstSession session, String memo, String ipaddr) throws Exception {
		List<LoanOrdPlan> plans = this.fileParser.convertFromFile(file);
		logger.info("解析贷款计划文件，文件名：" + file.getAbsolutePath());
		this.uploadLoanOrdPlan(loanOrd, plans, session, memo, null);
	}

	@Override
	public List<LoanOrdPlan> overRidePlan(List<LoanOrdPlan> oldPlans,
			List<LoanOrdPlan> newPlans) {
		if (CollectionUtils.isEmpty(oldPlans)) {
			if (CollectionUtils.isEmpty(newPlans)) {
				return null;
			} else {
				return newPlans;
			}
		} else {
			if (CollectionUtils.isEmpty(newPlans) == false) {
				for (Iterator<LoanOrdPlan> it = oldPlans.iterator(); it
						.hasNext();) {
					LoanOrdPlan old = it.next();
					if (old.getStat() == PlanStat.PAID_UP_LOAN
							|| old.getStat() == PlanStat.BALANCE_FREEZE
							|| old.getStat() == PlanStat.FREEZING
							|| old.getStat() == PlanStat.THAW_APPLY) {
						continue;
					} else {
						it.remove();
					}
				}
				for (LoanOrdPlan newPlan : newPlans) {
					boolean add = true;
					for (LoanOrdPlan old : oldPlans) {
						if (newPlan.getPeriod().intValue() == old.getPeriod()
								.intValue()) {
							add = false;
							break;
						}
					}
					if (add)
						oldPlans.add(newPlan);
				}
				Collections.sort(oldPlans, new Comparator<LoanOrdPlan>() {

					@Override
					public int compare(LoanOrdPlan o1, LoanOrdPlan o2) {
						return o1.getPeriod().intValue()
								- o2.getPeriod().intValue();
					}

				});
			}
			return oldPlans;
		}
	}

	/**
	 * 增加产品
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanPdt addLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception {

		this.loanPdtService.addLoanPdtService(pdt);
		//日志
		this.operLogService.instPdtLog(session, pdt, ipaddr, "新增贷款产品", OperType.ADD_PDT, OperStat.SUCCESS);

		return pdt;
	}

	/**
	 * 产品上架
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanPdt grounding(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception {

		this.loanPdtService.updateLoanPdt(pdt);
		//日志
		this.operLogService.instPdtLog(session, pdt, ipaddr, "产品上架", OperType.UP_PDT, OperStat.SUCCESS);

		return pdt;
	}

	/**
	 * 产品下架
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanPdt undercarriage(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception {

		this.loanPdtService.updateLoanPdt(pdt);
		//日志
		this.operLogService.instPdtLog(session, pdt, ipaddr, "产品下架", OperType.DOWN_PDT, OperStat.SUCCESS);

		return pdt;
	}

	/**
	 * 更新产品信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanPdt updateLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception {

		this.loanPdtService.updateLoanPdt(pdt);
		
		//日志
		this.operLogService.instPdtLog(session, pdt, ipaddr, "更新产品信息", OperType.MODIFY_PDT, OperStat.SUCCESS);

		return pdt;
	}

	/**
	 * 删除产品
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void deleteLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception {
		pdt.setDeleteFlag(YesOrNoType.YES);
		this.loanPdtService.updateLoanPdt(pdt);
		//日志
		this.operLogService.instPdtLog(session, pdt, ipaddr, "删除产品信息", OperType.DEL_PDT, OperStat.SUCCESS);

	}

	@Override
	public List<LoanPdt> queryByInst(Inst inst, PdtStat stat, Page page)
			throws Exception {
		LoanPdtCondition cond = new LoanPdtCondition();
		cond.setIinst(inst.getIinst());
		cond.setPdtStatus(stat);
		return this.loanPdtService.queryLoanPdtByCon(cond, page);
	}

	@Override
	public LoanPdt queryByPdtId(Long ipdt) throws Exception {
		return this.loanPdtService.getLoanPdtById(ipdt);
	}

	@Override
	public void batchCredit(List<LoanOrd> ords, TransMsg transCfg,
			InstSession session, String memo, String ipaddr ) throws Exception {
		for (LoanOrd ord : ords) {
			this.credit(ord, transCfg, session, memo, ipaddr,null);
		}
	}

	@Override
	public void batchOthCredit(List<LoanOrd> ords, InstSession session,
			String memo, String ipaddr) throws Exception {
		for (LoanOrd ord : ords) {
			this.othCredit(ord, session, memo, ipaddr);
		}
	}

	@Override
	public List<LoanOrd> queryByCond(LoanOrdCondition cond, Page page)
			throws Exception {
		if (cond.getBeginDate() != null) {
			Date beginDate = DateUtils
					.round(cond.getBeginDate(), Calendar.DATE);
			cond.setBeginDate(beginDate);
		}
		if (cond.getEndDate() != null) {
			Date endDate = DateUtils.rollDate(cond.getEndDate(), Calendar.DATE,
					1);
			endDate = DateUtils.roundDate(endDate, Calendar.DATE);
			cond.setEndDate(endDate);
		}
	
		List<LoanOrd> list = this.loanOrdService.queryOrdByCon(cond, page);

		return list;
	}
	
	public List<LoanOrd> queryByCondProAudi(LoanOrdCondition cond, Page page)
			throws Exception {
		if (cond.getBeginDate() != null) {
			Date beginDate = DateUtils
					.round(cond.getBeginDate(), Calendar.DATE);
			cond.setBeginDate(beginDate);
		}
		if (cond.getEndDate() != null) {
			Date endDate = DateUtils.rollDate(cond.getEndDate(), Calendar.DATE,
					1);
			endDate = DateUtils.roundDate(endDate, Calendar.DATE);
			cond.setEndDate(endDate);
		}
		
		List<LoanOrd> list = this.loanOrdService.queryOrdByCon(cond, page);

		return list;
	}

	@Override
	public List<LoanOrd> queryOrd(Inst inst, Set<OrdStat> status)
			throws Exception {
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setIinst(inst.getIinst());
		cond.setStatus(status);
		List<LoanOrd> list = this.loanOrdService.queryByCondition(cond);
		/*
		 * if (CollectionUtils.isEmpty(list) == false){ for (LoanOrd ord: list){
		 * setLoanOrd(ord, inst); } }
		 */
		return list;
	}

	@Override
	public List<OperLog> queryOperLogBy(LoanOrd loanOrd) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setIloanOrd(loanOrd.getIloanOrd());
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("traceNo"));
		cond.setOrders(orderList);
		return this.operLogService.queryByCond(cond);
	}

	@Override
	public List<LoanOrdPlan> convertPlanFrom(File planFile) {
		return this.fileParser.convertFromFile(planFile);
	}

	@Override
	public List<ApplyRequest> queryFreezeReq(Inst inst, LoanOrd loanOrd,
			ApplyStat apply) {
		ApplyRequestCondition cond = new ApplyRequestCondition();
		cond.setIinst(inst.getIinst());
		cond.setStat(ApplyStat.APPLY);
		cond.setIloanOrd(loanOrd.getIloanOrd());
		cond.setApplyType(ApplyType.FREEZE);
		return this.applyReqService.queryBy(cond);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd cancelFreeze(LoanOrd loanOrd, ApplyRequest req,
			InstSession session, String memo, String ipaddr) throws Exception {
		if (req.getStat() != ApplyStat.APPLY)
			throw new Exception("该笔冻结请求已处理！");
		String[] periods = StringUtils.split(req.getPeriods(), ",");
		if (periods == null || periods.length == 0)
			throw new Exception("参数错误");
		ArrayList<LoanOrdPlan> plans = new ArrayList<LoanOrdPlan>();
		for (String p : periods) {
			if (StringUtils.isBlank(p))
				continue;
			Integer index = Integer.valueOf(p.trim());
			LoanOrdPlan plan = this.loanOrdPlanService.queryBy(
					loanOrd.getIloanOrd(), index);
			if (plan != null)
				plans.add(plan);
		}
		this.applyReqService.cancel(req);
		//日志
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.CANCEL_FREEZE, OperStat.SUCCESS);
		return loanOrd;
	}
	
	@Override
	public LoanOrd queryLoanOrdByDebit(DebitBid debitBid, Inst inst) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setIloanPdt(debitBid.getIdebitBid());
		cond.setPdtType(PdtType.MERCH_PROD);
		cond.setIinst(inst.getIinst());
		cond.setLeStat(OrdStat.DELIN_QUENCY);
		List<LoanOrd> loanOrdList = this.loanOrdService.queryByCondition(cond);
		if (CollectionUtils.isEmpty(loanOrdList)) return null;
		return loanOrdList.get(0);
	}
	
	@Override
	public LoanOrd queryDebitByInst(DebitBid debitBid, Inst inst) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setIloanPdt(debitBid.getIdebitBid());
		cond.setPdtType(PdtType.MERCH_PROD);
		cond.setIinst(inst.getIinst());
		List<LoanOrd> loanOrdList = this.loanOrdService.queryByCondition(cond);
		if (CollectionUtils.isEmpty(loanOrdList)) return null;
		return loanOrdList.get(0);
	}

	@Override
	public LoanOrd applyDebit(DebitBid debitBid, InstSession session,
			String memo, String ipaddr) throws Exception {
		if (debitBid.getBidStat() == DebitbidStat.REVOCATION) throw new Exception("产品已撤销，不能受理！");
		if (this.queryLoanOrdByDebit(debitBid, session.getInst()) != null)
			throw new Exception("您已受理该笔竟投产品！");
		LoanOrd loanOrd = null;
		loanOrd = new LoanOrd();
		loanOrd.setPdtType(PdtType.MERCH_PROD);
		loanOrd.setImerch(debitBid.getImerch());
		loanOrd.setMerchName(debitBid.getMerchName());
		loanOrd.setMerchType(debitBid.getMerchType());
		loanOrd.setIloanPdt(debitBid.getIdebitBid());
		loanOrd.setQuota(debitBid.getQuota());
		loanOrd.setTerm(debitBid.getTerm());
		loanOrd.setRate(debitBid.getYearRate());
		loanOrd.setLoanPdtId(debitBid.getDebitBidId());
		loanOrd.setIinst(session.getInst().getIinst());
		loanOrd.setInstName(session.getInst().getInstName());
		loanOrd.setGenTime(new Date());
		loanOrd.setOrdDate(new Date());
		loanOrd.setUpdTime(new Date());
		loanOrd.setMerchId(this.getMerchId(debitBid.getImerch()));
		loanOrd.setOrganId(session.getInst().getInstId());
		loanOrd.setOrdStat(OrdStat.APPLY);
		loanOrd.setExpiryDate(debitBid.getExpireDate());
		loanOrd.setRecivePeriod(0);
		loanOrd.setRemainPayment(debitBid.getQuota());
		loanOrd.setRemainPeriod(debitBid.getTerm());
		loanOrd.setCurtPayment(BigDecimal.ZERO);
		loanOrd = loanOrdService.applyOrdBy(loanOrd);
		this.accept(loanOrd, session, memo, ipaddr);
		return loanOrd;
	}

	private String getMerchId(Long imerch) throws Exception {
		Merch merch = this.merchService.findMerchById(imerch);
		if (merch == null)
			throw new Exception("商户不存在");
		return merch.getMerchNo();
	}

	@Override
	public List<LoanOrd> queryByCon(LoanOrdCondition cond) throws Exception {
		if (cond.getBeginDate() != null) {
			Date beginDate = DateUtils
					.round(cond.getBeginDate(), Calendar.DATE);
			cond.setBeginDate(beginDate);
		}
		if (cond.getEndDate() != null) {
			Date endDate = DateUtils.rollDate(cond.getEndDate(), Calendar.DATE,
					1);
			endDate = DateUtils.roundDate(endDate, Calendar.DATE);
			cond.setEndDate(endDate);
		}
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		List<LoanOrd> list = this.loanOrdService.queryByCondition(cond);
		return list;
	}
}
