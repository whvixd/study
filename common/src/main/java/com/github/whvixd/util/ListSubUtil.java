package com.github.whvixd.util;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangzhx on 2019/7/19.
 */
public enum ListSubUtil {
    instance;
    private int start, count;
    private Integer lastForCount;
    private List<List<String>> list = Lists.newArrayList();

    public List<List<String>> getListGroup(List<String> elements, int subNumber) {
        intAllArg();
        return getListGroup(elements, subNumber, 0);
    }

    /**
     * 适用于for循环分割数组
     * 两个线程就又问题
     *
     * @param elements
     * @param subNumber
     * @param forCount
     * @return
     */
    public List<List<String>> getListGroup(List<String> elements, int subNumber, int forCount) {
        if (lastForCount == null || lastForCount != forCount) {
            intArg();
        }
        this.lastForCount = forCount;
        int end = ++count * subNumber;
        int size = elements.size();
        if (size < end) {
            if (size % subNumber != 0) {
                if (end - subNumber > size) {
                    throw new IllegalArgumentException("last for count as same as current for count!");
                }
                list.add(elements.subList(end - subNumber, size));
            }
            return list;
        }
        List<String> subList = elements.subList(start, end);
        list.add(subList);
        start = start + subNumber;
        return getListGroup(elements, subNumber, forCount);
    }

    private void intArg() {
        this.count = 0;
        this.start = 0;
        this.list = Lists.newArrayList();
    }

    private void intAllArg() {
        this.count = 0;
        this.start = 0;
        this.lastForCount = null;
        this.list = Lists.newArrayList();
    }
}
