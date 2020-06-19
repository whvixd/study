package com.github.whvixd.demo.jdk.util;

/**
 * Created by wangzhx on 2020/4/19.
 */
public class LazySingle {

    // volatile作用:
    //      a. 避免指令重排序;
    //      b. 保证变量可见性
    //      c. 内存屏蔽
    private static volatile LazySingle instance;

    private LazySingle() {}

    // 前提:多线程情况下
    // 为了保证线程安全，使用同步锁机制和双重判空逻辑
    public static LazySingle getInstance() {
        // 第一层判空逻辑，拦截已经初始化的实例
        if (instance == null) {
            // 同步锁机制，即多线程进入代码，排队等待执行
            synchronized (LazySingle.class) {
                // 第二层判断的原因:多线程进入，可能存在两个线程同时执行第一层判空，需要再次判断是否为空，若没有判空逻辑，则会出现创建多个实例的问题
                if (instance == null) {
                    // jvm层面创建对象过程：1. 开辟堆上内存空间，-> 2. 调用构造方法，-> 3. 分配指针指向实例对象的内存地址
                    // 由于编译优化，2、3操作没有依赖关系，可能执行顺序1->3->2，也可能是1->2->3，不过volatile可避免指令重排序，则顺序执行:1->2->3
                    // 不添加volatile在单线程情况下 1->3->2 执行没问题，不过在多线程下可能会有问题
                    // 比如线程A执行1操作时，时间片用完，线程B进入执行1->3，时间片用完，线程A继续执行，发现不为空，直接返回，其实还没有执行构造方法，对象还是没有实例化
                    instance = new LazySingle();
                }
            }
        }
        return instance;
    }
}
