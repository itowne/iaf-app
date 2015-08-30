package newland.iaf.base.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import newland.base.util.PropertiesReader;
import newland.iaf.IafApplication;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class HicardPayCfg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 金掌柜支付URL
	 */
	private String paymentUrl;
	/**
	 * 私钥文件包内路径使用classpath 搜索
	 */
	private String privateKeyPath;
	/**
	 * 私钥实际保存地址，如果有配置privateKeyPath将失效，上生产时使用
	 */
	private String privateKeyRealPath;
	/**
	 * 公钥实际保存地址，如果有配置publicKeyPath将失效，上生产时使用
	 */
	private String publicKeyRealPath;
	/**
	 * 公钥文件包内路径使用classpath 搜索
	 */
	private String publicKeyPath;
	/**
	 * 金掌柜回调地址
	 */
	private String callBackUrl;
	/**
	 * 系统编号
	 */
	private String systemOrganNo;
	/**
	 * 系统商户号
	 */
	private String systemMerId ;
	/**
	 * 商户通讯密码
	 */
	private String password;
	
	@javax.annotation.Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	/**
	 * 密钥文件路径
	 */
	private static String TEMP_PATH = IafApplication.getKeyFileTempPath();

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void init(){
		if (StringUtils.isNotBlank(this.privateKeyRealPath) || StringUtils.isNotBlank(this.publicKeyRealPath)){
			this.privateKeyRealPath = TEMP_PATH + "/" + this.privateKeyRealPath;
			this.publicKeyRealPath = TEMP_PATH + "/" + this.publicKeyRealPath;
		}else if (StringUtils.isNotBlank(this.privateKeyPath) && StringUtils.isNotBlank(this.publicKeyPath)){
			try{
				ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
				Resource resource = resourcePatternResolver.getResource("classpath:" + this.privateKeyPath);
				if (resource.isReadable()){
					this.privateKeyRealPath = this.getRealPath(resource, "privateKeyFile.dat");
				}else{
					throw new Exception("无法找开 " + this.privateKeyPath);
				}
				resource = resourcePatternResolver.getResource("classpath:" + this.publicKeyPath);
				if (resource.isReadable()){
					this.publicKeyRealPath = this.getRealPath(resource, "publicKeyFile.dat");
				}else{
					throw new Exception("无法找开 " + this.publicKeyPath);
				}
			}catch(Throwable e){
				logger.error("生成密钥文件失败", e);
				throw new RuntimeException("密钥文件查找失败", e);
			}
		}else{
			throw new RuntimeException("密钥文件路径未配置！");
		}
		
		param = this.sysParamService.getSysParam(SysParamType.payUrl, SysParamName.PAY_URL);
		if (IafApplication.isTest() == false){
			if (param == null) throw new RuntimeException("金掌柜支付URL未配置！");
			this.paymentUrl = param.getValue();
		}
	}
	
	SysParam param;
	
	public String getRealPath(Resource resource, String fileName) throws Throwable{
		InputStream in  = null;
		FileOutputStream out = null;
		String real = "";
		try{
			File path = new File(TEMP_PATH);
			if (path.exists() == false){
				path.mkdirs();
			}
			real = TEMP_PATH + "/" + fileName;
			in = resource.getInputStream();
			byte[] bufs = new byte[1024];
			int len = 0;
			out = new FileOutputStream(real);
			while ((len = in.read(bufs)) > 0){
				out.write(bufs, 0, len);
				out.flush();
			}
			
		}finally{
			if (in != null) in.close();
			if (out != null) out.close();
		}
		return real;
	}

	public String getPaymentUrl() {
		if (IafApplication.isTest()){
			if (this.param == null) throw new RuntimeException("金掌柜支付地址未配置！");
			if (StringUtils.isBlank(param.getValue())) throw new RuntimeException("金掌柜支付地址未配置！");
			paymentUrl = param.getValue();
		}else{
			if (this.param == null) throw new RuntimeException("金掌柜支付地址未配置！");
			if (StringUtils.isBlank(param.getValue())) throw new RuntimeException("金掌柜支付地址未配置！");
			paymentUrl = param.getValue();
		}
		
		return paymentUrl;
	}
	
	/**
	 * 获取最小利率
	 * @return
	 */
	public String getMinRate(){
		SysParam min_rate = this.sysParamService.getSysParam(SysParamType.rate, SysParamName.MIN_RATE);
		return min_rate.getValue();
	}
	
	/**
	 * 获取最大利率
	 * @return
	 */
	public String getMaxRate(){
		SysParam max_rate = this.sysParamService.getSysParam(SysParamType.rate, SysParamName.MAX_RATE);
		return max_rate.getValue();
	}
	
	/**
	 * 获取交易利率
	 * @return
	 */
	public String getTradeRate(){
		SysParam trade_rate = this.sysParamService.getSysParam(SysParamType.rate, SysParamName.TRADE_RATE);
		return trade_rate.getValue();
	}
	
	public String getPrivateKeyPath() {
		return privateKeyPath;
	}

	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}

	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	public String getPrivateKeyRealPath() {
		return privateKeyRealPath;
	}

	public void setPrivateKeyRealPath(String privateKeyRealPath) {
		this.privateKeyRealPath = privateKeyRealPath;
	}

	public String getPublicKeyRealPath() {
		return publicKeyRealPath;
	}

	public void setPublicKeyRealPath(String publicKeyRealPath) {
		this.publicKeyRealPath = publicKeyRealPath;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getSystemOrganNo() {
		SysParam organNo_param = this.sysParamService.getSysParam(SysParamType.organNo, SysParamName.ORGANNO);
		//if (IafApplication.isTest()) return IafApplication.getProperties("organNo");
		return organNo_param.getValue();
	}

	public void setSystemOrganNo(String systemOrganNo) {
		this.systemOrganNo = systemOrganNo;
	}

	public String getSystemMerId() {
		//if (IafApplication.isTest()) return IafApplication.getProperties("merchId");
		return systemMerId;
	}

	public void setSystemMerId(String systemMerId) {
		this.systemMerId = systemMerId;
	}

	public String getPassword() {
		SysParam pwd_param = this.sysParamService.getSysParam(SysParamType.tradePwd, SysParamName.TRADE_PWD);
		//if (IafApplication.isTest()) return IafApplication.getProperties("password");
		return pwd_param.getValue();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

}
