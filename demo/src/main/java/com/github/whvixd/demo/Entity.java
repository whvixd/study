package com.github.whvixd.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public interface Entity {

    @Data
    class Student {
        private int id;
        private String name;
        private Course course;//如果不注入，及在类加载时候默认为null，如果注入bean（没有初始化），既该类里面的参数为默认值

        @Autowired//其实该注解是<bean autowired="byType">的属性，可以根据类型自动引用Context中的Bean
        public void setCourse(Course course) {
            this.course = course;
        }

        public Student(){}

        public Student(int id, String name, Course course) {
            this.id = id;
            this.name = name;
            this.course = course;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Course implements Comparable<Course>{
        private String courseName;
        private double score;

        @Override
        public int compareTo(Course course) {
            Comparator<Object> com = Collator.getInstance(Locale.CHINA);
            return com.compare(this.getCourseName(), course.getCourseName());
        }
    }
}
