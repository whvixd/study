package com.github.whvixd.message;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


@Data
public class SubscriberMessage {
    private Object subscriber;
    private Class clazz;
    private Method method;
    private int parameterCount = 1;
    private Class<?> parameterType;
    private boolean isPrior;

    private SubscriberMessage() {
    }

    public void setMethodMessage(Method method) {
        setMethod(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null) {
            setParameterCount(0);
            throw new RuntimeException();
        }
        setParameterType(parameterTypes[0]);
    }

    public static SubscriberMessage builder() {
        return new SubscriberMessage();
    }

    public SubscriberMessage methodMessage(Method method) {
        setMethodMessage(method);
        return this;
    }

    public SubscriberMessage clazz(Class clazz) {
        setClazz(clazz);
        return this;
    }

    public SubscriberMessage subscriber(Object subscriber) {
        setSubscriber(subscriber);
        return this;
    }

    void invoke(Object arg) {
        try {
            method.invoke(subscriber, arg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }

}