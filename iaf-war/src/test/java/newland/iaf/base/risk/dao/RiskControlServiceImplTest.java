package newland.iaf.base.risk.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.newland.BeanTest;

/**
 * 
 * @author rabbit
 * 
 */
public class RiskControlServiceImplTest extends BeanTest {

	@Resource(name = "com.newland.riskControlService")
	private RiskControlService riskControlService;

	private DateFormat df = new SimpleDateFormat("yyyyMM");

	@Test
	public void test() throws ParseException {
		Date begin = df.parse("201210");
		Date end = df.parse("201304");
		System.out.println(end);
		riskControlService.genTransLogMouth(begin, end);
	}

}
