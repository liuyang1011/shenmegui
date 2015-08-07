package com.dc.esb.servicegov.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Administrator on 2015/8/4.
 */
@Aspect
public class OperationLogAspect {
    @Before("execution(* com.dc.esb.servicegov.service.impl.*.*(..))")
    public void before(JoinPoint joinpoint){
        joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        System.out.println("被拦截方法调用之前调用此方法，输出此语句");
    }
    @After("execution(* com.dc.esb.servicegov.controller.*.*(..))")
    public void after(JoinPoint joinpoint){
        System.out.println("被拦截方法调用之后调用此方法，输出此语句");
    }
}
