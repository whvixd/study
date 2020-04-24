package com.github.whvixd.demo.jdk.enumeration;

public enum ActionImpl implements Action {
    A {
        @Override
        public String print(String string) {
            System.out.println("A ExceptionHandler");
            return "A";
        }
    },
    B {
        @Override
        public String print(String string) {
            return "B";
        }
    },
    C {
        @Override
        public String print(String string) {
            return "C";
        }
    };

    public static void main(String[] args) {
        String type = Route.Type.A.name();
        Action action = ActionImpl.valueOf(Route.Type.A.name());
        //相当于新建了一个对象，但是不知道是什么类型的，和switch很像
        String s = action.print("");
    }

}
