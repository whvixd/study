package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

 示例 1:
 s = "abc", t = "ahbgdc"

 返回 true.

 示例 2:
 s = "axc", t = "ahbgdc"

 返回 false.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/is-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/27.
 */
public enum Q392 {
    instance;

    public boolean isSubsequence(String s, String t) {
        if(s==null||s.length()==0)return true;
        int i=0,j=0;
        for(;i<s.length();){
            for(;j<t.length();j++){
                if(t.charAt(j)==s.charAt(i)){
                    ++i;
                    ++j;
                    break;
                }
            }
            if(j==t.length()){
                return i == s.length();
            }
            if(i == s.length()){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q392.instance.isSubsequence("abc","ahbgdc"));

        // assert false
        System.out.println(Q392.instance.isSubsequence("axc","ahbgdc"));

        // assert false
        System.out.println(Q392.instance.isSubsequence("aaaaaa","bbaaaa"));
    }
}
