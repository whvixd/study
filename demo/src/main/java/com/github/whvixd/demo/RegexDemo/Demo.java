package com.github.whvixd.demo.RegexDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则学习整理
 * Created by wangzhx on 2018/3/27 17:18.
 */
public class Demo {

    public static void main(String[] args) {

        String regex = "^-*\\$\\{[a-zA-Z.#]+}$|-*\\d+|.*[+\\-*/]{2}.*|.*[\\u4e00-\\u9fa5].*|.*[^\\da-zA-Z${.#_}()+\\-*/].*|.*[#_]{3}.*|^[+*/]+.*|.*[+\\-*/]+\\$|\\s|((?![+\\-*/]).)*|";

        //不包含+-*/任意一个
        System.out.println(Pattern.matches("((?![+\\-*/]).)*","2-2"));
        System.out.println(Pattern.matches("[^+-/*]", "+"));//[^] 非

        System.out.println(Pattern.matches("(a|b|c|d)", "a"));//(a|b)包含a或b

        //* 匹配0次或多次，qwe ，qwer多个,+ 匹配qwerrr，字符加上.或*或?，该字符可以出现的次数
        System.out.println(Pattern.matches("(qwer)*","qwerqwer"));//{0,} ->  () 可以认为是一个字符，
        System.out.println(Pattern.matches("qwer+","qwe"));//{1,}
        System.out.println(Pattern.matches("qwer?","qwerr"));//{0,1}

        System.out.println(Pattern.matches("^q.*e$","qeqwe"));//^以什么开头，$以什么结尾，如果中间是任意字符，可以用.*
        System.out.println("b"+Pattern.matches("er\\b","eber"));//

        System.out.println(Pattern.matches("(qwer){3,}","qwerqwerqwer"));//匹配qwer至少三次
        System.out.println(Pattern.matches("10*1","1001"));
        System.out.println(Pattern.matches("(qwer){3,}","qwerqwerqwer"));

        //<a href="http://www.zjmainstay.cn/my-regexp" class="demo8" title="正则三段论应用举例">正则表达式入门教程</a>


        /**
         * 环视
         * 环视主要有以下4个用法：
         (?<=exp) 匹配前面是exp的数据
         (?<!exp) 匹配前面不是exp的数据
         (?=exp) 匹配后面是exp的数据
         (?!exp) 匹配后面不是exp的数据
         注：exp是表达式
         */

        System.out.println(Pattern.matches("(?<=q)wer","qwer"));
        Pattern pattern = Pattern.compile("(?<=B)AAA");
        Matcher matcher = pattern.matcher("BAAA BAAA");
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }

    }

    /**
     * 只能有.-_数据及英文，字段.-_不能是首尾，不能连续出现2次及以上
     * @param name
     * @return
     */
    public boolean isName(String name) {
        return Pattern.matches("[\\w_.-]+", name)
                &&
                !Pattern.matches("^[_.-].*|.*[_.-]$|.*[_.-]{2,}.*", name);
    }

    /**
     * 判断是否是表达式
     * 1. 以 -${..}
     * 2. -2
     * 3. +-/* 出现两次及以上
     * 4. 中文
     * 5. 没有出现数字英文${.#_}()+-乘/
     * 6. #_出现三次以上
     * 7. 以+乘/开头的
     * 8. +-乘/$
     * 9. \\s 空白字符
     * 10 空
     */
    public void isCalculate() {
        String regex = "^-*\\$\\{[a-zA-Z.#]+}$|-*\\d+|.*[+\\-*/]{2}.*|.*[\\u4e00-\\u9fa5].*|.*[^\\w${.#_}()+\\-*/].*|.*[#_]{3}.*|^[+*/]+.*|.*[+\\-*/]+\\$|\\s|";
        //String regex = "^-*\\\$\\{[a-zA-Z.#]+}\$|-*\\d+|.*[+\\-*/]{2}.*|.*[\\u4e00-\\u9fa5].*|.*[^\\da-zA-Z\${.#_}()+\\-*/].*|.*[#_]{3}.*|^[+*/]+.*|.*[+\\-*/]+\$|\\s|";

        String f4 = "你好_";
        System.out.println(!Pattern.matches(regex, f4) == false);

        String f5 = "-你好";
        System.out.println(!Pattern.matches(regex, f5) == false);

        String f6 = "___";
        System.out.println(!Pattern.matches(regex, f6) == false);

        String f7 = "";
        System.out.println(!Pattern.matches(regex, f7) == false);

        String f8 = "^^^";
        System.out.println(!Pattern.matches(regex, f8) == false);

        String f9 = "1+1";
        System.out.println(!Pattern.matches(regex, f9) == true);


        String f17 = "1";
        System.out.println(!Pattern.matches(regex, f17) == false);

        String f18 = "-1";
        System.out.println(!Pattern.matches(regex, f18) == false);

        String f19 = "0";
        System.out.println(!Pattern.matches(regex, f19) == false);


        String f26 = "你好啊,你知道我是谁艾玛)(!*&*(&^HS*(DT&AS ";
        System.out.println(!Pattern.matches(regex, f26) == false);

        String f27 = "=)^&@!^#(*!@&YT";
        System.out.println(!Pattern.matches(regex, f27) == false);

    }
}
