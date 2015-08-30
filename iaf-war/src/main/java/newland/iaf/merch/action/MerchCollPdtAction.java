package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.MerchCollPdt;
import newland.iaf.merch.service.MerchCollService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

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

/**
 * 商户关注产品  Action
 * @author wwa
 *
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchCollPdt")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "viewProduct",  type = "dispatcher",location = "/merch/prod-req-detail.jsp"),
	@Result(name = "failed",  type = "dispatcher",location = "/merch/prod-attention.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/merch/prod-attention.jsp")
    })
public class MerchCollPdtAction extends MerchBaseAction {
	
	private static final long serialVersionUID = 3856457951478663011L;

	private DataSet dataSet;//用户JQGrid数据
	
	@Resource(name="merchCollService")
	private MerchCollService merchCollService;
	
	@Resource(name="loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanPdt> loanPdtList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String yearRate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtStatus;	
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<String> provinceList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanPdt loanPdt;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long iloanPdt;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String iinst;
	
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
	private Boolean collFlag;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String rate;
	
	private String termType;
	
	private String minterm;
	
	private String Maxterm;
	
    private String minTermType;
    
    private String MaxTermType;
    
	private List<LoanPdt> pdtList;
	
	private String rateType;
	
	private String times;
	
	private String creditTerm;
	
	@Override
	@Begin
	
	public String execute() throws Exception {
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		pdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		iinst = "";
		return "success";
	}

	public String add() throws Exception {
		MerchSession merchSess = (MerchSession) SessionFilter.getIafSession();
		Long imerchUser = merchSess.getMerchUser().getImerchUser();
		Long imerch = merchSess.getMerch().getImerch();
		MerchCollPdt mcp = this.merchCollService.queryByIloanPdtAndImerch(iloanPdt,imerch);
		if(mcp==null){
			mcp = new MerchCollPdt();
			mcp.setImerch(imerch);
			mcp.setiMerchUsr(imerchUser);
			mcp.setiLoanPdt(iloanPdt);
			mcp.setiInst(new Long(iinst));
			mcp.setGenTime(new Date());
			this.merchCollService.addMerchCollPdt(mcp);
		}
		return execute();
	}
	
	public String list() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);//设置每页显示数
			if(dataSet == null){
				dataSet = new DataSet();
				page.setPageOffset(0);
			}else{
				page.setPageOffset(dataSet.getPage()-1);
			}
			LoanPdtCondition loanPdtCondition = new LoanPdtCondition();
			//设置查询条件
			if (StringUtils.isNotBlank(rate)) {
				loanPdtCondition.setMaxRate(new BigDecimal(rate.trim()));
			}
			if(StringUtils.isNotBlank(rateType)){
				loanPdtCondition.setRateType(RateType.valueOf(rateType));
			}
			if(StringUtils.isNotBlank(pdtId)){
				List<Long> list = new ArrayList<Long>();
				list.add(Long.parseLong(pdtId));
				loanPdtCondition.setIloanPdt(list);
			}
			if(StringUtils.isNotBlank(minterm)&&StringUtils.isNotBlank(minTermType)){
				loanPdtCondition.setMinleTerm(Integer.parseInt(minterm));
			}
			if(StringUtils.isNotBlank(Maxterm)&&StringUtils.isNotBlank(MaxTermType)){
				loanPdtCondition.setMaxTerm(Integer.parseInt(Maxterm));
			}
			if(StringUtils.isNotBlank(minTermType)){
				loanPdtCondition.setMinTermType(TermType.valueOf(minTermType));
			}
			if(StringUtils.isNotBlank(MaxTermType)){
				loanPdtCondition.setMaxTermType(TermType.valueOf(MaxTermType));
			}
			if (StringUtils.isNotBlank(quota)) {
				loanPdtCondition.setMinleQuota(new BigDecimal(quota.trim()));
				loanPdtCondition.setMaxgeQuota(new BigDecimal(quota.trim()));
			}
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				loanPdtCondition.setProvinceCode(provinceCode);
			}
			if (StringUtils.isNotBlank(region)) {
				loanPdtCondition.setRegion(region);
			}
			if (StringUtils.isNotBlank(iinst)) {
				loanPdtCondition.setIinst(new Long(iinst));
			}
			if(StringUtils.isNotBlank(times)){
				loanPdtCondition.setReqTotal(Long.parseLong(times));
			}
			if(StringUtils.isNotBlank(creditTerm)){
				loanPdtCondition.setCreditTerm(Integer.parseInt(creditTerm));
			}
			
			MerchSession merchSession = (MerchSession) SessionFilter.getIafSession();
			
			Long imerch = merchSession.getMerch().getImerch();
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iloanOrd"));
			loanPdtCondition.setOrders(orderList);
			loanPdtList = this.merchCollService.queryMerchCollPdtByCon(imerch, loanPdtCondition, page);
			if (instList == null)
				instList = instService.queryInst();
			for (LoanPdt loanPdt : loanPdtList) {
				DecimalFormat df = new DecimalFormat();
				String s = df.format(loanPdt.getRate());
				loanPdt.setRate(new BigDecimal(s));
				for (Inst inst : instList) {
					if (loanPdt.getIinst().longValue() == inst.getIinst().longValue()) {
						loanPdt.setInst(inst);
					}
				}
			}
			dataSet.setGridModel(loanPdtList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public String viewProduct() throws Exception{
		if(index == null){
			super.addActionMessage("产品对象为空，请检查");
			return "failed";
		}
		//loanPdt = this.loanPdtService.getLoanPdtById(index);
		loanPdt = loanPdtList.get(index.intValue());
		loanPdt.setInst(this.instService.findInstById(loanPdt.getIinst()));
		if(loanPdt!=null){
			String feature = StringUtils.isNotBlank(loanPdt.getFeature())?loanPdt.getFeature().replaceAll("\r\n", "<br>"):"";
			String condition =StringUtils.isNotBlank(loanPdt.getCondition())?loanPdt.getCondition().replaceAll("\r\n", "<br>"):"";
			String cl = StringUtils.isNotBlank(loanPdt.getCl())?loanPdt.getCl().replaceAll("\r\n", "<br>"):"";
			loanPdt.setFeature(feature);
			loanPdt.setCondition(condition);
			loanPdt.setCl(cl);
		}
		MerchCollPdt mcp = this.merchCollService.queryByIloanPdtAndImerch(loanPdt.getIloanPdt(),this.getMerch().getImerch());
		if(mcp!=null){
			collFlag = true;
		}
		return "viewProduct";
	}
	
	public String move() throws Exception{
		if(index == null){
			super.addActionMessage("产品对象为空，请检查");
			return "failed";
		}
		loanPdt=loanPdtList.get(index.intValue());
		MerchSession merchSession = (MerchSession) SessionFilter.getIafSession();
		MerchCollPdt mcp = merchCollService.queryByIloanPdtAndImerch(loanPdt.getIloanPdt(),merchSession.getMerch().getImerch());
		this.merchCollService.deleteMerchCollPdt(mcp.getiMerchCollPdt());
		return execute();
	}
	@JqDataSet(content = "{o:pdtName},{o:inst.instName},{o:area},{o:quotaUi},{o:termUi},{o:pdtRate},{o:creditTerm},{o:reqTotal}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public MerchCollService getMerchCollService() {
		return merchCollService;
	}

	public void setMerchCollService(MerchCollService merchCollService) {
		this.merchCollService = merchCollService;
	}

	public List<LoanPdt> getLoanPdtList() {
		return loanPdtList;
	}

	public void setLoanPdtList(List<LoanPdt> loanPdtList) {
		this.loanPdtList = loanPdtList;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getPdtName() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPdtStatus() {
		return pdtStatus;
	}

	public void setPdtStatus(String pdtStatus) {
		this.pdtStatus = pdtStatus;
	}

	public List<String> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<String> provinceList) {
		this.provinceList = provinceList;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public LoanPdtService getLoanPdtService() {
		return loanPdtService;
	}

	public void setLoanPdtService(LoanPdtService loanPdtService) {
		this.loanPdtService = loanPdtService;
	}

	public LoanPdt getLoanPdt() {
		return loanPdt;
	}

	public void setLoanPdt(LoanPdt loanPdt) {
		this.loanPdt = loanPdt;
	}

	public Long getIloanPdt() {
		return iloanPdt;
	}

	public void setIloanPdt(Long iloanPdt) {
		this.iloanPdt = iloanPdt;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
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

	public String getIinst() {
		return iinst;
	}

	public void setIinst(String iinst) {
		this.iinst = iinst;
	}

	public Boolean getCollFlag() {
		return collFlag;
	}

	public void setCollFlag(Boolean collFlag) {
		this.collFlag = collFlag;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public String getPdtId() {
		return pdtId;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getMinterm() {
		return minterm;
	}

	public void setMinterm(String minterm) {
		this.minterm = minterm;
	}

	public String getMaxterm() {
		return Maxterm;
	}

	public void setMaxterm(String maxterm) {
		Maxterm = maxterm;
	}

	public String getMinTermType() {
		return minTermType;
	}

	public void setMinTermType(String minTermType) {
		this.minTermType = minTermType;
	}

	public String getMaxTermType() {
		return MaxTermType;
	}

	public void setMaxTermType(String maxTermType) {
		MaxTermType = maxTermType;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getCreditTerm() {
		return creditTerm;
	}

	public void setCreditTerm(String creditTerm) {
		this.creditTerm = creditTerm;
	}

}
