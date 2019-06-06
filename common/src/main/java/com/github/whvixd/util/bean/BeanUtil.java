package com.github.whvixd.util.bean;

import java.util.function.BiConsumer;

/**
 * 无参构造器之间的类型转换
 */
public class BeanUtil {

    public static <SRC, RET> RET transfer(SRC src, Class<RET> retClass, Processor<SRC, RET> processor) {
        try {
            RET ret = retClass.getConstructor().newInstance();
            processor.doSomething(src, ret);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException("Class Cast Error!");
        }
    }

    public static <Before, After> After transfer(Before before, Class<After> afterClass, BiConsumer<Before, After> biConsumer) {
        try {
            After afterEntity = afterClass.getDeclaredConstructor().newInstance();
            biConsumer.accept(before, afterEntity);
            return afterEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Class Cast Error!");
        }
    }

    @FunctionalInterface
    public interface Processor<SRC, RET> {
        void doSomething(SRC src, RET ret);
    }

}
