package com.github.whvixd.demo.mybatisDemo.mapper;

import com.github.whvixd.demo.mybatisDemo.model.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangzhx on 2020/3/7.
 */
public interface StudentMapper {

    @Select(value = "select * from student")
    List<Student> findAll();
}
