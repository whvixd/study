package com.github.whvixd.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

 示例 1:

 输入: [1,2,0]
 输出: 3
 示例 2:

 输入: [3,4,-1,1]
 输出: 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/first-missing-positive
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/27.
 */
public enum Q41 {
    instance;
    public int firstMissingPositive(int[] nums) {
        if(nums==null||nums.length==0)return 1;
        // 排序
        Arrays.sort(nums);
        int l=nums.length;
        // 都是负数 或 都是大于1的正数
        if(nums[l-1]<=0||nums[0]>1){
            return 1;
        }

        int minP=nums[l-1]+1;
        for(int i=l-1;i>0;i--){
            int num=nums[i];
            int last=nums[i-1];
            if(num>=0&&last>=0){
                // 若前后两个数相等或是连续，则跳过
                if(num==last||num==last+1){
                    continue;
                }
                minP=last+1;
            }else if(last<1&&num>1){
                return 1;
            }
        }

        return minP;
    }

    public static void main(String[] args) {
        // assert 3
        System.out.println(Q41.instance.firstMissingPositive(new int[]{1,2,0}));

        // assert 2
        System.out.println(Q41.instance.firstMissingPositive(new int[]{3,4,-1,1}));

        // assert 1
        System.out.println(Q41.instance.firstMissingPositive(new int[]{7,8,9,11,12}));

        // assert 3
        System.out.println(Q41.instance.firstMissingPositive(new int[]{0,1,2,4,-1}));

    }
}
