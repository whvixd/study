package com.github.whvixd.syntax
/**
 * Created by wangzhx on 2019/2/18.
 * 参考：https://www.jianshu.com/p/e8dec95c4326
 *
 */
class GroovyShareOne {
    //自动生成getter和setter方法
    String name
    Integer age

    def static add(a, b) {
        a + b
    }

    static void main(String[] args) {
        println add(1, 2)

        print new GroovyShareOne(age: 21).getAge()


        forMethod()
        print switchMethod(1)
        stringMethod()
        collection()
        def one = new GroovyShareOne()
        println one.closure("x")

        one.closureMethod([1..3]) { x -> println x }
    }

    def static forMethod() {
        for (def i = 0; i < 3; i++) {
            println i
        }

        for (def i : [1, 2, 3]) {
            println i
        }

        for (def i : 1..3) {
            println i
        }
    }

    def static switchMethod(x) {
        switch (x) {
            case 'string':
                return 'string'
            case [1, 3]:
                return 'array'
            case 1..3:
                return 'range'
            case String:
                return 'String.class'
            default:
                return 'default'
        }
    }

    def static stringMethod() {
        def str = 'share'
        println '${str}' //${str}
        println "${str}" //share
        println "$str" //share

        str = '''
test
    string
'''
        println str
    }

    def static collection() {
        def array = [1, 'a', [2.2, 'bc']] as List
        println array[2][1]

        def map = ['k': 'v', 1: 'b', 'array': [1, 2]]
        println map['array']
    }

    def closureString = 'closure'
    def closure = { x ->
        if ('x' == x) {
            x = '=='
        } else {
            x = '!='
        }
        x
    }

    def closureMethod(array, print) {
        for (def x : array) {
            print(x)
        }
    }

    def number() {
        def number = 8888888888 //def 会根据字面值自动调整类型
        def number_int = 8I //Integer
        def number_float = 8.8F //Float
        def number_double = 8.88D //Double
        def number_big_decimal = 88.88G //BigDecimal
    }

}
