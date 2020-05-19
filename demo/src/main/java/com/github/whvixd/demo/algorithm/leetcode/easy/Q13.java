package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/roman-to-integer/
 *
 * Created by wangzhx on 2020/5/19.
 */
public enum Q13 {
    instance;
    // "IV"
    public int romanToInt(String s) {
        int sum=0;
        for(int i=0,j=i+1;i<s.length();i++,j++){
            int cur = getSingleRomanToInt(s.charAt(i));
            if(j<s.length()){
                int next = getSingleRomanToInt(s.charAt(j));
                if(cur<next){
                    cur=next-cur;
                    i++;
                    j++;
                }
            }
            sum+=cur;
        }
        return sum;
    }



    private int getSingleRomanToInt(char c){
        switch (c){
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        System.out.println( Q13.instance.romanToInt("IV"));
        assert Q13.instance.romanToInt("IV")==4;
        System.out.println( Q13.instance.romanToInt("IX"));
        System.out.println( Q13.instance.romanToInt("III"));
        System.out.println( Q13.instance.romanToInt("LVIII"));
        System.out.println( Q13.instance.romanToInt("MCMXCIV"));
        System.out.println( Q13.instance.romanToInt("IM"));

    }


}
