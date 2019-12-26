package com.github.whvixd.message;

import com.github.whvixd.annotation.Prior;
import com.github.whvixd.annotation.Subscribe;
import com.github.whvixd.util.InvokeTask;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

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
     * <p>
     * 同步容器
     */
    private Map<String, List<SubscriberMessage>> syncContainer;
    /**
     * 异步容器
     */
    private Map<String, List<SubscriberMessage>> asyncContainer;
    /**
     * 线程池
     */
    @Setter
    private ThreadPoolExecutor executor;

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
        /**
         * 同一订阅者中若有异步，则优先执行
         */
        getThreadPool(getContainerCount()).execute(
                InvokeTask.newInstance(() -> execute(report.getClazzName(), report)));
    }

    private void setContainer(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Prior clazzPrior = clazz.getDeclaredAnnotation(Prior.class);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods.length != 0) {
            for (Method method : declaredMethods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe != null) {
                    SubscriberMessage message = SubscriberMessage.builder().
                            methodMessage(method).
                            subscriber(subscriber).
                            clazz(clazz);
                    if (clazzPrior != null) {
                        initContainer(getKey(method), message, clazzPrior);
                    } else {
                        Prior methodPrior = method.getAnnotation(Prior.class);
                        initContainer(getKey(method), message, methodPrior);
                    }
                }
            }
        }
    }

    private void initContainer(String key, SubscriberMessage message, Prior prior) {
        if (key == null || message == null) {
            return;
        }
        if (prior != null) {
            if (asyncContainer == null) {
                asyncContainer = new ConcurrentHashMap<>();
            }

            setMessages(key, message, getMessages(key, asyncContainer), asyncContainer);
        } else {
            if (syncContainer == null) {
                syncContainer = new ConcurrentHashMap<>();
            }
            setMessages(key, message, getMessages(key, syncContainer), syncContainer);
        }
    }

    private List<SubscriberMessage> getMessages(String key, Map<String, List<SubscriberMessage>> container) {
        if (container == null) {
            container = new ConcurrentHashMap<>();
        }
        return container.get(key);
    }

    private void setMessages(String key,
                             SubscriberMessage message,
                             List<SubscriberMessage> messages,
                             Map<String, List<SubscriberMessage>> container) {
        if (messages != null) {
            messages.add(message);
        } else {
            messages = new ArrayList<>();
            messages.add(message);
            container.put(key, messages);
        }
    }

    private void initThreadPool() {
        if (Objects.isNull(executor)) {
            setExecutor(new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>()));
        }
    }

    private ThreadPoolExecutor getThreadPool(int corePoolSize) {
        return new ThreadPoolExecutor(corePoolSize, corePoolSize * 2, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    private String getKey(Method method) {
        if (method == null || method.getParameterTypes().length == 0) {
            return null;
        }
        return method.getParameterTypes()[0].getSimpleName();
    }

    private void execute(String key, Report report) {
        initThreadPool();
        if (checkNullMap(asyncContainer) && checkNullList(asyncContainer.get(key))) {
            asyncContainer.get(key).forEach(message -> executor.execute(
                    InvokeTask.newInstance(() -> message.invoke(report.getContent()))));
        }

        if (checkNullMap(syncContainer) && checkNullList(syncContainer.get(key))) {
            syncContainer.get(key).forEach(message -> message.invoke(report.getContent()));
        }
    }

    private Integer getContainerCount() {
        int count = 0;
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(Map.class)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkNullList(List<?> messages) {
        return messages != null && messages.size() != 0;
    }

    public boolean checkNullMap(Map<?, ?> container) {
        return container != null && container.size() != 0;
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
