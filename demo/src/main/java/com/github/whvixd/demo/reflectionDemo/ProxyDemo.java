package com.github.whvixd.demo.reflectionDemo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {

    private static Animal animal = new Dog();

    public static Animal getProxy() {
        return (Animal) Proxy.newProxyInstance(
                ProxyDemo.class.getClassLoader(),//获取代理的类加载器
                animal.getClass().getInterfaces(),//获取类的接口
                (proxy, method, args) -> {

                    if (method.isDefault()) {
                        MethodHandle methodHandle = getMethodHandler(method);//MethodHandle 比Method更轻量级
                        methodHandle.bindTo(proxy).invokeWithArguments(args);
                    }

                    if (method.getName().equals("type")) {
                        System.out.println("这个一只狗!");
                        return method.invoke(animal, args);
                    }

                    if (method.getName().equals("name")) {
                        System.out.println("它的名字叫大黄!");
                        return method.invoke(animal, args);
                    }

                    if (method.getName().equals("age")) {
                        System.out.println("它今天5岁了!");
                        return method.invoke(animal, args);
                    }
                    return null;
                });
    }

    private static MethodHandle getMethodHandler(Method method)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, java.lang.reflect.InvocationTargetException {

        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        constructor.setAccessible(true);

        Class<?> declaringClass = method.getDeclaringClass();
        int allModes = (MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE);
        return constructor.newInstance(declaringClass, allModes)
                .unreflectSpecial(method, declaringClass);
    }

    public static void main(String[] args) throws Throwable {
        Animal animal = ProxyDemo.getProxy();
        animal.name("---");
        animal.print(Object.class.getName());

        /*Animal animal = new Dog();
        InvocationHandler invocationHandler = (proxy, method, arg) -> {

            if (method.getName().equals("type")) {
                System.out.println("这个一只狗!");
                return method.invoke(animal, arg);
            }

            if (method.getName().equals("name")) {
                System.out.println("它的名字叫大黄!");
                return method.invoke(animal, arg);
            }

            if (method.getName().equals("age")) {
                System.out.println("它今天5岁了!");
                return method.invoke(animal, arg);
            }
            return null;
        };

        Dog dog = (Dog) invocationHandler.invoke(animal, animal.getClass().getMethod("name", String.class), new Object[]{});
        dog.name("dahd");*/
    }
}
