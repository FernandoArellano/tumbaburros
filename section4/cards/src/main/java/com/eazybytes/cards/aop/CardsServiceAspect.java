package com.eazybytes.cards.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CardsServiceAspect {

    @Around("execution(* com.eazybytes.cards.service.impl.RandomService.*(..))")
    public void aroundServiceAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("Around aspect before method:" + methodName);
        Object result;


        try {
            // Execute the actual method
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            // Handle exceptions if necessary
            System.out.println(">>>> @Around: " + methodName + " threw an exception: " + throwable.getMessage());
            throw throwable;
        }

        System.out.println("Around aspect after method:" + methodName);
    }
}
