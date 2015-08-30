package newland.base.trans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.IafApplication;
import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.InstUser;
import newland.iaf.merch.model.MerchUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 图片请求servlet
 * @author lindaqun
 *
 */
@Service("instImaginServlet")
public class InstImaginServlet extends HttpServlet {
	
	private TFileService tfileService;
	
	private static final long MAX_PACKAGE_SIZE = 2*1024*1024;//2M
	
	Logger logger = LoggerFactory.getLogger(getClass());
	    
	    public void init(ServletConfig config) throws ServletException {
	         super.init(config);
	    }


	    public void doGet (HttpServletRequest req, HttpServletResponse res)
	                    throws ServletException, IOException
	    { 
	    	this.doPost(req, res);
	    }

	    public void doPost (HttpServletRequest req, HttpServletResponse res)
	                    throws ServletException, IOException
	    {
			int result = HttpServletResponse.SC_OK;
	        try {
	            // 如果请求内容太长，则返回内容太长错误
	            if (req.getContentLength() > MAX_PACKAGE_SIZE) {
	                throw new Exception("The post content is too long, the max length of the content is "+MAX_PACKAGE_SIZE+" Bytes!");
	            }
	            String id = req.getParameter("id");
	            String type = req.getParameter("type");
	            String uuidName = req.getParameter("uuidName");
	            HttpSession ses = req.getSession();
	        	IafSession iafSess = (IafSession) ses.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
	            Long iinst = (id !=null && !"".equals(id))?Long.valueOf(id):null;
	            byte data[] = createImage(res,iinst,uuidName,type,iafSess);
	            res.getOutputStream().write(data); //输出数据 
	            res.getOutputStream().close();
	            
	        } catch (Exception e ) {
	        	logger.debug("向客户端写流数据失败", e);
	        }
	    }
	    private byte[] createImage(HttpServletResponse res,Long id, String uuidName,String type,IafSession iafSess) throws Exception {
	    	if(tfileService == null)tfileService = (TFileService)(WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())).getBean("tfileService");
	    	if(id == null && uuidName == null){
	    		byte data[] = new byte[]{};
	    		return data;
	    	}else if(id != null && uuidName == null && type == null){
	    		TFile tfile = this.tfileService.getTFileByIfile(id);
	    		if(tfile == null){return new byte[]{};}
	    		res.setContentType(tfile.getMetaType());
	    		if(!check(tfile, iafSess)){return new byte[]{};}
	    		String path = IafApplication.renderPathSuffix(tfile.getPath());
	    		File file = new File(path+tfile.getUuidName()+"."+tfile.getExtName());
	    		if(!file.exists()){
	    			return new byte[]{};
	    		}
		    	byte data[] = passFileToData(file);
		    	res.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(tfile.getUploadName(), "UTF-8"));
				return data;
	    	}else if(id == null && uuidName != null){
	    		TFile tfile = this.tfileService.getTFileByUuidname(uuidName);
	    		if(tfile == null){return new byte[]{};}
	    		res.setContentType(tfile.getMetaType());
	    		if(!check(tfile, iafSess)){return new byte[]{};}
	    		String path = IafApplication.renderPathSuffix(tfile.getPath());
	    		File file = new File(path+tfile.getUuidName()+"."+tfile.getExtName());
	    		if(!file.exists()){
	    			return new byte[]{};
	    		}
		    	byte data[] = passFileToData(file);
		    	res.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(tfile.getUploadName(), "UTF-8"));
				return data;
	    	}else if(type != null && type.equals("exp")){
	    		TFile tfile = this.tfileService.getTFileByIfile(id);
	    		if(tfile == null){return new byte[]{};}
	    		res.setContentType(tfile.getMetaType());
	    		String path = IafApplication.renderPathSuffix(tfile.getPath());
	    		File file = new File(path+tfile.getUuidName()+"."+tfile.getExtName());
	    		if(!file.exists()){
	    			return new byte[]{};
	    		}
		    	byte data[] = passFileToData(file);
		    	res.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(tfile.getUploadName(), "UTF-8")); 
				return data;
	    	}
	    	res.setContentType("image/gif");
	    	return new byte[]{};
	    }
	    
	    private boolean check(TFile tfile,IafSession iafSess) throws Exception{
	    	boolean ret = false;
	    	//如果文件是没权限限制的，则不判断session
	    	if(tfile.getFileType() == FileType.instLogo ||tfile.getFileType() == FileType.merchLogo ||tfile.getFileType() == FileType.loanPdtLogo){
	    		return true;
	    	}else{
	    		//session必须是来判断
	    		if(iafSess!=null && iafSess instanceof MerchSession){
	    			MerchSession merchSession = (MerchSession)iafSess;
	    			MerchUser user = merchSession.getMerchUser();
	    			if(user != null && user.getImerchUser().longValue()==tfile.getImerchUser().longValue()){
	    				return true;
	    			}
	    		}else if(iafSess!=null && iafSess instanceof InstSession){
	    			InstSession instSession = (InstSession)iafSess;
	    			InstUser user = instSession.getInstUsr();
	    			if(user != null && user.getIinstUser().longValue()==tfile.getIinstuser().longValue()){
	    				return true;
	    			}
	    		}
	    	}
	    	//判断session是否满足
	    	return ret;
	    }
	    
	    private byte[] passFileToData(File file) throws Exception{
	    	FileInputStream  input = new FileInputStream (file);
			byte[] siInfo = instream2bytes(input);
			return siInfo;
	    }
	    
	    /**
		 * 输入流变为数组
		 * @param is
		 * @return
		 * @throws Exception
		 */
		private byte[] instream2bytes(InputStream is) throws Exception{
			ByteArrayOutputStream out = null;
			try{
				out = new ByteArrayOutputStream();
				byte buf[] = new byte[1024];
				int read = 0;
				while((read = is.read(buf, 0, buf.length))!=-1){
					out.write(buf, 0, read);
				}
			}catch(Exception e){
				throw e;
			}finally{
				is.close();
				out.close();
			}
			return out.toByteArray();
		}
}
