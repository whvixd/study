package com.github.whvixd.demo.jdk.enumeration;

import java.util.function.Function;

public enum Demo {
    A("a", 11), B("b", 22);

    private String sb;
    private int num;

    Demo(String sb, int num) {
        this.sb = sb;
        this.num = num;
    }
}

class DemoParse {
    private static final DemoParse A = new DemoParse("a", 11);
    private static final DemoParse B = new DemoParse("b", 22);

    private String sb;
    private int num;

    public DemoParse(String sb, int num) {
        this.sb = sb;
        this.num = num;
    }

    public static <T extends DemoParse> T valueOf(T t) {
        return t;
    }

    public static void main(String[] args) {
        DemoParse demoParse = DemoParse.valueOf(DemoParse.A);

        Tenum a = Tenum.A;
        a.v();
    }
}

//首先调用A中的v().如果A中没有重写v()方法，则调用外层的v()
enum Tenum {
    A {
        public void v() {
            System.out.println("A");
        }
    };

    public void v() {
        System.out.println("v()");
    }
}
enum T{
    A(new Function<String, String>() {
        @Override
        public String apply(String s) {
            return null;
        }
    });

    T(Function<String, String> function) {

    }
}
