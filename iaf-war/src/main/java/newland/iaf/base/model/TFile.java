package newland.iaf.base.model;

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

import newland.iaf.base.model.dict.AllowType;
import newland.iaf.base.model.dict.MetaType;
import newland.iaf.base.model.dict.YesOrNoType;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "t_file", uniqueConstraints = { @UniqueConstraint(columnNames = "uuid_name") })
@org.hibernate.annotations.Table(appliesTo = "t_file", indexes = {
		@Index(name = "idx_file1", columnNames = { "uuid_name" }),
		@Index(name = "idx_file2", columnNames = { "i_merch" }),
		@Index(name = "idx_file3", columnNames = { "i_merch_user" }),
		@Index(name = "idx_file4", columnNames = { "i_inst_user" }) })
public class TFile {

	@Id
	@TableGenerator(name = "i_file", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_file")
	@Column(name = "i_file")
	private Long ifile;

	/**
	 * 上传商户
	 */
	@Column(name = "i_merch")
	private Long imerch;

	/**
	 * 上传商户用户
	 */
	@Column(name = "i_merch_user")
	private Long imerchUser;

	/**
	 * 上传 机构
	 */
	@Column(name = "i_inst")
	private Long iinst;

	/**
	 * 上传机构用户
	 */
	@Column(name = "i_inst_user")
	private Long iinstuser;

	/**
	 * html文件类型
	 */
	//@Enumerated(EnumType.STRING)
	@Column(name = "meta_type")
	private String metaType;

	/**
	 * 文件名
	 */
	@Column(name = "upload_name")
	private String uploadName;

	/**
	 * 扩展名
	 */
	@Column(name = "ext_name")
	private String extName;
	
	@Column(name="sha1")
	private String sha1;

	@Enumerated(EnumType.STRING)
	@Column(name = "file_type")
	private FileType fileType;

	/**
	 * 存放路径
	 */
	@Column(name = "path")
	private String path;

	@Column(name = "uuid_name")
	private String uuidName;
	
	@Column(name = "i_loanOrd")
	private Long iLoanOrd;
	@Column(name = "periods")
	private String periods;
	

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Long getIfile() {
		return ifile;
	}

	public void setIfile(Long ifile) {
		this.ifile = ifile;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public Long getImerchUser() {
		return imerchUser;
	}

	public void setImerchUser(Long imerchUser) {
		this.imerchUser = imerchUser;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public Long getIinstuser() {
		return iinstuser;
	}

	public void setIinstuser(Long iinstuser) {
		this.iinstuser = iinstuser;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getMetaType() {
		return metaType;
	}

	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}

	public Long getiLoanOrd() {
		return iLoanOrd;
	}

	public void setiLoanOrd(Long iLoanOrd) {
		this.iLoanOrd = iLoanOrd;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

}
