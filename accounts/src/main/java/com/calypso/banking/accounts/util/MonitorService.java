package com.calypso.banking.accounts.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitorService {
	Logger log=LoggerFactory.getLogger(MonitorService.class);
	
	@Before("execution(* com..*Service.*(..))")            //.. ie many packages starting with com, 2nd * is any class, *Service any class ending in Service, (..) any parameters
	public void logBeforeMethod(JoinPoint point)
	{
	 	log.info("Before Target:{}",point.getTarget().toString());
	 	log.debug("Inside before advice");
	}

	@After("execution(* com..*Service.*(..))")           
	public void logAfterMethod(JoinPoint point)
	{
	 	log.info("After Target:{}",point.getTarget().toString());
	 	log.debug("Inside After advice");
	}
	
	@AfterThrowing(pointcut="execution(* com..*Service.*(..))", throwing="npe")           
	public void logAfterThrowMethod(JoinPoint point, NullPointerException npe)
	{
	 	log.info("Exception:{}",npe.getLocalizedMessage());
	 	log.error("Exception thrown:{}",npe);
	 	log.debug("Advice Invoked:{}", point.getSignature().getName());
	}
	
	
}
