package newland.iaf.inst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newland.iaf.inst.dao.InstUserDao;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstUserManagerService;

/**
 * 机构用户管理Service操作服务
 * 
 * @author lindaqun
 * 
 */
@Service("instUserManagerService")
public class InstUserManagerServiceImpl implements InstUserManagerService {

	/** 机构用户信息操作DAO接口 **/
	@Resource(name = "com.newland.iaf.instUserDao")
	private InstUserDao instUserDao;

	@Override
	public List queryInstUserByCon() throws Exception {
		// TODO Auto-generated method stub
		// 根据具体的条件实现分页查询功能
		return null;
	}

	@Override
	@Transactional
	public void addInstUser(InstUser instuser) throws Exception {
		// TODO Auto-generated method stub
		// 根据需要，设置对应的信息，完成后保存
		this.instUserDao.saveInstUser(instuser);
	}

	@Override
	public InstUser findInstUser(Long id) throws Exception {
		return (InstUser) this.instUserDao.getInstUser(id);
	}

	@Override
	public void updateInstUser(InstUser instuser) throws Exception {
		// TODO Auto-generated method stub
		// 可以根据实际的需要设置对应需要修改的信息，最后保存到数据库
		this.instUserDao.updateInstUser(instuser);
	}

	@Override
	public void disableInstUser(InstUser instuser) throws Exception {
		// TODO Auto-generated method stub
		// 设置机构用户的状态为不可用状态

		// 设置完成后保存实现对应的修改
		this.instUserDao.updateInstUser(instuser);
	}

	@Override
	public void deleteInstUser(InstUser instuser) throws Exception {
		// TODO Auto-generated method stub
		// 可以根据需要增加对应的判断信息，如果允许最后实现删除
		// this.instUserDao.deleteInstUser(instuser);

	}

}
