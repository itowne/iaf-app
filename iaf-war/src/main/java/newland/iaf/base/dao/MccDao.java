package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.inst.model.Mcc;

public interface MccDao {

	List<Mcc> queryAll();

	List<Mcc> findByTypeCode(String preCodeCode);
	
	Mcc queryByName(String name);
	
	Mcc queryById(String id);
}
