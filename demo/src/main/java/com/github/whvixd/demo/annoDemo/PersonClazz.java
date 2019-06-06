package com.github.whvixd.demo.annoDemo;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PersonClazz<T> {
    public T instance(T t) throws NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException, InvocationTargetException {

        /*  Annotation getAnnotation(Class annotationType)	获取注解在其上的annotationType
            Annotation[] getAnnotations()	                获取所有注解
            isAnnotationPresent(Class annotationType)	    判断当前元素是否被annotationType注解
            Annotation[] getDeclareAnnotations()	        与getAnnotations() 类似，但是不包括父类中被Inherited修饰的注解
        */

        //获取类的class
        Class<?> clazz = t.getClass();
        //类中的方法
        Method method = clazz.getDeclaredMethod("setName", String.class);

        //判断该类是否有该注解
        if (method.isAnnotationPresent(DoSomething.class)) {
            DoSomething doSomething = method.getDeclaredAnnotation(DoSomething.class);

            String name = doSomething.name();

            Class methodPara = method.getDeclaringClass();
            Field nameField = methodPara.getDeclaredField("name");
            method.invoke(name);
//            nameField.set("name",name);

        }
        return t;
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        PersonClazz<Person> person = new PersonClazz<>();
        Person p =person.instance(new Person());
        System.out.println(p.getName());
    }
}
