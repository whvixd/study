package com.github.whvixd.demo.algorithm;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 递归
 *
 * Created by wangzhx on 2018/5/30.
 */
public class RecursionDemo {
    @Data
    public static class C {
        private List<C> cs;

        public boolean hasC() {
            return !CollectionUtils.isEmpty(cs);
        }
    }

    public void recursion(C c) {
        if (c == null) {
            return;
        }

        boolean f = c.hasC();
        if (f) {
            List<C> cs = c.getCs();
            cs.forEach(c1 -> {
                if(c1.hasC()){
                    recursion(c1);
                    return;
                }
                System.out.println("hava c");
            });
        }

    }

    public static void main(String[] args) {
        RecursionDemo tmp = new RecursionDemo();
        C c = new C();
        C c1 = new C();
        C c2 = new C();

        List<C> cList = Lists.newArrayList();
        cList.add(c1);
        cList.add(c2);

        c.setCs(cList);
        tmp.recursion(c);
    }
}
