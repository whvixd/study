package com.github.whvixd.demo.algorithm.leetcode.moderate;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

 示例 1:

 输入: [1,3,4,2,2]
 输出: 2

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/26.
 */
public enum Q287 {
    instance;
    public int findDuplicate(int[] nums) {
        if(nums.length<=1) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            Integer count = map.get(num);
            if(count==null){
                map.put(num,1);
            }else{
                return num;
            }
        }
        return 0;
    }
    public int findDuplicate1(int[] nums) {
        int l=nums.length;
        for(int i=0;i<l;i++){
            for(int j=i+1;j<l;j++){
                if(nums[i]==nums[j]){
                    return nums[i];
                }
            }
        }
        return 0;
    }

    // LeetCode 解法
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    public static void main(String[] args) {
        // assert 1
        System.out.println(Q287.instance.findDuplicate1(new int[]{1,1,3,4,5}));
        // assert 3
        System.out.println(Q287.instance.findDuplicate1(new int[]{1,2,3,4,3}));
        // assert 0
        System.out.println(Q287.instance.findDuplicate1(new int[]{1,2,3,4,5}));
        // assert 4
        System.out.println(Q287.instance.findDuplicate2(new int[]{1,2,3,4,4}));
    }
}
