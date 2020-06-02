package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.util.SystemUtil;

/**
 *
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

  

 示例 1：

 输入: n = 3
 输出: 6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/qiu-12n-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/6/2.
 */
public enum QInterview64 {
    instance;

    // 等差数列
    public int sumNums(int n) {
        return (int)(n+Math.pow(n,2))>>1;
    }

    // 递归
    public int sumNums1(int n) {
        int sum=n;
        boolean f=n>0&&(sum+=sumNums1(n-1))>0;
        return sum;
    }

    public static void main(String[] args) {
        // assert 45
        SystemUtil.print(QInterview64.instance.sumNums(9));
        // assert 45
        SystemUtil.print(QInterview64.instance.sumNums1(9));
    }

}
