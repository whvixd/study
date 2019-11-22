package com.github.whvixd.syntax

import com.github.whvixd.BaseTest
import com.google.common.collect.Lists
import lombok.AllArgsConstructor
import lombok.Data

class SpecificationShare extends BaseTest {
    def "expect 断言（assert）"() {
        expect:
        sum(1, 1) == 2
    }

    def "setup given"() {
        setup://也可以用given代替
        def name = "vhsj"

        println name
    }

    def "when then 配合使用，when赋值，then对结果进行判断"() {
        when:
        def a = 21 + 211 + 211

        then:
        a > 20
    }

    def "when then 配合使用，thrown()抛出异常"() {
        when:
        throw new NullPointerException("eqwe")

        then:
        def e = thrown(rst)
        e.message == message

        where:
        _ || rst              | message
        _ || RuntimeException | "eqwe"

    }

    def "where"() {
        expect:
        Math.max(a, b) == c

        where:
        // 入参||结果
        a  | b  || c
        1  | 3  || 3
        23 | 11 || 23
    }

    def "<< where"() {
        expect:
        Math.max(a, b) == expected

        where:

        a << [66, 1, 11]
        b << [11, 2, 11]

        expected << [66, 2, 11]
    }

    def "expect where"() {
        expect:
        Math.min(a, b) == c

        where:
        a | _
        1 | _
        2 | _

        b << [2, 1]
        c = a < b ? a : b
    }

    def "Mock"() {
        given:
        def personA = [getName: { "Tom" }] as Person

        Person personB = Mock(Person.class)
        personB.getName() >> "Jam"

        println personA.getName()
        println personB.getName()
    }

    def "<< 可以向集合中添加元素"() {
        given:
        List<Integer> list = []
        list << 2

        println list[-1] //获取倒数第一个元素

    }


    def sum(a, b) {
        a + b
    }

    def ">>"() {
        given:
        def teacher = Mock(Teacher.class)
        def student = new Student(id: 1, name: "jams", age: 21, teacher: teacher)
        student.getStudents(_ as String) >> ["1000"]
        teacher.getName() >> "Tom"

        when:
        def name = student.getTeacherName()
        then:
        name == expectName
        where:
        rst      | expectName
        ["1000"] | "Tom"
    }

    def "duplicate list element"() {
        given:
        def list = ["_" * 2] * 3 + ["|"]//__,__,__,|
        println list//4

    }

    @Data
    @AllArgsConstructor
    class Student {
        Integer id
        String name
        Integer age
        Teacher teacher

        def getTeacherName() {
            teacher.getName()
        }

        def getStudents(String id) {
            Lists.newArrayList(id)
        }

    }

    @Data
    @AllArgsConstructor
    class Teacher {
        int id
        String name

        String getName() {
            String point = "."
            return name + point
        }
    }

    interface Person {
        String getName()
    }
}
