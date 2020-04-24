package com.github.whvixd.demo.jdk.enumeration;

/**
 * Created by wangzhx on 2018/5/29.
 */
public class TestEnum {
    enum Charac{
        A{
            void b() {
                a();
                System.out.println(this.name());
            }
        };
        void b(){};
        private static void a(){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestEnum.Charac.valueOf("A").b();
    }
}
