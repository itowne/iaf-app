package newland.iaf.inst.model;

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

import newland.iaf.base.model.dict.GeneralStatus;

/**
 * 公告
 * 
 * @author Mr.Towne
 * 
 */
@Entity
@Table(name = "t_inst_notice")
public class InstNotice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6215754769402743763L;

	@Id
	@Column(name = "i_inst_notice", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_notice", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_notice")
	private Long iinstNotice;

	@Column(name = "title", nullable = false, updatable = true, length = 50)
	private String title;
	
	@Column (name = "content")
	private String content;
	
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", updatable = true)
	private Date updTime;
	
	@Column(name = "status", nullable = false)
	@Enumerated
	private GeneralStatus status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getIinstNotice() {
		return iinstNotice;
	}

	public void setIinstNotice(Long iinstNotice) {
		this.iinstNotice = iinstNotice;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public GeneralStatus getStatus() {
		return status;
	}

	public void setStatus(GeneralStatus status) {
		this.status = status;
	}
}
