package newland.iaf.other.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import newland.iaf.backstage.action.BSBaseAction;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;
import newland.iaf.inst.service.InstNoticeService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/")
@Action(value = "homePage")
@Results({
		@Result(name = "homePageSuccess", type = "dispatcher", location = "/loanpdtIndex.jsp"),
		@Result(name = "offerLoanSuccess", type = "dispatcher", location = "/offer-loan.jsp"),
		@Result(name = "askLoanSuccess", type = "dispatcher", location = "/ask-loan.jsp"),
		@Result(name = "notifyDetailSuccess", type = "dispatcher", location = "/notify-detail.jsp"),
		@Result(name = "notifyIndexSuccess", type = "dispatcher", location = "/notifyIndex.jsp"),
		@Result(name = "loanOrdIndexSuccess", type = "dispatcher", location = "/loanOrdIndex.jsp"),
		@Result(name = "instIntroSuccess", type = "dispatcher", location = "/instIntro.jsp"),
		@Result(name = "loanDetailSuccess", type = "dispatcher", location = "/prod-req-detail.jsp"),
		@Result(name="prodDetailMenu",type="dispatcher",location="/prodDetailMenu.jsp"),
		@Result(name = "assessment", type = "dispatcher", location = "/assessment.jsp"),
		@Result(name = "loanPdtIndex", type = "dispatcher", location = "/loanpdtIndex.jsp"),
		@Result(name = "debitIndex", type = "dispatcher", location = "/debitIndex.jsp"),
		@Result(name = "bidDetailSuccess", type = "dispatcher", location = "/bidDetail.jsp")})
public class HomePageAction extends BSBaseAction {
	/**
	 * 汇融易首页
	 */
	private static final long serialVersionUID = 1L;

	// 贷款
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	// 竟投
	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;
	// 贷款订单
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	// 公告
	@Resource(name = "instNoticeService")
	private InstNoticeService instNoticeService;
	// 机构信息操作接口
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	// 借贷产品列表
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanPdt> loanPdtList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	// 借贷实况列表（订单）
	private List<LoanOrd> loanOrdList;
	// 机构列表
	private List<Inst> instList;
	// 商户列表
	private List<Merch> merchList;

	// 公告列表
	private List<InstNotice> instNoticeList;
	// 公告列表
	private InstNotice instNotice;

	private LoanPdt loanPdt;
	private DebitBid debitBid;
	
	private String rateType;
	
	// 竞投申请列表
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<DebitBid> debitBidList;
	// 借贷产品id
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long id;
	// 借贷申请id
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long bidId;
	// 借贷产品id
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long iinstNotice;
	// 金额
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	// 周期
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;
	// 利率
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String rate;
	// 地区
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;

	// 金额
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quotaBid;
	// 周期
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String termBid;
	// 利率
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String rateBid;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String bidFlag;
	
	// 当前页
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String curPage;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String bidCurPage;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String noticeCurPage;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdCurPage;
	
	// 总页数
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int pageAmt;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int bidPageAmt;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int noticePageAmt;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int loanOrdPageAmt;
	
	// 0首页，1我要借款，2我要放贷
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int navFlag;

	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> cityMap;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String noticeTitle;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String mccCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String code;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> mccMap;
	
	private String pdtTerm;
	
	private String debitTerm;
	
	private List<LoanPdt> pdtList;
	
	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@Resource(name="mccService")
	private MccService mccservice;
	
	private String area;
	/**
	 * 首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Begin
	public String execute() throws Exception {
//		quota = null;
//		term = null;
//		rate = null;
//		region = null;
//		quotaBid = null;
//		rateBid = null;
//		termBid = null;
//		provinceCode = "";
//		provMap = provinceService.getProvince();
//		// 主要用于页面菜单选中显示判断
//		navFlag = 0;
//		// 热门借贷产品列表
//		getLoadPdt(0, 6);
//		// 获取最新贷款申请列表
//		getDebitBid(0, 6);
//		// 借贷实况
//		getLoanOrd(0, 8);
//		// 获取公告列表
//		getInstNotice(0, 5);
//		return "homePageSuccess";
		return loanPdtIndex();
	}

	public String loanPdtIndex() throws Exception{
		quota = "";
		term = "";
		rate = "";
		region = "";
		quotaBid = "";
		rateBid = "";
		termBid = "";
		provinceCode = "";
		rateType="";
		pdtTerm="";
		debitTerm="";
		provMap = provinceService.getProvince();
		// 主要用于页面菜单选中显示判断
		navFlag = 0;
		// 热门借贷产品列表
		getLoadPdt(0, 6);
		// 借贷实况
		getLoanOrd(0, 8);
		// 获取公告列表
		getInstNotice(0, 5);
		return "loanPdtIndex";
	}
	
	public String debitIndex() throws Exception{
		quota = "";
		term = "";
		rate = "";
		region = "";
		quotaBid = "";
		rateBid = "";
		termBid = "";
		provinceCode = "";
		rateType="";
		pdtTerm="";
		debitTerm="";
		provMap = provinceService.getProvince();
		// 主要用于页面菜单选中显示判断
		navFlag = 0;
		// 借贷实况
		getDebitBid(0, 6);
		// 获取公告列表
		getInstNotice(0, 5);
		return "debitIndex";
	}
	
	/**
	 * 首页(用于搜索)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Begin
	public String executeSe() throws Exception {
		// 主要用于页面菜单选中显示判断
		navFlag = 0;
		// 热门借贷产品列表
		getLoadPdt(0, 6);
		// 获取最新贷款申请列表
		getDebitBid(0, 6);
		// 借贷实况
		getLoanOrd(0, 8);
		// 获取公告列表
		getInstNotice(0, 5);
		return "homePageSuccess";
	}
	
	public String loanPdtSe() throws Exception{
		provMap = provinceService.getProvince();
		// 主要用于页面菜单选中显示判断
		navFlag = 0;
		// 热门借贷产品列表
		getLoadPdt(0, 6);
		// 获取最新贷款申请列表
		//getDebitBid(0, 6);
		// 借贷实况
		getLoanOrd(0, 8);
		// 获取公告列表
		getInstNotice(0, 5);
		return "loanPdtIndex";
	}
	
	public String debitSe() throws Exception{
		// 主要用于页面菜单选中显示判断
		provMap = provinceService.getProvince();
		navFlag = 0;
		// 热门借贷产品列表
		//getLoadPdt(0, 6);
		// 获取最新贷款申请列表
		getDebitBid(0, 6);
		// 借贷实况
		getLoanOrd(0, 8);
		// 获取公告列表
		getInstNotice(0, 5);		
		return "debitIndex";
	}

	/**
	 * 获取公告列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getInstNotice(int curPage, int noticeCapacity) throws Exception {
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(noticeCapacity);// 设置每页显示数
		page.setPageOffset(curPage);// 设置开始页面

		InstNoticeCondition instNoticeCondition = new InstNoticeCondition();
		
		// 设置查询条件
		if (StringUtils.isNotBlank(noticeTitle)) {
			instNoticeCondition.setTitle(noticeTitle.trim());
		}
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinstNotice"));
		instNoticeCondition.setOrders(orderList);
		instNoticeList = instNoticeService.queryInstNoticeByCon(
				instNoticeCondition, page);
		noticePageAmt = page.getPageAmt();
	}

	/**
	 * 热门借贷产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getLoadPdt(int curPage, int capacity) throws Exception {

		Page page = new Page();
		page.setCount(true);
		page.setCapacity(capacity);// 设置每页显示数
		page.setPageOffset(curPage);// 设置开始页面
		this.curPage = curPage + "";
		LoanPdtCondition loanPdtCondition = new LoanPdtCondition();

		// 设置查询条件
		if (StringUtils.isNotBlank(quota)) {
			loanPdtCondition.setMaxgeQuota(new BigDecimal(quota.trim()));
			//loanPdtCondition.setMinleQuota(new BigDecimal(quota.trim()));
		}
		if (StringUtils.isNotBlank(term)&&StringUtils.isNotBlank(pdtTerm)) {
			loanPdtCondition.setMaxgeTerm(new Integer(term));
			//loanPdtCondition.setMinleTerm(new Integer(term));
		}
		if(StringUtils.isNotBlank(pdtTerm)&&StringUtils.isNotBlank(term)){
			loanPdtCondition.setMaxTermType(TermType.valueOf(pdtTerm));
		}
		
		if(StringUtils.isNotBlank(rateType)&&StringUtils.isNotBlank(rateType)){
			loanPdtCondition.setRateType(RateType.valueOf(rateType));
		}
		if (StringUtils.isNotBlank(rate)&&StringUtils.isNotBlank(rateType)) {
			loanPdtCondition.setMaxRate(new BigDecimal(rate.trim()));
		}
		if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
			loanPdtCondition.setProvinceCode(provinceCode);
		}
		if (StringUtils.isNotBlank(region)) {
			if (region.equals("NONE")) {
				loanPdtCondition.setRegion(null);
			} else {
				loanPdtCondition.setRegion(region);
			}
		}
		loanPdtCondition.setPdtStatus(PdtStat.GROUNDING);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("updTime"));
		loanPdtCondition.setOrders(orderList);

		loanPdtList = this.loanPdtService.queryLoanPdtByCon(loanPdtCondition,
				page);
		if (instList == null)
			instList = instService.queryInst();
		for (LoanPdt loanPdt : loanPdtList) {
			DecimalFormat df = new DecimalFormat();
			String s = df.format(loanPdt.getRate());
			loanPdt.setRate(new BigDecimal(s));
			for (Inst inst : instList) {
				if (loanPdt.getIinst().longValue() == inst.getIinst()
						.longValue()) {
					loanPdt.setInst(inst);
				}
			}
		}
		pageAmt = page.getPageAmt();
	}

	/**
	 * 获取最新贷款申请列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getDebitBid(int bidCurPage, int bidCapacity) throws Exception {
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(bidCapacity);// 设置每页显示数
		page.setPageOffset(bidCurPage);
		this.bidCurPage = bidCurPage + "";
		DebitBidCondition debitBidCondition = new DebitBidCondition();

		// 设置查询条件
		if (StringUtils.isNotBlank(quotaBid)) {
			debitBidCondition.setMaxQuota(new BigDecimal(quotaBid.trim())
					.multiply(new BigDecimal(10000)));
		}
		if (StringUtils.isNotBlank(rateBid)&&StringUtils.isNotBlank(rateType)) {
			debitBidCondition.setMaxRate(new BigDecimal(rateBid.trim()));
		}
		if(StringUtils.isNotBlank(rateType)&&StringUtils.isNotBlank(rateBid)){
			debitBidCondition.setRateType(RateType.valueOf(rateType));
		}
		
		if (StringUtils.isNotBlank(termBid)&&StringUtils.isNotBlank(debitTerm)) {
			debitBidCondition.setMaxTerm(new Integer(termBid.trim()));
		}

		if(StringUtils.isNotBlank(debitTerm)&&StringUtils.isNotBlank(termBid)){
			debitBidCondition.setTermType(TermType.valueOf(debitTerm));
		}
		
		if (StringUtils.isNotBlank(region)&&!region.equals("1")) {
			debitBidCondition.setRegion(region);
		}

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("idebitBid"));
		debitBidCondition.setOrders(orderList);

		debitBidList = this.debitBidService.queryDebitBidByCon(
				debitBidCondition, page);

		if (merchList == null) {
			merchList = merchService.queryMerch();
		}
		for (DebitBid debitBid : debitBidList) {
			//DecimalFormat df = new DecimalFormat();
			//String s = df.format(debitBid.getYearRate());
			//debitBid.setYearRate(new BigDecimal(s));
			for (Merch merch : merchList) {
				if (debitBid.getImerch().longValue() == merch.getImerch()
						.longValue()) {
					debitBid.setMerch(merch);
				}
			}
		}

		bidPageAmt = page.getPageAmt();
	}

	/**
	 * 借贷实况列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getLoanOrd(int curPage, int orderCapacity) throws Exception {
		LoanOrdCondition cond = new LoanOrdCondition();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("updTime"));
		cond.setOrders(orderList);
		Set<OrdStat> ordstatSet = new HashSet<OrdStat>();
		ordstatSet.add(OrdStat.CREDITING);
		ordstatSet.add(OrdStat.REFUNDING);
		ordstatSet.add(OrdStat.REFUND);
		ordstatSet.add(OrdStat.DELIN_QUENCY);
		//cond.setOrdStat(OrdStat.CREDITING);
		cond.setOrdStatSet(ordstatSet);
		cond.setShield(0);
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(orderCapacity);// 设置每页显示数
		page.setPageOffset(curPage);// 设置开始页面
		loanOrdList = this.loanOrdService.queryOrdByCon(cond, page);
		for(LoanOrd loanOrd : loanOrdList){
			Merch merch = new Merch();
			merch = this.merchService.findMerchById(loanOrd.getImerch());
			loanOrd.setMerchName(merch.getAlias());
		}
		loanOrdPageAmt = page.getPageAmt();
	}

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;
	
	/**
	 * 产品详情查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loanDetail() throws Exception {
		if (id == null) {
			super.addActionMessage("产品对象为空，请检查");
			return "homePageSuccess";
		}
		loanPdt = this.loanPdtService.getLoanPdtById(id);
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(10);// 设置每页显示数
		page.setPageOffset(0);
		loanPdt = this.loanPdtService.getLoanPdtById(id);
		inst = instService.getInstById(loanPdt.getIinst());
		if(loanPdt!=null){
			String feature = StringUtils.isNotBlank(loanPdt.getFeature())?loanPdt.getFeature().replaceAll("\r\n", "<br>"):"";
			String condition =StringUtils.isNotBlank(loanPdt.getCondition())?loanPdt.getCondition().replaceAll("\r\n", "<br>"):"";
			String cl = StringUtils.isNotBlank(loanPdt.getCl())?loanPdt.getCl().replaceAll("\r\n", "<br>"):"";
			loanPdt.setFeature(feature);
			loanPdt.setCondition(condition);
			loanPdt.setCl(cl);
			loanPdt.setInst(inst);
		}
		return "loanDetailSuccess";
	}
	
	public String loanDetailForMenu() throws Exception {
		if (id == null) {
			super.addActionMessage("产品对象为空，请检查");
			return "homePageSuccess";
		}
		loanPdt = this.loanPdtService.getLoanPdtById(id);
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(10);// 设置每页显示数
		page.setPageOffset(0);
		loanPdt = this.loanPdtService.getLoanPdtById(id);
		inst = instService.getInstById(loanPdt.getIinst());
		if(loanPdt!=null){
			String feature = StringUtils.isNotBlank(loanPdt.getFeature())?loanPdt.getFeature().replaceAll("\r\n", "<br>"):"";
			String condition =StringUtils.isNotBlank(loanPdt.getCondition())?loanPdt.getCondition().replaceAll("\r\n", "<br>"):"";
			String cl = StringUtils.isNotBlank(loanPdt.getCl())?loanPdt.getCl().replaceAll("\r\n", "<br>"):"";
			loanPdt.setFeature(feature);
			loanPdt.setCondition(condition);
			loanPdt.setCl(cl);
			loanPdt.setInst(inst);
		}
		return "prodDetailMenu";
	}

	/**
	 * 借款申请详情查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bidDetail() throws Exception {
		if (bidId == null) {
			super.addActionMessage("借款申请对象为空，请检查");
			return "homePageSuccess";
		}
		debitBid = this.debitBidService.getDebitBidById(bidId);
		merch = this.merchService.findMerchById(debitBid.getImerch());
		List<Province> list = provinceService.getProvinceAndCityName(debitBid
				.getRegion());
		area = "";
		if (list != null) {
			Collections.reverse(list);
			for (Province prov : list) {
				area += prov.getName();
			}
		}
		
		return "bidDetailSuccess";
	}
	
	/**
	 * 机构页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String instIntro() throws Exception {
		return "instIntroSuccess";
	}
	
	/**
	 * 详情公告页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loanNotifyDetail() throws Exception {
		if (iinstNotice == null) {
			super.addActionMessage("公告对象为空，请检查");
			return "homePageSuccess";
		}
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(1);// 设置每页显示数
		page.setPageOffset(1);// 设置开始页面

		InstNoticeCondition instNoticeCondition = new InstNoticeCondition();
		instNoticeCondition.setIinstNotice(iinstNotice);
		instNoticeList = instNoticeService.queryInstNoticeByCon(
				instNoticeCondition, page);
		instNotice = instNoticeList.get(0);

		return "notifyDetailSuccess";
	}

	/**
	 * 公告列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loanNotifyMore() throws Exception {
		if ("".equals(noticeCurPage)) {
			noticeCurPage = "0";
		}
		// 获取公告列表
		getInstNotice(Integer.parseInt(noticeCurPage), 10);
		
		return "notifyIndexSuccess";
	}
	
	/**
	 * 借贷实况列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loanOrdMore() throws Exception {
		if ("".equals(loanOrdCurPage)) {
			loanOrdCurPage = "0";
		}
		// 获取公告列表
		getLoanOrd(Integer.parseInt(loanOrdCurPage), 10);
		return "loanOrdIndexSuccess";
	}
	
	/**
	 * 我要放贷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String offerLoan() throws Exception {
		quotaBid = "";
		rateBid = "";
		termBid = "";
		provinceCode="";
		rateType="";
		pdtTerm="";
		debitTerm="";
		navFlag = 2;
		// 获取最新贷款申请列表
		getDebitBid(Integer.parseInt(bidCurPage), 10);
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		return "offerLoanSuccess";
	}

	/**
	 * 我要放贷(用于搜索)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String offerLoanSe() throws Exception {

		navFlag = 2;
		if ("".equals(bidCurPage)) {
			bidCurPage = "0";
		}
		// 获取最新贷款申请列表
		getDebitBid(Integer.parseInt(bidCurPage), 10);
		return "offerLoanSuccess";
	}

	/**
	 * 我要借款
	 * 
	 * @return
	 */
	public String askLoan() throws Exception {
		quota = "";
		term = "";
		rate = "";
		region = "";
		provinceCode="";
		navFlag = 1;
		rateType="";
		pdtTerm="";
		debitTerm="";
		// 热门借贷产品列表
		getLoadPdt(Integer.parseInt(curPage), 10);
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		return "askLoanSuccess";
	}

	public String getDebitTerm() {
		return debitTerm;
	}

	public void setDebitTerm(String debitTerm) {
		this.debitTerm = debitTerm;
	}

	/**
	 * 我要借款(用于搜索)
	 * 
	 * @return
	 */
	public String askLoanSe() throws Exception {

		navFlag = 1;
		// 热门借贷产品列表
		if ("".equals(curPage)) {
			curPage = "0";
		}
		getLoadPdt(Integer.parseInt(curPage), 10);
		return "askLoanSuccess";
	}
	
	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public String assessment(){
		code=null;
		mccCode = "";
		mccMap=mccservice.getFirstBussin();
		pdtList=loanPdtService.queryAll();
		return "assessment";
	}

	// 以下是get,set方法
	public String getQuota() {
		return quota;
	}

	public String getPdtTerm() {
		return pdtTerm;
	}

	public void setPdtTerm(String pdtTerm) {
		this.pdtTerm = pdtTerm;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<LoanPdt> getLoanPdtList() {
		return loanPdtList;
	}

	public void setLoanPdtList(List<LoanPdt> loanPdtList) {
		this.loanPdtList = loanPdtList;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
	}

	public LoanPdt getLoanPdt() {
		return loanPdt;
	}

	public void setLoanPdt(LoanPdt loanPdt) {
		this.loanPdt = loanPdt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public List<DebitBid> getDebitBidList() {
		return debitBidList;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Map<String, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<String, String> cityMap) {
		this.cityMap = cityMap;
	}

	public Map<String, String> getProvMap() {
		return provMap;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
	}

	public void setDebitBidList(List<DebitBid> debitBidList) {
		this.debitBidList = debitBidList;
	}

	public String getQuotaBid() {
		return quotaBid;
	}

	public void setQuotaBid(String quotaBid) {
		this.quotaBid = quotaBid;
	}

	public String getTermBid() {
		return termBid;
	}

	public void setTermBid(String termBid) {
		this.termBid = termBid;
	}

	public String getRateBid() {
		return rateBid;
	}

	public void setRateBid(String rateBid) {
		this.rateBid = rateBid;
	}

	public String getBidFlag() {
		return bidFlag;
	}

	public void setBidFlag(String bidFlag) {
		this.bidFlag = bidFlag;
	}

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	public List<InstNotice> getInstNoticeList() {
		return instNoticeList;
	}

	public void setInstNoticeList(List<InstNotice> instNoticeList) {
		this.instNoticeList = instNoticeList;
	}

	public int getPageAmt() {
		return pageAmt;
	}

	public void setPageAmt(int pageAmt) {
		this.pageAmt = pageAmt;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getBidCurPage() {
		return bidCurPage;
	}

	public void setBidCurPage(String bidCurPage) {
		this.bidCurPage = bidCurPage;
	}

	public int getBidPageAmt() {
		return bidPageAmt;
	}

	public void setBidPageAmt(int bidPageAmt) {
		this.bidPageAmt = bidPageAmt;
	}

	public int getNavFlag() {
		return navFlag;
	}

	public void setNavFlag(int navFlag) {
		this.navFlag = navFlag;
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

	public List<Merch> getMerchList() {
		return merchList;
	}

	public void setMerchList(List<Merch> merchList) {
		this.merchList = merchList;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public int getNoticePageAmt() {
		return noticePageAmt;
	}

	public void setNoticePageAmt(int noticePageAmt) {
		this.noticePageAmt = noticePageAmt;
	}

	public String getNoticeCurPage() {
		return noticeCurPage;
	}

	public void setNoticeCurPage(String noticeCurPage) {
		this.noticeCurPage = noticeCurPage;
	}

	public String getLoanOrdCurPage() {
		return loanOrdCurPage;
	}

	public void setLoanOrdCurPage(String loanOrdCurPage) {
		this.loanOrdCurPage = loanOrdCurPage;
	}

	public int getLoanOrdPageAmt() {
		return loanOrdPageAmt;
	}

	public void setLoanOrdPageAmt(int loanOrdPageAmt) {
		this.loanOrdPageAmt = loanOrdPageAmt;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

}