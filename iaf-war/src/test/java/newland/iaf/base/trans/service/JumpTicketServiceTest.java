package newland.iaf.base.trans.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;
import newland.iaf.base.trans.model.JumpTicket;
import newland.iaf.base.trans.service.impl.JumpTicketServiceImple;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import com.newland.BeanTest;

public class JumpTicketServiceTest extends BeanTest {

	@Resource(name = "com.newland.iaf.jumpTicketService")
	private JumpTicketServiceImple jumpTicketService;

	@Test
	public void test() throws IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException,
			IllegalBlockSizeException, BadPaddingException, ParseException,
			IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			DecoderException {
		JumpTicket jt = jumpTicketService
				.decode("YJi2S7M+6qtt9XE3lZtcsL4PHE4TlSa5FxO7f+qGRlOlTnN6rM09Un6onc1x0YyBD3xXzE72");

		boolean b = jumpTicketService.check(jt);
		Assert.assertFalse(b);

		jt.setGenTime(new Date());
		b = jumpTicketService.check(jt);
		Assert.assertTrue(b);
	}
}
