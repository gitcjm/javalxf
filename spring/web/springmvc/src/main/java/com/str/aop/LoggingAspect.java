package com.str.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*@Aspect
@Component
public class LoggingAspect {
    @After("execution(public * com.str.service.UserService.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        Object reVal = pjp.proceed();
        System.err.println("[Logging] " + pjp.getSignature());
        return reVal;
    }
}*/
