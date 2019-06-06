package com.github.whvixd.demo.cglibDemo;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodIntercepter implements MethodInterceptor {
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        //循环:获取方法名,在methodProxy.invokeSuper()之前操作,在其之后操作
        Object o = null;
        if (methodName.equals("printName")) {
            System.out.println("之前叫小明");
            o = methodProxy.invokeSuper(proxy, args);
            System.out.println("之后叫大名");
        }

        if (methodName.equals("getAge")) {

        }
        return o;

    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MethodObj.class);
        Callback callback = new MethodIntercepter();
        enhancer.setCallback(new MethodIntercepter());
        MethodObj methodObj = (MethodObj) enhancer.create();
        methodObj.printName("--");

        methodObj.getAge(21);
    }
}
