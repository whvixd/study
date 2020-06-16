package com.github.whvixd.util

import com.github.whvixd.BaseTest
import com.github.whvixd.util.bean.BeanUtil
import lombok.Data

import java.util.function.BiConsumer

/**
 * Created by wangzhixiang on 2020/6/15.
 */
class BeanUtilTest extends BaseTest {
    def "test_transfer"() {
        when:
        def after = BeanUtil.transfer(before, afterClass)
        def expectedAfterMap = BeanUtil.toMap(expectedAfter)

        then:
        BeanUtil.toMap(after).forEach(new BiConsumer<String, Object>() {
            @Override
            void accept(String k, Object v) {
                if (expectedAfterMap.containsKey(k)) {
                    if (k == "metaClass") {
                        return
                    }
                    assert v == expectedAfterMap.get(k)
                }
            }
        })
        where:
        before                                                | afterClass  || expectedAfter
        new Before(id: 1, age: 20, name: "test", score: 99.0) | After.class || new After(id: 1, age: 20, name: "test", score: 99.0)
        new Before(id: 1, age: 20, score: 99.0)               | After.class || new After(id: 1, age: 20, score: 99.0)
    }

    @Data
    static class Before {
        long id
        int age
        String name
        double score
    }

    @Data
    static class After {
        long id
        int age
        String name
        double score
    }


}
