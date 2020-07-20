package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.util.SystemUtil;

/**
 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

 说明:

 返回的下标值（index1 和 index2）不是从零开始的。
 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 示例:

 输入: numbers = [2, 7, 11, 15], target = 9
 输出: [1,2]
 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/20.
 */
public enum Q167 {
    instance;

    public int[] twoSum(int[] numbers, int target) {

        int l=numbers.length;
        int result[]=new int[2];
        for(int i=0;i<l;i++){
            for(int j=i-1;j>=0;j--){
                if(numbers[j]+numbers[i]==target){
                    result[0]=j+1;
                    result[1]=i+1;
                }else if(numbers[j]+numbers[i]<target){
                    break;
                }
            }
        }
        return result;
    }

    public int[] twoSum1(int[] numbers, int target) {
        int size=numbers.length;
        int result[]=new int[2];

        int l=0,r=size-1;
        while (l<r){
            if(numbers[l]+numbers[r]==target){
                result[0]=l+1;
                result[1]=r+1;
                break;
            }else if(numbers[l]+numbers[r]>target){
                r--;
            }else {
                l++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // assert [1,2]
        SystemUtil.print(Q167.instance.twoSum(new int[]{2, 7, 11, 15},9));
        SystemUtil.print(Q167.instance.twoSum1(new int[]{2, 7, 11, 15},9));

        // assert [1,2]
        SystemUtil.print(Q167.instance.twoSum(new int[]{-1,0},-1));
        SystemUtil.print(Q167.instance.twoSum1(new int[]{-1,0},-1));
    }
}
