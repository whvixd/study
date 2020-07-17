package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

 你可以假设数组中无重复元素。

 示例 1:

 输入: [1,3,5,6], 5
 输出: 2
 示例 2:

 输入: [1,3,5,6], 2
 输出: 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-insert-position
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/7/17.
 */
public enum Q35 {
    instance;

    public int searchInsert(int[] nums, int target) {
        if(nums[0]>=target){
            return 0;
        }
        if(nums[nums.length-1]<target){
            return nums.length;
        }
        if(nums[nums.length-1]==target){
            return nums.length-1;
        }
        for(int i=0;i<nums.length-1;i++){
            if(target==nums[i]){
                return i;
            }
            if(nums[i]<target&&nums[i+1]>target){
                return i+1;
            }
        }
        return -1;
    }

    // 二分法
    public int searchInsert1(int[] nums, int target) {
        if(nums[0]>target){
            return 0;
        }
        if(nums[nums.length-1]<target){
            return nums.length;
        }

        int l=0,r=nums.length-1;
        while (l<=r){
            int mid=(l+r)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target&&nums[mid-1]<target){
                return mid;
            }else if(nums[mid]<target&&nums[mid+1]>target){
                return mid+1;
            }else if(nums[mid]<target){
                l=mid+1;
            }else if(nums[mid]>target){
                r=mid-1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        // assert 2
        System.out.println(Q35.instance.searchInsert(new int[]{1,3,5,6},5));
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3,5,6},5));

        // assert 1
        System.out.println(Q35.instance.searchInsert(new int[]{1,3,5,6},2));
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3,5,6},2));

        // assert 4
        System.out.println(Q35.instance.searchInsert(new int[]{1,3,5,6},7));
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3,5,6},7));

        // assert 0
        System.out.println(Q35.instance.searchInsert(new int[]{1,3,5,6},0));
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3,5,6},0));

        // assert 1
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3},2));
        // assert 2
        System.out.println(Q35.instance.searchInsert1(new int[]{1,3},3));

        // assert 0
        System.out.println(Q35.instance.searchInsert1(new int[]{1},1));

    }
}
