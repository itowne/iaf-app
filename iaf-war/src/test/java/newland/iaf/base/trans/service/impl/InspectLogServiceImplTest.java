package newland.iaf.base.trans.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import junit.framework.Assert;

import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.service.FtpFileService;

import org.junit.Test;

import com.newland.BeanTest;

/**
 * 
 * @author rabbit
 * 
 */
public class InspectLogServiceImplTest extends BeanTest {

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.inspectLogService")
	private FtpFileService inspectLogService;

	@Test
	public void test() throws IOException {
		InputStream is = ClassLoader.getSystemResourceAsStream("巡检.csv");
		HcTrans trans = new HcTrans();
		inspectLogService.fetch(is, trans);
	}
}
