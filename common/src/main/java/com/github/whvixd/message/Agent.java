package com.github.whvixd.message;

import com.github.whvixd.annotation.Subscribe;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhx on 2019/7/10.
 * 中介
 * 1. 注册订阅者
 * 2. 给订阅者推消息
 */
public class Agent {
    /**
     * 存储订阅者方法的信息
     * {className:SubscriberMessage}
     */
    private Map<String, SubscriberMessage> container = new HashMap<>();

    /**
     * 1. 获取subscriber中的@Subscibe注解 class_method_argClass:1
     */
    public void register(Object subscriber) {
        if (subscriber == null) {
            return;
        }
        setContainer(subscriber);
    }

    /**
     * 发送消息给订阅者
     */
    public void push(Report report) {
        if (report == null) {
            return;
        }
        String key = report.getClazzName();
        SubscriberMessage subscriberMessage = container.get(key);
        if (subscriberMessage == null) {
            return;
        }
        subscriberMessage.invoke(report.getContent());
    }


    private void setContainer(Object object) {
        Class<?> clazz = object.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods.length != 0) {
            for (int i = 0; i < declaredMethods.length; i++) {
                Method method = declaredMethods[i];
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe != null) {
                    SubscriberMessage subscriberMessage = new SubscriberMessage();
                    subscriberMessage.setClazz(clazz);
                    subscriberMessage.setMethodMessage(method);
                    subscriberMessage.setSubscriber(object);
                    container.put(method.getParameterTypes()[0].getSimpleName(), subscriberMessage);
                }
            }
        }
    }


    @Data
    class SubscriberMessage {
        private Object subscriber;
        private Class clazz;
        private Method method;
        private int parameterCount = 1;
        private Class<?> parameterType;

        public void setMethodMessage(Method method) {
            setMethod(method);
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes == null) {
                setParameterCount(0);
                throw new RuntimeException();
            }
            setParameterType(parameterTypes[0]);
        }

        void invoke(Object arg) {
            try {
                method.invoke(subscriber, arg);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }
    }


    private String getFullKey(Class clazz, Method method) {
        if (clazz == null || method == null) {
            return null;
        }
        StringBuilder stringBuffer = new StringBuilder();
        String simpleName = clazz.getSimpleName();
        String methodName = method.getName();
        stringBuffer.append(simpleName).append("_").append(methodName).append("_");
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            String paramClazzName = parameterType.getSimpleName();
            if (i != parameterTypes.length - 1) {
                stringBuffer.append(paramClazzName).append("_");
            } else {
                stringBuffer.append(paramClazzName);
            }
        }
        return stringBuffer.toString();
    }

}
