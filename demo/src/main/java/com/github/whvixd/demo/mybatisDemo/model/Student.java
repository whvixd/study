package com.github.whvixd.demo.mybatisDemo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by wangzhx on 2020/3/7.
 */
@Data
public class Student {
    private String id;
    private String name;
    private Date birthday;
    private String sex;
}
