package com.github.whvixd.demo.algorithm.leetcode.moderate;

import java.util.Arrays;

/**
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 示例：

 输入：nums = [-1,2,1,-4], target = 1
 输出：2
 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/3sum-closest
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/24.
 */
public enum Q16 {
    instance;
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int size=nums.length;
        int t=nums[0]+nums[1]+nums[2];
        for(int i=0;i<size;i++){
            int l=i+1,r=size-1;
            while (l<r){
                int s=nums[i]+nums[l]+nums[r];
                // 比较绝对值
                if(abs(s-target)<abs(t-target)){
                    // 交换
                    t=s;
                }
                // 目标值大右移
                if(target>s){
                    l++;
                // 目标值小左移
                }else if(target<s){
                    r--;
                // 相等直接返回
                }else {
                    return t;
                }
            }
        }
        return t;
    }

    private int abs(int n){
        return n>=0?n:-n;
    }

    public static void main(String[] args) {
        // assert 2
        System.out.println(Q16.instance.threeSumClosest(new int[]{-1,2,1,-4},1));
    }
}
