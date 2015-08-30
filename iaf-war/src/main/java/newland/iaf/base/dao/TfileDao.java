package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.TFile;

/**
 * TFile操作DAO
 * @author lindaqun
 *
 */
public interface TfileDao {
	
	/**
	 * 保存上传文件信息
	 * @param tfile
	 * @throws Exception
	 */
	void saveTfile(TFile tfile) throws Exception;
	
	/**
	 * 修改上传文件信息
	 * @param tfile
	 * @throws Exception
	 */
	void updateTfile(TFile tfile) throws Exception;
	
	/**
	 * 根据主键ID查找上传文件信息
	 * @param ifile
	 * @return
	 * @throws Exception
	 */
	TFile getTFileByIfile(Long ifile) throws Exception;
	
	/**
	 * 根据文件UUIDNAME查找上传文件信息 
	 * @param uuidName
	 * @return
	 * @throws Exception
	 */
	TFile getTFileByUuidname(String uuidName) throws Exception;

	/**
	 * 条件查询
	 * @param tfile
	 */
	List<TFile> queryBy(TFile tfile);
	/**
	 * 删除文件
	 */
	public void deleteByIfile(Long iFile);
	
	TFile quueryFreeze(Long iloanOrd, String type, String periods);
	
}
