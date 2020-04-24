package com.github.whvixd.demo.algorithm.sort;

/**
 * Created by wangzhx on 2020/4/21.
 */
public class BubbleSort {
    private int[] sort(int[] originArray){
        int length = originArray.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-i-1;j++){
                if(originArray[j]>originArray[j+1]){
                    int t = originArray[j];
                    originArray[j] = originArray[j+1];
                    originArray[j+1] = t;
                }
            }
        }
        return originArray;
    }

}
