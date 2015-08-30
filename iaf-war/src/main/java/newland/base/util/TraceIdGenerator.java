package newland.base.util;

import java.util.Calendar;

import newland.base.system.SystemProperty;

/**
 * <p>
 * 追踪号生成器
 * </p>
 * <p>
 * 一分钟内获取次数若<tt>&lt;0xFFF</tt>，将永远不出现重复。
 * </p>
 * <p>
 * 该对象实现依赖于{@link com.newland.base.system.SystemProperty SystemProperty}
 * </p>
 * 
 * @author lance
 */
public class TraceIdGenerator {

	private static final int ROLLSEED = 0xFFF;

	private final Object IDKEY = new Object();

	private SimIdGenerator idGenerator;

	private static TraceIdGenerator traceIdGenerator;

	public TraceIdGenerator() {
		idGenerator = new SimIdGenerator(ROLLSEED);
	}

	public static TraceIdGenerator getInstance() {
		if (traceIdGenerator == null)
			traceIdGenerator = new TraceIdGenerator();

		return traceIdGenerator;
	}

	public String getTraceId() {

		StringBuilder sb = new StringBuilder();

		String systname = SystemProperty.getInstance().getSysName();
		String machineid = SystemProperty.getInstance().getMachineID();

		String unknowndefault = "n/a";

		sb.append((systname == null ? unknowndefault : systname) + "-"
				+ (machineid == null ? unknowndefault : machineid) + "-");

		synchronized (IDKEY) {

			Calendar current = Calendar.getInstance();
			int curDate = current.get(Calendar.DAY_OF_YEAR);

			int curHour = current.get(Calendar.HOUR_OF_DAY);
			int _curHourLength = 5;

			int curMin = current.get(Calendar.MINUTE);
			int _curMinLength = 6;

			curDate <<= _curHourLength + _curMinLength;
			curHour <<= _curMinLength;

			int curStamp = curDate + curHour + curMin;

			String curStampHEX = Integer.toHexString(curStamp);
			curStampHEX = StringUtils.leftPad(curStampHEX, 5, "0");

			int result = (idGenerator.getId(IDKEY)).intValue();
			String strSerial = Integer.toHexString(result);
			strSerial = StringUtils.leftPad(strSerial, 3, "0");

			sb.append(curStampHEX + "-" + strSerial);
		}

		return sb.toString();
	}


}
