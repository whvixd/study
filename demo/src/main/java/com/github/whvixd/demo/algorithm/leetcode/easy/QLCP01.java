package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/guess-numbers/
 *
 * Created by wangzhx on 2020/5/20.
 */
public enum QLCP01 {
    instance;
    public int game(int[] guess, int[] answer) {
        if(guess.length==0||0==answer.length) return 0;
        int count=0;
        for(int i=0;i<guess.length&&i<answer.length;i++){
            if(guess[i]==answer[i]) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] guess = new int[]{0,2,3};
        int[] answer = new int[]{1,2,3};
        System.out.println(QLCP01.instance.game(guess,answer));
    }
}
