package newland.iaf.base.json.merchBaseInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author rabbit
 * 
 */
public class RespBody  {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private String merchName;
	private String mcc;
	private String installAddr;
	private String busi;
	private String regAddr;
	private String settleAccount;
	private String settleBank;
	private Date regTime;
	private String regCap;
	private Date openTime;
	private String taxReg;

	private LegalPer legalPer;

	private String contract;
	private String contractTel;
	private String merchNature;

	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getInstallAddr() {
		return installAddr;
	}

	public void setInstallAddr(String installAddr) {
		this.installAddr = installAddr;
	}

	public String getBusi() {
		return busi;
	}

	public void setBusi(String busi) {
		this.busi = busi;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getSettleAccount() {
		return settleAccount;
	}

	public void setSettleAccount(String settleAccount) {
		this.settleAccount = settleAccount;
	}

	public String getSettleBank() {
		return settleBank;
	}

	public void setSettleBank(String settleBank) {
		this.settleBank = settleBank;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegCap() {
		return regCap;
	}

	public void setRegCap(String regCap) {
		this.regCap = regCap;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getTaxReg() {
		return taxReg;
	}

	public void setTaxReg(String taxReg) {
		this.taxReg = taxReg;
	}

	public LegalPer getLegalPer() {
		return legalPer;
	}

	public void setLegalPer(LegalPer legalPer) {
		this.legalPer = legalPer;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getMerchNature() {
		return merchNature;
	}

	public void setMerchNature(String merchNature) {
		this.merchNature = merchNature;
	}
}
