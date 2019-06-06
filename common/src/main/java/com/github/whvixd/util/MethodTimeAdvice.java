package com.github.whvixd.util;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by wangzhx on 2018/3/23 18:34.
 */
@Slf4j
public class MethodTimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = invocation.proceed();
        stopWatch.stop();
        //方法参数类型，转换成简单类型
        Class[] params = invocation.getMethod().getParameterTypes();
        String[] simpleParams = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            simpleParams[i] = params[i].getSimpleName();
        }

        log.debug("Takes:" + stopWatch.getTime() + " ms ["
                + invocation.getThis().getClass().getName() + "."
                + invocation.getMethod().getName() + "(" + StringUtils.join(simpleParams, ",") + ")] ");
        return result;
    }
}
