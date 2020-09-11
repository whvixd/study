package com.github.whvixd.util;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by wangzhixiang on 2020/9/11.
 */
public class CompletionUtilsTest {

    @Test
    public void test1() {
        boolean process = CompletionUtils.process(4, () -> {
            System.out.println("----");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(process);
    }

    @Test
    public void test2() {
        boolean process = CompletionUtils.process(4, () -> {
            System.out.println("----");
            throw new RuntimeException();
        });

        System.out.println(process);
    }

    @Test
    public void test3() throws Exception{
        List<Integer> list = Lists.newArrayList();
        IntStream.range(0, 1000).forEach(list::add);

        boolean process = CompletionUtils.process(100, 100, 333,
                (start, end) -> {
                    System.out.println(list.get(start));
                    System.out.println(list.get(end));
                    System.out.println("===========");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(process);
    }
}
