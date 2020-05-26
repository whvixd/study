package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 *
 * Created by wangzhx on 2020/5/26.
 */
public enum Q1295 {
    instance;
    public int findNumbers(int[] nums) {
        int count=0;
        for(int num:nums){
            if(isEvenNumberByDigit(num)) count++;
        }
        return count;
    }

    private boolean isEvenNumberByDigit(int num){
        // 2100/10=210  210/10=21 21/10=2 2/10=0
        // 4321/10=432 432/10=43 43/10=4 4/10=0
        int digit=0;
        while (num!=0){
            num/=10;
            digit++;
        }
        return digit%2==0;
    }
    private boolean isEvenNumberByStringDigit(int num){
        // 转字符串的时间负责度会更高
        return (num+"").length()%2==0;
    }

    public static void main(String[] args) {
        // assert 1
        System.out.println(Q1295.instance.findNumbers(new int[]{555,901,482,1771}));

        // assert 2
        System.out.println(Q1295.instance.findNumbers(new int[]{12,345,2,6,7896}));

        // assert 2
        System.out.println(Q1295.instance.findNumbers(new int[]{1,10,100,1000}));
    }
}
