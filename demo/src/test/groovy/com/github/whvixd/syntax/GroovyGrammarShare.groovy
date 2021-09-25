package com.github.whvixd.syntax

import com.alibaba.fastjson.JSON
import groovy.json.JsonSlurper

/**
 * Created by wangzhx on 2019/10/16.
 * see：https://www.jianshu.com/p/e8dec95c4326
 *
 */
class GroovyGrammarShare {
    //自动生成getter和setter方法
    String name
    Integer age

    def static add(a, b) {
        a = a + 1
        //默认最后一行为返回值
        a + b
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
            case 1..3:
                return 'range'
            case [1,2,3]:
                return 'array'
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

    def static closure = { x ->
        if ('x' == x) {
            x = '=='
        } else {
            x = '!='
        }
        x
    }

    def static closureT = { x, y, z ->
        print x+y+z
    }

    static def closureMethod(array, print) {
        for (def x : array) {
            print(x)
        }
    }

    def static number() {
        def number = 8888888888 //def 会根据字面值自动调整类型
        def number_int = 8I //Integer
        def number_float = 8.8F //Float
        def number_double = 8.88D //Double
        def number_big_decimal = 88.88G //BigDecimal
    }

    def jsonDemo(){
        String json="{\"extra\":\"{\\\\\\\"businessId\\\\\\\":2,\\\\\\\"from\\\\\\\":\\\\\\\"sms\\\\\\\",\\\\\\\"qos_is_realtime\\\\\\\":0,\\\\\\\"requestId\\\\\\\":\\\\\\\"mock_2021090801\\\\\\\",\\\\\\\"templateId\\\\\\\":8457}\"}"

        def map=JSON.parseObject(json,HashMap.class)
        println map.extra
        def extraJson=map.extra as String
        extraJson=extraJson.replaceAll("\\\\","").replaceAll(" ","")
        println extraJson

        JsonSlurper slurper=new JsonSlurper()
        def ex=slurper.parseText(extraJson) as Map
        print ex.businessId as String
    }


    static void main(String[] args) {

        print switchMethod(1)
        println add(1, 2)

        print new GroovyGrammarShare(age: 21).getAge()
        forMethod()

        stringMethod()
        collection()

        closure("x")
        closureT("x","y","z")
        closureMethod([1..3]) { x -> println x }
    }

}
