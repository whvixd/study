package com.github.whvixd.util

import com.github.whvixd.BaseTest
import com.github.whvixd.util.bean.BeanUtil
import lombok.Data
import lombok.NoArgsConstructor

/**
 * Created by wangzhixiang on 2020/6/15.
 */
class BeanUtilTest extends BaseTest {
    def "test_transfer"() {
        when:
        def after = BeanUtil.transfer(before, afterClass)
        println(GsonUtil.toJson(after))
        then:
        // todo 添加属性判断
        after == expectedAfter
        where:
        before                                                | afterClass  || expectedAfter
        new Before(id: 1, age: 20, name: "test", score: 99.0) | After.class || new After(id: 1, age: 20, name: "test", score: 99.0)
    }

    @Data
    @NoArgsConstructor
    static class Before {
        long id
        int age
        String name
        double score
    }

    @Data
    @NoArgsConstructor
    static class After {
        long id
        int age
        String name
        double score
    }


}
