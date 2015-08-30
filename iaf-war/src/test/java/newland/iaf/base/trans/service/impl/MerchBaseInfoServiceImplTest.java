package newland.iaf.base.trans.service.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.service.FtpFileService;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.newland.BeanTest;

public class MerchBaseInfoServiceImplTest extends BeanTest {

	@Resource(name = "com.newland.iaf.merchBaseInfoService")
	private FtpFileService merchBaseInfoService;

	@Test
	public void testFetchReader() throws Exception {
		 InputStream is = ClassLoader.getSystemResourceAsStream("shxx_20131018.csv");
		 merchBaseInfoService.fetch(is, new HcTrans());

		//initDemoService.initBaseDate();
		//merchBaseInfoService.fetch("quanbu");
	}
}
