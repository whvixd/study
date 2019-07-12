package com.github.whvixd.demo.javaDemo.reflect;

import com.google.common.reflect.Reflection;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * todo java原生的代理于guava的代理区别？
 * <p>
 * 代理。当每次调用方法时，都会去代理中去执行自己添加的逻辑
 * Created by wangzhx on 2018/7/15.
 */
public interface DynamicProxy {

    /**
     * 通过注解设置车的速度
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
//如果一个子类想获取到父类上的注解信息，那么必须在父类上使用的注解上面 加上@Inherit关键字
    @interface Speed {
        double value() default 212.1;
    }

    /**
     * 接口
     */
    interface Car {
        @Speed(222)
        String run(String carMessage);

        void print();
    }

    /**
     * 实际类
     */
    class Lamborghini implements Car {

        @Speed(333.33)
        @Override
        public String run(String carMessage) {
            return carMessage;
        }

        @Override
        public void print() {

        }

    }

    /**
     * 代理类
     */
    class LamborghiniProxy {
        private Car car = new Lamborghini();
        private static final String RUNMETHOD = "run";

        /**
         * Java原生写法
         *
         * @param clazz，可以获取实现类中的方式
         * @return
         */
        Car getProxy(Class clazz) {
            return (Car) Proxy.newProxyInstance(Car.class.getClassLoader(), Lamborghini.class.getInterfaces(),//new Class<?>[] {clazz}
                    /**
                     * proxy 要代理的对象
                     * method 代理对象的方法
                     * args 代理对象的方法中的入参
                     */
                    (proxy, method, args) -> {

                        //代理的method 不会将方法上的注解代理过来
                        Method runMethod = clazz.getMethod(RUNMETHOD, String.class);
                        Speed speed = runMethod.getDeclaredAnnotation(Speed.class);
                        if (speed != null) {
                            if (args.length == 1 && args[0] instanceof String) {
                                args[0] += String.valueOf(speed.value());
                            }
                        }
                        return method.invoke(car, args);

                    });
        }

        /**
         * Guava封装了Java原生的代理
         *
         * @param clazz
         * @return
         */
        Car getProxyWithGuava(Class<Car> clazz) {
            return Reflection.newProxy(clazz, (proxy, method, args) -> {
                Method runMethod = clazz.getDeclaredMethod(RUNMETHOD, String.class);
                Speed speed = runMethod.getDeclaredAnnotation(Speed.class);
                if (speed != null) {
                    if (args.length == 1 && args[0] instanceof String) {
                        args[0] += String.valueOf(speed.value());
                    }
                }

                return method.invoke(car, args);
            });
        }

        /**
         * Cglib通过修改字节码的方式实现代理，比Java原生的更强大
         * 可以代理注解，也可以代理实现类
         *
         * @return
         */
        Car getProxyWithCglib() {
            return (Car) Enhancer.create(Car.class,
                    (MethodInterceptor) (proxy, method, args, methodProxy) -> {
                        Speed speed = method.getDeclaredAnnotation(Speed.class);
                        if (speed != null) {
                            if (args.length == 1 && args[0] instanceof String) {
                                args[0] += String.valueOf(speed.value());
                                return methodProxy.invoke(car, args);
//                                return methodProxy.invokeSuper(proxy, args);
                            }
                        }
                        return method.invoke(car, args);
                    }
            );
        }

    }

    class Test {
        public static void main(String[] args) throws NoSuchMethodException {
            LamborghiniProxy lamborghiniProxy = new LamborghiniProxy();
//            Car car = lamborghiniProxy.getProxy(Lamborghini.class);
            Car car = lamborghiniProxy.getProxyWithGuava(Car.class);
//            Car car = lamborghiniProxy.getProxyWithCglib();
//            System.out.println(car.run("速度:"));
            car.run("--");
            car.print();

        }

    }
}
