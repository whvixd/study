package com.github.whvixd.util.bean;

import lombok.experimental.UtilityClass;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * 无参构造器之间的类型转换
 * 属性之间浅copy
 * <p>
 * Created by wangzhixiang on 2020/6/16.
 */
@UtilityClass
public class BeanUtil {

    // 将before转为after
    public static <Before, After> After transfer(Before before, Class<After> afterClass, Processor<Before, After> processor) {
        try {
            After after = transfer(before, afterClass);
            processor.accept(before, after);
            return after;
        } catch (Exception e) {
            throw new RuntimeException("Bean transfer error!",e);
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

    public static <Before, After> After transfer(Before before, Class<After> afterClass) {
        try {
            After after = afterClass.getDeclaredConstructor().newInstance();
            PropertyDescriptor[] beforePropertyDescriptors = getPropertyDescriptor(before);
            PropertyDescriptor[] afterPropertyDescriptors = getPropertyDescriptor(after);

            for (PropertyDescriptor descriptor : afterPropertyDescriptors) {
                for (PropertyDescriptor beforePropertyDescriptor : beforePropertyDescriptors) {
                    if (beforePropertyDescriptor.getName().equals(descriptor.getName())) {
                        Object value = beforePropertyDescriptor.getReadMethod().invoke(before);
                        descriptor.getWriteMethod().invoke(after, value);
                        break;
                    }
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
    public interface Processor<Before, After> extends BiConsumer<Before, After> {
    }

    public boolean diff(Object o1, Object o2) {
        if (o1 == null && o2 == null) return false;
        if (o1 == null || o2 == null) return true;
        if(!o1.getClass().isAssignableFrom(o2.getClass())){
            throw new RuntimeException();
        }
        Map<String, Object> objectMap1 = toMap(o1);
        Map<String, Object> objectMap2 = toMap(o2);
        if (objectMap1.size() != objectMap2.size()) return true;
        for (String key : objectMap1.keySet()) {
            if (objectMap2.containsKey(key)) {
                Object v1 = objectMap1.get(key);
                Object v2 = objectMap2.get(key);
                if (!Objects.equals(v1, v2)) return true;
            }else {
                return true;
            }
        }
        return false;
    }

}
