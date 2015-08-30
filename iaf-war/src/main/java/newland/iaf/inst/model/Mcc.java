package newland.iaf.inst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户行业类别
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_mcc")
public class Mcc {

	@Id
	@Column(name = "id", columnDefinition = "varchar(8)", nullable = false, updatable = false)
	private String id;

	@Column(name = "name", columnDefinition = "varchar(45)", nullable = false, updatable = false)
	private String name;

	@Column(name = "type", columnDefinition = "varchar(5)", updatable = false)
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
