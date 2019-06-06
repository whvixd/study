package com.github.whvixd.demo.enumDemo;

public enum Operation implements Function {
    /* PLUS, MINUS, TIMES, DIVIDE;

     double apply(double x, double y) {
         switch (this) {
             case PLUS:
                 return x + y;
             case MINUS:
                 return x - y;
             case TIMES:
                 return x * y;
             case DIVIDE:
                 return x / y;
         }
         throw new AssertionError("Unknown op" + this);
     }*/
    PLUS("+") {
        //常量的类主体
        @Override
        public double getValue(double x, double y) {
            return x + y;
        }

        @Override
        public void printValue() {
        }
    },
    MINUS("-") {
        @Override
        public double getValue(double x, double y) {
            return x - y;
        }

        @Override
        public void printValue() {

        }
    },
    TIMES("*") {
        @Override
        public double getValue(double x, double y) {
            return x * y;
        }

        @Override
        public void printValue() {

        }
    },
    DIVIDE("/") {
        @Override
        public double getValue(double x, double y) {
            return x / y;
        }

        @Override
        public void printValue() {

        }
    };

    private final String symbol;

    @Override
    public String toString() {
        return symbol;
    }

    Operation(String symbol) {
        this.symbol = symbol;
    }

}

interface Function {
    double getValue(double x, double y);

    void printValue();
}

class Test {
    public static void main(String[] args) {
        double x = 4;
        double y = 2;
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f %n", x, op, y, op.getValue(x, y));
        }
    }
}