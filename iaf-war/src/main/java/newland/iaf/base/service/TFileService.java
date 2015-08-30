package newland.iaf.base.service;

import java.io.File;
import java.util.List;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;

/**
 * 上传文件信息Service接口
 * @author lindaqun
 *
 */
public interface TFileService {
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
	
	TFile quueryFreeze(Long iloanOrd,String type,String periods) throws Exception;
	
	/**
	 * 根据文件UUIDNAME查找上传文件信息 
	 * @param uuidName
	 * @return
	 * @throws Exception
	 */
	TFile getTFileByUuidname(String uuidName) throws Exception;

	/**
	 * 根据商户号与类型查询文件信息
	 * @param imerch
	 * @return
	 */
	List<TFile> queryBy(Long imerch, FileType type);
	
	/**
	 * 根据机构ID和文件类型查询文件信息
	 * @param iinst
	 * @param type
	 * @return
	 */
	public List<TFile> queryInstFile(Long iinst,FileType type);
	
	/**
	 * 上传文件通用方法
	 * @param file
	 * @param filetype
	 * @param extName
	 * @param metaType
	 * @param sysParamType
	 * @param sysParamName
	 * @param merchSession
	 * @param instSession
	 */
	public Long CommonFileUpload(File file, String filetype, String fileName,
			String metaType,MerchSession merchSession,InstSession instSession)throws Exception;
	/**
	 * 删除文件
	 */
	public void deleteByIfile(Long iFile);
	
	public List<TFile> queryByImerch(Long imerch);
	
	public Long CommonFileUploadById(File file, String fileType, String fileName,
			String metaType, Long imerch, Long iinst)
			throws Exception;
	
	public Long CommonFileUploadForiloanOrd(File file, String fileType, String fileName,
			String metaType,Long iloanOrd,String peroids, Long iinst)
			throws Exception;
}
