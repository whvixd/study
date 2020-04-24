package com.github.whvixd.demo.jdk.serviceloader;

import java.util.ServiceLoader;

/**
 * Created by wangzhx on 2019/5/24.
 */
public class ServiceLoaderDemo {
    public static void main(String[] args) {
        ServiceLoader<AnimalDemo> serviceLoader = ServiceLoader.load(AnimalDemo.class);
        for (AnimalDemo animalDemo : serviceLoader) {
            animalDemo.say();
        }

    }
}
