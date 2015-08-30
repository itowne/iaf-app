package newland.iaf.base.service;

import java.util.List;
import java.util.Map;

import newland.iaf.inst.model.Mcc;


public interface MccService {

	/**
	 * 
	 * 
	 * @param code
	 * @return
	 */
	Map<String, String> getFirstBussin();
	
	List<Mcc> getSecondBussin(String secondCode);
	
	Mcc queryByName(String name);
	
	Mcc queryById(String id);
}
