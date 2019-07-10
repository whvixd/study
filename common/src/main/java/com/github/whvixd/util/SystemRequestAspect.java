package com.github.whvixd.util;

import com.github.whvixd.annotation.InterfaceProperty;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by wangzhx on 2018/3/22 11:20.
 */
@Component
@Aspect
public class SystemRequestAspect {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SystemRequestAspect.class);

    /**
     * 接口超时日志
     */
    private static final Logger INTERFACE_TIMEOUT_LOG = LoggerFactory.getLogger("INTERFACE_TIMEOUT_LOG");

    //todo 目前还不能拦截

    @Around(value = "execution(* com.github.whvixd.demo.springDemo.Container.main(..))")//, argNames = "pjp"
    public Object validator(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        /** 拦截的方法名称 */
        String methodName = pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName();

        try {
            long start = System.currentTimeMillis();
            Object obj = pjp.proceed(args);
            long finish = System.currentTimeMillis();
            long useTime = finish - start;
            /** 接口响应时间监控 */
            interfaceUseTimeMonitor(pjp.getTarget().getClass(), pjp.getSignature().getName(), args, useTime);

            return obj;
        } catch (Throwable e) {//处理你的异常
        } finally {//处理其他
        }
        return null;
    }

    /**
     * 接口响应时间监控
     *
     * @param targetClass 接口实现class
     * @param methodName  接口方法
     * @param args        接口如参
     * @param useTime     调用接口实际使用时间
     */
    private void interfaceUseTimeMonitor(Class targetClass, String methodName, Object[] args, long useTime) {
        /** 与接口注解最高用时做比较,符合条件发送邮件 */
        try {
            Class[] classArray = new Class[args.length];
            for (int i = 0; i < args.length; ++i) {
                classArray[i] = args[i].getClass();
            }
            Method method = targetClass.getMethod(methodName, classArray);
            if (method.isAnnotationPresent(InterfaceProperty.class)) {
                InterfaceProperty interfaceProperty = method.getAnnotation(InterfaceProperty.class);
                if (useTime >= interfaceProperty.timeout()) {
                    if (INTERFACE_TIMEOUT_LOG.isInfoEnabled()) {
                        INTERFACE_TIMEOUT_LOG.info("接口超时,interface:[{}].useTime:[{}].settingUseTime:[{}].traceId:[{}]",
                                new Object[]{targetClass.getSimpleName() + "." + methodName,
                                        useTime, interfaceProperty.timeout()}, "");// TraceUtils.getTrace()
                    }
                }
            }
        } catch (Throwable e) {
            /** 监控逻辑处理错误什么都不做 */
        }
    }

}