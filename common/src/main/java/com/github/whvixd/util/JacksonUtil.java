package com.github.whvixd.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;

/**
 * toJson需要对象setter方法
 * <p>
 * Created by wangzhx on 2018/5/23.
 */
@UtilityClass
public class JacksonUtil {
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper()
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    public static String toJson(Object object) {
        try {
            return OBJECTMAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("json转换失败,object:%s", String.valueOf(object)));
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return OBJECTMAPPER.readValue(json, tClass);
        } catch (IOException e) {
            throw new RuntimeException(String.format("json转换失败,object:%s", json));
        }
    }

    /**
     * 支持泛型转化
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeReference typeReference) {
        try {
            return OBJECTMAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("json转换失败！");
        }
    }

    public static <T> T fromJson(String json) {
        try {
            return OBJECTMAPPER.readValue(json, new TypeReference<T>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换失败！");
        }
    }


    public static Object fromJson(String json, JavaType javaType) {
        try {
            return OBJECTMAPPER.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换失败！");
        }
    }

    /**
     * Map<String,Integer> map = JacksonUtil.fromJson("{\"a\":21}",HashMap.class,String.class,Integer.class);
     *
     * @param json
     * @param parametrized
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> parametrized, Class... classes) {
        try {
            return OBJECTMAPPER.readValue(json,
                    OBJECTMAPPER.getTypeFactory().constructParametricType(parametrized, classes));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json转换失败！");
        }
    }
}
