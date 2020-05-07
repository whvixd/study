package com.github.whvixd.message;

import com.github.whvixd.annotation.Prior;
import com.github.whvixd.annotation.Subscribe;
import com.github.whvixd.util.InvokeTask;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
     * 容器
     */
    private Map<String, List<SubscriberMessage>> container;
    /**
     * vip容器
     */
    private Map<String, List<SubscriberMessage>> priorContainer;
    /**
     * 线程池
     */
    private ThreadPoolExecutor executor;

    private boolean isAsync;


    public Agent() {
    }

    public Agent(boolean isAsync) {
        this.isAsync = isAsync;
    }

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
        execute(report.getClazzName(), report);
    }

    /**
     * 若类添加了@Prior，则添加@Subscribe的所有方法都是优先级较高
     * 若方法添加了@Prior，则添加@Subscribe的方法是优先级较高
     */
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
            if (priorContainer == null) {
                priorContainer = new ConcurrentHashMap<>();
            }
            setMessages(key, message, getMessages(key, priorContainer), priorContainer);
        } else {
            if (container == null) {
                container = new ConcurrentHashMap<>();
            }
            setMessages(key, message, getMessages(key, container), container);
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
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                    10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.CallerRunsPolicy());
            setExecutor(executor);
        }
    }

    private String getKey(Method method) {
        if (method == null || method.getParameterTypes().length == 0) {
            return null;
        }
        return method.getParameterTypes()[0].getSimpleName();
    }

    private void execute(String containerKey, Report report) {
        doExecute(containerKey, report, priorContainer);
        doExecute(containerKey, report, container);
    }

    private void doExecute(String containerKey, Report report, Map<String, List<SubscriberMessage>> container) {
        if (isAsync) {
            initThreadPool();
            if (checkNullMap(container) && checkNullList(container.get(containerKey))) {
                container.get(containerKey).forEach(message -> executor.execute(
                        InvokeTask.newInstance(() -> message.invoke(report.getContent()))));
            }
        } else {
            if (checkNullMap(container) && checkNullList(container.get(containerKey))) {
                container.get(containerKey).forEach(message -> message.invoke(report.getContent()));
            }
        }
    }

    public boolean checkNullList(List<?> messages) {
        return messages != null && messages.size() != 0;
    }

    public boolean checkNullMap(Map<?, ?> container) {
        return container != null && container.size() != 0;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

}
