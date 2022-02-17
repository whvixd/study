package com.github.whvixd.spring.boot.starter.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@Slf4j
public class ActionAnnotationInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } finally {
            log.info("record something,methodInvocation.name:{}", methodInvocation.getMethod().getName());
        }
    }
}
