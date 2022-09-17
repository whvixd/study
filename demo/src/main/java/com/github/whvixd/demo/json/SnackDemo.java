package com.github.whvixd.demo.json;

import com.github.whvixd.demo.Entity;
import org.noear.snack.ONode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhx on 2022/6/11.
 */
public class SnackDemo {
    public static void main(String[] args) {
        Entity.Student student = new Entity.Student();
        student.setId(1);
        student.setName("Tom");
        Entity.Course course = new Entity.Course("chinese", 100.00);
        student.setCourse(course);
        String json = ONode.stringify(student);
        System.out.println(json);
//        json = ONode.serialize(json);
        System.out.println(json);

        Entity.Student user = ONode.deserialize(json, Entity.Student.class);
        System.out.println(user);

//        List<Entity.Student> list = ONode.deserialize(json, (new ArrayList<Entity.Student>(){}).getClass());

        ONode oNode = ONode.loadObj(student);
        Entity.Course course1 = oNode.get("course").toObject(Entity.Course.class);
        System.out.println(course1);


    }
}
