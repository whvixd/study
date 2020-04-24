package com.github.whvixd.demo.jdk.util;

import com.github.whvixd.demo.Entity;
import com.google.common.collect.Lists;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by wangzhx on 2019/1/21.
 */
public class ComparableDemo implements Comparable<Entity.Course> {
    @Override
    public int compareTo(Entity.Course course) {
        if (course.getScore() > 10) return 1;
        if (course.getScore() < 10) return -1;
        return 0;
    }

    public static void main(String[] args) {
        Entity.Course course1 = new Entity.Course("比贼", 100);
        Entity.Course course2 = new Entity.Course("啊贼", 9);
        Entity.Course course3 = new Entity.Course("比", -10);
        Entity.Course course4 = null;
        List<Entity.Course> courses = Lists.newArrayList(course1, course2, course3,course4);
        Collections.sort(courses);
//        sort(courses);
        System.out.println(courses);
    }


    private static void sort(List<Entity.Course> courses) {
        courses.sort((course1, course2) -> {
            Comparator<Object> com = Collator.getInstance(Locale.CHINA);
            return com.compare(course1.getCourseName(), course2.getCourseName());
        });
    }
}
