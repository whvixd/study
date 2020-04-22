package com.github.whvixd.demo.algorithmDemo.sort;

/**
 * Created by wangzhx on 2020/4/21.
 */
public class SelectSort {
    // [升序+乱序]
    private void sort(int[] originArray) {
        // k 为升序与乱序的分界点
        int k = 0, length = originArray.length;
        while (k < length-1) {
            // minIndex 乱序中的最小下标
            int i = k,minIndex=i;
            while (i<length-1){
                // 寻找minIndex
                if(originArray[minIndex]>originArray[i+1]){
                    minIndex = i+1;
                }
                i++;
            }
            // 与有序交换
            if(minIndex!=k){
                int t = originArray[k];
                originArray[k] = originArray[minIndex];
                originArray[minIndex] = t;
            }
            k++;
        }
    }
}
