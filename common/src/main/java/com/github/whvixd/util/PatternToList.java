package com.github.whvixd.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by wangzhx on 2019/3/14.
 */
public class PatternToList {

    /**
     * 获取(.*) 的次数 = 二维数组的长度
     * 获取 获取大括号中的内容
     * 再获取数组中的数字
     * 遍历 List<Condition> conditions
     *
     * @param conditionPattern
     * @param conditions
     * @return
     */
    public static List<List<Condition>> go(String conditionPattern, List<Condition> conditions) {
        return patternToList(conditionPattern).stream()
                .map(ors -> ors.stream()
                        .map(rowNo -> getRowNoCondition(rowNo, conditions))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    static class Condition {
        private String value;
        private int rowNo;
    }

    static List<List<Integer>> patternToList(String conditionPattern) {
        Pattern compile = Pattern.compile("\\((.+?)\\)");
        Matcher matcher = compile.matcher(conditionPattern);
        List<List<Integer>> numbers = Lists.newArrayList();
        while (matcher.find()) {
            String group = matcher.group(1);
            numbers.add(getAndNumber(group));
        }
        return numbers;
    }

    static List<Integer> getAndNumber(String andPattern) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(andPattern);
        List<Integer> andNumbers = Lists.newArrayList();
        while (matcher.find()) {
            String group = matcher.group();
            andNumbers.add(Integer.valueOf(group));
        }
        return andNumbers;
    }

    static Condition getRowNoCondition(int rowNo, List<Condition> conditions) {
        for (Condition condition : conditions) {
            if (condition.rowNo == rowNo) {
                return condition;
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        String p = "(1 and 2) or (3) or (4 and 5)";
//        List<Condition> conditions = Lists.newArrayList(new Condition("a", 0), new Condition("b", 1), new Condition("c", 2), new Condition("d", 3));
////        System.out.println(GsonUtil.toJson(go(p, conditions)));
//        System.out.println(getAndNumber("0 and 1 and 2 and 10 and 19 and 20"));
//    }


}
