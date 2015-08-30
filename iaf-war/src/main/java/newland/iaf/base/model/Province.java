package newland.iaf.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_province")
public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "prov_code", length = 6, nullable = false, updatable = false)
	private String code;

	@Column(name = "pre_prov_code", length = 6, updatable = false, nullable = false)
	private String preProvCode;

	@Column(name = "name", length = 30, nullable = false, updatable = false)
	private String name;

	@Column(name = "close_flag", nullable = false)
	private Boolean closeFlag;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreProvCode() {
		return preProvCode;
	}

	public void setPreProvCode(String preProvCode) {
		this.preProvCode = preProvCode;
	}

	public Boolean getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(Boolean closeFlag) {
		this.closeFlag = closeFlag;
	}

}
