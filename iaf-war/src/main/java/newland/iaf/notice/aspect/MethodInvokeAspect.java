package newland.iaf.notice.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import newland.iaf.notice.ProcessType;
import newland.iaf.notice.annotation.MethodStrategy;
import newland.iaf.notice.annotation.MethodTrigger;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Aspect
@Service("methodInvokeAspect")
public class MethodInvokeAspect implements ApplicationContextAware, InitializingBean{
	
	private TaskExecutor taskExecutor;	
	
	static final Map<String, Object>  processors = new HashMap<String, Object>();
	Logger logger = LoggerFactory.getLogger(getClass());

	//@AfterReturning(pointcut = "@annotation(trigger)")
	public void afterReturning(JoinPoint jp, MethodTrigger trigger) throws Throwable{
		intercept(jp, trigger);
	}

	private void intercept(JoinPoint jp, MethodTrigger trigger) {
		try{
			String[] stratgys = StringUtils.split(trigger.stratgy(), ",");
			if (stratgys != null && stratgys.length > 0){
				for (String stratgy: stratgys){
					stratgy = StringUtils.trim(stratgy);
					if (StringUtils.isNotBlank(stratgy)){
						Object processor = processors.get(stratgy);
						if (processor == null) {
							logger.warn("方法调用处理程序未定义");
						}else{
							Method method  = ((MethodSignature) jp.getSignature()).getMethod();
							Object[] args = jp.getArgs();
							Class<?>[] types = method.getParameterTypes();
							MethodStrategy strategy = processor.getClass().getAnnotation(MethodStrategy.class);
							run(strategy.processType(), processor, method.getName(), types, args);
						}
					}
				}
			}
		}catch (Throwable e){
			logger.error("动行时出错", e);
		}
	}
	@Around("@annotation(trigger)")
	public Object around(ProceedingJoinPoint jp, MethodTrigger trigger) throws Throwable{
		try{
			logger.info("拦载到方法调用：[" + jp.getSignature().getName() + "]");
			this.intercept(jp, trigger);
			Object ret = null;
			if (jp.getArgs() == null || jp.getArgs().length < 0){
				ret = jp.proceed();
			}else{
				ret = jp.proceed(jp.getArgs());
			}
			
			return ret;
		}catch(Throwable e){
			logger.info("调用目标方法失败，不进行方法拦截", e);
			throw e;
		}
	}
	
	private void run(ProcessType type, final Object processor, final String methodName,final Class<?>[] types, final Object[] args ) throws Throwable{
		if (type == ProcessType.SYNC){
			execute(processor, methodName, types, args);
		}else{
			this.taskExecutor.execute(new Runnable(){

				@Override
				public void run() {
					try {
						execute(processor, methodName, types, args);
					} catch (Throwable e) {
						logger.error("调用处理程序出错", e);
						throw new RuntimeException(e);
					}
				}
				
			});
		}
	}
	
	private final void execute(Object target, String methodName, Class<?>[] types, Object[] args) throws Throwable{
		Method method = null;
		try{
			method = target.getClass().getMethod(methodName, types);
		}catch(Throwable e){
			logger.error("调用方法出错", e);
		}
		if (method == null) {
			String signure = "";
			if (types != null && types.length > 0){
				for (int i = 0 ; i < types.length ; i++){
					signure += types[i].getName() + ((i < types.length)?",":"");
				}
			}
			logger.error("未找到对应的处理程序: " + methodName + "(" + signure + ")");
		}else{
			method.invoke(target, args);
		}
	}
	
	public void registNoticeProcessor(String key, Object processor){
		Object proc = processors.get(key);
		if (proc != null) throw new RuntimeException("["+ key + "]处理器已注册！");
		processors.put(key, processor);
	}
	
	ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> beansMap = this.context.getBeansWithAnnotation(MethodStrategy.class);
		for(Map.Entry<String, Object> entry : beansMap.entrySet()){
			MethodStrategy strategy = entry.getValue().getClass().getAnnotation(MethodStrategy.class);
			registNoticeProcessor(strategy.value(), entry.getValue());
			logger.info("注册策略类：[" + strategy.value() + "][" + entry.getValue().getClass().getName() + "]");
		}
		if (this.taskExecutor == null){
			ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
			tp.afterPropertiesSet();
			this.taskExecutor = tp;
		}
	}
}
