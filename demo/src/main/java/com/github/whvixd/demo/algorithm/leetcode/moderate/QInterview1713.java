package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。

 注意：本题相对原题稍作改动，只需返回未识别的字符数

  

 示例：

 输入：
 dictionary = ["looked","just","like","her","brother"]
 sentence = "jesslookedjustliketimherbrother"
 输出： 7
 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/re-space-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/7/9.
 */
public enum  QInterview1713 {
    instance;

    public int respace(String[] dictionary, String sentence) {
        int n = sentence.length();
        int m = dictionary.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];
            for (int j = 0; j < m ; j++) {
                // 找到最短的一个单词
                if (i < dictionary[j].length()) continue;
                // 截取字符是否相等
                if (sentence.substring(i - dictionary[j].length(), i).equals(dictionary[j])) {
                    // 若有连续的单词，取最长的那个，为了使未识别的单词尽量少
                    dp[i] = Math.max(dp[i - dictionary[j].length()] + dictionary[j].length(), dp[i]);
                }
            }
        }
        return n - dp[n];
    }

    public static void main(String[] args) {
        // assert 7
        System.out.println(QInterview1713.instance.respace(new String[]{"looked","just","like","her","brother"},"jesslookedjustliketimherbrother"));
    }
}
