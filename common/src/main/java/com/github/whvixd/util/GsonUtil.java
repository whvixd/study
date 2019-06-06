package com.github.whvixd.util;

import com.google.gson.*;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;

@UtilityClass
public class GsonUtil {

    /**
     * js中1 与1.0一样的类型（number）
     * 前端转来 1，可能在此方法中转为了 1.0
     * Double longValue() 强转为long类型
     */
    private static Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
        if (src == src.longValue()) {
            return new JsonPrimitive(src.longValue());
        }
        return new JsonPrimitive(src);
    }).create();

    /**
     * 传递字段为null
     */
    private static Gson gsonWithNull = new GsonBuilder().serializeNulls().registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
        if (src == src.longValue()) {
            return new JsonPrimitive(src.longValue());
        }
        return new JsonPrimitive(src);
    }).create();

    /**
     * gson格式化
     */
    private static Gson toJsonPrettily = new GsonBuilder().setPrettyPrinting().create();

    /**
     * json -> 对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * json文件 -> 对象
     *
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T fromJsonWhithFile(String fileName, Class<T> clazz) throws IOException {
        return gson.fromJson(IOUtils.toString(GsonUtil.class.getClassLoader().getResourceAsStream(fileName)), clazz);
    }

    public static <T> T fromJsonWithFile(String fileName, Type type) throws IOException {
        return gson.fromJson(IOUtils.toString(GsonUtil.class.getClassLoader().getResourceAsStream(fileName)), type);
    }

    /**
     * 对象 -> Json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * file为null
     *
     * @param object
     * @return
     */
    public static String toJsonWithNull(Object object) {
        return gsonWithNull.toJson(object);
    }

    /**
     * 格式化json
     *
     * @param object
     * @return
     */
    public static String toPrettyJson(Object object){
        return toJsonPrettily.toJson(object);
    }
}
