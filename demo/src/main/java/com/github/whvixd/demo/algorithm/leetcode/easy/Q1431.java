package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 *
 * Created by wangzhx on 2020/5/20.
 */
public enum Q1431 {
    instance;
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max=Integer.MIN_VALUE;
        for(int candy:candies){
            max=max<candy?candy:max;
        }
        List<Boolean> list = new ArrayList<>(candies.length);
        for(int candy:candies){
            if(candy+extraCandies>=max){
                list.add(true);
            }else {
                list.add(false);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] candies=new int[]{4,2,1,1,2};  int extraCandies=1;
        System.out.println(Q1431.instance.kidsWithCandies(candies,extraCandies));
    }
}
