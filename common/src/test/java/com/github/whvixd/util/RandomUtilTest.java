package com.github.whvixd.util;

import cn.hutool.core.lang.Pair;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2022/02/10.
 */
public class RandomUtilTest {

    @Test
    public void test() {
        RandomUtil randomUtil = new RandomUtil();
        randomUtil.element(Lists.newArrayList(new Pair<>("A", 10D), new Pair<>("B", 10D), new Pair<>("C", 80D)));
        String random = randomUtil.random();
        System.out.println(random);
    }
}
