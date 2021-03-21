package com.stef.spring.batch.jpa.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.stef.spring.batch.jpa.reader..*(..)))")
    public Object profileReaderMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        return getLogProfil(proceedingJoinPoint);
    }
    @Around("execution(* com.stef.spring.batch.jpa.writer..*(..)))")
    public Object profileWriterMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        return getLogProfil(proceedingJoinPoint);
    }
    @Around("execution(* com.stef.spring.batch.jpa.processor..*(..)))")
    public Object profileProsessorMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        return getLogProfil(proceedingJoinPoint);
    }
    private Object getLogProfil(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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
        log.info("Execution time of {}.{} :: {} ms" , className, methodName , stopWatch.getTotalTimeMillis());
        return result;
    }
}