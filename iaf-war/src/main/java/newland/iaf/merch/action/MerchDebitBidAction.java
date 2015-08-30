package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.DateUtils;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 商户竞投申请Action
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchDebitBid")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "debitBidAdd", type = "dispatcher", location = "/merch/new-bidd-req.jsp"),
		@Result(name = "viewDebitBit", type = "dispatcher", location = "/merch/bidd-req-detail.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merch/bidd-req.jsp"),
		@Result(name = "toIndex", type = "redirect", location = "/merch/merchDebitBid"),
		@Result(name = "addSuccess", type = "dispatcher", location = "/merch/bidAddSuccess.jsp"),
		@Result(name = "index", type = "dispatcher", location = "/merch/new-bidd-req.jsp"),
		@Result(name = "infoCheck", type = "dispatcher", location = "/merch/bidd-req-check.jsp"),
		@Result(name = "saveSuccess", type = "dispatcher", location = "/merch/bidd-req-check-success.jsp")
})
public class MerchDebitBidAction extends MerchBaseAction {

	private static final long serialVersionUID = 6120244359192565346L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;

	@Resource(name = "merchLoanService")
	private MerchLoanService merchLoanService;

	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;

	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name="mccService")
	private MccService mccService;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<DebitBid> debitBidList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private DebitBid debitBid;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String yearRate;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String Maxquota;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String bidStat;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String[] inst;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String startDate;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endDate;

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
	private String cityCode;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String areaName;
	
	private String Maxterm;
	
	private String MaxyearRate;
	
	private String times;
	
	private String maxTimes;
	
	private String rateType;
	
	private String termType;
	
	private String merchName;
	    
    private String industry;
    
	private Map<String, String> mccMap;
	
	private String mccCode;
	
	private String code;

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	
	public String getBidStat() {
		return bidStat;
	}

	public void setBidStat(String bidStat) {
		this.bidStat = bidStat;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getQuota() {
		return quota;
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

	public List<DebitBid> getDebitBidList() {
		return debitBidList;
	}

	public void setDebitBidList(List<DebitBid> debitBidList) {
		this.debitBidList = debitBidList;
	}

	@Begin
	public String execute() {
		merchName = this.getMerch().getMerchName();
		industry = this.getMerch().getIndustry();
		return "success";
	}

	// 转跳至新增页面
	public String debitBidAdd() throws Exception {
		instList = instService.queryInst();
		debitBid = new DebitBid();
		provinceCode="";
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		
		Date now = new Date();
		debitBid.setGenTime(now);
		Map<SysParamName, SysParam> map = this.sysParamService.findSysParamMapByType(SysParamType.expireDate);
		try{
			String sysParamValue = map.get(SysParamName.EXPIRE_DATE).getValue();
			Date expireDate = DateUtils.addDays(now, Integer.parseInt(sysParamValue));
			debitBid.setExpireDate(DateUtils.roundDate(expireDate, Calendar.DATE));
		}catch(Exception e){
			throw new Exception("系统参数获取异常："+e.getMessage());
		}
		return "debitBidAdd";
	}

	// 撤销
	public String cancel() throws Exception {
		debitBid.setBidStat(DebitbidStat.REVOCATION);
		this.merchLoanService.updateDebitBid(debitBid, getMerchSession(), "",
				getIpaddr());
		return "toIndex";
	}

	//
	public String viewDebitBit() throws Exception {
		debitBid = debitBidList.get(index.intValue());
		getInstNames(debitBid);
		getAreaNames(debitBid);
		return "viewDebitBit";
	}
	
	private void getInstNames(DebitBid debitBid) throws Exception{
		List<LoanOrd> loanOrdList = this.loanOrdService.queryByDebit(debitBid
				.getIdebitBid());
		List<Inst> instList = instService.getInstList(loanOrdList);
		instName = "";
		for (int i = 0; i < instList.size(); i++) {

			if (i == instList.size() - 1) {
				instName += instList.get(i).getInstName();
			} else {
				instName += instList.get(i).getInstName() + " ; ";
			}
		}
	}

	public String getMaxquota() {
		return Maxquota;
	}

	public void setMaxquota(String maxquota) {
		Maxquota = maxquota;
	}

	private void getAreaNames(DebitBid debitBid) throws Exception{
		List<Province> list = provinceService.getProvinceAndCityName(debitBid
				.getRegion());
		areaName = "";
		if (list!=null) {
			Collections.reverse(list);
			for (Province prov : list) {
				areaName += prov.getName();
			}
		}
	}
	
	// 新增竞投
	@InputConfig(resultName = "index")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "debitBid.purpose", shortCircuit = true, message = "借款用途不能为空!") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "debitBid.quota", message = "借款金额输入有误！", shortCircuit = true),
			@RequiredFieldValidator(fieldName = "debitBid.term", message = "借款期限输入有误！", shortCircuit = true),
			@RequiredFieldValidator(fieldName = "debitBid.yearRate", message = "利率输入有误！", shortCircuit = true) }, regexFields = {
			@RegexFieldValidator(fieldName = "debitBid.quota", expression = "^\\d+\\.{0,1}\\d+$", message = "借款金额必须为数字!"),
			@RegexFieldValidator(fieldName = "debitBid.term", expression = "^[0-9]+$", message = "借款期限必须为数字!"),
			@RegexFieldValidator(fieldName = "debitBid.yearRate", expression = "^\\d+\\.{0,1}\\d+$", message = "利率必须为数字!") })
	public String saveDebitBit() throws Exception {
		try {
			MerchSession merchSession = (MerchSession) SessionFilter
					.getIafSession();
			debitBid.setImerch(merchSession.getMerch().getImerch());
			debitBid.setQuota(debitBid.getQuota().multiply(
					new BigDecimal(10000)));
			debitBid.setiMerchUser(merchSession.getMerchUser().getImerchUser());
			debitBid.setBidStat(DebitbidStat.NORMAL);
			debitBid.setMerchType(merchSession.getMerch().getMerchType());
			debitBid.setMerchName(merchSession.getMerch().getMerchName());
			if(StringUtils.isNotBlank(rateType)){
				debitBid.setRateType(RateType.valueOf(rateType));
			}
			if(StringUtils.isNotBlank(termType)){
				debitBid.setTermType(TermType.valueOf(termType));
			}
			if(StringUtils.isNotBlank(cityCode)){
				debitBid.setRegion(cityCode);
			}else if(StringUtils.isNotBlank(provinceCode)){
				debitBid.setRegion(provinceCode);
			}else{
				debitBid.setRegion("1");
			}
			debitBid = this.merchLoanService.addDebitBid(debitBid, getMerchSession(), "",
					getIpaddr());
			if (inst != null && inst.length > 0) {
				Set<Long> instset = new HashSet<Long>();
				for (int i = 0; i < inst.length; i++) {
					// 新增订单
					instset.add(Long.valueOf(inst[i]));
				}
				this.merchLoanService.applyDebitBid(debitBid, instset,
						getMerchSession(), "借款发布", getIpaddr());
			}
			getInstNames(debitBid);
			getAreaNames(debitBid);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			super.addActionMessage(e.getMessage());
			return "success";
		}
		return "addSuccess";
	}

	public String list() throws Exception {
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			if (dataSet == null) {
				dataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(dataSet.getPage() - 1);
			}

			MerchSession merchSession = (MerchSession) SessionFilter
					.getIafSession();
			DebitBidCondition debitBidCondition = new DebitBidCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(yearRate)&&StringUtils.isNotBlank(rateType)) {
				debitBidCondition.setMinRate(new BigDecimal(yearRate.trim()));
			}
			if(StringUtils.isNotBlank(MaxyearRate)){
				debitBidCondition.setMaxRate(new BigDecimal(MaxyearRate.trim()));
			}
			if(StringUtils.isNotBlank(rateType)&&StringUtils.isNotBlank(yearRate)){
				debitBidCondition.setRateType(RateType.valueOf(rateType));
			}
			if (StringUtils.isNotBlank(quota)) {
				//debitBidCondition.setQuota((new BigDecimal(quota.trim())).multiply(new BigDecimal(10000)));
				debitBidCondition.setMinquota((new BigDecimal(quota.trim())).multiply(new BigDecimal(10000)));
			}
			if(StringUtils.isNotBlank(Maxquota)){
				debitBidCondition.setMaxquota((new BigDecimal(Maxquota.trim())).multiply(new BigDecimal(10000)));
			}
			if (StringUtils.isNotBlank(term)&&StringUtils.isNotBlank(termType)) {
				debitBidCondition.setMinTerm(new Integer(term.trim()));
			}
			
			if(StringUtils.isNotBlank(termType)){
				debitBidCondition.setTermType(TermType.valueOf(termType));
			}
			if(StringUtils.isNotBlank(Maxterm)){
				debitBidCondition.setMaxTerm(new Integer(Maxterm.trim()));
			}
			
			if(StringUtils.isNotBlank(times)){
				debitBidCondition.setMinacceptTotal(new Long(times.trim()));
			}
			if(StringUtils.isNotBlank(maxTimes)){
				debitBidCondition.setMaxacceptTotal(new Long(maxTimes.trim()));
			}
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			if (StringUtils.isNotBlank(startDate)) {
//				debitBidCondition.setStartGenDate(sdf.parse(startDate));
//			}
//			if (StringUtils.isNotBlank(endDate)) {
//				debitBidCondition.setEndGenDate(sdf.parse(endDate));
//			}
			if (StringUtils.isNotBlank(bidStat)) {
				if (bidStat.equals("1")) {
					// 正常包括上架、下架
					debitBidCondition.setDebitbidStat(DebitbidStat.NORMAL);
				} else {
					debitBidCondition.setDebitbidStat(DebitbidStat.REVOCATION);
				}
			}
//			debitBidCondition.setiMerchUser(merchSession.getMerchUser()
//					.getImerchUser());
			debitBidCondition.setImerch(merchSession.getMerch().getImerch());
			debitBidList = this.debitBidService.queryDebitBidByCon(
					debitBidCondition, page);
			dataSet.setGridModel(debitBidList);
			dataSet.setRecords(debitBidList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	/**
	 * 检验注册商户的商户名和所属行业是否为空，如果为空，则需填写后才能进行贷款操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String infoCheck() throws Exception {
		try {
			code=null;
			mccCode = "";
			mccMap=mccService.getFirstBussin();
			return "infoCheck";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "failed";
		}
	}
	/**
	 * 检验注册商户的商户名和所属行业是否为空，填写后保存信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveCheck() throws Exception {
		try {
			Merch merch = this.getMerchSession().getMerch();
			merch.setMerchName(merchName);
			merch.setIndustry(mccService.queryById(code).getName());
			this.merchService.updateMerch(merch);
			return "saveSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "failed";
		}
	}

	@JqDataSet(content = "{o:wangyuanQuota},{o:debitTerm},{o:debitRate},{o:bidStat.desc},{o:acceptTotal},{o:idebitBid}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public DebitBidService getDebitBidService() {
		return debitBidService;
	}

	public void setDebitBidService(DebitBidService debitBidService) {
		this.debitBidService = debitBidService;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public LoanOrdService getLoanOrdService() {
		return loanOrdService;
	}

	public void setLoanOrdService(LoanOrdService loanOrdService) {
		this.loanOrdService = loanOrdService;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getMaxterm() {
		return Maxterm;
	}

	public void setMaxterm(String maxterm) {
		Maxterm = maxterm;
	}

	public String getMaxyearRate() {
		return MaxyearRate;
	}

	public void setMaxyearRate(String maxyearRate) {
		MaxyearRate = maxyearRate;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(String maxTimes) {
		this.maxTimes = maxTimes;
	}

	public InstService getInstService() {
		return instService;
	}

	public void setInstService(InstService instService) {
		this.instService = instService;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String[] getInst() {
		return inst;
	}

	public void setInst(String[] inst) {
		this.inst = inst;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
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

}
