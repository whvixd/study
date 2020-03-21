package com.github.whvixd.demo.algorithmDemo.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 *
 *  用hashmap 效率高
 * Created by wangzhx on 2020/3/20.
 */
public class Q1 {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                if (a + b == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }


        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = new Q1().twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
        Arrays.stream(ints).forEach(k -> System.out.println(k));
    }
}
