package com.github.whvixd.util;

import cn.hutool.core.lang.Pair;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by wangzhixiang on 2022/02/10.
 */
public class RandomUtil {

    private final TreeMap<Double, String> treeMap = new TreeMap<>();

    // A(0,10] B(10,20] C(20,100] 落入三个区间的概率
    public void element(List<Pair<String, Double>> list) {
        for (Pair<String, Double> pair : list) {
            double lastRatio = this.treeMap.size() == 0 ? 0 : this.treeMap.lastKey();
            this.treeMap.put(pair.getValue() + lastRatio, pair.getKey());
        }
    }

    public String random() {
        // 1000*0.81=80
        double ratio = this.treeMap.lastKey() * Math.random();

        // 落入[80,100]的区间中
        SortedMap<Double, String> sortedMap = this.treeMap.tailMap(ratio, false);
        return this.treeMap.get(sortedMap.firstKey());
    }

    public String random(String key) {
        // key.hashCode()需要小于1
        double ratio = this.treeMap.lastKey() * key.hashCode();
        SortedMap<Double, String> sortedMap = this.treeMap.tailMap(ratio, false);
        return this.treeMap.get(sortedMap.firstKey());
    }

}
