package newland.iaf.merch.model.condition;

import java.util.List;

import newland.iaf.base.model.dict.CreditType;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(Merch.class)
public class MerchCondition extends DetachedCriteriaEx{

	private static final long serialVersionUID = 5516573169396740196L;
	/**
	 * 商户内部编号
	 */
	@Expression(operator = Operator.in, propertyName = "imerch")
	private List<Long> imerch;
	/**
	 * 商户名称
	 */
	@Expression(operator = Operator.ilike, propertyName = "merchName",likeMatchMode=LikeMatchMode.anywhere)
	private String merchName;
	
	@Expression(operator = Operator.eq, propertyName = "merchStatus")
	private String merchStatus;
	
	@Expression(operator = Operator.ne, propertyName = "merchStatus")
	private String neMerchStatus;
	/**
	 * 所属行业
	 */
	@Expression(operator = Operator.eq, propertyName = "industry")
	private String industry;
	/**
	 * 信用情况
	 */
	@Expression(operator = Operator.eq, propertyName = "credit")
	private CreditType credit;
	/**
	 * 商户类型
	 */
	@Expression(operator = Operator.eq, propertyName = "merchType")
	private MerchType merchType;
	
	/**
	 * 汇卡商户号
	 */
	@Expression(operator = Operator.ilike, propertyName = "merchNo",likeMatchMode=LikeMatchMode.anywhere)
	private String merchNo;
	
	/**
	 * 联系人
	 */
	@Expression(operator = Operator.ilike, propertyName = "contract",likeMatchMode=LikeMatchMode.anywhere)
	private String contract;
	
	@Expression(operator = Operator.ilike, propertyName = "contractTel",likeMatchMode=LikeMatchMode.anywhere)
	private String contractTel;
	
	/**
	 * 公司规模
	 */
	@Expression(operator = Operator.ilike, propertyName = "companySize",likeMatchMode=LikeMatchMode.anywhere)
	private String companySize;
	
	@Expression(operator = Operator.eq, propertyName = "province")
	private String province;
	
	@Expression(operator = Operator.eq, propertyName = "cityCode")
	private String cityCode;
	
	
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
	public CreditType getCredit() {
		return credit;
	}
	public void setCredit(CreditType credit) {
		this.credit = credit;
	}
	public List<Long> getImerch() {
		return imerch;
	}
	public void setImerch(List<Long> imerch) {
		this.imerch = imerch;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getContractTel() {
		return contractTel;
	}
	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}
	public MerchType getMerchType() {
		return merchType;
	}
	public void setMerchType(MerchType merchType) {
		this.merchType = merchType;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getMerchStatus() {
		return merchStatus;
	}
	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}
	public String getNeMerchStatus() {
		return neMerchStatus;
	}
	public void setNeMerchStatus(String neMerchStatus) {
		this.neMerchStatus = neMerchStatus;
	}
	
}
