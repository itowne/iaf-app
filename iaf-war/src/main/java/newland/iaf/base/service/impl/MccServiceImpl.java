package newland.iaf.base.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newland.iaf.base.dao.MccDao;
import newland.iaf.base.model.Province;
import newland.iaf.base.service.MccService;
import newland.iaf.inst.model.Mcc;

@Service("mccService")
@Transactional
public class MccServiceImpl implements MccService{

	@Resource(name = "mccDao")
	private MccDao mccDao;

	@Override
	public Map<String, String> getFirstBussin() {
		// TODO Auto-generated method stub
		return getFirstBussin("0");
	}
	
	public Map<String, String> getFirstBussin(String code) {
		List<Mcc> list = this.mccDao.findByTypeCode(code);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Mcc mcc : list) {
				map.put(mcc.getId(), mcc.getName());
			}
		}
		return map;
	}

	@Override
	public List<Mcc> getSecondBussin(String secondCode) {
		return this.mccDao.findByTypeCode(secondCode);
	}

	@Override
	public Mcc queryByName(String name) {
		// TODO Auto-generated method stub
		return this.mccDao.queryByName(name);
	}

	@Override
	public Mcc queryById(String id) {
		// TODO Auto-generated method stub
		return this.mccDao.queryById(id);
	}
}
