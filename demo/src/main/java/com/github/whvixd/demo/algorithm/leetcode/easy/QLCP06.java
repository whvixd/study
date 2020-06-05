package com.github.whvixd.demo.algorithm.leetcode.easy;

/**

 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。

 示例 1：

 输入：[4,2,1]

 输出：4

 解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。

 https://leetcode-cn.com/problems/na-ying-bi/

 * Created by wangzhx on 2020/6/4.
 */
public enum  QLCP06 {
    instance;
    public int minCount(int[] coins) {
        if(coins.length==0)return 0;
        int sum=0;
        for(int coin:coins){
            if(coin>0&&coin<=2){
                sum++;
            }else if(coin>2){
                while (coin>0){
                    sum++;
                    coin-=2;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // assert 8
        System.out.println(QLCP06.instance.minCount(new int[]{2,3,10}));
    }
}
