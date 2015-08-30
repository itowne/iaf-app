package newland.iaf.base.service.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.base.formater.Formatter;
import newland.base.util.HTMLParser;
import newland.base.util.RequestParams;
import newland.iaf.IafApplication;
import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.dao.FundFlowDao;
import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.TransColumn;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.model.dict.HcTransType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.TransStat;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hicard.Hicard;

@Service("hicardPaymentService")
@Transactional
public class HicardPaymentServiceImpl implements HicardPaymentService {

	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;

	@Resource(name = "merchLoanService")
	private MerchLoanService merchLoanService;

	@Resource(name = "transMsgDao")
	private TransMsgDao transMsgDao;
	
	@Resource(name = "fundFlowDao")
	private FundFlowDao fundFlowDao;

	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;
	
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource (name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@Resource (name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;
	
	@Resource (name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource (name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 生成放款支付订单
	 */
	@Override
	public TransMsg genCreditTransMsg(LoanOrd loanOrd, GateWayType gateWay, String baseUrl) throws Exception {
		if (loanOrd.getOrdStat() != OrdStat.AUDIT) throw new Exception("订单状态不正确！");
		TransMsg msg = new TransMsg();
		msg.setOrderNo(this.serialNoService.genTransLogNo());
		
		if(StringUtils.isEmpty(this.payCfg.getTradeRate())){
			throw new Exception("请配置交易费率！");
		}
		if(StringUtils.isEmpty(this.payCfg.getMaxRate())){
			throw new Exception("请配置最小手续费！");
		}
		if(StringUtils.isEmpty(this.payCfg.getMinRate())){
			throw new Exception("请配置最高手续费！");
		}
		
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
		
		msg.setOrderAmount(rate.add(loanOrd.getQuota()).setScale(2, BigDecimal.ROUND_HALF_UP));
		msg.setGateId(gateWay.getValue());
		msg.setIinst(loanOrd.getIinst());
		msg.setGateType(gateWay.getValue());
		if (IafApplication.isSameSettleAcct()){
			//msg.setMerId(this.payCfg.getSystemMerId());
			Inst inst = instService.findInstById(loanOrd.getIinst());
			if (inst == null) throw new Exception("机构不存在！");
			if (StringUtils.isBlank(inst.getInstId())) throw new Exception("商户号不存在！");
			msg.setMerId(inst.getInstId());
			msg.setSettleNo(inst.getInstId());
		}else{
			Inst inst = instService.findInstById(loanOrd.getIinst());
			if (inst == null) throw new Exception("机构不存在！");
			if (StringUtils.isBlank(inst.getInstId())) throw new Exception("商户号不存在！");
			msg.setMerId(inst.getInstId());
			msg.setSettleNo(inst.getInstId());
		}
		//msg.setSettleNo(this.payCfg.getSystemMerId());
		msg.setOrganNo(this.payCfg.getSystemOrganNo());
		msg.setPassword(this.payCfg.getPassword());
		msg.setTransDate(new Date());
		msg.setTransType(HcTransType.TRANS.getValue());
		msg.setTransCount(1);
		msg.setCallBackUrl(baseUrl + this.payCfg.getCallBackUrl());
		msg.setBgRetUrl(msg.getCallBackUrl());
		msg.setPayUrl(this.payCfg.getPaymentUrl());
		msg.setType(FundFlowType.CREDIT);
		msg.setTransStat(TransStat.INITIAL);
		
		if (StringUtils.isBlank(msg.getMerId())) throw new Exception("商户ID配配置");
		if (StringUtils.isBlank(msg.getOrganNo())) throw new Exception("机构ID配配置");
		if (StringUtils.isBlank(msg.getSettleNo())) throw new Exception("清算商户ID配配置");
		if (StringUtils.isBlank(msg.getPassword())) throw new Exception("商户密码ID配配置");
		this.transMsgDao.save(msg);
		return msg;
	}

	/**
	 * 更新支付订单
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TransMsg update(TransMsg trans) {
		this.transMsgDao.update(trans);
		return trans;
	}

	void rollback(TransMsg trans, String resp){
		if (trans != null && StringUtils.isNotBlank(resp) && resp.equals("00") == false){
			try {
				this.backStageLoanService.expiryTrans(trans);
			} catch (Exception e) {
				logger.error("回滚失败", e);
			}
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public TransResult procRet(String encryStr, String signStr, TransMsg trans, String resp){
		String privateKeyPath = this.payCfg.getPrivateKeyRealPath();
		String reqStr = "";
		if (StringUtils.isNotBlank(encryStr) && StringUtils.isNotBlank(signStr)) {
			try{
				reqStr = Hicard.decrypt(privateKeyPath, encryStr, "gbk");
			}catch(Throwable e){
				TransResult res = this.getResult("E0", true);
				if (trans != null){
						trans.setRespCode(res.getCode());
						trans.setErrorMsg(res.getDesc());
						//this.update(trans);
				}
				this.rollback(trans, resp);
				return res;
			}
			logger.info("encryStr: [" + encryStr + "]");
			logger.info("reqStr: [" + reqStr + "]");
		}else{
			logger.debug("加密数据为空！");
			TransResult res = this.getResult("E1", true);
			if (trans != null){
				trans.setRespCode(res.getCode());
				trans.setErrorMsg(res.getDesc());
				//this.update(trans);;
			}
			this.rollback(trans, resp);
			return res;
		}
		boolean sign = false;
		String publicKeyPath = this.payCfg.getPublicKeyRealPath();
		try{
			sign = Hicard.verify(publicKeyPath, reqStr, signStr, "gbk");
		}catch(Throwable e){
			logger.error("验签失败", e);
			TransResult res = this.getResult("E2", true);
			if (trans != null){
				trans.setRespCode(res.getCode());
				trans.setErrorMsg(res.getDesc());
				//this.update(trans);;
			}
			this.rollback(trans, resp);
			return res;
		}
		
		RequestParams params = new RequestParams(reqStr);
		String merId = params.getParam("merId");
		String settleNo = params.getParam("settleNo");
		String orderNo = params.getParam("orderNo");
		String payAmount = params.getParam("payAmount");
		String transTime = params.getParam("transTime");
		//String currCode = params.getParam("currCode");
		resp = params.getParam("resp");
		//String settDate = params.getParam("settDate");
		String reserved01 = params.getParam("reserved01");
		
		if (sign) {
			if (StringUtils.isNotBlank(orderNo) && StringUtils.isNotBlank(resp)){
				if (trans == null){
					trans = this.queryById(orderNo);
				}
				if (trans == null){
					String msg = "该支付交易不存在！\n交易时间：[" + transTime + "]\n" +
							"支付订单号：[" + orderNo 
							+ "]\n商户号：[" + merId + "]\n清算号：[" + settleNo + "]\n交易金额：" 
							+ payAmount + "]\n汇卡订单号：" + reserved01 + "]\n返回代码：" + resp + "]";
					logger.error(msg);
					return new TransResult("E3", "交易不匹配，订单号：[" + orderNo + "] 汇卡订单号：[" + reserved01 + "]", false);
				}
			    if (trans.getTransStat() == TransStat.SUCCESS) return this.getResult("E6", false);
				TransResult res = this.checkResp(trans, resp, reserved01);
				if (res.getCode().equals(TransResult.SUCCESS)){
					trans.setTransStat(TransStat.SUCCESS);
					logger.info("hc_order_no="+reserved01+"  info:merchId="+trans.getMerId()+",instId="+trans.getIinst()+",iMerch="+trans.getImerch()+",transDate="+trans.getTransDate());
					if(payAmount!=null){
						BigDecimal amt=new BigDecimal(payAmount); 
						trans.setOrderAmount(amt);
					}else{
						logger.error("对方没回传交易总金额");
					}
					this.update(trans);
					//放款
					List<FundFlow> fundFlowList=fundFlowDao.listQueryBySysNo(trans.getOrderNo());
					if(!CollectionUtils.isEmpty(fundFlowList)){
					for (FundFlow fundFlow : fundFlowList) {
						if(fundFlow.getType() == FundFlowType.CREDIT){//放款
							//更新fundfolw状态
							fundFlow.setStatus(FundFlowStat.AUDIT);
							fundFlowDao.update(fundFlow);
							if(fundFlow==null){
								logger.error("支付订单号"+trans.getOrderNo()+"---fundFlow 未找到");
							}
							//更新订单信息
							LoanOrd loanOrd = loanOrdService.queryByLoanOrdId(fundFlow.getLoanOrdId());
							loanOrd.setOrdStat(OrdStat.CREDITING);
							try {
								loanOrdService.updateLoanOrd(loanOrd);
							} catch (Exception e) {
								logger.error("支付订单号：" + trans.getOrderNo() +"订单号"+fundFlow.getLoanOrdId()+", 更新状态失败");
							}
						}else if(fundFlow.getType() == FundFlowType.REFUND){//还款
									//更新fundflow状态
							fundFlow.setStatus(FundFlowStat.AUDIT);
									fundFlowDao.update(fundFlow);
									if(fundFlow==null){
										logger.error("支付订单号"+trans.getOrderNo()+"---fundFlow 未找到");
									}
									//更新订单状态
									LoanOrd loanOrd = loanOrdService.queryByLoanOrdId(fundFlow.getLoanOrdId());
									List<LoanOrdPlan> loanOrdPlanList =loanOrdPlanService.queryByLoanOrdId(loanOrd.getLoanOrdId());
									if(loanOrdPlanList!=null){
										for (LoanOrdPlan loanOrdPlan : loanOrdPlanList) {
											boolean bool=fundFlow.getIloanOrdPlan().toString().equals(loanOrdPlan.getIlanOrdPlan().toString());
											if(bool){
												try {
													loanOrdService.refundOrd(loanOrd, loanOrdPlan);
												} catch (Exception e) {
													logger.error("还款订单号"+trans.getOrderNo()+"--- 还款计划状态变更失败.."+"loanOrd订单号："+loanOrd.getLoanOrdId()+"loanOrdPlanId:"+loanOrdPlan.getIlanOrdPlan());
												}
											}
										}
									}else{
										logger.error("还款订单号"+trans.getOrderNo()+"--- 还款计划状态变更失败.."+"loanOrd订单号："+loanOrd.getLoanOrdId());
									}
									
								}
							}
					}
					return res;
				}else{
					//if (res.isRollback()){
						try {
							if (IafApplication.isRollback()){
								//this.backStageLoanService.expiryTrans(trans);
							}
							logger.error("支付订单号：" + trans.getOrderNo() + ", 交易失败");
						} catch (Exception e) {
							logger.error("交易失败,订单号：[" + trans.getOrderNo() + "]", e);
						}
					//}
					//this.update(trans);
					logger.error("支付订单号：" + trans.getOrderNo() + ", 交易失败");
					return res;
				}
				
			}else{
				String msg = "返回数据不全！\n加密数据：[" + encryStr + "]\n 原    文：[" + reqStr + "]\n签名数据：[" + signStr + "]";
				logger.error(msg);
				TransResult res = this.getResult("E4", true);
				if (trans != null){
					trans.setRespCode(res.getCode());
					trans.setErrorMsg(res.getDesc());
					//this.update(trans);;
				}
				return res;
			}
		} else {
			logger.error("验签未通过！\n加密数据：[" + encryStr + "]\n 原    文：[" + reqStr + "]\n签名数据：[" + signStr + "]");
			TransResult res = this.getResult("E5", true);
			if (trans != null){
				trans.setRespCode(res.getCode());
				trans.setErrorMsg(res.getDesc());
				//this.update(trans);;
			}
			this.rollback(trans, resp);
			return res;
		}
	}
	
	static Map<String, String> errMsgs = new HashMap<String, String>();
	static {
		errMsgs.put("E0", "报文解密失败！");
		errMsgs.put("E1", "加密数据为空！");
		errMsgs.put("E2", "验签失败");
		errMsgs.put("E3", "交易不匹配");
		errMsgs.put("E4", "返回数据不全");
		errMsgs.put("E5", "验签未通过");
		errMsgs.put("E6", "订单已支付");
		errMsgs.put("S0", "保存成功");
		errMsgs.put("S1", "保存失败");
		errMsgs.put("00", "交易成功");
		errMsgs.put("01", "交易重复");
		errMsgs.put("02", "卡状态不正常");
		errMsgs.put("03", "无效商户");
		errMsgs.put("04", "卡已过有效期");
		errMsgs.put("05", "卡上余额不足");
		errMsgs.put("06", "卡不存在");
		errMsgs.put("07", "商户不存在");
		errMsgs.put("08", "交易无效");
		errMsgs.put("09", "不能在该商户内消费");
		errMsgs.put("10", "商户不允许消费或商圈无效");
		errMsgs.put("11", "订单号相同");
		errMsgs.put("12", "商户无效（商户号或密码错误）");
		errMsgs.put("13", "交易无效");
		errMsgs.put("14", "验证商户签名失败");
		errMsgs.put("15", "金额超限（>=1.00）");
		errMsgs.put("16", "订单号不存在或不属于该商户");
		errMsgs.put("51", "账户内余额不足");
		errMsgs.put("52", "无此支票账户");
		errMsgs.put("53", "无此储蓄卡账户");
		errMsgs.put("54", "过期的卡");
		errMsgs.put("55", "密码错误");
		
	}
	
	@Override
	public String getErrMsg(String resp){
		return errMsgs.get(resp);
	}
	
	private TransResult getResult(String resp, boolean rollback){
		String errMsg = this.getErrMsg(resp);
		if (StringUtils.isBlank(errMsg)){
			errMsg = "未知错误！";
		}
		TransResult res = new TransResult(resp, errMsg, rollback);
		return res;
	}

	
	public static class TransResult {
		
		public static final String SUCCESS = "00";
		String code;
		String desc;
		boolean rollback = false;
		
		public TransResult(String code, String desc, boolean rollback){
			this.code = code;
			this.desc = desc;
			this.rollback = rollback;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public boolean isRollback() {
			return rollback;
		}

		public void setRollback(boolean rollback) {
			this.rollback = rollback;
		}
	}

	private TransResult checkResp(TransMsg transMsg, String resp, String otherSysNo) {
		transMsg.setRespCode(resp);
		if ("00".equals(resp.trim())){
			transMsg.setTransStat(TransStat.SUCCESS);
			transMsg.setOtherSysNo(otherSysNo);
			return this.getResult(resp, false);
		}else if ("S0".equals(resp.trim())){
			transMsg.setTransStat(TransStat.SUCCESS);
			transMsg.setOtherSysNo(otherSysNo);
			return this.getResult(resp, false);
		}else if ("S1".equals(resp.trim())){
			transMsg.setTransStat(TransStat.ABNORMAL);
			transMsg.setOtherSysNo(otherSysNo);
			return this.getResult(resp, true);
		}else{
			transMsg.setTransStat(TransStat.EXPIRY);
			transMsg.setOtherSysNo(otherSysNo);
			return this.getResult(resp, true);
		}
	}

	@Override
	public Map<String, Object> encryptAndSign(TransMsg trans) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		String sourceStr = "";
		Field[] fields = trans.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			int i = 0;
			for (Field field : fields) {
				boolean oldaccp = field.isAccessible();
				field.setAccessible(true);
				TransColumn tc = field.getAnnotation(TransColumn.class);
				String[] temp = getProperty(trans, field, tc);
				if (StringUtils.isNotBlank(temp[0])) {
					if (i == 0){
						sourceStr += temp[0] + "=" + temp[1];
						i++;
					}else{
						sourceStr += "&" + temp[0] + "=" + temp[1];
					}
					//maps.put(temp[0], temp[1]);
				}
				field.setAccessible(oldaccp);
			}
		}
		logger.debug("发送原文件：[" + sourceStr + "]");
		String publicKeyPath = this.payCfg.getPublicKeyRealPath();
		//sourceStr = "merId=000000000000001&settleNo=000000000000005&organNo=0000000001&pwd=123456&orderNo=0407105731519450&transType=0&currCode=CNY&charge=1&orderAmount=1.00&gateType=N&gateId=N&resultMode=&version=v1.0.0&priv1=0&bgRetUrl=&callBackUrl= http://192.168.10.47:8080/struts1/gateway.do?operate=resGateway&userId=01";
		maps.put("msg", Hicard.encrypt(publicKeyPath, sourceStr, "gbk"));
		logger.debug("加密文件: [" + maps.get("msg") + "]");
		String privateKeyPath = this.payCfg.getPrivateKeyRealPath();
		maps.put("signMsg", Hicard.sign(privateKeyPath, sourceStr,"gbk"));
		logger.debug("签名文件: [" + maps.get("signMsg") + "]");
		return maps;
	}

	private String[] getProperty(TransMsg trans, Field field, TransColumn tc)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] ret = new String[2];
		if (tc != null) {
			ret[0] = field.getName();
			if (StringUtils.isNotBlank(tc.name())) {
				ret[0] = tc.name();
			}
			if (tc.formatter().getName().equals(Formatter.class.getName()) == false) {
				Formatter formatter = tc.formatter().newInstance();
				if (StringUtils.isBlank(tc.pattern())) {
					formatter.setPattern(tc.pattern());
				}
				ret[1] = formatter.format(BeanUtils.getProperty(trans,
						field.getName()));
			} else {
				ret[1] = BeanUtils.getProperty(trans, field.getName());
				if (ret[1] == null)
					ret[1] = "";
			}
		}
		return ret;
	}
	
	
	

	@Override
	public void transfer(TransMsg trans){
		trans.setTransStat(TransStat.UNCONFIRMED);
		this.transMsgDao.update(trans);
	}

	@Override
	public TransMsg genCreditTransMsg(List<LoanOrd> ords, GateWayType gateWay,
			String baseUrl) throws Exception {
		if (CollectionUtils.isEmpty(ords)) throw new Exception("订单列表为空！");
		TransMsg msg = new TransMsg();
		BigDecimal quota = BigDecimal.ZERO;
		Long iinst = null;
		for (LoanOrd loanOrd:ords){
			if(loanOrd.getOrdStat() != OrdStat.AUDIT) throw new Exception("订单号：" + loanOrd.getLoanOrdId()+ ", 状态不正确！");
			
			BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
			BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
			BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
			Double r = tradeRate.doubleValue()/100;
			BigDecimal rate = loanOrd.getQuota().multiply(new BigDecimal(r.toString()));
			rate=rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			if(rate.compareTo(minRate)==-1){
				rate=minRate;
			}
			if(rate.compareTo(maxRate)==1){
				rate=maxRate;
			}
				
			quota = quota.add(rate.add(loanOrd.getQuota().setScale(2, BigDecimal.ROUND_HALF_UP)));
			iinst = loanOrd.getIinst();
		}
		msg.setTransCount(ords.size());
		msg.setOrderNo(this.serialNoService.genTransLogNo());
		msg.setOrderAmount(quota);
		msg.setGateId(gateWay.getValue());
		msg.setGateType(gateWay.getValue());
		if (IafApplication.isSameSettleAcct()){
			Inst inst = instService.findInstById(iinst);
			if (inst == null) throw new Exception("机构不存在！");
			if (StringUtils.isBlank(inst.getInstId())) throw new Exception("商户号不存在！");
			msg.setMerId(inst.getInstId());
			msg.setSettleNo(inst.getInstId());
		}else{
			Inst inst = instService.findInstById(iinst);
			if (inst == null) throw new Exception("机构不存在！");
			if (StringUtils.isBlank(inst.getInstId())) throw new Exception("商户号不存在！");
			msg.setMerId(inst.getInstId());
			msg.setSettleNo(inst.getInstId());
		}
		//msg.setSettleNo(this.payCfg.getSystemMerId());
		msg.setOrganNo(this.payCfg.getSystemOrganNo());
		msg.setPassword(this.payCfg.getPassword());
		msg.setPayUrl(this.payCfg.getPaymentUrl());
		msg.setIinst(iinst);		
		msg.setTransDate(new Date());
		msg.setTransType(HcTransType.TRANS.getValue());
		msg.setCallBackUrl(baseUrl + this.payCfg.getCallBackUrl());
		msg.setBgRetUrl(msg.getCallBackUrl());
		msg.setPassword(this.payCfg.getPassword());
		msg.setType(FundFlowType.CREDIT);
		msg.setTransStat(TransStat.INITIAL);
		msg.setTransCount(ords.size());
		
		if (StringUtils.isBlank(msg.getMerId())) throw new Exception("商户ID配配置");
		if (StringUtils.isBlank(msg.getOrganNo())) throw new Exception("机构ID配配置");
		if (StringUtils.isBlank(msg.getSettleNo())) throw new Exception("清算商户ID配配置");
		if (StringUtils.isBlank(msg.getPassword())) throw new Exception("商户密码ID配配置");
		this.transMsgDao.save(msg);
		return msg;
	}

	@Override
	public TransMsg queryById(String orderNo) {
		return this.transMsgDao.findById(orderNo);
	}

	@Override
	public TransMsg genRefundTransMsg(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			GateWayType gateWay, String baseUrl)
			throws Exception {
		TransMsg msg = new TransMsg();
		BigDecimal quota = BigDecimal.ZERO;
		for (LoanOrdPlan plan : plans){
			
			if(plan.getStat().ordinal() > PlanStat.DELIN_QUENCY.ordinal()) throw new Exception("订单号：" + loanOrd.getLoanOrdId()+ ", 第" + plan.getPeriod() + "状态不正确！");
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
			quota = quota.add(rate.add(plan.getRepayment())).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (CollectionUtils.isEmpty(plans)) throw new Exception("计划列表为空！");
		msg.setOrderNo(this.serialNoService.genTransLogNo());
		msg.setOrderAmount(quota.setScale(2,BigDecimal.ROUND_HALF_UP));
		msg.setGateId(gateWay.getValue());
		msg.setGateType(gateWay.getValue());
		msg.setImerch(loanOrd.getImerch());
		if (IafApplication.isSameSettleAcct()){
			//msg.setMerId(this.payCfg.getSystemMerId());
			Merch merch = merchService.findMerchById(loanOrd.getImerch());
			if (merch == null) throw new Exception("商户不存在！");
			if (merch.getMerchType() != MerchType.GOLD_SHOPKEEPER) throw new Exception("非金掌柜商户！");
			if (StringUtils.isBlank(merch.getMerchNo())) throw new Exception("商户号为空！");
			msg.setMerId(merch.getMerchNo());
			msg.setSettleNo(merch.getMerchNo());
		}else{
			Merch merch = merchService.findMerchById(loanOrd.getImerch());
			if (merch == null) throw new Exception("商户不存在！");
			if (merch.getMerchType() != MerchType.GOLD_SHOPKEEPER) throw new Exception("非金掌柜商户！");
			if (StringUtils.isBlank(merch.getMerchNo())) throw new Exception("商户号为空！");
			msg.setMerId(merch.getMerchNo());
			msg.setSettleNo(merch.getMerchNo());
		}
		msg.setCharge("1");
		//msg.setSettleNo(this.payCfg.getSystemMerId());
		msg.setOrganNo(this.payCfg.getSystemOrganNo());
		msg.setPassword(this.payCfg.getPassword());
		msg.setPayUrl(this.payCfg.getPaymentUrl());
		msg.setTransDate(new Date());
		msg.setTransType(HcTransType.TRANS.getValue());
		msg.setTransCount(plans.size());
		msg.setCallBackUrl(baseUrl + this.payCfg.getCallBackUrl());
		msg.setBgRetUrl(msg.getCallBackUrl());
		msg.setType(FundFlowType.REFUND);
		msg.setTransStat(TransStat.INITIAL);
		
		if (StringUtils.isBlank(msg.getMerId())) throw new Exception("商户ID配配置");
		if (StringUtils.isBlank(msg.getOrganNo())) throw new Exception("机构ID配配置");
		//if (StringUtils.isBlank(msg.getSettleNo())) throw new Exception("清算商户ID配配置");
		if (StringUtils.isBlank(msg.getPassword())) throw new Exception("商户密码ID配配置");
		this.transMsgDao.save(msg);
		return msg;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public TransMsg synchronize(TransMsg transMsg) throws Throwable{
		TransMsg trans = new TransMsg();
		BeanUtils.copyProperties(trans, transMsg);
//		trans.setSettleNo(this.payCfg.getSystemMerId());
		trans.setOrganNo(this.payCfg.getSystemOrganNo());
		trans.setSettleNo(transMsg.getSettleNo());
		trans.setPassword(this.payCfg.getPassword());
		trans.setTransType(HcTransType.QUERY.getValue());
		Map<String, Object> maps = this.encryptAndSign(trans);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry: maps.entrySet()){
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(this.payCfg.getPaymentUrl());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
		post.setEntity(entity);
		HttpResponse resp = httpClient.execute(post);
		if (resp.getStatusLine().getStatusCode() == 200){
			InputStream in = resp.getEntity().getContent();
			HTMLParser parser = new HTMLParser(in,"gbk",true);
			Map<String, String> map = parser.getElementByTag("input");
			String encryStr = map.get("encryStr");
			String signStr = map.get("signStr");
			String response = map.get("resp");
			TransResult result = this.procRet(encryStr, signStr, transMsg, response);
			if (result.getCode().equals("00") == true){
				logger.error("交易同步成功！");
				logger.error("交易同步：[交易订单号：" + trans.getOrderNo() + ", resp: " + result.getCode() + ", msg: " + result.getDesc() + "] --- 订单状态变更成功");
			}
		}
		return transMsg;
	}

}
