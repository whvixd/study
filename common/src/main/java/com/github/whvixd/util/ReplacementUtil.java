package com.github.whvixd.util;

import com.google.common.base.Joiner;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangzhx on 2018/3/25 13:09.
 */
@UtilityClass
public class ReplacementUtil {
    /**
     * {a}--{b}--{c}--{d}--
     * a--b--c--d--
     *
     * @param text
     * @param regex
     * @return
     */
    public String replaceStringWithRegex(String text, Map<String, Object> valueMap, String regex) {
        valueMap.forEach((name, value) -> {
            String newValue = "";
            if (value instanceof List) {
                List newList = (List) value;
                newValue = Joiner.on(",").join(newList);

            } else {
                newValue = value + "";
            }
            if (!newValue.contains(".")) {
                newValue = "${" + name + "}";
            }
            valueMap.put(name, newValue);
        });

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String attachedString = matcher.group(1);
            //将匹配到的字符替换后放在StringBuffer里，只会匹配到最后一个符合条件的，最后一个后面的内容不会加在sb中
            matcher.appendReplacement(stringBuffer, (String) valueMap.get(attachedString));
        }
        //会将最后一个匹配的后面的内容加上去
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public String replaceStringWithRegex2(String text, Map<String, Object> valueMap) {
        long startTime = System.currentTimeMillis();
        final String[] result = {text};
        valueMap.forEach((name, value) -> {
            String newValue = "";
            if (value instanceof List) {
                List newList = (List) value;
                newValue = Joiner.on(",").join(newList);
            } else {
                newValue = value + "";
            }
            if (!name.contains(".")) {
                newValue = "${" + name + "}";
            }
            valueMap.put(name, newValue);

            String rep = "${" + name + "}";
            if (result[0].contains(rep)) {
                result[0] = result[0].replace(rep, (String) valueMap.get(name));
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("方法内：" + (endTime - startTime));
        return result[0];
    }

}
