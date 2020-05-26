package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

 以数组形式返回答案。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/26.
 */
public enum Q1365 {
    instance;

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int count=0;
            for(int j:nums){
                if(nums[i]>j){
                    count++;
                }
            }
            result[i]=count;
        }
        return result;
    }

    public static void main(String[] args) {
        // assert [4,0,1,1,3]
        System.out.println(Arrays.toString(Q1365.instance.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        // assert [0,0,0,0]
        System.out.println(Arrays.toString(Q1365.instance.smallerNumbersThanCurrent(new int[]{7,7,7,7})));

    }


}
