package com.github.whvixd

import com.fasterxml.jackson.core.type.TypeReference

import com.github.whvixd.util.JacksonUtil
import com.google.gson.reflect.TypeToken
import org.apache.commons.io.IOUtils
import spock.lang.Specification

import com.github.whvixd.util.GsonUtil

class BaseTest<T> extends Specification {

    def getEntityFromFile(String fileName, Class<T> clazz) {
        GsonUtil.fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/" + fileName)),
                clazz)
    }

    Map getMapFromFile(String fileName) {
        GsonUtil.fromJson(IOUtils.toString(new FileInputStream(BaseTest.class.getResource("/data/" + fileName).getPath())),
                Map.class)
    }

    def <T> T getEntityFromFile(String fileName) {
        T t = GsonUtil.fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/" + fileName)),
                new TypeToken<T>() {}.getType())
        if (Objects.isNull(t)) {
            t = JacksonUtil.fromJson(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/" + fileName)),
                    new TypeReference<T>() {})
        }
        return t
    }
}
