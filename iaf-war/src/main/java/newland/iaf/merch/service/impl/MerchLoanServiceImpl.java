package newland.iaf.merch.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.StringSplitParser;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.PdtOperLog;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 商户贷款实现
 * 
 * @author new
 * 
 */
@Service("merchLoanService")
@Transactional(rollbackFor = Throwable.class)
public class MerchLoanServiceImpl implements MerchLoanService {
	@Resource(name = "loanOrdService")
	LoanOrdService loanOrdService;
	@Resource(name = "operLogService")
	OperLogService operLogService;
	@Resource (name = "debitBidService")
	DebitBidService debitBidService;
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	@Resource (name = "com.newland.iaf.instService")
	private InstService instService;
	@Resource (name = "hicardPaymentService")
	private HicardPaymentService hicardPaymentService;
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;
	
	@Resource(name = "instLoanService")
	private InstLoanService instLoanService;

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd applyLoanPtd(LoanPdt pdt, BigDecimal quota, Integer term,
			MerchSession session, String purpose, String memo, String ipaddr,String termType) throws Exception {
		if (quota.intValue()/10000 > pdt.getMaxQuota().intValue()) 
			throw new Exception("超出最大可贷款额度");
		if (quota.intValue()/10000 < pdt.getMinQuota().intValue())
			throw new Exception("低于最小可贷款额度");
//		if (term > pdt.getMaxTerm())
//			throw new Exception("超出最大可贷期限");
//		if (term < pdt.getMinTerm())
//			throw new Exception("低于最小可贷期限");
		LoanOrd ord = new LoanOrd();
		ord.setCurtPayment(BigDecimal.ZERO);
		ord.setGenTime(new Date());
		ord.setIinst(pdt.getIinst());
		ord.setImerch(session.getMerch().getImerch());
		ord.setOrdDate(new Date());
		ord.setOrdStat(OrdStat.APPLY);
		ord.setQuota(quota);
		ord.setPreQuota(quota);
		ord.setRate(pdt.getRate());
		ord.setMerchName(session.getMerch().getMerchName());
		ord.setMerchType(session.getMerch().getMerchType());
		ord.setIloanPdt(pdt.getIloanPdtHis());//这个要存放产品历史表的对象ID
		ord.setLoanPdtId(pdt.getLoadPdtId());
		ord.setRecivePeriod(0);
		ord.setRemainPayment(quota);
		ord.setRemainPeriod(0);
		ord.setRepayment(BigDecimal.ZERO);
		ord.setTerm(term);
		if(StringUtils.isNotBlank(termType)){
			ord.setTermType(TermType.valueOf(termType));
	}
		ord.setPdtType(PdtType.INST_PROD);
		ord.setUpdTime(new Date());
		ord.setPdtName(pdt.getPdtName());
		ord.setPurpose(purpose);
		ord.setInstName(this.getInstName(pdt.getIinst()));
		ord.setMerchId(session.getMerch().getMerchNo());
		ord.setOrganId(this.getInstId(pdt.getIinst()));
		ord.setRecivePeriod(0);
		ord.setRemainPayment(quota);
		ord.setRemainPeriod(term);
		if(pdt.getRateType()!=null){
			ord.setRateType(pdt.getRateType());
		}
//		Date expiryDate = DateUtils.roundDate(new Date(), Calendar.DATE);
//		expiryDate = DateUtils.rollDate(expiryDate, Calendar.DATE, LoanOrdServiceImpl.EXPIRE_DATE);
		Date now = new Date();
		Map<SysParamName, SysParam> map = this.sysParamService.findSysParamMapByType(SysParamType.expireDate);
		try{
			String sysParamValue = map.get(SysParamName.EXPIRE_DATE).getValue();
			Date expireDate = DateUtils.addDays(now, Integer.parseInt(sysParamValue));
			ord.setExpiryDate(DateUtils.roundDate(expireDate, Calendar.DATE));
		}catch (Exception e) {
			throw new Exception("添加产品获取过期时间出错!："+e.getMessage());
		}
		this.loanOrdService.applyOrdBy(ord);
		this.operLogService.merchOrdLog(session, ord, ipaddr, memo, OperType.APPLY, OperStat.SUCCESS);
		this.loanPdtService.updateLoanPdtReqTotal(pdt);
		return ord;
	}
	
	private String getInstId(Long iinst) throws Exception{
		Inst inst = this.instService.findInstById(iinst);
		if (inst == null) throw new Exception("机构不存在");
		return inst.getInstId();
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<LoanOrd> applyDebitBid(DebitBid debitBid, Set<Long> insts,
			MerchSession session, String memo, String ipaddr) throws Exception {
		List<LoanOrd> ords = new ArrayList<LoanOrd>();
		for (Iterator<Long> it = insts.iterator(); it.hasNext();) {
			Long inst = it.next();
			LoanOrd ord = new LoanOrd();
			ord.setCurtPayment(BigDecimal.ZERO);
			ord.setExpiryDate(debitBid.getExpireDate());
			ord.setGenTime(new Date());
			ord.setIloanPdt(debitBid.getIdebitBid());
			ord.setLoanPdtId(debitBid.getDebitBidId());
			ord.setIinst(inst);
			ord.setImerch(debitBid.getImerch());
			ord.setMerchId(session.getMerch().getMerchNo());
			ord.setOrganId(this.getInstId(inst));
			ord.setOrdDate(new Date());
			ord.setOrdStat(OrdStat.APPLY);
			ord.setMerchName(session.getMerch().getMerchName());
			ord.setMerchType(session.getMerch().getMerchType());
			ord.setPdtType(PdtType.MERCH_PROD);
			ord.setQuota(debitBid.getQuota());
			ord.setPreQuota(debitBid.getQuota());
			ord.setRate(debitBid.getYearRate());
			ord.setRecivePeriod(0);
			ord.setRemainPayment(debitBid.getQuota());
			ord.setRemainPeriod(0);
			if(debitBid.getRateType()!=null){
				ord.setRateType(debitBid.getRateType());
			}
			if(debitBid.getTermType()!=null){
				ord.setTermType(debitBid.getTermType());
			}
			ord.setRepayment(BigDecimal.ZERO);
			ord.setTerm(debitBid.getTerm());
			ord.setUpdTime(new Date());
			ord.setInstName(this.getInstName(inst));
			ord.setPurpose(debitBid.getPurpose());
			ord.setRecivePeriod(0);
			ord.setRemainPayment(debitBid.getQuota());
			ord.setRemainPeriod(debitBid.getTerm());
			ords.add(ord);
			this.loanOrdService.applyOrdBy(ord);
			if(!it.hasNext()){
				this.operLogService.merchOrdLog(session, ord, ipaddr, memo, OperType.APPLY, OperStat.SUCCESS);
			}
		}
		return ords;
	}
	
	private String getInstName(Long iinst){
		Inst inst = this.instService.findInstById(iinst);
		if (inst != null){
			return inst.getInstName();
		}
		return "";
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd cancelLoanOrd(LoanOrd loanOrd, MerchSession session, String memo, String ipaddr)
			throws Exception {
		
		this.loanOrdService.merchCancel(loanOrd);
		this.operLogService.merchOrdLog(session, loanOrd, ipaddr, memo, OperType.CANCEL, OperStat.SUCCESS);
		return loanOrd;
	}
	
	enum NameType {
		ID, NAME;
	}
	
	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd updateLoanOrd(LoanOrd loanOrd, MerchSession session, String memo, String ipaddr)
			throws Exception {
		this.loanOrdService.updateLoanOrd(loanOrd);
		this.operLogService.merchOrdLog(session , loanOrd,	ipaddr, memo , OperType.MODIFY_LOANORD, OperStat.SUCCESS);
		return loanOrd;
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd refund(LoanOrd loanOrd, LoanOrdPlan plan, TransMsg transCfg, 
			MerchSession session, String memo, String ipaddr) throws Exception {
		if(StringUtils.isBlank(transCfg.getOrderNo())){
			logger.error("支付跟踪号为空");
			throw new Exception("参数错误");
		}
		
		//this.loanOrdService.refundOrd(loanOrd, plan);
		//FundFlow fundFlow = this.genFundFlow(loanOrd, plan, transCfg, FundFlowType.REFUND, FundFlowStat.REFUND_INIT);
//		Inst inst = this.instService.findInstById(loanOrd.getIinst());
//		if (inst == null) throw new Exception("机构不存在");
//		parser.append(fundFlow.getReciveAcctNo(), 0);
//		parser.append(fundFlow.getReciveAcctName(), 1);
//		parser.append(fundFlow.getReciveBankCode(), 2);
//		parser.append(fundFlow.getReiciveBankName(), 3);
		
		
		//获取付款人信息
		StringSplitParser parser = new StringSplitParser("#");
		Inst inst = instService.findInstById(loanOrd.getIinst());
		InstBusiInfo instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
//		Merch instBankCode = merchService.queryByMerchNo(inst.getInstId());
//		if(instBankCode==null){
//			throw new Exception("付款人银行行号不存在![机构]");
//		}
		Merch merch = this.merchService.findMerchById(loanOrd.getImerch());
		if (merch == null) throw new Exception("商户不存在!还款附加信息缺失");
		MerchBusiInfo mbi= merchService.getMerchBusiInfoByImerch(session.getMerch().getImerch());
		if(mbi==null){
			throw new Exception("还款信息未设置!");
		}
		
		//收款人信息
		if(StringUtils.isEmpty(instBusiInfo.getRepaymentNo())){
			throw new Exception("还款---请填写付款人银行卡号!");
		}
		parser.append(instBusiInfo.getRepaymentNo(),0);
		//收款人开户名
		if(StringUtils.isEmpty(instBusiInfo.getRepaymentName())){
			throw new Exception("还款---请填写收款人开户名!");
		}
		parser.append(instBusiInfo.getRepaymentName(),1);
		//收款人行号
		if(StringUtils.isEmpty(instBusiInfo.getBankCode())){
			throw new Exception("还款---收款人银行行号为空,无法进行交易!");
		}
		parser.append(instBusiInfo.getBankCode(),2);
		//收款人银行名称
		if(StringUtils.isEmpty(instBusiInfo.getRepaymentBank())){
			throw new Exception("还款---请填写收款人银行名称!");
		}
		parser.append(instBusiInfo.getRepaymentBank(),3);
		
		//付款人信息
		//付款人银行卡号
		parser.append(mbi.getAccountNo(), 4);
		parser.append(mbi.getAccountName(), 5);
		parser.append(merch.getBankCode(), 6);
		parser.append(mbi.getBank(), 7);
		
		parser.append("还款",8);//还款标志
		
		BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
		BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
		BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
		Double r = tradeRate.doubleValue()/100;
		BigDecimal rate = plan.getRepayment().multiply(new BigDecimal(r.toString()));
		rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(rate.compareTo(minRate)==-1){
			rate=minRate;
		}
		if(rate.compareTo(maxRate)==1){
			rate=maxRate;
		}
		
		parser.append(rate.toString(), 9);
		
		transCfg.setAttachment(parser.toString());
		//this.fundFlowService.save(fundFlow);
		return loanOrd;
	}
	
	public FundFlow genFundFlow(LoanOrd loanOrd, LoanOrdPlan plan, TransMsg transCfg, FundFlowType type,
			FundFlowStat fundFlowStat,MerchSession session,String memo,String ipaddr) throws Exception {
		InstBusiInfo busi = this.instService.findInstBusiInfoByiinst(loanOrd.getIinst());
		if (busi == null) throw new Exception("未登记机构业务信息！");
		if(StringUtils.isBlank(busi.getLoanAccountNo())) throw new Exception("机贷款账户未设置！");
		if(StringUtils.isBlank(busi.getLoanName())) throw new Exception("机构贷款账户名称未设置");
		if (StringUtils.isBlank(busi.getBank())) throw new Exception("机构贷款账户银行名称未设置");
		if (StringUtils.isBlank(busi.getBankCode())) throw new Exception("机构贷款账户银行编号未设置");
		FundFlow fundFlow = new FundFlow();
		fundFlow.setGenTime(new Date());
		fundFlow.setIinst(loanOrd.getIinst());
		fundFlow.setIloanOrd(loanOrd.getIloanOrd());
		fundFlow.setImerch(loanOrd.getImerch());
		fundFlow.setLoanOrdId(loanOrd.getLoanOrdId());
		fundFlow.setReciveAcctName(busi.getLoanName());
		fundFlow.setReciveAcctNo(busi.getLoanAccountNo());
		fundFlow.setReiciveBankName(busi.getBank());
		fundFlow.setReciveBankCode(busi.getBankCode());
		if (transCfg != null){
			fundFlow.setMerchId(transCfg.getMerId());//还款 清算比对 商户号
			fundFlow.setOtherSysTraceNo(transCfg.getOrderNo());
			fundFlow.setTransType(GateWayType.getTransType(transCfg.getGateId()));
		}
		fundFlow.setStatus(fundFlowStat);
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setType(type);
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setUpdTime(new Date());
		fundFlow.setCapital(loanOrd.getQuota());
		if (plan != null){
			fundFlow.setAmount(plan.getRepayment());
			fundFlow.setPeriod(plan.getPeriod());
			fundFlow.setIloanOrdPlan(plan.getIloanOrdPlan());
		}
		this.fundFlowService.save(fundFlow);
		this.operLogService.merchOrdLog(session , loanOrd,	ipaddr, memo , OperType.REFUND_APPLY, OperStat.SUCCESS);
		return fundFlow;
	}
	
	public FundFlow othGenFundFlow(LoanOrd loanOrd, LoanOrdPlan plan, TransMsg transCfg, FundFlowType type,
			FundFlowStat fundFlowStat,InstSession session,String memo,String ipaddr) throws Exception {
//		InstBusiInfo busi = this.instService.findInstBusiInfoByiinst(loanOrd.getIinst());
//		if (busi == null) throw new Exception("未登记机构业务信息！");
//		if(StringUtils.isBlank(busi.getLoanAccountNo())) throw new Exception("机贷款账户未设置！");
//		if(StringUtils.isBlank(busi.getLoanName())) throw new Exception("机构贷款账户名称未设置");
//		if (StringUtils.isBlank(busi.getBank())) throw new Exception("机构贷款账户银行名称未设置");
//		if (StringUtils.isBlank(busi.getBankCode())) throw new Exception("机构贷款账户银行编号未设置");
		FundFlow fundFlow = new FundFlow();
		fundFlow.setGenTime(new Date());
		fundFlow.setIinst(loanOrd.getIinst());
		fundFlow.setIloanOrd(loanOrd.getIloanOrd());
		fundFlow.setImerch(loanOrd.getImerch());
		fundFlow.setLoanOrdId(loanOrd.getLoanOrdId());
		fundFlow.setReciveAcctName("");
		fundFlow.setReciveAcctNo("");
		fundFlow.setReiciveBankName("");
		fundFlow.setReciveBankCode("");
		if (transCfg != null){
			fundFlow.setMerchId(transCfg.getMerId());//还款 清算比对 商户号
			fundFlow.setOtherSysTraceNo(transCfg.getOrderNo());
			fundFlow.setTransType(GateWayType.getTransType(transCfg.getGateId()));
		}
		fundFlow.setStatus(fundFlowStat);
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setType(type);
		fundFlow.setInstName(loanOrd.getInstName());
		fundFlow.setMerchName(loanOrd.getMerchName());
		fundFlow.setUpdTime(new Date());
		fundFlow.setCapital(loanOrd.getQuota());
		if (plan != null){
			fundFlow.setAmount(plan.getRepayment());
			fundFlow.setPeriod(plan.getPeriod());
			fundFlow.setIloanOrdPlan(plan.getIloanOrdPlan());
		}
		this.fundFlowService.save(fundFlow);
		
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.REFUND_APPLY, OperStat.SUCCESS);
		return fundFlow;
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd othRefund(LoanOrd loanOrd, LoanOrdPlan plan, MerchSession session, 
			String memo, String ipaddr) throws Exception {
		
		this.loanOrdService.othRefundOrd(loanOrd, plan,memo);
		FundFlow fundFlow = this.genFundFlow(loanOrd,plan, null, FundFlowType.OTH_REFUND, FundFlowStat.SUCCESS,session,memo,ipaddr);
		this.fundFlowService.save(fundFlow);
		this.operLogService.merchOrdLog(session , loanOrd,	ipaddr, memo , OperType.REFUND, OperStat.SUCCESS);
		return loanOrd;
	}
	
	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public LoanOrd instOthRefund(LoanOrd loanOrd, LoanOrdPlan plan, InstSession session, 
			String memo, String ipaddr) throws Exception {
		
		FundFlow fundFlow = this.othGenFundFlow(loanOrd,plan, null, FundFlowType.OTH_REFUND, FundFlowStat.SUCCESS,session,memo,ipaddr); 
		this.fundFlowService.save(fundFlow);
		
		this.loanOrdService.othRefundOrd(loanOrd, plan,memo);
		
		this.operLogService.instOrdLog(session, loanOrd, ipaddr, memo, OperType.REFUND, OperStat.SUCCESS); 
		return loanOrd;
	}

	@Override
	public LoanOrd queryOrdById(Long iLoanOrd) throws Exception {
		return this.loanOrdService.findLoanOrdById(iLoanOrd);
	}

	@Override
	public List<LoanOrd> queryOrd(Merch merch, OrdStat stat) throws Exception {
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setImerch(merch.getImerch());
		cond.setOrdStat(stat);
		return this.loanOrdService.queryByCondition(cond);
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
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public DebitBid addDebitBid(DebitBid bid, MerchSession session, String memo,
			String ipaddr) throws Exception {
		Date now = new Date();
		bid.setGenTime(now);
		bid.setUpdTime(now);
		Map<SysParamName, SysParam> map = this.sysParamService.findSysParamMapByType(SysParamType.expireDate);
		try{
			String sysParamValue = map.get(SysParamName.EXPIRE_DATE).getValue();
			Date expireDate = DateUtils.addDays(now, Integer.parseInt(sysParamValue));
			bid.setExpireDate(DateUtils.roundDate(expireDate, Calendar.DATE));
		}catch(Exception e){
			throw new Exception("系统参数获取异常："+e.getMessage());
		}
		
		bid.setBidStat(DebitbidStat.NORMAL);
		this.debitBidService.save(bid);
		//日志
		this.operLogService.merchDebitLog(session, bid, ipaddr, memo, OperType.ADD_PDT, OperStat.SUCCESS);
		return bid;
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public DebitBid updateDebitBid(DebitBid bid, MerchSession session, 
			String memo, String ipaddr) throws Exception {
		this.debitBidService.update(bid);
		//日志
		this.operLogService.merchDebitLog(session, bid, ipaddr, memo, OperType.MODIFY_PDT, OperStat.SUCCESS);
		return bid;
	}
	
	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public DebitBid grounding(DebitBid bid, MerchSession session,  String memo,
			String ipaddr) throws Exception {
		bid.setBidStat(DebitbidStat.NORMAL);
		this.debitBidService.update(bid);
		//日志
		this.operLogService.merchDebitLog(session, bid, ipaddr, "产品上架", OperType.UP_PDT, OperStat.SUCCESS);
		return bid;
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public DebitBid undercarriage(DebitBid bid, MerchSession session, 
			String memo, String ipaddr) throws Exception {
		bid.setBidStat(DebitbidStat.NORMAL);
		this.debitBidService.update(bid);
		//日志
		this.operLogService.merchDebitLog(session, bid, ipaddr, "产品下架", OperType.DOWN_PDT, OperStat.SUCCESS);

		return bid;
	}

	@Override
	public LoanOrd batchRefund(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			TransMsg transCfg, MerchSession session, String memo, String ipaddr)
			throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("还款计划列表为空！");
		this.hicardPaymentService.transfer(transCfg);
		for (LoanOrdPlan plan :plans){
			this.refund(loanOrd, plan, transCfg, session, memo, ipaddr);
		}
		transCfg.setTransCount(plans.size());
		return loanOrd;
	}

}
