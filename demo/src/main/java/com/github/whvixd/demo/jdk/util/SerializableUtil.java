package com.github.whvixd.demo.jdk.util;

import com.github.whvixd.util.JacksonUtil;
import lombok.Setter;

import java.io.*;

/**
 * Created by wangzhx on 2019/5/9.
 */
public class SerializableUtil {

    static class Demo implements Serializable{
        @Setter
        private long id;
        private transient String name;//name不参与本地的序列化，name当作null处理
        private static String nm="J";// 静态变量不参数本地的序列化

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

        // 不会写到本地，所以不依赖 Serializable 参数
        JacksonUtil.toJson(demo);
    }
}
