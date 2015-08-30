package newland.iaf.base.action;

import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.base.service.impl.HicardPaymentServiceImpl.TransResult;
import newland.iaf.inst.action.InstBaseAction;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParentPackage("struts")
@Namespace("/")
@Action(value = "payRet")
@Results({
	@Result(name = "success", type = "dispatcher", location = "/inst/loanord/payment/success.jsp"),
	@Result(name = "error", type = "dispatcher", location = "/inst/loanord/payment/error.jsp")
})
public class PaymentReturnAction extends InstBaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource (name = "hicardPaymentService")
	private HicardPaymentService paymentService;
	
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String msg;
	
	private TransMsg trans;
	
	private FundFlow ff;

	public String execute(){
		String encryStr = this.getRequest().getParameter("encryStr");
		String signStr = this.getRequest().getParameter("signStr");
		String resp = this.getRequest().getParameter("resp");
		trans = (TransMsg)this.getSessionObj("transMsg");
		if(trans!=null){
			FundFlowCondition ffc = new FundFlowCondition();
			ffc.setOtherSysNo(trans.getOrderNo());
			//ffc.setType(FundFlowType.REFUND);
			List<FundFlow> list=fundFlowService.queryFundFlowBy(ffc);
			if(list.size()>0){
				ff=list.get(0);
			}
		}
		if (StringUtils.isBlank(encryStr) && StringUtils.isBlank(signStr)){
			msg = "返回信息为空！";
			return "error";
		}
		TransResult res = this.paymentService.procRet(encryStr, signStr, trans, null);
		logger.info("交易结果：[resp: " + res.getCode() + " msg: " + res.getDesc() + "]");
		if (res.getCode().equals(TransResult.SUCCESS) == false){
			msg = "[返回码：" + res.getCode() + " - 错误信息：" + res.getDesc() + "]";
			return "error";
		}
		return "success";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public TransMsg getTrans() {
		return trans;
	}

	public void setTrans(TransMsg trans) {
		this.trans = trans;
	}

}
