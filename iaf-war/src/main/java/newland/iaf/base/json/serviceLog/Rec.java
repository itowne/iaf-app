package newland.iaf.base.json.serviceLog;

import java.util.Date;

import newland.iaf.base.format.BeanField;

/**
 * 
 * @author rabbit
 * 
 */
public class Rec {
	@BeanField
	private String subject;

	@BeanField
	private Date date;

	@BeanField
	private String content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
