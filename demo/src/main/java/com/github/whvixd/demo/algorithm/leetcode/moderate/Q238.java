package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.util.SystemUtil;

/**
 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 示例:

 输入: [1,2,3,4]
 输出: [24,12,8,6]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/6/4.
 */
public enum Q238 {
    instance;
    // O(n^2)
    public int[] productExceptSelf(int[] nums) {
        int l=nums.length;
        int[] target=new int[l];
        for(int i=0;i<l;i++){
            int multiply=1;
            for(int j=0;j<l;j++){
                if(i!=j){
                    multiply*=nums[j];
                }
            }
            target[i]=multiply;
        }
        return target;
    }

    // O(n)
    public int[] productExceptSelf1(int[] nums) {
        int multiply=1;
        for(int num:nums){
            multiply*=num;
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=multiply/nums[i];
        }
        return nums;
    }

    // O(n)
    public int[] productExceptSelf2(int[] nums) {
        int left = 1;
        int right = 1;
        int len = nums.length;
        int[] output = new int[len];
        for(int i=0;i<len;i++){
            output[i] = left;
            left *= nums[i];
        }
        for(int j=len-1;j>=0;j--){
            output[j] *= right;
            right *= nums[j];
        }
        return output;
    }

    public static void main(String[] args) {
        // assert [24, 12, 8, 6]
        SystemUtil.print(Q238.instance.productExceptSelf(new int[]{1,2,3,4}));
        SystemUtil.print(Q238.instance.productExceptSelf1(new int[]{1,2,3,4}));
        SystemUtil.print(Q238.instance.productExceptSelf2(new int[]{1,2,3,4}));
    }
}
