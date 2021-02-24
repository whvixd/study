package com.github.whvixd.demo.algorithm;

/**
 * Created by wangzhixiang on 2021/2/23.
 */
public class Algorithm {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public static int upper_bound_ (int n, int v, int[] a) {
        // write code here
        int i=0,j=n-1,m=0,r=0;
        if(n==0){
            return n;
        }else if(n==1){
            if(v<=a[0]){
                return 1;
            }
        }

        if(a[0]>=v){
            return 1;
        }
        if(a[n-1]<v){
            return n+1;
        }


        while(j>=i){
            m=(i+j)/2;
            r=a[m];
            if(v==r){

                while (a[m]==v){
                    m--;
                    if(a[m]!=v){
                        return m+2;
                    }
                }
                return m+1;
            }else if(v>r){
                i=m+1;
                if(a[i]>=v){
                    return i+1;
                }
            }else{
                j=m-1;
                if(j>=0&&a[j]<=v){
                    return j+1;
                }
            }
        }
        return n+1;
    }

    public static void main(String[] args) {
        int i = upper_bound_(100, 97, new int[]{3,3,4,4,4,5,6,6,6,7,8,8,12,13,15,16,21,21,22,24,24,27,28,32,34,35,35,36,36,39,40,41,41,42,44,44,45,45,47,47,47,47,48,48,50,51,51,53,53,53,54,54,54,56,56,57,59,60,60,60,60,61,62,63,65,65,65,65,67,67,68,70,71,71,74,75,75,79,81,84,84,86,86,87,90,90,90,90,91,92,93,94,94,94,95,97,97,98,98,99});
        System.out.println(i);
    }
}
