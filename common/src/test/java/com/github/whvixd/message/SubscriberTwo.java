package com.github.whvixd.message;

import com.github.whvixd.annotation.Subscribe;

/**
 * Created by wangzhx on 2019/6/25.
 * 订阅者
 * <p>
 * 注册 @Subscribe 的类
 * 服务 推消息，跟进消费的入参类型去执行
 */
public class SubscriberTwo {

    @Subscribe
    public void toDo(String action) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(getClass().getSimpleName()+action.getClass().getSimpleName() + action);
    }
}
