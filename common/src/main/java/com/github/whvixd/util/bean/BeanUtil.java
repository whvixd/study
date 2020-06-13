package com.github.whvixd.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * todo test
 * 无参构造器之间的类型转换
 */
public class BeanUtil {

    // 将before转为after
    public static <Before, After> After transfer(Before before, Class<After> afterClass, Processor<Before, After> processor) {
        try {
            After after = copy(before, afterClass);
            processor.accept(before, after);
            return after;
        } catch (Exception e) {
            throw new RuntimeException("Class Cast Error!");
        }
    }

    // 根据bean的getter方法将bean转为map
    public static Map<String, Object> toMap(Object bean) {
        if (bean == null) return null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, Object> map = new HashMap<>();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                Object propertyValue = descriptor.getReadMethod().invoke(bean);
                map.put(descriptor.getName(), propertyValue);
            }
            return map;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // 将Before中的字段复制到after中，注：需要setter和getter方法
    public static <Before, After> void copyProperty(Before before, After after) throws IntrospectionException {
        PropertyDescriptor[] beforePropertyDescriptors = getPropertyDescriptor(before);
        PropertyDescriptor[] afterPropertyDescriptors = getPropertyDescriptor(after);
        try {
            for (PropertyDescriptor descriptor : afterPropertyDescriptors) {
                for (PropertyDescriptor beforePropertyDescriptor : beforePropertyDescriptors) {
                    Object value = beforePropertyDescriptor.getReadMethod().invoke(before);
                    descriptor.getWriteMethod().invoke(after, value);
                }
            }
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static <Before, After> After copy(Before before, Class<After> afterClass){
        try {
            After after = afterClass.getDeclaredConstructor().newInstance();
            PropertyDescriptor[] beforePropertyDescriptors = getPropertyDescriptor(before);
            PropertyDescriptor[] afterPropertyDescriptors = getPropertyDescriptor(after);

            for (PropertyDescriptor descriptor : afterPropertyDescriptors) {
                for (PropertyDescriptor beforePropertyDescriptor : beforePropertyDescriptors) {
                    Object value = beforePropertyDescriptor.getReadMethod().invoke(before);
                    descriptor.getWriteMethod().invoke(after, value);
                }
            }
            return after;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private static PropertyDescriptor[] getPropertyDescriptor(Object bean) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            return beanInfo.getPropertyDescriptors();
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // 支持自定义修改字段
    public interface Processor<Before, After> extends BiConsumer<Before, After>{
    }

}
