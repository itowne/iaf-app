package newland.iaf.base.trans.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.service.FtpFileService;

import org.junit.Test;

import com.newland.BeanTest;

/**
 * 
 * @author rabbit
 * 
 */
public class InstallLogServiceImplTest extends BeanTest {

	@Resource(name = "com.newland.iaf.installLogService")
	private FtpFileService installLogService;

	@Test
	public void testFetchReader() throws IOException {
		InputStream is = ClassLoader.getSystemResourceAsStream("zjcj_20130521.csv");
		HcTrans trans = new HcTrans();
		installLogService.fetch(is, trans);
	}

}
