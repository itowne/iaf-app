package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.Province;

/**
 * 
 * @author rabbit
 * 
 */
public interface ProvinceDao {

	void save(Province province);

	void update(Province provincd);

	List<Province> queryAll();

	List<Province> findByPreProvCode(String preCodeCode);
	
	Province queryByCityCode(String cityCode);

}
