package com.github.whvixd.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by wangzhixiang on 2022/04/07.
 */
@Slf4j
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String AES_TYPE = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param keyStr  密钥
     * @param originS 明文
     * @return 密文
     */
    public static String encrypt(String keyStr, String originS) throws Exception {
        byte[] encrypt;
        try {
            Key key = genKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(originS.getBytes());
        } catch (Exception e) {
            log.warn("encrypt exception,e:", e);
            throw e;
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    /**
     * 解密
     *
     * @param keyStr   密钥
     * @param encryptS 密文
     * @return 明文
     */
    public static String decrypt(String keyStr, String encryptS) throws Exception {
        byte[] decrypt;
        try {
            Key key = genKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptS));
        } catch (Exception e) {
            log.warn("decrypt exception,e:", e);
            throw e;
        }
        return new String(decrypt).trim();
    }


    /**
     * @param keyS 密钥
     */
    private static Key genKey(String keyS) throws Exception {
        try {
            return new SecretKeySpec(keyS.getBytes(), ALGORITHM);
        } catch (Exception e) {
            log.warn("genKey exception.", e);
            throw e;
        }
    }
}
