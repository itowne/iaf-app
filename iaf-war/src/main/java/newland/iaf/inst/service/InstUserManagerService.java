package newland.iaf.inst.service;

import java.util.List;

import newland.iaf.inst.model.InstUser;

/**
 * 机构用户管理Service操作服务
 * @author lindaqun
 *
 */
public interface InstUserManagerService {
	
	/**
	 * 分页查询用户信息，条件信息后续完善
	 * @return 
	 * @throws Exception
	 */
	List queryInstUserByCon() throws Exception;
	/**
	 * 增加机构用户的信息，如果发现错误抛出对应错误信息
	 * @param instuser 机构用户
	 * @return Exception 一些可能的异常信息
	 */
	void addInstUser(InstUser instuser) throws Exception;
	
	/**
	 * 根据机构用户ID查询机构用户信息
	 * @param id 机构用户ID
	 * @return InstUser 查询到的机构用户
	 * @throws Exception 一些可能的异常信息
	 */
	InstUser findInstUser(Long id) throws Exception;
	
	/**
	 * 修改机构用户信息，异常情况抛出对应的错误信息
	 * @param instuser 机构用户
	 * @throws Exception 一些可能的异常信息
	 */
	void updateInstUser(InstUser instuser) throws Exception;
	
	/**
	 * 将机构用户置为不可用状态，异常情况抛出对应的错误信息
	 * @param instuser 机构用户
	 * @throws Exception 一些可能的异常信息
	 */
	void disableInstUser(InstUser instuser) throws Exception;
	
	/**
	 * 删除机构用户信息，异常情况抛出对应的错误信息
	 * @param instuser 机构用户
	 * @throws Exception 一些可能的异常信息
	 */
	void deleteInstUser(InstUser instuser) throws Exception;
}
