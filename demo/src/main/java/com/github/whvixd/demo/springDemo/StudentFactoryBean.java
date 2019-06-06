package com.github.whvixd.demo.springDemo;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by wangzhx on 2018/10/17.
 */
@Data
public class StudentFactoryBean implements FactoryBean<Bean> {
    private String name;

    //Bean 可以用范型替代，再加个代理，去获取用Bean中的范型

    @Override
    public Bean getObject() throws Exception {
        Bean bean = new Bean();
        bean.setName(name);
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return Bean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
