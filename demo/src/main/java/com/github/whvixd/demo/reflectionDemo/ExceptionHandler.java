package com.github.whvixd.demo.reflectionDemo;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 对异常的标识
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionHandler {
    String value() default "";

    Class<? extends Map<String, String>> isClass() default None.class;

    class None extends HashMap<String, String> {
    }
}

class Test {

    @ExceptionHandler("运行时异常")
    public static void m1() {
        throw new RuntimeException();
    }

    public void m2() {
        throw new RuntimeException();
    }

    //怎么在调用方法之前自动识别注解
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Test test = new Test();
        Class testClazz = test.getClass();
        Method[] methods = testClazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ExceptionHandler.class)) {
                ExceptionHandler exceptionHandler = method.getAnnotation(ExceptionHandler.class);
                String annoValue = exceptionHandler.value();
                try {
                    method.invoke(null);
                } catch (InvocationTargetException invocationTargetException) {
                    //(cause==this ? null : cause)
                    System.err.println(method + " fail " + annoValue + ">>>>>>>>>>>>>>" + invocationTargetException.getCause());
                } catch (Exception e) {
                    System.err.println(method + " fail " + e);
                }
            }
        }
    }
}