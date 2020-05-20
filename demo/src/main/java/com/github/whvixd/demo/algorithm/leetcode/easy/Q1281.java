package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * Created by wangzhx on 2020/5/20.
 */
public enum Q1281 {
    instance;
    public int subtractProductAndSum(int n) {
        // 积
        int product=1;
        // 和
        int sum=0;

        while (n!=0){
            int i=n%10;
            product*=i;
            sum+=i;
            n/=10;
        }
        return product-sum;
    }

    public static void main(String[] args) {
        System.out.println(Q1281.instance.subtractProductAndSum(234));//15
        System.out.println(Q1281.instance.subtractProductAndSum(4421));//21
    }

}
