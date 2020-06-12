package com.github.whvixd.util;

import com.alibaba.fastjson.TypeReference;
import com.github.whvixd.util.bean.BeanUtil;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Json2EntityTest {

    @Test
    public void testFromJson() throws IOException {
        Student s = GsonUtil.fromJson(
                IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/new01.json")), Student.class);
        System.out.println(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/new01.json")));
        System.out.println(s);
    }

    @Test
    public void testTransfer() {
        Entity.Cat cat = new Entity.Cat();
        cat.setName("Tom");
        cat.setOwner("whvixd");
        Entity.Dog dog = BeanUtil.transfer(cat, Entity.Dog.class, (cat1, dog1) -> {
            dog1.setName(cat.getName());
            dog1.setOwner(cat.getOwner());
        });

        Entity.Dog dogC = BeanUtil.transfer(cat, Entity.Dog.class, (cat1, dog1) -> {
            dog1.setName(cat.getName());
            dog1.setOwner(cat.getOwner());
        });

        Assert.assertSame("Tom", dog.getName());
        Assert.assertSame("Tom", dogC.getName());
    }

    @Test
    public void testJackson() {
        com.github.whvixd.demo.Entity.Course course = new com.github.whvixd.demo.Entity.Course("数学", 99.0);
        System.out.println(JacksonUtil.toJson(course));//Jackson可以打印Double --> 99.0
        System.out.println(GsonUtil.toJson(course));//Gson --> 99

        /*---------------------------*/
        String mapString = "{\"a\":21}";
        Map map = JacksonUtil.fromJson(mapString, Map.class, String.class, Integer.class);
        Assert.assertNotNull(map);


    }

    @Test
    public void testFastJsonUtil() {
        List list = Lists.newArrayList("a", 1, new com.github.whvixd.demo.Entity.Course("数学", 99.0));
        Assert.assertNotNull(FastjsonUtil.toJson(list));
        String listJson = "[\"a\",1,{\"courseName\":\"数学\",\"score\":99}]";
        /*---------------------------*/
        List jsonArray = FastjsonUtil.fromArrayJson(listJson,
                new TypeReference<String>() {
                }.getType(),
                new TypeReference<Integer>() {
                }.getType(),
                new TypeReference<com.github.whvixd.demo.Entity.Course>() {
                }.getType());
        Assert.assertEquals(list, jsonArray);

        /*---------------------------*/
        String intListString = "[2,1,1]";
        List<Integer> intList = FastjsonUtil.fromArrayJson(intListString, int.class);
        Assert.assertFalse(intList.isEmpty());
    }

    interface Entity {
        @Data
        class Cat {
            private String name;
            private String owner;
        }

        @Data
        class Dog {
            private String name;
            private String owner;
        }
    }
}
