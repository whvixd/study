package com.github.whvixd.util;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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

        Assert.assertTrue(process);
    }

    @Test
    public void test2() {
        boolean process = CompletionUtils.process(4, () -> {
            System.out.println("----");
            throw new RuntimeException();
        });
        Assert.assertFalse(process);
    }

    @Test
    public void test3() throws Exception {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(0, 1000).forEach(list::add);
        AtomicInteger counter=new AtomicInteger();

        boolean process = CompletionUtils.process(100, 1, list.size() - 1,
                (start, end) -> {
                    for(int j=start;j<end;j++){
                        System.out.println(list.get(j));
                        counter.incrementAndGet();

                    }
                });

        Assert.assertTrue(process);
        Assert.assertTrue(counter.get()==999);
    }

    @Test
    public void test4() throws Exception {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(0, 1).forEach(list::add);
        AtomicInteger counter=new AtomicInteger();

        boolean process = CompletionUtils.process(1, 0, list.size() - 1,
                (start, end) -> {
                    for(int j=start;j<end;j++){
                        System.out.println(list.get(j));
                        counter.incrementAndGet();

                    }
                });

        Assert.assertTrue(process);
        Assert.assertTrue(counter.get()==1);
    }

    @Test
    public void test5() throws Exception {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(0, 1).forEach(list::add);

        boolean process = CompletionUtils.process(list, System.out::println
        );

        Assert.assertTrue(process);
    }

    @Test
    public void test6() throws Exception {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(100, 201).forEach(list::add);

        AtomicInteger counter=new AtomicInteger();
        boolean process = CompletionUtils.process(list, e->{
            counter.incrementAndGet();
        });

        System.out.println(counter.get());
        Assert.assertTrue(list.size()==counter.get());
        Assert.assertTrue(process);
    }

    @Test
    public void test7() throws Exception {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(100, 201).forEach(list::add);

        AtomicInteger counter=new AtomicInteger();
        boolean process = CompletionUtils.process(100,0,list.size()-1,list, e->{
            counter.incrementAndGet();
        });

        System.out.println(counter.get());
        Assert.assertTrue(list.size()==counter.get());
        Assert.assertTrue(process);
    }
}
