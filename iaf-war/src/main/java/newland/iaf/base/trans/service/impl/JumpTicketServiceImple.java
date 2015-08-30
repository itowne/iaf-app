package newland.iaf.base.trans.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import newland.iaf.base.format.csv.CsvBeanFormat;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.model.JumpTicket;
import newland.iaf.base.trans.service.JumpTicketService;
import newland.iaf.utils.AesCipher;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvReader;
import com.itextpdf.text.pdf.codec.Base64;

@Service("com.newland.iaf.jumpTicketService")
@Transactional
public class JumpTicketServiceImple implements JumpTicketService {

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public AesCipher getAesChipher() throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, DecoderException {
		Map<SysParamName, SysParam> set = sysParamService
				.findSysParamMapByType(SysParamType.jumpTicketCrypt);
		String key = set.get(SysParamName.JUMP_TICKET_KEY).getValue();
		String iv = set.get(SysParamName.JUMP_TICKET_IV).getValue();
		return new AesCipher(key, iv);
	}

	public JumpTicket decode(String str) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, ParseException,
			IOException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			DecoderException {
		byte[] buf = Base64.decode(str);
		String s = null;
		AesCipher aesCipher = getAesChipher();
		s = aesCipher.decryptString(buf);
		log.debug("jumpticket解密后=[" + s + "].");
		CsvBeanFormat<JumpTicket> cvf = new CsvBeanFormat<JumpTicket>(JumpTicket.class);
		StringReader sr = new StringReader(s);
		CsvReader cr = new CsvReader(sr);
		cr.readRecord();
		return cvf.parse(cr);
	}

	public boolean check(JumpTicket jt) {
		SysParam param = sysParamService.getSysParam(
				SysParamType.jumpTicketCrypt,
				SysParamName.JUMP_TICKET_EXPIRE_SECOND);
		int expireSec = Integer.parseInt(param.getValue());
		Date now = new Date();
		Date expire = jt.getGenTime();
		expire = DateUtils.addSeconds(expire, expireSec);
		return now.before(expire);
	}

}
