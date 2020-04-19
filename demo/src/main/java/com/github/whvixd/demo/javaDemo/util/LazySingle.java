package com.github.whvixd.demo.javaDemo.util;

/**
 * Created by wangzhx on 2020/4/19.
 */
public class LazySingle {

    // volatile 避免指令重排序
    private static volatile LazySingle instance;

    private LazySingle() {

    }

    public static LazySingle getInstance() {
        if (instance == null) {
            // 多线程进入，排队等待
            synchronized (LazySingle.class) {
                // 因为是多线程进入，所以需要再次判断，若没有，则会创建多个对象，第一层判断可以过滤掉其他线程
                if (instance == null) {
                    // jvm层面创建对象过程：1. 开辟堆上内存空间，2. 调用构造方法，3. 分配指针指向实例对象的内存地址
                    // 由于编译优化，2、3操作没有依赖关系，可能执行顺序1->3->2，添加volatile就会顺序执行，1->2->3
                    // 单线程 1->3->2 执行没问题，多线程可能会有问题，线程A 执行1操作时，时间片用完，线程B执行1->3，时间片用完，线程A继续执行，发现不为空，直接返回
                    instance = new LazySingle();
                }
            }
        }
        return instance;
    }
}
