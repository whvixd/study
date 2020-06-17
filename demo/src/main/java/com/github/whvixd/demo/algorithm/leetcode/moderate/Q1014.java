package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

 返回一对观光景点能取得的最高分。

 示例：

 输入：[8,1,5,2,6]
 输出：11
 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/6/17.
 */
public enum Q1014 {
    instance;

    // O(n)
    // A[i] + A[j] + i - j = A[i] + i + A[j]-j , (j > i)
    public int maxScoreSightseeingPair(int[] A) {
        int leftMax=A[0], max=Integer.MIN_VALUE;
        for (int j = 1; j < A.length; j++) {
            int c=leftMax+A[j]-j;
            max=max>c?max:c;

            int left=A[j]+j;
            leftMax =leftMax>left?leftMax:left;
        }
        return max;
    }

    // 时间复杂度过高 O(n^2)
    public int maxScoreSightseeingPair1(int[] A) {
        int max=Integer.MIN_VALUE;
        int l=A.length;
        for(int i=0;i<l;i++){
            for(int j=i+1;j<l;j++){
                int n=A[i]+A[j]+i-j;
                max=n>max?n:max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // assert 11
        System.out.println(Q1014.instance.maxScoreSightseeingPair(new int[]{8,1,5,2,6}));
        System.out.println(Q1014.instance.maxScoreSightseeingPair1(new int[]{8,1,5,2,6}));
    }
}
