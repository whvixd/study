package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

 示例:

 输入: 3
 输出: 5
 解释:
 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/15.
 */
public enum  Q96 {
    instance;
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int l = 0, r = i - 1,count = 0;
            while (l < r) {
                count += dp[l++] * dp[r--];
            }
            count *= 2;
            if (l == r) count += dp[l] * dp[l];
            dp[i] = count;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // assert 5
        System.out.println(Q96.instance.numTrees(3));
    }
}
