package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.util.SystemUtil;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。

 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 示例:

 s = "3[a]2[bc]", 返回 "aaabcbc".
 s = "3[a2[c]]", 返回 "accaccacc".
 s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/decode-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/5/27.
 */
public enum Q394 {
    instance;

    public String decodeString(String s) {
        if(s.length()==0) return s;
        return decode(s, 0,0,0);
    }

    private String decode(String s,int repetition,int lPoint,int rPoint){
        for(int i=lPoint;i<s.length();){
            char c=s.charAt(i);
            if(isNumber(c)){
                // 重复执行次数
                repetition= getNumByString(s,i);
                int digit = getDigit(repetition);
                // 左指针,[
                i+=digit;
                return decode(s,repetition,i,rPoint);
            }else if(c=='['){
                // 左指针
                lPoint=i;
            }else if(c==']'){
                // 右指针
                rPoint=i;
                if(lPoint<rPoint){
                    // 2[ab] -> abab
                    int digit=getDigit(repetition);
                    String merge = s.substring(lPoint+1, rPoint);
                    StringBuilder sb=new StringBuilder();
                    while (repetition>0){
                        sb.append(merge);
                        repetition--;
                    }
                    // 插入到字符中
                    s=s.substring(0,lPoint-digit)+sb.toString()+s.substring(rPoint+1);
                    // 可能会有2[ab],重头遍历
                    i=0;
                    continue;

                }
            }
            i++;
        }
        return s;
    }

    private boolean isNumber(char c){
        // [0-9]
        return 48<=c&&57>=c;
    }

    private int getNumByString(String s, int point){
        StringBuilder num= new StringBuilder();
        for(int i=point;i<s.length();i++){
            char c = s.charAt(i);
            if(48<=c&&57>=c){
                num.append(c);
            }else {
                break;
            }
        }
        return Integer.valueOf(num.toString());
    }

    private int getDigit(int num){
        int count=1;
        while (num/10!=0){
            count++;
            num/=10;
        }
        return count;
    }

    public static void main(String[] args) {
        // assert "acdcdacdcdacdcd"
        SystemUtil.print(Q394.instance.decodeString("3[a2[cd]]"));
        // assert "abcabccdcdcdef"
        SystemUtil.print(Q394.instance.decodeString("2[abc]3[cd]ef"));
        // assert "accaccacc"
        SystemUtil.print(Q394.instance.decodeString("3[a2[c]]"));
        // assert "aaabcbc"
        SystemUtil.print(Q394.instance.decodeString("3[a]2[bc]"));
        // assert "sdfeegfeegi"
        SystemUtil.print(Q394.instance.decodeString("sd2[f2[e]g]i"));
        // assert "leetcode"
        SystemUtil.print(Q394.instance.decodeString("leetcode"));

        SystemUtil.print(Q394.instance.decodeString("100[leetcode]"));


    }

}
