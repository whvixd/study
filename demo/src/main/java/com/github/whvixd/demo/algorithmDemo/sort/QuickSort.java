package com.github.whvixd.demo.algorithmDemo.sort;

/**
 * 取基数，一次交换使得  左<基数<右 左右不一定有序
 * Created by wangzhx on 2020/4/21.
 */
public class QuickSort {

    void sort(int[] originArray) {
        int length = originArray.length;
        sort(originArray, 0, length-1);
    }

    private void sort(int[] originArray, int left, int right) {
        if(left>=right){
            return;
        }

        int pivot = originArray[left];
        int i = left,j=right;
        while (i<j){
            // 从左边找到比基数小的
            while (originArray[i]<=pivot&&i<j){
                i++;
            }
            // 从右边找到比基数大的
            while (originArray[j]>=pivot&&i<j){
                j--;
            }

            if(i<j){
                // i j 位子交换
                int t = originArray[i];
                originArray[i]=originArray[j];
                originArray[j]=t;
            }

        }
        // 基数与 i 交换， 得到的结果：左<基数<右 左右不一定有序
        originArray[left]= originArray[i];
        originArray[i]=pivot;
        // 递归 之后所有基数升序
        sort(originArray, left, i-1);
        sort(originArray, i+1, right);
    }

}
