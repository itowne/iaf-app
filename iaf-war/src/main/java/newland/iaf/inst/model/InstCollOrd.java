package newland.iaf.inst.model;

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
 * 
 * 机构关注订单表
 * 
 * @author tzy
 * 
 */
@Entity
@Table(name = "t_inst_coll_ord")
@org.hibernate.annotations.Table(appliesTo = "t_inst_coll_ord", indexes = {
		@Index(name = "idx_inst_coll_ord1", columnNames = { "i_inst" }),
		@Index(name = "idx_inst_coll_ord2", columnNames = { "i_merch" })
})
public class InstCollOrd implements Serializable {

	private static final long serialVersionUID = 8129856087615603358L;

	/**
	 * 机构关注订单表内部编号
	 */
	@Id
	@Column(name = "i_inst_coll_ord", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_coll_ord", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_coll_ord")
	private Long iInstCollOrd;
	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = true)
	private Long iInst;
	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false, updatable = true)
	private Long iMerch;
	/**
	 * 借款竞投申请内部编号
	 */
	@Column(name = "i_debit_bid", nullable = false, updatable = false)
	private Long idebitBid;
	
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiInstCollOrd() {
		return iInstCollOrd;
	}

	public void setiInstCollOrd(Long iInstCollOrd) {
		this.iInstCollOrd = iInstCollOrd;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public Long getiMerch() {
		return iMerch;
	}

	public void setiMerch(Long iMerch) {
		this.iMerch = iMerch;
	}

	public Long getIdebitBid() {
		return idebitBid;
	}

	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}
}
