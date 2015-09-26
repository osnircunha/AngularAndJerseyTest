package com.ocunha.election.logs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by osnircunha on 9/24/15.
 */
@Component
@Aspect
public class Logging {
    Logger LOGGER = LoggerFactory.getLogger(Logging.class);

    private static final String FORMAT = "mm:ss.SSS";

    @Around("execution(* com.ocunha.election.facade.UserFacade.*(..))")
    public Object  logUsers(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long spent = System.currentTimeMillis() - start;
        printMethodExecution(joinPoint.getSignature().toShortString(), spent);
        return result;
    }

    @Around("execution(* com.ocunha.election.facade.Facade.*(..))")
    public Object  logServices(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long spent = System.currentTimeMillis() - start;
        printMethodExecution(joinPoint.getSignature().toShortString(), spent);
        return result;
    }

    private void printMethodExecution(String method, long time){
        String sTime = new SimpleDateFormat(FORMAT).format(new Date(time));
        LOGGER.debug(String.format("Intercepting %s - time to execute : %s", method, sTime));
    }
}
