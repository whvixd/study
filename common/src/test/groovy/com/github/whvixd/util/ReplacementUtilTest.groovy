package com.github.whvixd.util

import com.github.whvixd.BaseTest

/**
 * Created by wangzhixiang on 2020/8/6.
 */
class ReplacementUtilTest extends BaseTest{
    def "replaceStringWithRegex"(){
        given:
        def regex = ReplacementUtil.replaceStringWithRegex("{a}--{b}--{c}--{d}--", [a: "1", "b": "2"], "\\{.*}")
        println regex
    }
}
