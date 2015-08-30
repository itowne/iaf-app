package newland.iaf.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;

import org.hibernate.annotations.Index;

/**
 * 系统参数表
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_sys_param", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"param_type", "param_name" }) })
@org.hibernate.annotations.Table(appliesTo = "t_sys_param", indexes = {
		@Index(name = "idx_sys_param1", columnNames = { "param_type" }),
		@Index(name = "idx_sys_param2", columnNames = { "param_name" }) })
public class SysParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6442560329902386735L;

	@Id
	@TableGenerator(name = "i_sys_param", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_sys_param")
	@Column(name = "i_sys_param", nullable = false, updatable = false)
	private Long isysParm;

	/**
	 * 竟投产品编号
	 */
	@Column(name = "param_type", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private SysParamType paramType;

	/**
	 * 参数名称
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "param_name", nullable = false, length = 30)
	private SysParamName paramName;

	/**
	 * 值
	 */
	@Column(name = "value", nullable = false, length = 100)
	private String value;

	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false)
	private Date genTime;

	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", nullable = false)
	private Date updTime;

	/**
	 * 版本号
	 */
	@Version
	private Long version;

	public Long getIsysParm() {
		return isysParm;
	}

	public void setIsysParm(Long isysParm) {
		this.isysParm = isysParm;
	}

	public SysParamName getParamName() {
		return paramName;
	}

	public void setParamName(SysParamName paramName) {
		this.paramName = paramName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public SysParamType getParamType() {
		return paramType;
	}

	public void setParamType(SysParamType paramType) {
		this.paramType = paramType;
	}

}
