package com.github.whvixd.demo.algorithmDemo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 *
 * Created by wangzhx on 2020/3/25.
 */
public enum Q9 {
    instance;

    // todo 未解出来
    public int firstMissingPositive(int[] nums) {
        int min = 1;
        if (nums.length == 0) {
            return min;
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>0){
                if(nums[i+1]>0){
                    min=nums[i]>nums[i+1]?nums[i+1]:nums[i];
                }else{
                    min=nums[i];
                }
                min++;
                if(!list.contains(min)){
                    list.add(min);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(Q9.instance.firstMissingPositive(new int[]{4,2,3,5}));
    }
}
