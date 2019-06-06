package com.github.whvixd.demo.algorithmDemo;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 12, 7, 8, 3, 4, 65, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免第归中频繁开辟空间
        int[] temp = new int[arr.length];
        System.out.println(222);
        sort(arr, 0, arr.length - 1, temp);
        System.out.println(333);


    }            //                         0          9

    //0-9，0-4，0-2，0-1
    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {//0<9
            System.out.println("left:" + left + " right:" + right);
            int mid = (left + right) / 2;//4，2
            System.out.println("mid1:" + mid);
            sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            System.out.println(444);


            sort(arr, mid + 1, right, temp);//右边归并并排序，使得右子序列有序
            System.out.println(555);
            System.out.println("left:" + left + " mid:" + mid + ":" + " right:" + right);
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
            System.out.println("mid2:" + mid);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println(111);
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}