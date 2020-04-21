package com.github.whvixd.demo.algorithmDemo.sort;

/**
 * 先分再合
 * Created by wangzhx on 2020/4/21.
 */
public class MergeSort {
    private void sort(int[] originArray) {
        int length = originArray.length;
        mergeSort(originArray,0,length-1);

    }

    private void mergeSort(int[] originArray, int left, int right) {
        if (right > left) {
            int middle = (right + left) / 2;
            // 左分
            mergeSort(originArray, left, middle);
            // 右分
            mergeSort(originArray, middle + 1, right);
            // 合
            merge(originArray, left, middle, right);
        }
    }

    private void merge(int[] originArray, int left, int middle, int right) {
        if(left<=middle&&middle<=right){
            int i=left,j=middle+1,k=0;
            int[] t=new int[right-left+1];
            while (i<=middle&&j<=right){
                if(originArray[i]<originArray[j]){
                    t[k++]=originArray[i++];
                }else{
                    t[k++]=originArray[j++];
                }
            }
            while (i<=middle){
                t[k++]=originArray[i++];
            }
            while (j<=right){
                t[k++]=originArray[j++];
            }
            for (int e : t) {
                originArray[left++] = e;
            }
        }
    }
}
