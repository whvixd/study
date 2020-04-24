package com.github.whvixd.demo.algorithm.sort;

/**
 * Created by wangzhx on 2020/4/21.
 */
public class InsertSort {

    private void sort(int[] originArray) {
        int k = 0, length = originArray.length;
        while (k < length) {
            int i = k;
            // 待比较的值
            int t = originArray[k];
            while (i > 0) {
                if (t <= originArray[i - 1]) {
                    // 若待比较的值小于有序中的值，则移位再比较
                    originArray[i] = originArray[i - 1];
                    i--;
                } else {
                    break;
                }

            }
            // 插入到有序中的地址
            if (i >= 0) {
                originArray[i] = t;
            }
            k++;
        }
    }
}
