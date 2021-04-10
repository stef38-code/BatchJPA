package com.stef.spring.batch.jpa.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Slf4j
public class LogProfil {
    protected Object getLogProfil(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time {} ms of {}.{} return {} :: {} values {}" ,stopWatch.getTotalTimeMillis(), className, methodName ,methodSignature.getReturnType().getSimpleName(),Arrays.toString( methodSignature.getParameterNames()),Arrays.toString(proceedingJoinPoint.getArgs()));
        return result;
    }
}
