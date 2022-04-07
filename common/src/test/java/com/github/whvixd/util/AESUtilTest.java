package com.github.whvixd.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangzhixiang on 2022/04/07.
 */
public class AESUtilTest {
    @Test
    public void test() throws Exception {
        // https://www.imaegoo.com/2020/aes-key-generator/  密钥生成器
        String key="s7P3SebpFpusrKEYWgrgZg==";


        String f = AESUtil.encrypt(key, "123");
        System.out.println(f);

        String decrypt = AESUtil.decrypt(key, f);
        System.out.println(decrypt);
    }

    @Test
    public void testGenerateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
            kg.init(128);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String secret = Base64.encodeBase64String(b);
            System.out.println(secret);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此算法");
        }
    }
}
