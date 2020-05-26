package com.github.whvixd.demo.algorithm.leetcode.easy;


import java.util.Stack;

/**
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-parentheses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/5/26.
 */
public enum Q20 {

    instance;

    public boolean isValid(String s) {
        int l=s.length();
        if(l==0||l%2==1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<l/2;i++){
            stack.push(s.charAt(i));
        }
        for(int i=l/2;i<l;i++){
            if(!isMatched(stack.pop(),s.charAt(i))) return false;
        }
        return true;
    }

    private boolean isMatched(char c1,char c2){
        switch (c1){
            case '(':return c2 == ')';
            case '[':return c2 == ']';
            case '{':return c2 == '}';
            default:return false;
        }
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q20.instance.isValid("{[]}"));
        // TODO: 2020/5/26 修改
        System.out.println(Q20.instance.isValid("()[]{}"));
    }

}
