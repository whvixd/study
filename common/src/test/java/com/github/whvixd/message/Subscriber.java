package com.github.whvixd.message;

import com.github.whvixd.annotation.Prior;
import com.github.whvixd.annotation.Subscribe;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhx on 2019/6/25.
 * 订阅者
 *
 * 注册 @Subscribe 的类
 * 服务 推消息，跟进消费的入参类型去执行
 */
public class Subscriber {

    @Subscribe
    public void toDo(String action) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        System.out.println(getClass().getSimpleName()+action);
    }

    @Subscribe
    @Prior
    public void toDo(Integer action){
        System.out.println(action);
    }
}
