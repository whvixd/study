package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 *
 输入：nums = [1,2,3,4]
 输出：[2,4,4,4]
 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/decompress-run-length-encoded-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/20.
 */
public enum Q1313 {
    instance;

    public int[] decompressRLElist(int[] nums) {
        int size=0;
        // 获取返回数组的长度
        for(int i=0;i<nums.length-1;i+=2){
            size+=nums[i];
        }

        int[] list = new int[size];
        for(int i=0,j=i+1,k=0;i<nums.length-1;i+=2,j+=2){
            int num=nums[j];
            int count=nums[i];
            for(int l=0;l<count;l++){
                list[k++]=num;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        // assert [2, 4, 4, 4]
        System.out.println(Arrays.toString(Q1313.instance.decompressRLElist(new int[]{1, 2, 3, 4})));
    }
}
