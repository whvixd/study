package com.github.whvixd.message;

import com.github.whvixd.annotation.Subscribe;

import java.lang.reflect.Method;
import java.util.*;

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
    private Map<String, List<SubscriberMessage>> container;

    /**
     * 注册订阅者
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
        List<SubscriberMessage> messages = container.get(key);
        if (messages != null && messages.size() != 0) {
            //todo 异步
            messages.forEach(message -> message.invoke(report.getContent()));
        }
    }

    private void setContainer(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods.length != 0) {
            for (Method method : declaredMethods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe != null) {
                    SubscriberMessage message = SubscriberMessage.builder().
                            methodMessage(method).
                            subscriber(subscriber).
                            clazz(clazz);
                    initContainer(getKey(method), message);
                }
            }
        }
    }

    private void initContainer(String key, SubscriberMessage message) {
        if (key == null || message == null) {
            return;
        }
        if (container == null) {
            container = new HashMap<>();
        }
        List<SubscriberMessage> messages = container.get(key);
        if (messages != null) {
            messages.add(message);
        } else {
            messages = new ArrayList<>();
            messages.add(message);
            container.put(key, messages);
        }
    }

    private String getKey(Method method) {
        if (method == null || method.getParameterTypes().length == 0) {
            return null;
        }
        return method.getParameterTypes()[0].getSimpleName();
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
