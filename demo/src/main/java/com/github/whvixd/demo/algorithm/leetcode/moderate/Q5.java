package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/3/22.
 */
public enum Q5 {
    instance;

    // TODO: 2020/3/22 时间负责度
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length == 0 || length == 1) {
            return s;
        }
        int max = 0, start = 0, end = 0;

        for (int i = 0; i < length; i++) {
            int j = length - 1;
            for (; j > i; ) {
                int count = 0;
                for (int a = i, b = j; b >= a; a++, b--) {
                    if (chars[a] == chars[b]) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }

                if (count != 0 && (count >= max) && (end - start < j - i)) {
                    max = count;
                    start = i;
                    end = j;
                }
                j--;
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        // assert aa
        System.out.println(Q5.instance.longestPalindrome("aacdefcaa"));
    }
}
