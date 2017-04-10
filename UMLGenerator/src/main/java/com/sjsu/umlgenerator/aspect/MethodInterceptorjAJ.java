package com.sjsu.umlgenerator.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodInterceptorjAJ {


    @Pointcut("(execution(* *(..))&& !cflow(within(MethodInterceptorjAJ)))")
    public void logging() {
    }

    @Before("logging()")
    public Object logging(ProceedingJoinPoint thisJoinPoint) throws Throwable {
	System.out.println("Before " + thisJoinPoint.getSignature());
	final Object ret = thisJoinPoint.proceed();
	System.out.println("After " + thisJoinPoint.getSignature());
	return ret;
    }

}
