package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

import newland.iaf.base.model.dict.PagePosition;
@Entity
@Table(name = "t_merch_info_perm")
@org.hibernate.annotations.Table(appliesTo = "t_merch_info_perm", indexes = {
		@Index(name = "idx_minf_perm1", columnNames = { "imerch", "position" }),
		@Index(name = "idx_minf_perm1", columnNames = { "imerch" })})
public class MerchInfoPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254702878982817796L;
	@Id
	@Column (name = "i_minf_perm")
	@TableGenerator(name = "i_minf_perm", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "i_minf_perm")
	private Long iminfPerm;
	
	@Column (name = "imerch")
	private Long imerch;
	
	@Enumerated
	@Column (name = "position")
	private PagePosition position;
	
	@Column (name = "gen_time")
	private Date genTime;
	
	@Enumerated
	@Column (name = "accept_type")
	private AcceptType acceptType;

	public Long getIminfPerm() {
		return iminfPerm;
	}

	public void setIminfPerm(Long iminfPerm) {
		this.iminfPerm = iminfPerm;
	}

	public PagePosition getPosition() {
		return position;
	}

	public void setPosition(PagePosition position) {
		this.position = position;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public AcceptType getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(AcceptType acceptType) {
		this.acceptType = acceptType;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

}
