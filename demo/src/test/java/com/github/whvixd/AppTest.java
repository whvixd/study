package com.github.whvixd;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppTest {
    //验证数字是否在有效范围內
    public boolean isNumberSize(String expression) {
        //${Account.number}+99999999999999999999999999 false
        //Day(3123)+${Account.create_by}
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            if (matcher.group().length() > 14) {
                return false;
            }
        }
        return true;
    }

    @org.junit.Test
    public void test() throws Throwable {
//        Assert.assertTrue(isNumberSize("${a.b}+999999990"));
//        Assert.assertTrue(isNumberSize("Day(3123)+${Account.create_by}"));

        MethodHandle methodHandle = getMethodHandler(this.getClass().getDeclaredMethod("print",String.class));
        Object appTest = new AppTest();
        String name = "ewq";
        methodHandle.bindTo(appTest).invokeExact(name);
//        System.out.println(methodHandle);
    }

    public void print(String name){
        System.out.println("ttt"+name);
    }


    private MethodHandle getMethodHandler(Method method)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, java.lang.reflect.InvocationTargetException {

        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        constructor.setAccessible(true);

        Class<?> declaringClass = method.getDeclaringClass();
        int allModes = (MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE);
        return constructor.newInstance(declaringClass, allModes)
                .unreflectSpecial(method, declaringClass);
    }
}
