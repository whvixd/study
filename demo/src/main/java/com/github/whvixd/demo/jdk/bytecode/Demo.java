package com.github.whvixd.demo.jdk.bytecode;

/**
 * Created by wangzhx on 2020/4/24.
 */
public class Demo {
    private volatile int a = 0;

    private int add(int b, int c) {
        /*
        字节码:
        0）aload_0 #将this的引用入栈 (aload_0即将局部变量表中索引为0的引用压到操作数栈中)
        1）iload_1 #将参数b入栈 (将局部变量中的索引为1的整数压到操作数栈中)
        2）iload_2 #将参数c入栈
        3）iadd    #将栈顶的两个数相加，并将结果保存至栈顶,此时栈的内容为 b+c,this
        4) putfield#将栈顶的两个值出栈，第一个值（b+c)赋值给第二个值(this)的对应的成员变量(是的，没错即使是赋值也要执行两次出栈操作）
         */
        a = b + c;
        return a;
    }

    public static void main(String[] args) {
        new Demo().add(1, 2);
    }

}
