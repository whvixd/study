package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：

 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。

 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？

 示例 1：

 输入：N = 10, K = 1, W = 10
 输出：1.00000
 说明：爱丽丝得到一张卡，然后停止。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/new-21-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/6/3.
 */
public enum Q837 {
    instance;

    // 少于 k,从[1,W]抽取,累计之和不超过N，可再次抽取
    public double new21Game(int N, int K, int W) {
        double[]dp=new double[N+1];
        double sum=0;
        dp[0]=1;
        if(K>0) sum+=1;
        for(int i=1;i<=N;i++){
            dp[i]=sum/  W;
            if(i<K) sum+=dp[i];
            if(i>=W) sum-=dp[i-W];
        }
        double ans=0;
        for(int i=K;i<=N;i++) ans+=dp[i];
        return ans;
    }

    public static void main(String[] args) {
        // assert 0.73278
        System.out.println(Q837.instance.new21Game(21,17,10));
    }
}
