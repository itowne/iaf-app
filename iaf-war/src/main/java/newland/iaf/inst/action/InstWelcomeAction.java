package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstRole;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

@ParentPackage("struts")
@Namespace("/inst")
@Action(value = "welcome")
@Results({
		@Result(name = "welcome", type = "dispatcher", location = "/inst/welcome.jsp")
		})
public class InstWelcomeAction extends InstBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource (name = "loanStatisticsService")
	private LoanStatisticsService statisticsService;
	
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService statisticsMgrService;
	
	private List<LoanPdt> pdtList;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	private List<LoanOrd> loanOrdList;
	
	private BigDecimal amt;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	
	private String str;
		
	private Long tenCount;
	
	private BigDecimal tenTotal;
	
	private Integer fcount;
	
	private InstRole instRole;
	
	
	public String execute(){
		Inst inst = this.getInst();
		instRole=this.getUserSession().getRoles().get(0);
		pdtList=loanPdtService.queryAll();
		this.flushStatistics();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//十天后的日期
		Date d = getDateAfter(new Date(),10);
		str = sdf.format(d);
		//查找今天到十天后已还款的总金额
		List<Object> obj = loanordservice.queryTenDays(new Date(), d, inst.getIinst());
		//查找今天到十天后的未还款订单
		List<LoanOrdPlan> planslist=loanordservice.queryIloanOrd(new Date(), d, inst.getIinst());
		Set<Long> list = new HashSet<Long>();
		if(!CollectionUtils.isEmpty(obj)){
			for (LoanOrdPlan lop : planslist) {
				list.add(lop.getIloanOrd());
			}
		}
		Object[] res = (Object[]) obj.get(0);
		if(res[0]!=null){
			tenTotal=(BigDecimal) res[0];
		}else{
			tenTotal=BigDecimal.ZERO;
		}
		tenCount=(long) list.size();
		//统计冻结订单总数
		List<LoanOrd> lolist=loanordservice.queryfreezeLoanOrd(inst.getIinst());
		fcount = lolist.size();
		return "welcome";
	}
	
	private void flushStatistics(){
		InstSession sess = this.getUserSession();
		Inst inst = this.getInst();
		//根据iinst和instType查找贷款订单统计
		LoanStatistics ls = this.statisticsMgrService.query(inst.getIinst(), InstType.INST);
		try {
			//根据iinst查找逾期欠款金额
			periodRefund(inst.getIinst(),
					InstType.INST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//当期待还款总额
		ls.setCurPeriodRefundAmount(amt);
		//到期还款数
		ls.setExpireRefund((long) loanOrdList.size());
		statisticsMgrService.update(ls);
		sess.setStatistics(ls);
		sess.setLoanPdtCount(this.statisticsService.getLoanPdtCount());
		sess.setDebitBidCount(this.statisticsService.getDebitBidCount());
	}

	public void periodRefund (Long id,InstType type) throws Exception{
		LoanOrdCondition loanordcondition = new LoanOrdCondition();
		if(type==InstType.INST){
			loanordcondition.setIinst(id);
		}else{
			loanordcondition.setImerch(id);
		}
		Set<OrdStat> ordStatSet = new HashSet<OrdStat>();
		//逾期
		ordStatSet.add(OrdStat.DELIN_QUENCY);
		loanordcondition.setStatus(ordStatSet);
		//查找逾期欠款金额
		loanOrdList = loanordservice.queryByCondition(loanordcondition);
		amt = BigDecimal.ZERO.setScale(2);
		if (!CollectionUtils.isEmpty(loanOrdList)) {
			for (LoanOrd lo : loanOrdList) {
				List<LoanOrdPlan> lop = loanOrdPlanService.queryPlanById(lo
						.getIloanOrd());
				if (lop.size() == 0) {
					continue;
				}
				for (LoanOrdPlan loanOrdPlan : lop) {
					amt=amt.add(loanOrdPlan.getRepayment());
				}
			}
		}
	}
	
	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public static Date getDateAfter(Date d, int day) {   
        Calendar now = Calendar.getInstance();
        now.setTime(d);   
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);   
        return now.getTime();   
    }

	public Long getTenCount() {
		return tenCount;
	}

	public void setTenCount(Long tenCount) {
		this.tenCount = tenCount;
	}

	public BigDecimal getTenTotal() {
		return tenTotal;
	}

	public void setTenTotal(BigDecimal tenTotal) {
		this.tenTotal = tenTotal;
	}

	public Integer getFcount() {
		return fcount;
	}

	public void setFcount(Integer fcount) {
		this.fcount = fcount;
	}

	public InstRole getInstRole() {
		return instRole;
	}

	public void setInstRole(InstRole instRole) {
		this.instRole = instRole;
	}

}
