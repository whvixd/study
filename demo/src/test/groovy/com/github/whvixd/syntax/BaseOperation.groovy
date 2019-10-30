package com.github.whvixd.syntax

import com.google.common.collect.Lists
import lombok.Data
import spock.lang.Unroll

import com.github.whvixd.BaseTest

class BaseOperation extends BaseTest {
    def "expect 断言（assert）"() {
        expect:

        sum(1, 1) == 1
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
        def a = null

        then:
        def exception = thrown(NullPointerException)
        exception.cause == null
    }

    def "where"() {
        expect:
        Math.max(a, b) == c

        where:
        a  | b  || c
        1  | 3  || 3
        23 | 11 || 11
    }

    @Unroll
//会将对应的值三个参数的方法运行
    def "@Unroll where"() {
        expect:
        Math.max(a, b) == c

        where:

        a << [66, 1, 11]
        b << [11, 2, 11]
        c << [3, 2, 11]
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
        Student stu1 = new Student(1, "张三", 21)
        Student stu2 = new Student(2, "李四", 22)

        def coder1 = [
                getName: { "my name is whvixd" }
        ] as Coder

        Coder coder2 = Mock([getName: { "coder2" }])
        println coder2
    }

    def "<< 可以向集合中添加元素"() {
        given:
        List<Integer> list = []
        list << 2

        println list[-1] //获取倒数第一个元素

    }


    def sum(a, b) {
        return a + b
    }

    def ">>"() {
        given:
        def teacher = Mock(Teacher.class)
//        def student = Mock(Student.class, teacher: teacher)

        def student = new Student(id: 1, name: "jams", age: 21, teacher: teacher)
        student.getStudents(_) >> ["1000"] // 适用于Mock的对象
        teacher.getName() >> "Tom"

        when:
        List<String> students = student.getStudents("1")
        def name = student.getTeacherName()

        then:
//        students == rst
        name == expectName
        where:
        rst      | expectName
        ["1000"] | "Tom"
    }

    def "duplicate list element"() {
        given:
        def list = ["_" * 2] * 3 + ["|"]
        println list
    }

    @Data
    class Student {
        Integer id
        String name
        Integer age
        Teacher teacher

        Student() {}

        Student(int id, String name, int age) {
            this.id = id
            this.name = name
            this.age = age
        }

        String getTeacherName() {
            return teacher.getName()
        }

        List<String> getStudents(String id) {
            return Lists.newArrayList(id)
        }

    }

    class Teacher {
        int id
        String name

        String getName() {
            String point = "."
            return name + point
        }
    }

    interface Coder {
        String getName()
    }
}
