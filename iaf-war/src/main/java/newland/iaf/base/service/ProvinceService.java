package newland.iaf.base.service;

import java.util.List;
import java.util.Map;

import newland.iaf.base.model.Province;

/**
 * 
 * @author rabbit
 * 
 */
public interface ProvinceService {

	Map<String, String> getProvinceMap();

	/**
	 * 取省一级区域
	 * 
	 * @return
	 */
	Map<String, String> getProvince();

	/**
	 * 根据指定的code取的下一级区域
	 * 
	 * @param code
	 * @return
	 */
	Map<String, String> getProvince(String provCode);
	
	List<Province> getProvince2(String provCode);
	
	/**
	 * 根据城市码获取省+城市中文显示
	 * @param code
	 * @return
	 */
	List<Province> getProvinceAndCityName(String provCode);
	
	Province queryByProvCode(String provCode);
}
