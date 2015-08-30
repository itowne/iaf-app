package newland.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *<p>系统内序列号生成器</p>
 *<p>一个1000以内，获取系统内不重复序号的方式如下</p>
 *<pre>
 *	Create...
 *	Object object = new Object();
 *	SimpleSerialIdGenerator idgenerator = new SimpleSerialIdGenerator(1000);
 *	Thread 1...
 *		Long s1 = idgenerator.getId(object);
 *	Thread 2...
 *		Long s2 = idgenerator.getId(object);
 *</pre>
 *可以期待的是，在确保递增<tt>1000</tt>以内的发生,<tt>s1 != s2</tt>
 *
 * @author lance
 *
 */
public final class SimIdGenerator{

	private long maxValue;
	
	private final Map <Object,AtomicLong> valueMap = new HashMap<Object, AtomicLong>();
	
	public SimIdGenerator(long maxValue){
		this.maxValue = maxValue;
	}

	public long getMaxValue() {
		return this.maxValue;
	}
	
	private Long getSerialId(Object seed) {
		AtomicLong value = null;
		synchronized (valueMap) {
			value = valueMap.get(seed);
			if(value == null){
				value = new AtomicLong(0);
				valueMap.put(seed, value);
			}
		}
		synchronized (value) {
			long l = value.addAndGet(1);
			if(l >= maxValue){
				value.set(0);
			}
			return l;
		}
	}


	public void clear(Object seed) {
		synchronized (valueMap) {
			AtomicLong value = valueMap.get(seed);
			if(value == null||value.get() == 0){
				return;
			}else{
				valueMap.put(seed, new AtomicLong(0));
			}
		}
	}

	public Long getId(Object seed) {
		return getSerialId(seed);
	}

	public void clearAll() {
		synchronized (valueMap) {
			valueMap.clear();
		}
	}
}
