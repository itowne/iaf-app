package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.newland.BeanTest;

public class PwdChkServiceImplTest extends BeanTest {

	@Resource(name = "com.newland.iaf.pwdChkService")
	PwdChkServiceImpl pwdChkServiceImpl;

	@Test
	public void testXx() throws Exception {
		// initDemoService.initBaseDate();
//		Assert.assertTrue(pwdChkServiceImpl.check("linxu345", "123456"));
		//Assert.assertTrue(pwdChkServiceImpl.check("00020000", "123"));
	}

}
