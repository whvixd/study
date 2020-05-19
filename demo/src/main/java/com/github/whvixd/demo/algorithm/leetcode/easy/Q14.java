package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-common-prefix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/19.
 */
public enum Q14 {
    instance;
    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0){
            return "";
        }

        // 先获取最短字符串
        int min=Integer.MAX_VALUE;
        for(String str:strs){
            min=min>str.length()?str.length():min;
        }
        // 对比每个字符相等
        int point=0;
        for(int i=0;i<min;i++){
            boolean f=true;
            for(int j=0;j<strs.length-1;j++){
                if(strs[j].charAt(i)!=strs[j+1].charAt(i)){
                    f=false;
                }
            }
            if(!f){
                break;
            }else {
                point++;
            }
        }
        return point==0?"":strs[0].substring(0,point);

    }

    public static void main(String[] args) {
        String[] strs1 = new String[]{"flower","flow","flight"};
        System.out.println(Q14.instance.longestCommonPrefix(strs1));

        String[] strs2 = new String[]{"dog","racecar","car"};
        System.out.println(Q14.instance.longestCommonPrefix(strs2));
    }
}
