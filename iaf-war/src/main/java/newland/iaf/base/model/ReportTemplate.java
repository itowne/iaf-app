package newland.iaf.base.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.dict.PagePosition;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
@Entity
@Table (name = "rpt_temp")
public class ReportTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "i_rpt_temp", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_rpt_temp")
	private Long ireportTemplate;
	
	/**
	 * 页面定义
	 */
	@Column (name = "position")
	private PagePosition position;
	
	/**
	 * 模板文件名
	 */
	@Column (name = "file_name", length = 200)
	private String fileName;
	
	
	@Column (name = "gen_time", nullable = false)
	private Date genTime;
	
	@Column (name = "upd_time", nullable = false)
	private Date updTime;
	
	@Transient
	public boolean isDefault(){
		if (this.ireportTemplate == null) return true;
		return false;
	}

	public Long getIreportTemplate() {
		return ireportTemplate;
	}

	public void setIreportTemplate(Long ireportTemplate) {
		this.ireportTemplate = ireportTemplate;
	}

	public PagePosition getPosition() {
		return position;
	}

	public void setPosition(PagePosition position) {
		this.position = position;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	
	@Transient
	public InputStream openInputStream(String prefix) throws Throwable{
		if (this.isDefault()){
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			Resource resource = resourcePatternResolver.getResource(prefix + "/" + this.fileName);
			if (resource.isReadable()){
				return resource.getInputStream();
			}
			throw new RuntimeException("打开默认模板文件出错! 文件名：" + prefix + "/" + this.fileName);
		}else{
			File file = new File(this.fileName);
			FileInputStream in = new FileInputStream(file);
			return in;
		}
	}

}
