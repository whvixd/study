package com.github.whvixd.util;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangzhx on 2019/7/19.
 */
public enum ListUtil {
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
                    throw new IllegalArgumentException("las count as same as current count!");
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

    /**
     * @param source [1,2,3,4]
     * @param n      2
     * @return [[1, 2], [3, 4]]
     */
    public static <T> List<List<T>> getSubList(List<T> source, int n) {
        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = Lists.newArrayList();

        int sourceSize = source.size();
        int size = (source.size() / n) + 1;
        for (int i = 0; i < size; i++) {
            List<T> subset = Lists.newArrayList();
            for (int j = i * n; j < (i + 1) * n; j++) {
                if (j < sourceSize) {
                    subset.add(source.get(j));
                }
            }
            result.add(subset);
        }
        return result;
    }

    /**
     * @param source [1,2,3,4,5,6]
     * @param n      3
     * @return [[1, 2], [3, 4], [5, 6]]
     */
    public static <T> List<List<T>> getAverageList(List<T> source, int n) {
        List<List<T>> result = Lists.newArrayList();
        int remainder = source.size() % n;
        int number = source.size() / n;
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
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
