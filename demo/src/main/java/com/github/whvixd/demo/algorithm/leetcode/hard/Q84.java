package com.github.whvixd.demo.algorithm.leetcode.hard;

import com.github.whvixd.util.SystemUtil;

/**
 *
 *
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

 求在该柱状图中，能够勾勒出来的矩形的最大面积。

 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/

 * created by wangzhx on 2020/05/30.
 */
public enum Q84 {
    instance;

    // 暴力
    public int largestRectangleArea(int[] heights) {
        if(heights.length==0)return 0;
        if(heights.length==1)return heights[0];

        int size=heights.length;
        int maxArea=0;

        for(int i=0;i<size;i++){
            int height=Integer.MAX_VALUE;
            for(int j=i;j<size;j++){
                height=height>heights[j]?heights[j]:height;
                int area=height*(j-i+1);
                maxArea=area>maxArea?area:maxArea;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        // assert 10
        SystemUtil.print(Q84.instance.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        // assert 15
        SystemUtil.print(Q84.instance.largestRectangleArea(new int[]{2,1,6,6,2,5,5,5,3}));
    }
}
