package com.github.whvixd.springbootdemo;

import com.github.whvixd.restful.client.annotation.RestfulClientScan;
import com.github.whvixd.springbootdemo.client.HelloClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RestfulClientScan(basePackages = "com.github.whvixd.springbootdemo.client")
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);
		HelloClient bean = context.getBeanFactory().getBean(HelloClient.class);
	}

}
