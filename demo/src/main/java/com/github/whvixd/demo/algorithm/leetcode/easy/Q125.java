package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

 说明：本题中，我们将空字符串定义为有效的回文串。

 示例 1:

 输入: "A man, a plan, a canal: Panama"
 输出: true
 示例 2:

 输入: "race a car"
 输出: false

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-palindrome
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/19.
 */
public enum Q125 {
    instance;

    public boolean isPalindrome(String s) {
        if(s==null||s.length()==0)return true;
        for(int i=0,j=s.length()-1;i<j;){
            char left=s.charAt(i);
            if(!isValidChar(left)){
                i++;
                continue;
            }
            char right=s.charAt(j);
            if(!isValidChar(right)){
                j--;
                continue;
            }

            if(lower(left)!=lower(right)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 48-57:'0'-'9'
    // 97-122:'a'-'z'
    // 65-90:'A'-'Z'
    private boolean isValidChar(char c){
        return (c>='a'&&c<='z')||
                (c>='A'&&c<='Z')||
                (c>='0'&&c<='9');
    }

    private char lower(char c){
        if(c>='A'&&c<='Z'){
            return (char)(c+32);
        }
        return c;
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q125.instance.isPalindrome("A man, a plan, a canal: Panama"));
        // assert true
        System.out.println(Q125.instance.isPalindrome("race a car"));
        // assert false
        System.out.println(Q125.instance.isPalindrome("9;8'4P?X:1Q8`dOfJuJXD6FF,8;`Y4! YBy'Wb:ll;;`;\"JI0c2uvD':!LAk:s\"!'0.!2B55.T1VI?00Du?1,l``RFsc?Y?9vD5 K'3'1b!N8hn:'l. R:9:o`m1r.M2mrJ?`Wjv1`G6i6G`1vjW`?Jrm2M.r1m`o:::R .l':nh8N!b1'3'K 5Dv9?Y?csFR``l,1?uD00?IV1T.55B2!.0'!\"s:kAL!:'Dvu2c0IJ\";`;;ll9bW'yBY !4Y`;8,FF6DXJuJfOd`8Q1:X?P4'8;9"));
        // assert true
        System.out.println(Q125.instance.isPalindrome("Zeus was deified, saw Suez."));
    }
}
