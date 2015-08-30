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
 * t_merch_coll_inst商户收藏机构表
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_merch_coll_inst")
@org.hibernate.annotations.Table(appliesTo = "t_merch_coll_inst", indexes = {
		@Index(name = "idx_merch_coll_inst1", columnNames = { "i_inst" }),
		@Index(name = "idx_merch_coll_inst2", columnNames = { "i_merch_usr" }) })
public class MerchCollInst implements Serializable {

	private static final long serialVersionUID = -6531831105654562415L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch_coll_inst", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_coll_inst")
	@Column(name = "i_merch_coll_inst", updatable = false)
	private Long iMerchCollInst;

	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = false)
	private Long iInst;
	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false, updatable = false)
	private Long imerch;
	/**
	 * 商户用户内部编号
	 */
	@Column(name = "i_merch_usr", nullable = false, updatable = false)
	private Long iMerchUsr;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiMerchCollInst() {
		return iMerchCollInst;
	}

	public void setiMerchCollInst(Long iMerchCollInst) {
		this.iMerchCollInst = iMerchCollInst;
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
