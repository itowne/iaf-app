package newland.iaf.utils.service.impl;

import newland.base.formater.Formatter;
import newland.iaf.utils.formatter.SnoFormatter;
import newland.iaf.utils.service.HiLoIdGenerator;
import newland.iaf.utils.service.PrefixIdGenerator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("instNoGenerator")
public class InstNoGenerator extends HiLoIdGenerator implements
	PrefixIdGenerator {

	private static final String IDGEN_NAME = "INST_NO";
	
	private static final int MAXLENGTH = 7;
	
	private SnoFormatter snoFormatter;
	
	protected int maxValue  = -1;
	
	public InstNoGenerator(){
		this(MAXLENGTH);
	}
	
	protected InstNoGenerator(int maxLength){
		maxValue = (int)Math.pow(10, maxLength) - 1;
		if(maxLength <= 0 || this.maxValue <= 0)
			throw new RuntimeException( 
					"failed to init idgen of "+getSnoType()+" for maxLength:"+maxLength+",maxValue:"+maxValue);
		
		snoFormatter = new SnoFormatter(maxLength);
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

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String generate(String prefix) {
		snoFormatter.setPrefix(prefix);
		return super.generate();
	}

}
