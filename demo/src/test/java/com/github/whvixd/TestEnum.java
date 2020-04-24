package com.github.whvixd;

import com.github.whvixd.demo.jdk.enumeration.Car;

import com.github.whvixd.demo.jdk.enumeration.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by wangzhx on 2018/6/28.
 */
public class TestEnum {

    @Test
    public void test() {
        Class kClazz = Car.KOENIGSEGG.getClass();
        Field[] fields = kClazz.getFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) &&
                    Modifier.isStatic(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers())) {
                System.out.println(field.getName() + "元素的修饰符为public static final");
            }
        }
    }

    @Test
    public void testFactory() {
        //创建入参创建对应的实例
        Factory.ICar iCar = Factory.ICarImpl.valueOf(Car.KOENIGSEGG.name());

        //用KOENIGSEGG这个对象去调用printCatName方法
        Assert.assertSame("科尼赛克价格：1.0E9",
                iCar.printCatName(Factory.Car.KOENIGSEGG));
    }


}
