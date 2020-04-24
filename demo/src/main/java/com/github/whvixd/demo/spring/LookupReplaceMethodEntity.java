package com.github.whvixd.demo.spring;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by wangzhx on 2018/5/27.
 * test spring new attributes:lookup-method and replace-method
 */
public interface LookupReplaceMethodEntity {
    public class Persion {
        public void showMessage() {
            System.out.println("persion");
        }
    }

    public class Zhangsan extends Persion {
        String name = "张三";

        @Override
        public void showMessage() {
            System.out.println(this.name);
        }

    }

    public abstract class TestLookup {

        public void print() {
            this.getBean().showMessage();
        }

        public abstract Persion getBean();
    }

    public class TestMethodReplacer implements MethodReplacer {
        @Override
        public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
            System.out.println("replace zhangsan's showMessage method");
            return null;
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/firstBean.xml");
        TestLookup testLookup = (TestLookup) context.getBean("testLookup");
        testLookup.print();
    }

}
