package com.github.whvixd.demo.jdk.exception;

/**
 * Created by wangzhx on 2019/4/15.
 */
public class ExceptionDemo {
    private static void get(){
        throw  new NullPointerException();
    }


    public static void main(String[] args) {
        try {
            get();
        }catch (EnumConstantNotPresentException e){
            System.out.println("EnumConstantNotPresentException");
        }catch (NullPointerException e){

        }
    }
}
