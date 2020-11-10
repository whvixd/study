package com.github.whvixd.demo.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by wangzhixiang on 2020/11/9.
 */
public class StandardEvaluationContextTest {
    public static void main(String[] args) {
        // 初始化springEL表达式解析器实例
        ExpressionParser parser = new SpelExpressionParser();
        // 初始化解析内容上下文
        EvaluationContext context = new StandardEvaluationContext();
        A a = new A();
        a.setId("123");
        a.setB(new B("456"));
        context.setVariable("a", a);
        context.setVariable("c", "789");

        Expression expression1 = parser.parseExpression("#a.id");
        Expression expression2 = parser.parseExpression("#a.b.bId");
        Expression expression3 = parser.parseExpression("#c");

        Object value1 = expression1.getValue(context);
        Object value2 = expression2.getValue(context);
        Object value3 = expression3.getValue(context);


        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);

    }

    @Data
    static class A {
        private String id;
        private B b;
    }

    @Data
    @AllArgsConstructor
    static class B {
        private String bId;
    }


}
