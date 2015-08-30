package newland.iaf.merch.service.impl;

import java.util.List;

import javax.annotation.Resource;

import newland.iaf.merch.dao.MerchUsedPayerDao;
import newland.iaf.merch.model.MerchUsedPayer;
import newland.iaf.merch.service.MerchUsedPayerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("merchUsedPayerService")
@Transactional
public class MerchUsedPayerServiceImpl implements MerchUsedPayerService {
	
	@Resource(name = "merchUsedPayerDao")
	private MerchUsedPayerDao merchUsedPayerDao;


	@Override
	public void saveMerchUsedPayer(MerchUsedPayer merchUsedPayer)
			throws Exception {
		List<MerchUsedPayer> ls = this.merchUsedPayerDao.listMerchUsedPayerByImerch(merchUsedPayer.getImerch());
		//先判断是否已经有该付款人
		for(MerchUsedPayer payer:ls){
			if(payer.getPayerCardNo().equals(merchUsedPayer.getPayerCardNo())){
				this.merchUsedPayerDao.deleteMerchUsedPayer(payer);
			}
		}
		this.merchUsedPayerDao.saveMerchUsedPayer(merchUsedPayer);

	}

	@Override
	public List<MerchUsedPayer> listMerchUsedPayerByImerch(Long imerch)
			throws Exception {
		return this.merchUsedPayerDao.listMerchUsedPayerByImerch(imerch);
	}

}
