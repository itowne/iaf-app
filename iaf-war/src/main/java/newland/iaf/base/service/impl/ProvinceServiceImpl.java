package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.dao.ProvinceDao;
import newland.iaf.base.model.Province;
import newland.iaf.base.service.ProvinceService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("provinceService")
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Resource(name = "provinceDao")
	private ProvinceDao provinceDao;

	@Override
	@Cacheable(value = "iafcache", key = "'provinceMap'")
	public Map<String, String> getProvinceMap() {
		List<Province> list = this.provinceDao.queryAll();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Province pro : list) {
				map.put(pro.getCode(), pro.getName());
			}
		}
		return map;
	}

	public Map<String, String> getProvince() {
		return getProvince("000000");
	}

	public Map<String, String> getProvince(String code) {
		List<Province> list = this.provinceDao.findByPreProvCode(code);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (CollectionUtils.isNotEmpty(list)) {
			map.put("1", "全国");
			for (Province pro : list) {
				map.put(pro.getCode(), pro.getName());
			}
		}
		return map;
	}

	@Override
	public List<Province> getProvince2(String provCode) {
		return this.provinceDao.findByPreProvCode(provCode);
	}


	public List<Province> getProvinceAndCityName(String provCode) {
		List<Province> provinceList = new ArrayList<Province>();
		Province city = provinceDao.queryByCityCode(provCode);
		provinceList.add(city);
		while (city != null
				&& !StringUtils.equals("000000", city.getPreProvCode())) {
			city = provinceDao.queryByCityCode(city.getPreProvCode());
			if(city!=null){
				provinceList.add(city);
			}
		}
		return provinceList;
	}

	@Override
	public Province queryByProvCode(String provCode) {
		// TODO Auto-generated method stub
		return provinceDao.queryByCityCode(provCode);
	}
}
