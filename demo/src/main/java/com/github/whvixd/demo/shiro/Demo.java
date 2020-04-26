package com.github.whvixd.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by wangzhx on 2020/4/26.
 */
public class Demo {
    public static void main(String[] args) {
        // 1. 初始化 init
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
        // 2. 获取 securityManager、绑定
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3. 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 4. 用户名、密码
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");
        // 5. 登陆
        subject.login(token);
        assert subject.isAuthenticated();
    }
}
