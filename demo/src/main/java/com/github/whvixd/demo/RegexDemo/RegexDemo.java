package com.github.whvixd.demo.RegexDemo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    //${activity_0##ReturnedGoodsInvoiceObj##created_by}+10  ->  ${ReturnedGoodsInvoiceObj.created_by}+10
    public static String reduceWell(String s) {

        String regex = "\\$\\{(.+?)##";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()){
            String result =  matcher.group(1);
            String replace = s.replace(result,"").replace("##",".");
            StringBuffer sb = new StringBuffer(replace);
            return sb.delete(2,3).toString();
        }
        return "";
    }

    private static boolean isExpression(String field) {
        String REGEX_CALCULATE = ".*\\$\\{.*}.*";
        String REGEX_SYMBOL = ".*[+\\-*/].*";
        return Pattern.matches(REGEX_CALCULATE, field)&&Pattern.matches(REGEX_SYMBOL,field);
    }

    public static void main(String[] args) {
        String pattren = ".*\\$\\{.*}.*(\\+?)";
        String pattren1 = ".*\\$\\{.*}.*[+\\-*/].*";
//        System.out.println( Pattern.matches(pattren1,"${dsad}"));
        System.out.println(Pattern.matches(pattren1, "${dsad}"));


        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        String strs = "1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0)";
        String field = "${a}+11";
        String internalExpression1 = "\\$\\{a}";
        Object value = 10;
        String formula = field.replaceAll(internalExpression1, value.toString());
        System.out.println(formula);
        try {
            System.out.println(jse.eval(strs));
        } catch (Exception t) {
        }

        /*String pa = "${activity_0##ReturnedGoodsInvoiceObj##created_by}+10";
        String str = reduceWell("${activity_0##ReturnedGoodsInvoiceObj##created_by}+10");
        String str1 = pa.replace(str,"");
        String str2 = str1.replace("##",".");
        StringBuffer sb = new StringBuffer(str2);
        sb.delete(2,3);
        System.out.println(sb);*/

        String str = reduceWell("${activity_0##ReturnedGoodsInvoiceObj##created_by}+10");
        System.out.println(str);

        System.out.println(isExpression("1-${a.n}"));
        System.out.println(isExpression("${a.n}-1"));
        System.out.println(isExpression("${a.n}-${a.n}"));

    }

}
