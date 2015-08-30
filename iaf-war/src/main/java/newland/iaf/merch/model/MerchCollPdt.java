package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

/**
 * t_merch_coll_pdt商户收藏产品表
 * @author wwa
 *
 */
@Entity
@Table(name = "t_merch_coll_pdt")
@org.hibernate.annotations.Table(appliesTo = "t_merch_coll_pdt", indexes = {
		@Index(name = "idx_merch_coll_pdt1", columnNames = { "i_merch_usr" }),
		@Index(name = "idx_merch_coll_pdt2", columnNames = { "i_loan_pdt" }) })
public class MerchCollPdt implements Serializable {

	private static final long serialVersionUID = 5557344945208487L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch_coll_pdt", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_coll_pdt")
	@Column(name = "i_merch_coll_pdt", updatable = false)
	private Long iMerchCollPdt;
	
	/**
	 * 贷款产品
	 */
	@Column(name = "i_loan_pdt", nullable = false, updatable = false)
	private Long iLoanPdt;
	
	/**
	 * 机构
	 */
	@Column(name = "i_inst", nullable = false, updatable = false)
	private Long iInst;
	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false, updatable = false)
	private Long imerch;
	/**
	 * 商户用户
	 */
	@Column(name = "i_merch_usr", nullable = false, updatable = false)
	private Long iMerchUsr;
	
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiMerchCollPdt() {
		return iMerchCollPdt;
	}

	public void setiMerchCollPdt(Long iMerchCollPdt) {
		this.iMerchCollPdt = iMerchCollPdt;
	}

	public Long getiLoanPdt() {
		return iLoanPdt;
	}

	public void setiLoanPdt(Long iLoanPdt) {
		this.iLoanPdt = iLoanPdt;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public Long getiMerchUsr() {
		return iMerchUsr;
	}

	public void setiMerchUsr(Long iMerchUsr) {
		this.iMerchUsr = iMerchUsr;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	
}
