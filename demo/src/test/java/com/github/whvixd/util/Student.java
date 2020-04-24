package com.github.whvixd.util;

import lombok.Data;

@Data
public class Student {

    private String id;
    private String name;
    private Integer age;
    private Double score;
    private Object object;

    public static Student getInstance() {
        Student student = new Student();
        return student;
    }

    public String getName() {

        if("null".equals(name)){
            System.out.println("\"null\"");
        }
        if(name!=null){
            System.out.println("name为null");
        }
        if (name == null) {
            System.out.println("name真为空");
            return name = GsonUtil.toJson(object);
        }
        return name;
    }
}
