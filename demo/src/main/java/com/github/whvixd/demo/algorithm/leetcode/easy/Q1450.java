package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
 *
 * https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time/
 *
 * Created by wangzhx on 2020/5/19.
 */
public enum Q1450 {
    instance;
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        if(startTime.length==0||endTime.length==0||startTime.length!=endTime.length) return 0;

        int sum=0;
        for(int i=0;i<startTime.length;i++){
            if(startTime[i]<=queryTime&&endTime[i]>=queryTime){
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] startTime=new int[]{9,8,7,6,5,4,3,2,1};
        int[] endTime=new int[]{10,10,10,10,10,10,10,10,10};
        System.out.println(Q1450.instance.busyStudent(startTime,endTime,5));
    }
}
