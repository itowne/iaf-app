package newland.iaf.base.dao;

import org.hibernate.Criteria;

public interface CriteriaExecutor<T>{
	
	T execute(Criteria expr);

}
