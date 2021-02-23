package com.github.whvixd.demo.algorithm;

/**
 * Created by wangzhx on 2021/2/23.
 */
public class FindKthDemo {
    public static int findKth(int[] a, int n, int K) {
        // write code here
        return quickSort(a,0,n-1,K);
    }
    static int  quickSort(int[] a,int l,int r,int K){
        int i=l;
        int j=r;
        int p=a[i];
        int t=0;
        while(i<j){
            // 招比p小的数
            while(i<j&&p>=a[j]){
                j--;
            }
            while(i<j&&p<=a[i]){
                i++;
            }
            if(i<j){
                t=a[i];
                a[i]=a[j];
                a[j]=t;
            }

        }
        a[l]=a[i];
        a[i]=p;
        if(i==K-1){
            return a[i];
        }else if(i<K-1){
            return quickSort(a,i+1,r,K);
        }else{
            return quickSort(a,l,i-1,K);
        }


    }

    public static void main(String[] args) {
        // [1,3,5,2,2],5,3
        int kth = findKth(new int[]{3, 4, 2, 1, 5}, 5, 3);
        System.out.println(kth);
    }
}
