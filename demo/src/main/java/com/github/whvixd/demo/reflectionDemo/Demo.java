package com.github.whvixd.demo.reflectionDemo;

import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * Created by wangzhx on 2018/7/11.
 */
public interface Demo {

    class Test {
        private static Map<String,Object> map = Maps.newHashMap();

        public void test(@FieldAnno("name") String name) {
            System.out.println(name);
        }

        public static Test proxy(Class<Test> clazz) {
            return Reflection.newProxy(clazz, (proxy, method, args) -> {
                Parameter[] parameters = method.getParameters();
                for (int i=0;i<parameters.length;i++) {
                    FieldAnno fieldAnno = parameters[i].getAnnotation(FieldAnno.class);
                    String fieldAnnoValue = fieldAnno.value();
                    map.put(fieldAnnoValue,args[i]);
                }
                return null;
            });
        }

        public static void main(String[] args) throws NoSuchMethodException {
            Method method = Test.class.getMethod("test", String.class);
            Parameter[] parameters = method.getParameters();
            for (Parameter p : parameters) {
                FieldAnno fieldAnno = p.getAnnotation(FieldAnno.class);
                System.out.println(fieldAnno.value());//利用代理，将方法中注解的值作为k，参数的值作为map的v
            }
        }
    }
}
