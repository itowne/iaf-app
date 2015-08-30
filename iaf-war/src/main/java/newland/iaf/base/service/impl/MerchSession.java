package newland.iaf.base.service.impl;

import java.math.BigInteger;
import java.util.Date;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.servlet.LoginFlag;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.utils.DateUtils;

/**
 * 商户session
 * 
 * @author rabbit
 * 
 */
public class MerchSession extends AbsSession {

	private MerchUser merchUser;

	private Merch merch;

	private MerchBusiInfo merchBusiInfo;

	private MerchLegalPers merchLegalPers;
	
	private LoanStatistics statistics;
	
	private BigInteger loanPdtCount;
	
	private BigInteger debitBidCount;
	

	@Override
	public String flag() {
		return LoginFlag.MERCH_LOGIN;
	}
	
	public String getAmOrPm(){
		return DateUtils.getAM_PM_Chinese(new Date());
	}

	public MerchUser getMerchUser() {
		return merchUser;
	}

	public void setMerchUser(MerchUser merchUser) {
		this.merchUser = merchUser;
	}

	public MerchLegalPers getMerchLegalPers() {
		return merchLegalPers;
	}

	public void setMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.merchLegalPers = merchLegalPers;
	}

	public MerchBusiInfo getMerchBusiInfo() {
		return merchBusiInfo;
	}

	public void setMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchBusiInfo = merchBusiInfo;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public LoanStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(LoanStatistics statistics) {
		this.statistics = statistics;
	}

	public BigInteger getLoanPdtCount() {
		return loanPdtCount;
	}

	public void setLoanPdtCount(BigInteger loanPdtCount) {
		this.loanPdtCount = loanPdtCount;
	}

	public BigInteger getDebitBidCount() {
		return debitBidCount;
	}

	public void setDebitBidCount(BigInteger debitBidCount) {
		this.debitBidCount = debitBidCount;
	}

}
