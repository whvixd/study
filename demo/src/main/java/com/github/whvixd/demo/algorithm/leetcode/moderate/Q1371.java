package com.github.whvixd.demo.algorithm.leetcode.moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。

 示例 1：

 输入：s = "eleetminicoworoep"
 输出：13
 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/20.
 */
public enum Q1371 {
    instance;
    //eleetminicoworoep         :原字符
    //0 2 3  6 8 10 12 14 15    :下标
    // 1. 获取元音下标
    // 2. 最大最小偶数差
    public int findTheLongestSubstring(String s) {
        List<Integer> vowels = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(isVowel(s.charAt(i))){
                vowels.add(i);
            }
        }
        int size=vowels.size();
        if(size==0){
            return s.length();
        }else if(size==1){
            int a=vowels.get(0);
            int b=s.length()-1-a;
            return a>b?a:b;
        }
        // 偶数
        else if(size%2==0){
            return vowels.get(size-1)-vowels.get(0);
        // 奇数
        }else {
            int a = vowels.get(size-1)-vowels.get(1);
            int b = vowels.get(size-2)-vowels.get(0);
            return a>b?a:b;
        }
    }

    private boolean isVowel(char c){
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
    }

    public int findTheLongestSubstring2(String s){
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(Q1371.instance.findTheLongestSubstring2("eleetminicoworoep"));
    }
}
