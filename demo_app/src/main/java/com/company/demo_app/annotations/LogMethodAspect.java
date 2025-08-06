package com.company.demo_app.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Configuration
@Aspect
@Slf4j
public class LogMethodAspect {

    @Around("@annotation(com.company.demo_app.annotations.LogMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        log.info("[Operation: {} invoked with input data: {}, returned output: {}, executed in {} ms.]",
                joinPoint.getSignature(), joinPoint.getArgs(), proceed, stopWatch.getTotalTimeMillis());
        return proceed;
    }

    @AfterThrowing(pointcut = "@annotation(com.company.demo_app.annotations.LogMethod)", throwing = "e")
    public void logMethodAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.error("[Error upon executing method: {}.{}, with input {}. Error: {}]",
                joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(),
                joinPoint.getArgs(), e.getMessage());
    }

}
