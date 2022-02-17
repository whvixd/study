package com.github.whvixd.spring.boot.starter.demo.autoconfigure;

import com.github.whvixd.spring.boot.starter.demo.annotation.ActionRecord;
import com.github.whvixd.spring.boot.starter.demo.aop.ActionAnnotationAdvisor;
import com.github.whvixd.spring.boot.starter.demo.aop.ActionAnnotationInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@Configuration
public class DemoAutoConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor actionAnnotationAdvisor() {
        ActionAnnotationInterceptor interceptor = new ActionAnnotationInterceptor();
        return new ActionAnnotationAdvisor(interceptor, ActionRecord.class);
    }
}
