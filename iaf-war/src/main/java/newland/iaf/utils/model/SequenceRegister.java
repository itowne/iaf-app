package newland.iaf.utils.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "t_sno_cfg")
@org.hibernate.annotations.Table(appliesTo = "t_sno_cfg", indexes = { @Index(name = "idx_sno_cfg1", columnNames = { "sno_type" }) })
public class SequenceRegister implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "snoid_gen" )
	@GenericGenerator(name="snoid_gen",strategy="native")
	@Column(name = "sno_id")
	private Integer snoId;
	@Column(name = "sno_type")
	private String snoType;
	@Column(name = "max_val")
	private Integer maxVal;

	
	public Integer getSnoId() {
		return snoId;
	}

	public void setSnoId(Integer snoId) {
		this.snoId = snoId;
	}
	
	
	public String getSnoType() {
		return snoType;
	}

	public void setSnoType(String snoType) {
		this.snoType = snoType;
	}
	
	
	public Integer getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

 
	
	
}
