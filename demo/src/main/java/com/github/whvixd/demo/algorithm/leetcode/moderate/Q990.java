package com.github.whvixd.demo.algorithm.leetcode.moderate;

import java.util.*;

/**
 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

 示例 4：

 输入：["a==b","b!=c","c==a"]
 输出：false

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhx on 2020/6/8.
 */
public enum Q990 {
    instance;

    public boolean equationsPossible(String[] equations) {
        if(equations.length==0) return true;
        Map<Character,List<Character>> equals=new HashMap<>();

        // 将 == 放入map中
        for (String equation : equations) {
            if (equation.contains("==")) {
                char leftC = equation.charAt(0);
                char rightC = equation.charAt(3);
                add(equals, leftC, rightC);
                add(equals, rightC, leftC);
            }
        }

        for (String equation : equations) {
            if (equation.contains("!=")) {
                char leftC = equation.charAt(0);
                char rightC = equation.charAt(3);
                if(leftC==rightC)return false;
                if(!compare(equals,rightC,equals.get(leftC),leftC,1)){
                    return false;
                }
            }
        }
        return true;

    }


    private boolean compare(Map<Character,List<Character>> equals,char compareC,List<Character> list,char firstKey,int count){
        if(list==null||!equals.containsKey(compareC)){
            return true;
        }
        if(list.contains(compareC)){
            return false;
        }
        boolean result=false;
        for(Character c:list){
            // todo 死循环如何解决
            if(firstKey==c||count>=equals.size()){
                result=true;
                continue;
            }
            result=compare(equals,compareC,equals.get(c),firstKey,++count);
        }
        return result;
    }

    private void add(Map<Character, List<Character>> equals, char leftC, char rightC) {
        if (equals.containsKey(leftC)) {
            List<Character> characters = equals.get(leftC);
            if (!characters.contains(rightC)) {
                characters.add(rightC);
            }
        }else {
            List<Character> characters=new ArrayList<>();
            characters.add(rightC);
            equals.put(leftC,characters);
        }
    }


    public static void main(String[] args) {
        // assert false
        System.out.println(Q990.instance.equationsPossible1(new String[]{"f==a","a==b","f!=e","a==c","b==e","c==f"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible1(new String[]{"d!=b","b==b","d==a","e!=b","f!=f","c==a"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","c==d","a==c","a!=d"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","e==c","b==c","a!=e"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"e==e","d!=e","c==d","d!=e"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible(new String[]{"e!=c","b!=b","b!=a","e==d"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"b==b","b==e","e==c","d!=e"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible(new String[]{"a!=a"}));
        // assert false
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","b!=c","c==a"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","b==c","a==c"}));

        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"c==c","b==d","x!=z"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","b==c","f==e","a==e","e==c"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==a","b==b","c==c","d==d","e==e"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"a!=b","c==c"}));
        // assert true
        System.out.println(Q990.instance.equationsPossible(new String[]{"a==b","b==a"}));
    }


    public boolean equationsPossible1(String[] equations) {
        if(equations.length==0) return true;
        List<List<Character>> lists = new ArrayList<>();

        for (String equation : equations) {
            if (equation.contains("==")) {
                char leftC = equation.charAt(0);
                char rightC = equation.charAt(3);

                List<Character> l1=null;
                List<Character> l2=null;
                for(List<Character> e:lists){
                    if(!e.isEmpty()){
                        // 找到对应的链表
                        if(e.contains(leftC)){
                            l1=e;

                        }
                        if(e.contains(rightC)){
                            l2=e;
                        }
                    }
                }
                // 如果 l=r 则两个数组相等，合并链表
                if(l1!=null&&l2!=null&&l1!=l2){
                    l1.addAll(l2);
                    l2.clear();
                 // 都为空，则添加链表
                }else if(l1==null&&l2==null){
                    List<Character> linkedList=new LinkedList<>();
                    linkedList.add(leftC);
                    if(leftC!=rightC){
                        linkedList.add(rightC);
                    }
                    lists.add(linkedList);
                // 若有一个为空，则字符添加到链表中
                }else if(l1!=null&&l2==null){
                    l1.add(rightC);
                }else if(l1==null&&l2!=null){
                    l2.add(leftC);
                }

            }
        }

        // 对 != 字符校验
        for (String equation : equations) {
            if (equation.contains("!=")) {
                char leftC = equation.charAt(0);
                char rightC = equation.charAt(3);
                if (leftC == rightC) return false;
                for (List<Character> list : lists) {
                    if (!list.isEmpty()) {
                        // 若左右字符都在一个链表中，则false
                        if (list.contains(leftC) && list.contains(rightC)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
