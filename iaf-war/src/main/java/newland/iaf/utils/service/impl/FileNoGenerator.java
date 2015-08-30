package newland.iaf.utils.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import newland.base.formater.Formatter;
import newland.iaf.utils.formatter.SnoFormatter;
import newland.iaf.utils.service.PrefixIdGenerator;
import newland.iaf.utils.service.SeqIdGenerator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件序列号生成器
 * 
 * @author lance
 */
@Service(value="fileNoGenerator")
public class FileNoGenerator extends SeqIdGenerator implements PrefixIdGenerator{

	private static final String IDGEN_NAME = "FILE_NO";
	
	private static final int MAXLENGTH = 8;
	
	
	private SnoFormatter snoFormatter;
	
	protected int maxValue  = -1;
	
	public FileNoGenerator(){
		this(MAXLENGTH);
	}
	
	protected FileNoGenerator(int maxLength){
		maxValue = (int)Math.pow(10, maxLength) - 1;
		if(maxLength <= 0 || this.maxValue <= 0)
			throw new RuntimeException( 
					"failed to init idgen of "+getSnoType()+" for maxLength:"+maxLength+",maxValue:"+maxValue);
		
		snoFormatter = new SnoFormatter(maxLength);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String generate(String prefix){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		snoFormatter.setPrefix(prefix+sdf.format(date));
		return super.generate();
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
	protected Formatter getFormatter() {
		return snoFormatter;
	}	







}
