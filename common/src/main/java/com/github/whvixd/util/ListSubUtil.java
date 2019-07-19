package com.github.whvixd.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangzhx on 2019/7/19.
 */
public enum ListSubUtil {
    instance;
    private int start, count, lastForCount;
    private List<List<String>> list = Lists.newArrayList();

    public List<List<String>> getListGroup(List<String> objectIds, int subNumber) {
        return getListGroup(objectIds, subNumber, 0);
    }

    public List<List<String>> getListGroup(List<String> objectIds, int subNumber, int forCount) {
        if (lastForCount != forCount) {
            intArg();
        }
        this.lastForCount = forCount;
        int end = ++count * subNumber;
        int size = objectIds.size();
        if (size < end) {
            if (size % subNumber != 0) {
                list.add(objectIds.subList(end - subNumber, size));
            }
            return list;
        }
        List<String> subList = objectIds.subList(start, end);
        list.add(subList);
        start = start + subNumber;
        return getListGroup(objectIds, subNumber, forCount);
    }

    private void intArg() {
        this.count = 0;
        this.start = 0;
        list = Lists.newArrayList();
    }
}
