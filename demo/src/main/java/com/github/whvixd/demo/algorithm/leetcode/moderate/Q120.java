package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.google.common.collect.Lists;

import java.util.List;

/**
 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

  

 例如，给定三角形：

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/triangle
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/14.
 */
public enum Q120 {
    instance;
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 加1可以不用初始化最后一层
        int[][] dp = new int[triangle.size()+1][triangle.size()+1];

        for (int i = triangle.size()-1; i>=0; i--){
            List<Integer> curTr = triangle.get(i);
            for(int j = 0 ; j< curTr.size(); j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + curTr.get(j);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
//        List<Integer> one= Lists.newArrayList(2);
//        List<Integer> two= Lists.newArrayList(3,4);
//        List<Integer> three= Lists.newArrayList(6,5,7);
//        List<Integer> four= Lists.newArrayList(4,1,8,3);
//        System.out.println(Q120.instance.minimumTotal(Lists.newArrayList(one,two,three,four)));

        List<Integer> one= Lists.newArrayList(-1);
        List<Integer> two= Lists.newArrayList(2,3);
        List<Integer> three= Lists.newArrayList(1,-1,-3);
        System.out.println(Q120.instance.minimumTotal(Lists.newArrayList(one,two,three)));

    }
}
