package newland.iaf.base.action;

import java.math.BigDecimal;
import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.inst.action.InstBaseAction;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ParentPackage("json-default")
@Namespace("/")
@Action(value = "refundplan")
@Results({
		@Result(name = "success", type = "json") })
public class RefundPlanAction extends InstBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private List<LoanOrdPlan> plans;
	
	private LoanOrd loanOrd;
	
	private static String planList = "planList";
	
	private static String loan_ord = "loan_ord";
	
	private BigDecimal payment;
	
	private BigDecimal interest;
	
	private BigDecimal captial;
	
	private BigDecimal total;
	
	private String title = "hello word";
	
	private int checkFlag = -1;
	
	@Override
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		plans = (List<LoanOrdPlan>) this.getSessionObj(planList);
		loanOrd = (LoanOrd) this.getSessionObj(loan_ord);
		//表示可以保存
		checkFlag = 0;
		if(plans == null){
			//表示上传列表为空
			checkFlag = 2;
//			throw new Exception("上传列表为空！");
		}
		if(loanOrd == null){
			//表示上传列表为空
			checkFlag = 2;
//			throw new Exception("贷款订单不存在！");
		}
		this.setSessionObj(planList,null);
		this.setSessionObj(loan_ord, null);
		payment = BigDecimal.ZERO;
		interest = BigDecimal.ZERO;
		captial = BigDecimal.ZERO;
		if (CollectionUtils.isEmpty(plans) == false){
			for (LoanOrdPlan plan:plans){
				captial = captial.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
				interest = interest.add(plan.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
				payment = payment.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}
		if(loanOrd != null){
			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			if(loanOrd.getQuota().setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(captial) != 0){
				//表示还款总本金与贷款金额金额不符
				checkFlag = 1;
			}
		}
		
        return SUCCESS;
    }
	
	public List<LoanOrdPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<LoanOrdPlan> plans) {
		this.plans = plans;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getCaptial() {
		return captial;
	}

	public void setCaptial(BigDecimal captial) {
		this.captial = captial;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}





}
