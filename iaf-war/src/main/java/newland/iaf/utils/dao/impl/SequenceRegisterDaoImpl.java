package newland.iaf.utils.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.utils.dao.SequenceRegisterDao;
import newland.iaf.utils.model.SequenceRegister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sequenceRegisterDao")
@Transactional(readOnly = false)
public class SequenceRegisterDaoImpl extends BaseDao implements SequenceRegisterDao{



	@SuppressWarnings("unchecked")
	@Override
	public SequenceRegister findBySnoType(String snoType) {
		List<SequenceRegister>  l = this.getHibernateDao().find("FROM " 
				+ SequenceRegister.class.getName() +
				" where sno_type = ?", new Object[]{snoType});
		if(l == null || l.size()<=0)
			return null;
		
		return l.get(0); 
	}

	@Override
	public void lock(Integer snoId) {
		this.getHibernateDao().executeUpdate("update " 
				+ SequenceRegister.class.getName() +
				" r set r.maxVal = r.maxVal where r.snoId = ?",snoId);
	}

	@Override
	public SequenceRegister save(SequenceRegister register) {
		this.getSession().save(register);
		return register;
	}

	@Override
	public void update(SequenceRegister register) {
		this.getSession().update(register);
		
	}

	@Override
	public SequenceRegister findById(Integer snoId) {
		
		return (SequenceRegister)this.getHibernateDao().getFirst("from " + SequenceRegister.class.getName() + 
				" seq where seq.snoId = ?", snoId);
	}




	
	

}
