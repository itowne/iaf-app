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
 * 机构关注商户表
 * 
 * @author Mr.Towne
 * 
 */
@Entity
@Table(name = "t_inst_coll_merch")
@org.hibernate.annotations.Table(appliesTo = "t_inst_coll_merch", indexes = {
		@Index(name = "idx_inst_coll_merch1", columnNames = { "i_inst" }),
		@Index(name = "idx_inst_coll_merch2", columnNames = { "i_merch" }),
		@Index(name = "idx_inst_coll_merch3", columnNames = { "i_inst_usr" }) })
public class InstCollMerch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6820418860496968602L;
	/**
	 * 机构关注商户内部编号
	 */
	@Id
	@Column(name = "i_inst_coll_merch", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_coll_merch", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_coll_merch")
	private Long iInstCollMerch;
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
	 * 机构用户内部编号
	 */
	@Column(name = "i_inst_usr", nullable = false, updatable = true)
	private Long iInstUsr;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiInstCollMerch() {
		return iInstCollMerch;
	}

	public void setiInstCollMerch(Long iInstCollMerch) {
		this.iInstCollMerch = iInstCollMerch;
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

	public Long getiInstUsr() {
		return iInstUsr;
	}

	public void setiInstUsr(Long iInstUsr) {
		this.iInstUsr = iInstUsr;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

}
