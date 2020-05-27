package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.util.SystemUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

  

 示例：

 输入：A = [4,5,0,-2,-3,1], K = 5
 输出：7
 解释：
 有 7 个子数组满足其元素之和可被 K = 5 整除：
 [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/27.
 */
public enum Q974 {
    instance;
    // O(n^2)
    public int subarraysDivByK(int[] A, int K) {
        int count=0;
        for(int i=0;i<A.length;i++){
            int sum=0;
            for(int j=i;j<A.length;j++){
                sum+=A[j];
                if(sum%K==0){
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraysDivByK1(int[] A, int K) {
        // 同余定理:[P(j)-P(i-1)]%K==0 <=> P(j)%K==P(i-1)%K,P(j)=a[0]+a[1+]..+a[j]

        // k:余数;v:次数（k:3,v:1 余数是3的K整除的数是1）
        Map<Integer,Integer> map = new HashMap<>();
        // 0%K==0
        map.put(0,1);
        int sum=0,count=0;
        for(int i:A){
            sum+=i;
            int remainder=(sum%K+K)%K;//sum=13,K=5:-13%5=-3,-3+5=2,2%5=3
            int remainderCount=map.getOrDefault(remainder,0);
            count+=remainderCount;
            map.put(remainder,remainderCount+1);
        }
        return count;
    }

    public static void main(String[] args) {
        // assert 7
        SystemUtil.print(Q974.instance.subarraysDivByK1(new int[]{4,5,0,-2,-3,1},5));
    }
}
