package com.github.whvixd.demo.jdk.thread;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * tags:自动化测试
 * https://www.jianshu.com/p/3aa45532e179
 * Created by wangzhx on 2020/4/3.
 */
public class SeleniumDemo {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // RemoteWebDriver的基本使用

        //第一个参数：表示服务器的地址。第二个参数：表示预期的执行对象，其他的浏览器都可以以此类推
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub/"), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("alert('我现在在服务器')");
        Thread.sleep(2000);
        driver.quit();
    }

}
