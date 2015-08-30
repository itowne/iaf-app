package newland.iaf.base.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.service.TransMsgService;
@Service("transMsgService")
@Transactional(readOnly = false)
public class TransMsgServiceImpl implements TransMsgService {

	@Resource(name="transMsgDao")
	private TransMsgDao transMsgDao;

	@Override
	public TransMsg findById(String orderNo) {
		// TODO Auto-generated method stub
		return this.transMsgDao.findById(orderNo);
	}

}
