package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;
import newland.iaf.base.service.LoanStatisticsSubService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;
import newland.iaf.inst.service.InstNoticeService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

import freemarker.template.SimpleDate;

@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "welcome")
@Results({
		@Result(name = "welcome", type = "dispatcher", location = "/merch/welcome.jsp"),
		@Result(name = "notifyDetailSuccess", type = "dispatcher", location = "/merch/noticeDetail.jsp") })
public class MerchWeclcomeAction extends MerchBaseAction {

	private static final long serialVersionUID = 704043061222803142L;

	// 公告列表
	private List<InstNotice> instNoticeList;

	@Resource(name = "instNoticeService")
	private InstNoticeService instNoticeService;

	@Resource(name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService statisticsMgrService;

	@Resource(name = "loanStatisticsService")
	private LoanStatisticsService statisticsService;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;

	// 公告
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstNotice instNotice;
	// 公告id
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long iinstNotice;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;

	private Integer merchUsedMonth;

	private Integer merchUsedDay;
	
	private List<LoanOrd> loanOrdList;
	
	private BigDecimal amt;
	
	private Integer count;
	
	private Integer debtCount;
	private BigDecimal debtTotal;
	
	private String str;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;
	
	private Long tenCount;
	
	private BigDecimal tenTotal;

	public String execute() {
		flushStatistics();
		this.getInstNotice(0, 10);
		MerchSession sess = this.getMerchSession();
		merch = sess.getMerch();

		if (StringUtils.isNotEmpty(merch.getOriginalTime())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = sdf.format(new Date());
			try {
				Date now = sdf.parse(today);//今天
				Date merch_date = sdf.parse(merch.getOriginalTime()+ " 00:00:00");//汇卡提供的录入时间
				Long between = now.getTime() - merch_date.getTime();//时间差
				if(between<=0){
					merchUsedMonth = 0;
					merchUsedDay = 0;
					return "welcome";
				}
				Long l = between / (24 * 60 * 60 * 1000);//天
				if (l <= 0) {
					merchUsedMonth = 0;
					merchUsedDay = 0;
					return "welcome";
				} else {
					merchUsedMonth=l.intValue()/30;//月
					if (merchUsedMonth == 0) {
						merchUsedDay = (int) (between / (24 * 60 * 60 * 1000));
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<LoanOrdPlan> plans = loanOrdPlanService.queryByImerch(merch.getImerch());
		count = 0;
		debtCount=0;
		debtTotal=BigDecimal.ZERO;
		Set<Long> listPlansdebit = new HashSet<Long>();
		Set<Long> listPlansfreeze = new HashSet<Long>();
		if(!CollectionUtils.isEmpty(plans)){
			for (LoanOrdPlan loanOrdPlan : plans) {
				if(loanOrdPlan.getStat()==PlanStat.BALANCE_FREEZE||loanOrdPlan.getStat()==PlanStat.FREEZING){
					LoanOrd lo = loanordservice.findLoanOrdById(loanOrdPlan.getIloanOrd());
					listPlansfreeze.add(lo.getIloanOrd());
				}else{
					continue;
				}
			}
			count=listPlansfreeze.size();
			for (LoanOrdPlan loanOrdPlan : plans) {
				if(loanOrdPlan.getStat()==PlanStat.DELIN_QUENCY){
					LoanOrd lo = loanordservice.findLoanOrdById(loanOrdPlan.getIloanOrd());
					listPlansdebit.add(lo.getIloanOrd());
					debtTotal=debtTotal.add(loanOrdPlan.getCapital());
				}else{
					continue;
				}
			}
			debtCount=listPlansdebit.size();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = getDateAfter(new Date(),10);
		str = sdf.format(d);
		List<Object> obj = merchService.queryTenDays(new Date(), d, merch.getImerch());
		List<LoanOrdPlan> planslist=merchService.queryIloanOrd(new Date(), d, merch.getImerch());
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
		return "welcome";
	}

	private void flushStatistics() {
		MerchSession sess = this.getMerchSession();
		Merch merch = sess.getMerch();
		LoanStatistics ls = this.statisticsMgrService.query(merch.getImerch(),
				InstType.MERCH);
		try {
			periodRefund(merch.getImerch(),
					InstType.MERCH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ls.setCurPeriodRefundAmount(amt);
		ls.setExpireRefund((long) loanOrdList.size());
		statisticsMgrService.update(ls);
		sess.setStatistics(ls);
		
		sess.setLoanPdtCount(this.statisticsService.getLoanPdtCount());
		sess.setDebitBidCount(this.statisticsService.getDebitBidCount());
	}

	public void getInstNotice(int curPage, int noticeCapacity) {
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(noticeCapacity);// 设置每页显示数
		page.setPageOffset(curPage);// 设置开始页面

		InstNoticeCondition instNoticeCondition = new InstNoticeCondition();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinstNotice"));
		instNoticeCondition.setOrders(orderList);
		instNoticeList = instNoticeService.queryInstNoticeByCon(
				instNoticeCondition, page);
	}

	public String getNoticeDetail() {
		if (iinstNotice == null) {
			super.addActionMessage("公告对象为空，请检查");
			return execute();
		}
		Page page = new Page();
		page.setCount(false);
		page.setCapacity(1);// 设置每页显示数
		page.setPageOffset(0);// 设置开始页面

		InstNoticeCondition instNoticeCondition = new InstNoticeCondition();
		instNoticeCondition.setIinstNotice(iinstNotice);
		instNoticeList = instNoticeService.queryInstNoticeByCon(
				instNoticeCondition, page);
		this.instNotice = instNoticeList.get(0);
		return "notifyDetailSuccess";
	}

	public void periodRefund (Long id,InstType type) throws Exception{
		LoanOrdCondition loanordcondition = new LoanOrdCondition();
		if(type==InstType.INST){
			loanordcondition.setIinst(id);
		}else{
			loanordcondition.setImerch(id);
		}
		Set<OrdStat> ordStatSet = new HashSet<OrdStat>();
		ordStatSet.add(OrdStat.DELIN_QUENCY);
		loanordcondition.setStatus(ordStatSet);
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
	
	public static Date getDateAfter(Date d, int day) {   
        Calendar now = Calendar.getInstance();   
        now.setTime(d);   
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);   
        return now.getTime();   
    }
	
	public List<InstNotice> getInstNoticeList() {
		return instNoticeList;
	}

	public void setInstNoticeList(List<InstNotice> instNoticeList) {
		this.instNoticeList = instNoticeList;
	}

	public InstNotice getInstNotice() {
		return instNotice;
	}

	public void setInstNotice(InstNotice instNotice) {
		this.instNotice = instNotice;
	}

	public Long getIinstNotice() {
		return iinstNotice;
	}

	public void setIinstNotice(Long iinstNotice) {
		this.iinstNotice = iinstNotice;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public Integer getMerchUsedMonth() {
		return merchUsedMonth;
	}

	public void setMerchUsedMonth(Integer merchUsedMonth) {
		this.merchUsedMonth = merchUsedMonth;
	}

	public Integer getMerchUsedDay() {
		return merchUsedDay;
	}

	public void setMerchUsedDay(Integer merchUsedDay) {
		this.merchUsedDay = merchUsedDay;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getDebtCount() {
		return debtCount;
	}

	public void setDebtCount(Integer debtCount) {
		this.debtCount = debtCount;
	}

	public BigDecimal getDebtTotal() {
		return debtTotal;
	}

	public void setDebtTotal(BigDecimal debtTotal) {
		this.debtTotal = debtTotal;
	}

	public String getStr() {
		return str;
	}

	public BigDecimal getTenTotal() {
		return tenTotal;
	}

	public void setTenTotal(BigDecimal tenTotal) {
		this.tenTotal = tenTotal;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Long getTenCount() {
		return tenCount;
	}

	public void setTenCount(Long tenCount) {
		this.tenCount = tenCount;
	}

}
