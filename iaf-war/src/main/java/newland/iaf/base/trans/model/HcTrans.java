package newland.iaf.base.trans.model;

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

/**
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_hc_trans")
public class HcTrans {

	/**
	 * 
	 */
	@Id
	@TableGenerator(name = "i_hc_trans", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_hc_trans")
	@Column(name = "i_hc_trans")
	private Long ihcTrans;

	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	@Column(name = "remote_file", nullable = true, updatable = false, length = 50)
	private String remoteFile;

	@Column(name = "local_file", length = 50)
	private String localFile;

	@Column(name = "size", nullable = false, updatable = false)
	private Long size;

	@Column(name = "sha1", nullable = false, updatable = false, length = 28)
	private String sha1;

	@Column(name = "export_time", updatable = false)
	private Date exportTime;

	@Column(name = "rec_num")
	private Integer recNum;

	@Column(name = "realRecNum")
	private Integer realRecNum;

	/**
	 * 插入记录数
	 */
	@Column(name = "insert_num")
	private Integer insertNum;

	@Column(name = "trans_type", nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private HcTransType transType;

	/**
	 * 
	 */
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private HcTransStatus status;

	@Column(name = "upd_time")
	private Date updTime;

	@Column(name = "memo", length = 200)
	private String memo;

	public Long getIhcTrans() {
		return ihcTrans;
	}

	public void setIhcTrans(Long ihcTrans) {
		this.ihcTrans = ihcTrans;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public HcTransType getTransType() {
		return transType;
	}

	public void setTransType(HcTransType transType) {
		this.transType = transType;
	}

	public HcTransStatus getStatus() {
		return status;
	}

	public void setStatus(HcTransStatus status) {
		this.status = status;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getSha1() {
		return sha1;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}

	public Integer getRecNum() {
		return recNum;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}

	public Integer getRealRecNum() {
		return realRecNum;
	}

	public void setRealRecNum(Integer realRecNum) {
		this.realRecNum = realRecNum;
	}

	public Integer getInsertNum() {
		return insertNum;
	}

	public void setInsertNum(Integer insertNum) {
		this.insertNum = insertNum;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRemoteFile() {
		return remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}

}
