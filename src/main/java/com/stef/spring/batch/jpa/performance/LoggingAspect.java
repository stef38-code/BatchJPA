package com.stef.spring.batch.jpa.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect extends LogProfil
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

}