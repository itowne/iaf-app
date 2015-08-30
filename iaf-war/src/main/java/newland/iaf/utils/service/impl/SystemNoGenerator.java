package newland.iaf.utils.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import newland.base.formater.Formatter;
import newland.iaf.utils.formatter.SnoFormatter;
import newland.iaf.utils.service.HiLoIdGenerator;
import newland.iaf.utils.service.IdentifierGenerator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统流水号生成器
 * 
 * @author lance
 */
@Service(value = "systemNoGenerator")
public class SystemNoGenerator extends HiLoIdGenerator implements
		IdentifierGenerator {

	private static final String IDGEN_NAME = "SYSTEM_NO";

	private static final int MAXLENGTH = 8;

	private SnoFormatter snoFormatter;

	protected int maxValue = -1;

	public SystemNoGenerator() {
		this(MAXLENGTH);
	}

	protected SystemNoGenerator(int maxLength) {
		maxValue = (int) Math.pow(10, maxLength) - 1;
		if (maxLength <= 0 || this.maxValue <= 0)
			throw new RuntimeException("failed to init idgen of "
					+ getSnoType() + " for maxLength:" + maxLength
					+ ",maxValue:" + maxValue);

		snoFormatter = new SnoFormatter(maxLength);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String generate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		snoFormatter.setPrefix(sdf.format(date));
		return super.generate();
	}

	@Override
	protected Formatter getFormatter() {
		return snoFormatter;
	}

	@Override
	public String getSnoType() {
		return IDGEN_NAME;
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}

	@Override
	protected StrategyWhenOutofMaxValue getStrategyWhenOutofMaxValue() {
		return StrategyWhenOutofMaxValue.MAXVAL_TO_ZERO;
	}

}
