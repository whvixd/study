package com.github.whvixd.demo.jdk.reflect;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FileUtil;

/**
 * Created by wangzhixiang on 2021/04/27.
 */
public class IdGenClassLoader extends ClassLoader {
    IdGenClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    // name 为class文件中类的全局限名
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // base64加密的class文件
        String filePath = "/Users/didi/Documents/workspace/idea/study/demo/src/main/java/com/github/whvixd/demo/jdk/reflect/TestBase64.txt";
//        String filePath = "/Users/didi/Documents/workspace/idea/study/demo/src/main/java/com/github/whvixd/demo/jdk/reflect/Test";
//        String filePath = "/Users/didi/Documents/workspace/idea/study/demo/src/main/java/com/github/whvixd/demo/jdk/reflect/IdGen.class";
        byte[] b = FileUtil.readBytes(filePath);
//        String encode = Base64Encoder.encode(b);
        // 解密
        b=Base64Decoder.decode(b);
        return defineClass(name, b, 0, b.length);
    }
}
