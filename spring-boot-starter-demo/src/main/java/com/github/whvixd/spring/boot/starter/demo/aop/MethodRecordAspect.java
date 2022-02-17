package com.github.whvixd.spring.boot.starter.demo.aop;

import com.github.whvixd.spring.boot.starter.demo.annotation.MethodRecord;
import com.github.whvixd.spring.boot.starter.demo.autoconfigure.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@Aspect
@Slf4j
public class MethodRecordAspect {
    @Resource
    private DemoProperties demoProperties;

    @Pointcut("@annotation(com.github.whvixd.spring.boot.starter.demo.annotation.MethodRecord)")
    public void methodRecordAspect() {
    }

    @Around("methodRecordAspect()")
    public Object methodRecordAround(ProceedingJoinPoint proceeding) throws Throwable {
        MethodSignature sign = (MethodSignature) proceeding.getSignature();
        Method method = sign.getMethod();
        MethodRecord annotation = method.getAnnotation(MethodRecord.class);
        String value = annotation.value();
        try {
            return proceeding.proceed();
        } finally {
            log.info("method record,value:{},demoProperties:{}", value, demoProperties);
        }
    }
}
