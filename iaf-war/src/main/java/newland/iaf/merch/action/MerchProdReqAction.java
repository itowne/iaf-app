package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchCollPdt;
import newland.iaf.merch.service.MerchCollService;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 产品申请Action
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchProdReq")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "detail", type = "dispatcher", location = "/merch/prod-req-detail.jsp"),
		@Result(name = "want-prod-req", type = "dispatcher", location = "/merch/want-prod-req.jsp"),
		@Result(name = "failed", type = "dispatcher", location = "/merch/prod-req.jsp"),
		@Result(name = "instIntroSuccess", type = "dispatcher", location = "/merch/instIntro.jsp"),
		@Result(name="reqInfo",type="dispatcher",location="/merch/reqInfo.jsp"),
		@Result(name="applySuccess",type="dispatcher",location="/merch/applySuccess.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merch/prod-req.jsp"),
		@Result(name = "want-prod-req-check", type = "dispatcher", location = "/merch/want-prod-req-check.jsp"),
		@Result(name = "saveSuccess", type = "dispatcher", location = "/merch/want-prod-req-check-success.jsp")
})
public class MerchProdReqAction extends MerchBaseAction {

	private static final long serialVersionUID = 6110250643699690316L;

	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;

	@Resource(name = "merchLoanService")
	private MerchLoanService merchLoanService;

	@Resource(name="merchCollService")
	private MerchCollService merchCollService;
	
	@Resource(name="mccService")
	private MccService mccService;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;
		
	private DataSet dataSet;// 用户JQGrid数据

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanPdt> loanPdtList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanPdt loanPdt;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String rate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String iinst;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<String> provinceList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;

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

	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	
	private String times;
	
	private String creditTerm;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Boolean collFlag;
	
	private List<LoanPdt> pdtList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtId;
	
	private String rateType;
	
	private String termType;
	
	private String minterm;
	
	private String Maxterm;
	
    private String MaxTermType;
    
    private String minTermType;
    
    private String merchName;
    
    private String industry;
    
	private Map<String, String> mccMap;
	
	private String mccCode;
	
	private String code;
    
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tfileList;
	
	private String loanPdtId;
	@Override
	@Begin
	@End
	public String execute() throws Exception {
		collFlag = false;
		// 初始化页面需要的一些参数
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		pdtList=loanPdtService.queryAll();
		rate="";
		rateType="";
		minTermType="";
		MaxTermType="";
		Maxterm="";
		minterm="";
		return "success";
	}

	public String query() throws Exception {
		collFlag = false;
		//provMap = provinceService.getProvince();
		//cityMap = provinceService.getProvince(provinceCode);
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(10);// 设置每页显示数
		if (dataSet == null) {
			dataSet = new DataSet();
			page.setPageOffset(0);
		} else {
			page.setPageOffset(dataSet.getPage() - 1);
		}
		LoanPdtCondition loanpdtcondition = new LoanPdtCondition();
		//loanpdtcondition.setPdtName(pdtName);
		if(StringUtils.isNotBlank(loanPdtId)){
			loanpdtcondition.setIiloanPdt(Long.parseLong(loanPdtId));
		}
		//loanpdtcondition.setIinst(new Long(iinst));
		loanPdtList = this.loanPdtService.queryLoanPdtByCon(
				loanpdtcondition, page);
		if(CollectionUtils.isNotEmpty(loanPdtList)){
			loanPdt=loanPdtList.get(0);
			loanPdt.setInst(instService.findInstById(new Long(loanPdt.getIinst())));
		}else{
			loanPdt=new LoanPdt();
		}
		return "detail";
	}

	public String list() throws Exception {
		try {
			pdtList=loanPdtService.queryAll();
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			if (dataSet == null) {
				dataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(dataSet.getPage() - 1);
			}
			LoanPdtCondition loanpdtcondition = new LoanPdtCondition();
			// 只查上架的类型
			loanpdtcondition.setPdtStatus(PdtStat.GROUNDING);
			// 设置查询条件
			if (StringUtils.isNotBlank(rate)) {
				loanpdtcondition.setMaxRate(new BigDecimal(rate.trim()));
			}
			//if (StringUtils.isNotBlank(pdtName)) {
				//loanpdtcondition.setPdtName(pdtName);
			//}
			if(StringUtils.isNotBlank(rateType)){
				loanpdtcondition.setRateType(RateType.valueOf(rateType));
			}
			if(StringUtils.isNotBlank(pdtId)){
				List<Long> list = new ArrayList<Long>();
				list.add(Long.parseLong(pdtId));
				loanpdtcondition.setIloanPdt(list);
			}
			
			if(StringUtils.isNotBlank(minterm)&&StringUtils.isNotBlank(minTermType)){
				loanpdtcondition.setMinleTerm(Integer.parseInt(minterm));
			}
			if(StringUtils.isNotBlank(Maxterm)&&StringUtils.isNotBlank(MaxTermType)){
				loanpdtcondition.setMaxTerm(Integer.parseInt(Maxterm));
			}
			if(StringUtils.isNotBlank(minTermType)){
				loanpdtcondition.setMinTermType(TermType.valueOf(minTermType));
			}
			if(StringUtils.isNotBlank(MaxTermType)){
				loanpdtcondition.setMaxTermType(TermType.valueOf(MaxTermType));
			}
			if (StringUtils.isNotBlank(quota)) {
				loanpdtcondition.setMinleQuota(new BigDecimal(quota.trim()));
				loanpdtcondition.setMaxgeQuota(new BigDecimal(quota.trim()));
			}
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				loanpdtcondition.setProvinceCode(provinceCode);
			}
			if (StringUtils.isNotBlank(region)) {
				loanpdtcondition.setRegion(region);
			}
			if (StringUtils.isNotBlank(iinst)) {
				loanpdtcondition.setIinst(new Long(iinst));
			}
			if(StringUtils.isNotBlank(times)){
				loanpdtcondition.setReqTotal(Long.parseLong(times));
			}
			if(StringUtils.isNotBlank(creditTerm)){
				loanpdtcondition.setCreditTerm(Integer.parseInt(creditTerm));
			}
			//if (StringUtils.isNotBlank(term)) {
				//loanpdtcondition.setMaxgeTerm(new Integer(term));
				//loanpdtcondition.setMinleTerm(new Integer(term));
			//}

			loanPdtList = this.loanPdtService.queryLoanPdtByCon(
					loanpdtcondition, page);
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
			dataSet.setGridModel(loanPdtList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 详情查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception {
		if (index == null) {
			super.addActionMessage("产品对象为空，请检查");
			return "failed";
		}
		// loanPdt = this.loanPdtService.getLoanPdtById(index);
		loanPdt = loanPdtList.get(index.intValue());
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
		merchName = this.getMerch().getMerchName();
		industry = this.getMerch().getIndustry();
		return "detail";
	}

	public String back() {
		return "detail";
	}

	/**
	 * 我要申请后跳转页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String wantProdReq() throws Exception {
		try {
			if (index == null) {
				super.addActionMessage("产品对象为空，请检查");
				return "failed";
			}
			loanPdt = this.loanPdtService.getLoanPdtById(index);
			loanPdt.setInst(instService.findInstById(loanPdt.getIinst()));
			loanOrd = null;
			return "want-prod-req";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "failed";
		}
	}

	/**
	 * 保存申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReqInfo() throws Exception {
		try {
			// 初始化页面需要的一些参数
			if (loanOrd == null) {
				super.addActionMessage("输入信息为空，请检查");
				return "failed";
			}
			loanPdt = this.loanPdtService.getLoanPdtById(loanPdt.getIloanPdt());
			// 根据产品信息，申请订单申请
			MerchSession session = (MerchSession) SessionFilter.getIafSession();
			this.merchLoanService.applyLoanPtd(loanPdt, loanOrd.getQuota(),
					loanOrd.getTerm(), session, loanOrd.getPurpose(), "产品申请",
					this.getIpaddr(),termType);
			//super.addActionMessage("申请成功，查看订单信息请到【我的申请】中查看");
			//return execute();
			return "applySuccess";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			super.addActionMessage(e.getMessage());
			return "want-prod-req";
		}

	}

	/**
	 * 添加产品到我的关注
	 * @return
	 * @throws Exception
	 */
	public String addAttentionPdt() throws Exception {
		MerchSession merchSess = this.getMerchSession();
		Long imerchUser = merchSess.getMerchUser().getImerchUser();
		Long imerch = merchSess.getMerch().getImerch();
		MerchCollPdt mcp = this.merchCollService.queryByIloanPdtAndImerch(loanPdt.getIloanPdt(),imerch);
		if(mcp==null){
			mcp = new MerchCollPdt();
			mcp.setImerch(imerch);
			mcp.setiMerchUsr(imerchUser);
			mcp.setiLoanPdt(loanPdt.getIloanPdt());
			mcp.setiInst(loanPdt.getIinst());
			mcp.setGenTime(new Date());
			this.merchCollService.addMerchCollPdt(mcp);
		}
		super.addActionMessage("添加关注成功，查看信息请到【我的关注-贷款产品】中查看");
		return detail();
	}
	
	/**
	 * 机构详情页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String instIntro() throws Exception {
		inst = instService.getInstById(loanPdt.getIinst());
		tfileList = this.tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		return "instIntroSuccess";
	}
	
	public String reqInfo() throws Exception{
		if (loanOrd.wangyuanQuota.intValue() > loanPdt.getMaxQuota().intValue()){
			super.addActionMessage("超出最大可贷款额度");
			return "want-prod-req";
		}
		if (loanOrd.wangyuanQuota.intValue() < loanPdt.getMinQuota().intValue()){
			super.addActionMessage("低于最小可贷款额度");
			return "want-prod-req";
		}
//		if (loanOrd.term > loanPdt.getMaxTerm()){
//			super.addActionMessage("超出最大可贷期限");
//			return "want-prod-req";
//		}
//		if (loanOrd.term < loanPdt.getMinTerm()){
//			super.addActionMessage("低于最小可贷期限");
//			return "want-prod-req";
//		}
		return "reqInfo";
	}
	
	public String detailBack(){
		
		return "want-prod-req";
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
			return "want-prod-req-check";
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
	
	@JqDataSet(content = "{o:pdtName},{o:inst.instName},{o:area},{o:quotaUi},{o:termUi},{o:pdtRate},{o:creditTerm},{o:reqTotal}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<LoanPdt> getLoanPdtList() {
		return loanPdtList;
	}

	public void setLoanPdtList(List<LoanPdt> loanPdtList) {
		this.loanPdtList = loanPdtList;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public LoanPdt getLoanPdt() {
		return loanPdt;
	}

	public void setLoanPdt(LoanPdt loanPdt) {
		this.loanPdt = loanPdt;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public List<String> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<String> provinceList) {
		this.provinceList = provinceList;
	}

	public List<Inst> getInstList() {
		if (instList == null)
			instList = instService.queryInst();
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPdtName() {
		return pdtName;
	}

	public String getCreditTerm() {
		return creditTerm;
	}

	public List<TFile> getTfileList() {
		return tfileList;
	}

	public void setTfileList(List<TFile> tfileList) {
		this.tfileList = tfileList;
	}

	public String getMaxTermType() {
		return MaxTermType;
	}

	public void setMaxTermType(String maxTermType) {
		MaxTermType = maxTermType;
	}

	public String getMinTermType() {
		return minTermType;
	}

	public void setMinTermType(String minTermType) {
		this.minTermType = minTermType;
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

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public String getPdtId() {
		return pdtId;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public void setCreditTerm(String creditTerm) {
		this.creditTerm = creditTerm;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
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

	public void setTerm(String term) {
		this.term = term;
	}

	public String getIinst() {
		return iinst;
	}

	public void setIinst(String iinst) {
		this.iinst = iinst;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public Boolean getCollFlag() {
		return collFlag;
	}

	public void setCollFlag(Boolean collFlag) {
		this.collFlag = collFlag;
	}

	public String getLoanPdtId() {
		return loanPdtId;
	}

	public void setLoanPdtId(String loanPdtId) {
		this.loanPdtId = loanPdtId;
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
