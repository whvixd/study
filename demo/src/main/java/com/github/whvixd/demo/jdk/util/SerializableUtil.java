package com.github.whvixd.demo.jdk.util;

import lombok.Setter;

import java.io.*;

/**
 * Created by wangzhx on 2019/5/9.
 */
public class SerializableUtil {

    static class Demo implements Serializable{
        @Setter
        private long id;
        private String name;

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Demo demo = new Demo();
        demo.setId(23131231L);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("template"));
        objectOutputStream.writeObject(demo);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("template"));
        Demo demo1 = (Demo) objectInputStream.readObject();
        System.out.println(demo1.id);
    }
}
