package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

import static com.github.whvixd.util.SystemUtil.print;

/**
 * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：

 目标数组 target 最初为空。
 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
 请你返回目标数组。

 题目保证数字插入位置总是存在。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/create-target-array-in-the-given-order
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/5/26.
 */
public enum Q1389 {
    instance;
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] target=new int[nums.length];
        for(int i=0;i<target.length;i++){
            target[i]=-1;
        }
        for (int i=0;i<nums.length;i++){
            if(target[i]==-1){
                move(index[i],target);
            }
            target[index[i]]=nums[i];
        }
        return target;
    }

    private void move(int point,int[] target) {
        for (int i=target.length-1;i>point;i--){
            if(target[i]!=target[i-1]){
                target[i]=target[i-1];
            }
        }
    }

    public int[] createTargetArray1(int[] nums, int[] index) {
        int l=nums.length;
        List<Integer> list=new ArrayList<>(l);
        for(int i=0;i<l;i++){
            list.add(index[i],nums[i]);
        }
        int[] target=new int[l];
        for(int i=0;i<l;i++){
            target[i]=list.get(i);
        }
        return target;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,2,3,4};
        Q1389.instance.move(1,a);
        print(a,false);

        // assert [0,4,1,3,2]
        print(Q1389.instance.createTargetArray(new int[]{0,1,2,3,4},new int[]{0,1,2,2,1}));
        // assert [0,1,2,3,4]
        print(Q1389.instance.createTargetArray(new int[]{1,2,3,4,0},new int[]{0,1,2,3,0}));
        // assert [1]
        print(Q1389.instance.createTargetArray(new int[]{1},new int[]{0}));
        // assert [2,2,4,4,3]
        print(Q1389.instance.createTargetArray(new int[]{4,2,4,3,2},new int[]{0,0,1,3,1}));
        // assert [7,5,4,5,5,6,5,5]
        print(Q1389.instance.createTargetArray(new int[]{7,6,5,5,5,4,5,5},new int[]{0,1,1,2,4,2,3,6}));
        print(Q1389.instance.createTargetArray1(new int[]{7,6,5,5,5,4,5,5},new int[]{0,1,1,2,4,2,3,6}));
    }

}
