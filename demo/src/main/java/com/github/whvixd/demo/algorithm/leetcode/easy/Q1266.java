package com.github.whvixd.demo.algorithm.leetcode.easy;


import com.github.whvixd.util.SystemUtil;

/**
 * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
 *
 * 输入：points = [[1,1],[3,4],[-1,0]]
 输出：7
 解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
 从 [1,1] 到 [3,4] 需要 3 秒，注：max(3-1=2，4-1=3)
 从 [3,4] 到 [-1,0] 需要 4 秒，注：3--1=4,4-0=4
 一共需要 7 秒

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-time-visiting-all-points
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/26.
 */
public enum Q1266 {
    instance;
    public int minTimeToVisitAllPoints(int[][] points) {
        int second=0;
        for(int i=0;i<points.length-1;i++){
            int[] c=points[i];
            int[] n=points[i+1];
            int cS=c[0]-n[0];
            int nS=c[1]-n[1];
            // 绝对值
            cS=(cS < 0) ? -cS : cS;
            nS=(nS < 0) ? -nS : nS;
            // 最大值相加
            second+=cS>nS?cS:nS;
        }
        return second;
    }

    public static void main(String[] args) {
        int[][] ints = {new int[]{1, 1}, new int[]{3, 4}, new int[]{-1, 0}};
        // assert 7
        SystemUtil.print(Q1266.instance.minTimeToVisitAllPoints(ints));

        int[][] ints1 = {new int[]{3, 2}, new int[]{-2, 2}};
        // assert 5
        SystemUtil.print(Q1266.instance.minTimeToVisitAllPoints(ints1));
    }


}
