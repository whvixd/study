package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

 示例 1:

 输入: "aba"
 输出: True

 https://leetcode-cn.com/problems/valid-palindrome-ii/

 * Created by wangzhx on 2020/5/19.
 */
public enum Q680 {
    instance;

    public boolean validPalindrome(String s) {
        if(s==null||s.length()==0) return false;

        for(int i=0,j=s.length()-1;i<=j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                boolean a=false,b=false;
                if(s.charAt(i)==s.charAt(j-1)){
                    a=isReverse(s.substring(i,j));
                }
                if(s.charAt(i+1)==s.charAt(j)){
                    b=isReverse(s.substring(i+1,j+1));
                }
                // if string is "eceec"
                return a||b;
            }
        }
        return true;
    }

    private boolean isReverse(String s){
        for(int i=0,j=s.length()-1;i<=j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q680.instance.validPalindrome("caba"));
        // assert true
        System.out.println(Q680.instance.validPalindrome("abccba"));
        // assert true
        System.out.println(Q680.instance.validPalindrome("abcecba"));
        // assert true
        System.out.println(Q680.instance.validPalindrome("abcecbae"));
        // assert true
        System.out.println(Q680.instance.validPalindrome("ebcbbececabbacecbbcbe"));
        // assert true
        System.out.println(Q680.instance.validPalindrome("eceec"));
    }
}
