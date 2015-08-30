package newland.iaf.merch.dao;

import java.util.List;

import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchInfoPermission;

public interface MerchInfoPermissionDao {
	
	void save(MerchInfoPermission perm);
	
	void update(MerchInfoPermission perm);
	
	List<MerchInfoPermission> query(MerchInfoPermission perm,Merch merch);

}
