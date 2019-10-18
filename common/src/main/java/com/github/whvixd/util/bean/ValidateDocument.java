package com.github.whvixd.util.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验信息提示
 * Created by wangzhx on 2019/10/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface ValidateDocument {
    /**
     * 校验的模块
     */
    String module() default "";

    /**
     * 校验信息
     */
    String message() default "";
}
