package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.util.SystemUtil;

/**
 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。

 返回的长度需要从小到大排列。

 示例：

 输入：
 shorter = 1
 longer = 2
 k = 3
 输出： {3,4,5,6}

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/diving-board-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/6.
 */
public enum QInterview1611 {
    instance;
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k<1)return new int[0];
        if(shorter == longer) return new int[]{shorter * k};
        int[] result=new int[k+1];
        for(int i=0;i<=k;i++){
            int sum=0;
            for(int j=i;j<k;j++){
                sum+=shorter;
            }
            for(int z=0;z<i;z++){
                sum+=longer;
            }
            result[i]=sum;
        }
        return result;
    }

    public int[] divingBoard1(int shorter, int longer, int k) {
        if(k == 0) return new int[]{};
        if(shorter == longer) return new int[]{shorter * k};
        int[] results = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            // 短的于长的组合
            results[i] = shorter * (k - i) + longer * i;
        }
        return results;
    }

    public static void main(String[] args) {
        //[3, 4, 5, 6]
        SystemUtil.print(QInterview1611.instance.divingBoard(1,2,3));
        // [110,120,130,140,150,160,170,180,190,200,210,220]
        SystemUtil.print(QInterview1611.instance.divingBoard(10,20,11));
        SystemUtil.print(QInterview1611.instance.divingBoard1(10,20,11));
        // []
        SystemUtil.print(QInterview1611.instance.divingBoard(1,1,0));
    }

}
