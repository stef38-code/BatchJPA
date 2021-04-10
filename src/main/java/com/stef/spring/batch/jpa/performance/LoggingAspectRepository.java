package com.stef.spring.batch.jpa.performance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
@Component
@Slf4j
public class LoggingAspectRepository extends LogProfil{
    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository..*(..))))")
    public Object profileReaderMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        return getLogProfil(proceedingJoinPoint);
    }



}
