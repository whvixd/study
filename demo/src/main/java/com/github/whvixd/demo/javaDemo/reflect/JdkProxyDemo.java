package com.github.whvixd.demo.javaDemo.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface JdkProxyDemo {
    interface Person {
        String sing(String name);

        String dance(String name);
    }

    class Lqdehw implements Person {

        @Override
        public String sing(String name) {
            System.out.println("刘德华唱" + name + "歌!!");
            return "歌唱完了,谢谢大家!";
        }

        @Override
        public String dance(String name) {
            System.out.println("刘德华在跳" + name + "舞!");
            return "舞跳完了,谢谢大家!";
        }
    }

    class LqdehwProxy {

        private Person person = new Lqdehw();

        Person getProxy() {
            return (Person) Proxy.newProxyInstance(LqdehwProxy.class.getClassLoader(), person.getClass().getInterfaces(), (proxy, method, args) -> {
                if (method.getName().equals("sing")) {
                    System.out.println("我是他的经纪人，要找他唱歌得先给十万块钱！！");
                    //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去唱歌！
                    return method.invoke(person, args); //代理对象调用真实目标对象的sing方法去处理用户请求
                }
                //如果调用的是代理对象的dance方法
                if (method.getName().equals("dance")) {
                    System.out.println("我是他的经纪人，要找他跳舞得先给二十万块钱！！");
                    //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去跳舞！
                    return method.invoke(person, args);//代理对象调用真实目标对象的dance方法去处理用户请求
                }

                return this;
            });
        }

    }

    class ProxyTest {
        void test(String name){}

        public static void main(String[] args) throws NoSuchMethodException {
            LqdehwProxy lqdehwProxy = new LqdehwProxy();
            Person person = lqdehwProxy.getProxy();
            System.out.println(person.dance("dada"));

//            Method method = Lqdehw.class.getDeclaredMethod("sing",String.class);
//            System.out.println(method.isDefault());//1.8 接口中可以添加方法体，用default修饰符

        }
    }
}
