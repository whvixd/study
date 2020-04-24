package com.github.whvixd.demo.jdk.enumeration;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestInnerEnumMethod {

    private static int id = 1;

    private String name = "张三";

    private int age = 21;

    public void init() {
        InnerEnum.age = this.age;
    }

    private static void classMethod() {
        System.out.println("class_method");
    }

    interface InnerInterface {
        String method();
    }

    class AImpl implements InnerInterface {

        @Override
        public String method() {
            classMethod();
            return null;
        }
    }

    enum InnerEnum implements InnerInterface {
        innerEnum {
            @Override
            public String method() {
                classMethod();
                TestInnerEnumMethod testInnerEnumMethod = new TestInnerEnumMethod();
                return "innerEnum_method" + id + testInnerEnumMethod.name + age;
            }
        };

        private static int age;

        public static void main(String[] args) throws NoSuchFieldException {
            InnerEnum innerEnum = InnerEnum.innerEnum;
            System.out.println(InnerEnum.innerEnum.method());

            Class clazz = innerEnum.getClass();
            if (Modifier.isStatic(clazz.getModifiers())) {
                System.out.println("innerEnum的method方法被static修饰");
            }

            Field innerEnumField = InnerEnum.class.getField("innerEnum");
            if (Modifier.isPublic(innerEnumField.getModifiers()) &&
                    Modifier.isStatic(innerEnumField.getModifiers()) &&
                    Modifier.isFinal(innerEnumField.getModifiers())) {
                System.out.println("innerEnum被public static final修饰");
            }
        }

    }
}