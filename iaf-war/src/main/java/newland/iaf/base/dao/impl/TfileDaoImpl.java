package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.TfileDao;
import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;

import org.springframework.stereotype.Service;

@Service("tfileDao")
public class TfileDaoImpl extends BaseDao implements TfileDao {
	/**
	 * 保存上传文件信息
	 * @param tfile
	 * @throws Exception
	 */
	public void saveTfile(TFile tfile) throws Exception{
		this.getSession().save(tfile);
	}
	
	/**
	 * 修改上传文件信息
	 * @param tfile
	 * @throws Exception
	 */
	public void updateTfile(TFile tfile) throws Exception{
		this.getSession().update(tfile);
	}
	
	/**
	 * 根据主键ID查找上传文件信息
	 * @param ifile
	 * @return
	 * @throws Exception
	 */
	public TFile getTFileByIfile(Long ifile) throws Exception{
		TFile file = null;
		Object o = this.getSession().get(TFile.class, ifile);
		if(o != null)file = (TFile)o;
		return file;
	}
	
	/**
	 * 根据文件UUIDNAME查找上传文件信息 
	 * @param uuidName
	 * @return
	 * @throws Exception
	 */
	public TFile getTFileByUuidname(String uuidName) throws Exception{
		TFile tfile = new TFile();
		tfile.setUuidName(uuidName);
		return this.getHibernateDao().getFirstOneByExample(tfile);
	}

	@Override
	public List<TFile> queryBy(TFile tfile) {
		return this.getHibernateDao().findByExample(tfile);
	}

	@Override
	public void deleteByIfile(Long iFile) {
		// TODO Auto-generated method stub
		this.getHibernateDao().deleteById(TFile.class, iFile);
	}

	@Override
	public TFile quueryFreeze(Long iloanOrd, String type, String periods) {
		TFile tf = new TFile();
		tf.setiLoanOrd(iloanOrd);
		tf.setFileType(FileType.valueOf(type));
		tf.setPeriods(periods);
		return this.getHibernateDao().getFirstOneByExample(tf);
	}
}
