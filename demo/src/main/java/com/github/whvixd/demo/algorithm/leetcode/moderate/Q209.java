package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。

 示例: 

 输入: s = 7, nums = [2,3,1,2,4,3]
 输出: 2
 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/28.
 */
public enum Q209 {
    instance;
    public int minSubArrayLen(int s, int[] nums) {
        int minL=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int p=i;p<nums.length;p++){
                if(minL==1)return 1;
                // 累加
                sum+=nums[p];
                // 之和大于等于s，赋值
                if(sum>=s){
                    int e=p-i+1;
                    minL=minL>e?e:minL;
                    break;
                }
            }
        }
        return minL==Integer.MAX_VALUE?0:minL;
    }

    public int minSubArrayLen1(int s, int[] nums) {
        int minL=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j>=0;j--){
                if(minL==1)return 1;
                sum+=nums[j];
                if(sum>=s){
                    int e=i-j+1;
                    minL=minL>e?e:minL;
                    break;
                }
            }
        }
        return minL==Integer.MAX_VALUE?0:minL;
    }

    public static void main(String[] args) {
        // assert 2
        System.out.println(Q209.instance.minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
        System.out.println(Q209.instance.minSubArrayLen1(7,new int[]{2,3,1,2,4,3}));
    }
}
