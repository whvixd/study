package com.github.whvixd.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangzhx on 2018/3/23 18:34.
 */
@UtilityClass
public class StringUtils {
    /**
     * 半角字符替换成全角字符
     *
     * @param input
     * @return
     */
    String halfAngleChar2WideChar(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder sb = new StringBuilder(input.length() + 16);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '&':
                    sb.append('＆');// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                case '%':    // < 字符的 URL 编码形式表示的 ASCII 字符（十六进制格式） 是: %3c
                    processUrlEncoder(sb, input, i);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    void processUrlEncoder(StringBuilder sb, String s, int index) {
        if (s.length() >= index + 2) {
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'c' || s.charAt(index + 2) == 'C')) {    // %3c, %3C
                sb.append('＜');
                return;
            }
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '0') {    // %3c (0x3c=60)
                sb.append('＜');
                return;
            }
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'e' || s.charAt(index + 2) == 'E')) {    // %3e, %3E
                sb.append('＞');
                return;
            }
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '2') {    // %3e (0x3e=62)
                sb.append('＞');
                return;
            }
        }
        sb.append(s.charAt(index));
    }

    /**
     * 判断是否为公式
     * ${A.age}+21 true
     *
     * @param field
     * @return
     */
    public static boolean isCalculateExpression(String field) {
        char[] fChar = field.toCharArray();
        for (int i = 0; i < fChar.length; i++) {
            if ("+-*/".contains((fChar[i]) + "")) {
                if (isStandard(field, i)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isStandard(String field, int x) {
        if (x == 0 || x == field.length() - 1) {
            return false;
        }

        String left = field.substring(x - 1, x);
        String right = field.substring(x + 1, x + 2);
        if (Pattern.matches("[0-9]|[})]", left) &&
                Pattern.matches("[0-9]|[$(-]", right)) {
            return true;
        }
        return false;
    }

    //验证数字是否在有效范围內
    public static boolean isRangeNumberSize(String expression) {
        //${Account.number}+99999999999999999999999999 false
        //Day(3123)+${Account.create_by}
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            if (matcher.group().length() > 14) {
                return false;
            }
        }
        return true;
    }

    /**
     * 关于字符串的不会的api，不对外暴露
     */
    private void studyString() {
        System.out.println(String.format("%s---%s", "2", "3"));
    }

    /**
     * 判断数组是否是升序的
     * 1:升序
     *
     * @param ints
     * @param index
     * @return
     */
    private int isSort(int[] ints, int index) {
        if (index == 1) {
            return 1;
        }
        return ints[index] <= ints[index - 1] ? -1 : isSort(ints, index - 1);
    }

    /**
     * 获取字符串中的数字
     *
     * @param string
     * @return
     */
    public List<Integer> getNumberListInString(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        List<Integer> numbers = Lists.newArrayList();
        while (matcher.find()) {
            numbers.add(Integer.valueOf(matcher.group()));
        }
        return numbers;
    }

    public static String escapeText(String text) {
        if (org.apache.commons.lang3.StringUtils.isNoneBlank(text)) {
            String json = FastjsonUtil.toJson(text);
            return json.substring(1, json.length() - 1);
        }
        return text;
    }

    /**
     * @param text "test\
     * @return \\"test\\\
     */
    public static String escape(String text) {
        String regEx = "[\"\\\\]";
        StringBuffer stringBuffer = new StringBuffer();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            //appendReplacement 实现: 如果是'\\'  会跳过，append第二个; 如果正则中有group是\\ ，则\\\\\\ 长度是3，cursor也是3，异常; 四个整数倍，但是group 可能是\\
            matcher.appendReplacement(stringBuffer, "\\\\\\\\\\\\\\" + group);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    /**
     * @param in a:3,b:5,c:2@a:1,b:2
     * @return a:2,b:3,c:2
     */
    public static String getResult(String in) {
        int atIndex = in.indexOf("@");
        String beginString = in.substring(0, atIndex);
        String endString = in.substring(atIndex + 1, in.length());

        Map<String, Integer> beginMap = getMap(beginString.split(","));
        Map<String, Integer> endMap = new LinkedHashMap<>();
        if (endString.length() > 0) {
            endMap = getMap(endString.split(","));
        }

        StringBuilder result = new StringBuilder();
        for (String key : beginMap.keySet()) {
            Integer i = beginMap.get(key);
            if (endMap.containsKey(key)) {
                i = beginMap.get(key) - endMap.get(key);
            }
            result.append(key).append(":").append(i).append(",");
        }
        return result.substring(0, result.length() - 1);
    }


    private static Map<String, Integer> getMap(String[] split) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : split) {
            String[] string = s.split(":");
            String first = string[0];//a
            Integer second = Integer.valueOf(string[1]);//2
            if (second == 0) {
                continue;
            }
            map.put(first, second);
        }
        return map;
    }
}
