package com.github.whvixd.demo.jdk.enumeration;

/**
 * Created by wangzhx on 2020/6/3.
 */
public class Factory {
    private Object object=new Object();

    public Factory(){
        Type.object=this.object;
    }


    public Handler createHandler(String type){
        return Type.valueOf(type);
    }

    interface Handler{
        void doSomething();
    }

    enum Type implements Handler{
        Car {
            @Override
            public void doSomething() {
                object.getClass();
                System.out.println("Car");
            }
        },Film {
            @Override
            public void doSomething() {
                System.out.println("Film");
            }
        };
        private static Object object;
    }

    public static void main(String[] args) {
        Factory factory = new Factory();
        Handler handler= factory.createHandler("Car");
        handler.doSomething();
    }
}
