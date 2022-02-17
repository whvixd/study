package com.github.whvixd.spring.boot.starter.demo.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = DemoProperties.PREFIX)
@ToString
public class DemoProperties {
    public static final String PREFIX = "spring.demo.property";

    private String name;

    @NestedConfigurationProperty
    private DemoChildrenConfig demoChildrenConfig=new DemoChildrenConfig();
}
