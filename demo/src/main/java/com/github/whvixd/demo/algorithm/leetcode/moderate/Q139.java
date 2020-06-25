package com.github.whvixd.demo.algorithm.leetcode.moderate;

import org.testng.collections.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

 说明：

 拆分时可以重复使用字典中的单词。
 你可以假设字典中没有重复的单词。
 示例 1：

 输入: s = "leetcode", wordDict = ["leet", "code"]
 输出: true
 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-break
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/25.
 */
public enum Q139 {
    instance;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 上次配置的单词是true
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q139.instance.wordBreak("abc", Lists.newArrayList("abc","aa")));
        // assert true
        System.out.println(Q139.instance.wordBreak("applepenapple", Lists.newArrayList("apple", "pen")));
        // assert false
        System.out.println(Q139.instance.wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat")));
        // assert false
        System.out.println(Q139.instance.wordBreak("a", Lists.newArrayList("b")));
        // assert true
        System.out.println(Q139.instance.wordBreak("a", Lists.newArrayList("a")));
        // assert true
        System.out.println(Q139.instance.wordBreak("aaaaaaa", Lists.newArrayList("aaaa","aaa")));

    }

}
