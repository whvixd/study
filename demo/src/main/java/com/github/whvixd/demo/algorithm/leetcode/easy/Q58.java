package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 示例 1：

 输入: s = "abcdefg", k = 2
 输出: "cdefgab"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/5/20.
 */
public enum Q58 {
    instance;
    public String reverseLeftWords(String s, int n) {
        if(s==null||s.length()==0||n<0) return "";
        char[] chars = s.toCharArray();
        int length = chars.length;
        for(int i=0;i<n;i++){
            char t = chars[0];
            System.arraycopy(chars, 1, chars, 0, length - 1);
            chars[length-1]=t;
        }
        return String.valueOf(chars);
    }

    public String reverseLeftWords2(String s, int n) {
        if(s==null||s.length()==0||n<0) return "";
        String s1 = s.substring(0, n);
        String s2 = s.substring(n, s.length());
        return s2+s1;
    }

    public static void main(String[] args) {
        System.out.println(Q58.instance.reverseLeftWords2("abcdefg",2));
    }
}
