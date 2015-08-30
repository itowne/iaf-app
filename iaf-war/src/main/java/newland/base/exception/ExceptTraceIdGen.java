package newland.base.exception;

import newland.base.util.TraceIdGenerator;

public class ExceptTraceIdGen {
	
	private static TraceIdGenerator idGen = new TraceIdGenerator();
	
	private ExceptTraceIdGen(){
		
	}
	
	public static final ExceptTraceIdGen getInstance(){
		return new ExceptTraceIdGen();
	}

	public String getTraceId(){
		return idGen.getTraceId();
	}
	
}
