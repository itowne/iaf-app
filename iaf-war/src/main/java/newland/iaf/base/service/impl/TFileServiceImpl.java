package newland.iaf.base.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.base.dao.TfileDao;
import newland.iaf.base.model.FileType;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.service.TFileService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 上传文件信息Service
 * 
 * @author lindaqun
 * 
 */
@Service("tfileService")
@Transactional
public class TFileServiceImpl implements TFileService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "tfileDao")
	private TfileDao tfileDao;
	
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	@Resource(name="com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;
	
	@Override
	public void saveTfile(TFile tfile) throws Exception {
		this.tfileDao.saveTfile(tfile);
	}

	@Override
	public void updateTfile(TFile tfile) throws Exception {
		this.tfileDao.updateTfile(tfile);
	}

	@Override
	public TFile getTFileByIfile(Long ifile) throws Exception {
		return this.tfileDao.getTFileByIfile(ifile);
	}

	@Override
	public TFile getTFileByUuidname(String uuidName) throws Exception {
		return this.tfileDao.getTFileByUuidname(uuidName);
	}

	@Override
	public List<TFile> queryBy(Long imerch, FileType type) {
		TFile tfile = new TFile();
		tfile.setImerch(imerch);
		tfile.setFileType(type);
		return this.tfileDao.queryBy(tfile);

	}

	public List<TFile> queryByImerch(Long imerch) {
		TFile tfile = new TFile();
		tfile.setImerch(imerch);
		return this.tfileDao.queryBy(tfile);

	}
	
	@Override
	public List<TFile> queryInstFile(Long iinst, FileType type) {
		TFile tfile = new TFile();
		tfile.setIinst(iinst);
		tfile.setFileType(type);
		return this.tfileDao.queryBy(tfile);
	}

	@Override
	public Long CommonFileUpload(File file, String fileType, String fileName,
			String metaType, MerchSession merchSession, InstSession instSession)
			throws Exception {

		TFile tfile = new TFile();
		String[] names = fileName.split("\\.");

		// 系统参数
		String remotePath = "";
		if (fileType.equals("instLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instLogo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchLogo 参数获取出错!");
			}
		} else if (fileType.equals("loanPdtLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("loanPdtLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchRiskReport")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchRiskReport 参数获取出错!");
			}
		} else if (fileType.equals("merchFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 参数获取出错!");
			}
		} else if (fileType.equals("instFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instBaseInfo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instFile 参数获取出错!");
			}
		}else if(fileType.equals("YYZZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile  营业执照参数获取出错!");
			}
		}else if(fileType.equals("SWDJZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 税务登记证参数获取出错!");
			}
		}else if(fileType.equals("ZZJGDMZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 组织机构证参数获取出错!");
			}
		}else if(fileType.equals("PdtLogoPage")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("PdtLogoPage参数获取出错!");
			}
		}else if(fileType.equals("debitLogo")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("debitLogo参数获取出错!");
			}
		}else if(fileType.equals("FRSFZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("FRSFZ参数获取出错!");
			}
		}

		if (StringUtils.isBlank(remotePath)) {
			if(fileType.equals("YYZZ")||fileType.equals("SWDJZ")||fileType.equals("ZZJGDMZ")){
				remotePath = IafApplication.getApplicationPath() + "/" + "merchFile";
			}else{
				remotePath = IafApplication.getApplicationPath() + "/" + fileType;
			}
			FileUtils.forceMkdir(new File(remotePath));
			logger.warn("文件上传路径未配置使用默认路径：" + remotePath);
		}

		// 文件路径
		String uuidName = UUID.randomUUID().toString();
		String path = IafApplication.renderPathSuffix(remotePath);
		File newFile = new File(path + uuidName + "." + names[1]);
		FileUtils.copyFile(file, newFile);

		if (merchSession != null) {
			tfile.setImerch(merchSession.getMerch().getImerch());
			tfile.setImerchUser(merchSession.getMerchUser().getImerchUser());
		}
		if (instSession != null) {
			tfile.setIinst(instSession.getInst().getIinst());
			if (instSession.getInstUsr() != null) {
				tfile.setIinstuser(instSession.getInstUsr().getIinstUser());
			}
		}
		tfile.setExtName(names[1]);
		tfile.setFileType(FileType.valueOf(fileType));
		tfile.setMetaType(metaType);
		tfile.setPath(remotePath);
		tfile.setUploadName(fileName);
		tfile.setUuidName(uuidName);
		this.saveTfile(tfile);
		if (fileType.equals("instLogo")) {
			Inst inst=instService.queryByInstId(instSession.getInst().getInstId());
			inst.setLogoIfile(tfile.getIfile());
			instService.updateInst(inst,null,null);
		}
		if (fileType.equals("merchLogo")) {
			Merch merch=merchService.findMerchById(merchSession.getMerch().getImerch());
			merch.setLogoIfile(tfile.getIfile());
			merchService.updateMerch(merch);
		}
		return tfile.getIfile();
	}

	public Long CommonFileUploadById(File file, String fileType, String fileName,
			String metaType, Long imerch, Long iinst)
			throws Exception {

		TFile tfile = new TFile();
		String[] names = fileName.split("\\.");

		// 系统参数
		String remotePath = "";
		if (fileType.equals("instLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instLogo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchLogo 参数获取出错!");
			}
		} else if (fileType.equals("loanPdtLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("loanPdtLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchRiskReport")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchRiskReport 参数获取出错!");
			}
		} else if (fileType.equals("merchFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 参数获取出错!");
			}
		} else if (fileType.equals("instFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instBaseInfo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instFile 参数获取出错!");
			}
		}else if(fileType.equals("YYZZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile  营业执照参数获取出错!");
			}
		}else if(fileType.equals("SWDJZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 税务登记证参数获取出错!");
			}
		}else if(fileType.equals("ZZJGDMZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 组织机构证参数获取出错!");
			}
		}else if(fileType.equals("PdtLogoPage")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("PdtLogoPage参数获取出错!");
			}
		}else if(fileType.equals("debitLogo")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("debitLogo参数获取出错!");
			}
		}else if(fileType.equals("FRSFZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("FRSFZ参数获取出错!");
			}
		}

		if (StringUtils.isBlank(remotePath)) {
			if(fileType.equals("YYZZ")||fileType.equals("SWDJZ")||fileType.equals("ZZJGDMZ")){
				remotePath = IafApplication.getApplicationPath() + "/" + "merchFile";
			}else{
				remotePath = IafApplication.getApplicationPath() + "/" + fileType;
			}
			FileUtils.forceMkdir(new File(remotePath));
			logger.warn("文件上传路径未配置使用默认路径：" + remotePath);
		}

		// 文件路径
		String uuidName = UUID.randomUUID().toString();
		String path = IafApplication.renderPathSuffix(remotePath);
		File newFile = new File(path + uuidName + "." + names[1]);
		FileUtils.copyFile(file, newFile);
		
		if (imerch != null) {
			tfile.setImerch(imerch);
			MerchUser mu = merchService.queryUserByImerch(imerch);
			if(mu!=null){
				tfile.setImerchUser(mu.getImerchUser());
			}
		}
		if (iinst != null) {
			tfile.setIinst(iinst);
			InstUser iu = instService.queryUserByIinst(iinst);
			if (iu!= null) {
				tfile.setIinstuser(iu.getIinstUser());
			}
		}
		tfile.setExtName(names[1]);
		tfile.setFileType(FileType.valueOf(fileType));
		tfile.setMetaType(metaType);
		tfile.setPath(remotePath);
		tfile.setUploadName(fileName);
		tfile.setUuidName(uuidName);
		this.saveTfile(tfile);
		if (fileType.equals("instLogo")) {
			Inst inst = new Inst();
			if(iinst!=null){
				inst = instService.findInstById(iinst);
			}
			//Inst inst=instService.queryByInstId(instSession.getInst().getInstId());
			inst.setLogoIfile(tfile.getIfile());
			instService.updateInst(inst,null,null);
		}
		if (fileType.equals("merchLogo")) {
			Merch merch=merchService.findMerchById(imerch);
			merch.setLogoIfile(tfile.getIfile());
			merchService.updateMerch(merch);
		}
		return tfile.getIfile();
	}
	
	@Override
	public void deleteByIfile(Long iFile) {
		// TODO Auto-generated method stub
		this.tfileDao.deleteByIfile(iFile);
	}

	@Override
	public Long CommonFileUploadForiloanOrd(File file, String fileType,
			String fileName, String metaType, Long iloanOrd, String peroids,
			Long iinst) throws Exception {
		TFile tfile = new TFile();
		String[] names = fileName.split("\\.");

		// 系统参数
		String remotePath = "";
		if (fileType.equals("instLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instLogo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchLogo 参数获取出错!");
			}
		} else if (fileType.equals("loanPdtLogo")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("loanPdtLogo 参数获取出错!");
			}
		} else if (fileType.equals("merchRiskReport")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchRiskReport 参数获取出错!");
			}
		} else if (fileType.equals("merchFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 参数获取出错!");
			}
		} else if (fileType.equals("instFile")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instBaseInfo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("instFile 参数获取出错!");
			}
		}else if(fileType.equals("YYZZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile  营业执照参数获取出错!");
			}
		}else if(fileType.equals("SWDJZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 税务登记证参数获取出错!");
			}
		}else if(fileType.equals("ZZJGDMZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("merchFile 组织机构证参数获取出错!");
			}
		}else if(fileType.equals("PdtLogoPage")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.loanPdtLogo);
			if (map.get(SysParamName.FTP_LOCAL_PATH) != null) {
				remotePath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("PdtLogoPage参数获取出错!");
			}
		}else if(fileType.equals("debitLogo")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchLogo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("debitLogo参数获取出错!");
			}
		}else if(fileType.equals("FRSFZ")){
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.merchBaseInfo);
			if (map.get(SysParamName.MERCH_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("FRSFZ参数获取出错!");
			}
		}else if (fileType.equals("FREEZE")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instBaseInfo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("FREEZE 参数获取出错!");
			}
		}else if (fileType.equals("UNFREEZE")) {
			Map<SysParamName, SysParam> map = sysParamService
					.findSysParamMapByType(SysParamType.instBaseInfo);
			if (map.get(SysParamName.INST_UPLOAD_PATH) != null) {
				remotePath = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
			}
			if (map == null || remotePath == null) {
				throw new Exception("UNFREEZE 参数获取出错!");
			}
		}
		
		
		if (StringUtils.isBlank(remotePath)) {
			if(fileType.equals("YYZZ")||fileType.equals("SWDJZ")||fileType.equals("ZZJGDMZ")){
				remotePath = IafApplication.getApplicationPath() + "/" + "merchFile";
			}else{
				remotePath = IafApplication.getApplicationPath() + "/" + fileType;
			}
			FileUtils.forceMkdir(new File(remotePath));
			logger.warn("文件上传路径未配置使用默认路径：" + remotePath);
		}

		// 文件路径
		String uuidName = UUID.randomUUID().toString();
		String path = IafApplication.renderPathSuffix(remotePath);
		File newFile = new File(path + uuidName + "." + names[1]);
		FileUtils.copyFile(file, newFile);
		
		
		if (iinst != null) {
			tfile.setIinst(iinst);
			InstUser iu = instService.queryUserByIinst(iinst);
			if (iu!= null) {
				tfile.setIinstuser(iu.getIinstUser());
			}
		}
		tfile.setExtName(names[1]);
		tfile.setFileType(FileType.valueOf(fileType));
		tfile.setMetaType(metaType);
		tfile.setPath(remotePath);
		tfile.setUploadName(fileName);
		tfile.setUuidName(uuidName);
		tfile.setiLoanOrd(iloanOrd);
		tfile.setPeriods(peroids);
		this.saveTfile(tfile);
		if (fileType.equals("instLogo")) {
			Inst inst = new Inst();
			if(iinst!=null){
				inst = instService.findInstById(iinst);
			}
			//Inst inst=instService.queryByInstId(instSession.getInst().getInstId());
			inst.setLogoIfile(tfile.getIfile());
			instService.updateInst(inst,null,null);
		}
		
		return tfile.getIfile();
	}

	@Override
	public TFile quueryFreeze(Long iloanOrd, String type, String periods)
			throws Exception {
		TFile tf = new TFile();
		return this.tfileDao.quueryFreeze(iloanOrd, type, periods);
	}

}
