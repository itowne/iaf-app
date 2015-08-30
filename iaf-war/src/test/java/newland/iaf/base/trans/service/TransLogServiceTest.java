package newland.iaf.base.trans.service;

import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.base.trans.model.HcTrans;

import org.junit.Test;

import com.newland.BeanTest;

/**
 * 
 * @author rabbit
 * 
 */
public class TransLogServiceTest extends BeanTest {

	@Resource(name = "com.newland.iaf.transLogService")
	private FtpFileService transLogServcie;

	@Test
	public void testFetchTransLog() throws Exception {
		InputStream is = ClassLoader.getSystemResourceAsStream("jyjl_20131001.csv");
		HcTrans trans = new HcTrans();
		transLogServcie.fetch(is, trans);

		//initDemoService.initBaseDate();
		//transLogServcie.fetch(new Date());
	}

}
