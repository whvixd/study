package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

 示例 1:

 输入:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 输出: 3
 解释:
 长度最长的公共子数组是 [3, 2, 1]。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/7/1.
 */
public enum Q718 {
    instance;

    // 双循环，时间复杂度过大
    public int findLength(int[] A, int[] B) {
        int maxCommonLength=0;
        for(int i=0;i<A.length;i++){
                int p=i,j=0,q=j,length=0;
                for(;j<B.length&&q<B.length&&p<A.length;q++){
                    if(A[p]!=B[q]&&length>0){
                        if(length>maxCommonLength){
                            maxCommonLength=length;
                        }
                        length=0;
                        p=i;
                        q=j++;
                    }else if(A[p]==B[q]){
                        p++;
                        length++;
                    }
            }
            // 对于A尾部与B子数组相等时
            if(length>maxCommonLength){
                maxCommonLength=length;
            }
        }
        return maxCommonLength;
    }

    // 滑动窗口
    public int findLength1(int[] A, int[] B) {
        int m = A.length, n = B.length, res = 0;
        for (int diff = -(m - 1); diff <= n - 1; ++diff) { // 枚举对应关系
            for (int i = Math.max(0, -diff), l = 0; i < Math.min(m, n - diff); ++i) { // 遍历公共部分
                l = (A[i] == B[i + diff]) ? (l + 1) : 0;
                res = Math.max(res, l);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        // assert 3
        System.out.println(Q718.instance.findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
        // assert 6
        System.out.println(Q718.instance.findLength(new int[]{1,0,1,0,0,0,0,0,1,1},new int[]{1,1,0,1,1,0,0,0,0,0}));
        // assert 9
        System.out.println(Q718.instance.findLength(new int[]{0,0,0,0,0,0,1,0,0,0},new int[]{0,0,0,0,0,0,0,1,0,0}));
        System.out.println(Q718.instance.findLength1(new int[]{0,0,0,0,0,0,1,0,0,0},new int[]{0,0,0,0,0,0,0,1,0,0}));
    }
}
