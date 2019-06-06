package com.github.whvixd.demo.Java8;

import com.github.whvixd.demo.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * distinct 去重
 * filter 过滤 :true 保留,false 过滤
 * map 转型
 * peek 对原有的元素进行包装
 * sorted 排序
 * skip 跳过
 * limit 限制
 * forEach 循环
 * collect 聚集 toList toSet toMap
 * reduce
 */
public class StreamDemo {
    public static void main(String[] args) {
        String[] strings = {"a","a","a","a","a"};

        Stream stream = Stream.of("a","a","a","a","a");
        Stream stream1 = Arrays.stream(strings);
        Stream stream2 = Stream.of(strings);

        int []ii = new int[]{1,3,2};
//        IntStream.of(ii).forEach(value -> {for(int i:ii){System.out.print(i);}});

//        IntStream.range(1,10).forEach(System.out::print);//[1,10)

        /**
         * 九九乘法表
         */
        IntStream.range(1,10).forEach(i->{for(int j=1;j<=i;j++){System.out.print(j+"*"+i+"="+i*j+"\t");}System.out.println();});

        IntStream.rangeClosed(3,4).forEach(System.out::print);//[3,4]

        String[] strings1 = (String[]) stream.toArray(String[]::new);//类型转换
        System.out.println(strings1[3]);

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        for (Integer i:evens) {
            System.out.print(i);
        }
System.out.println("----------------------------------------");

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

System.out.println("----------------------------------------");

//        IntStream.of(3,12,1,2,1,3,21,3,21).filter(n -> n%10==1).peek(System.out::print).map(Integer::floatValue).collect(Collectors.toList());
        Stream.of("a","b").peek(System.out::print).filter(n -> n=="a").map(String::valueOf).collect(Collectors.toList()).forEach(System.out::print);

        Stream.of(14,31,2,1).filter(n -> n/10==0).forEach(System.out::print);

System.out.println("----------------------------------------");

        List<Integer> ints = new ArrayList();
        for (int i=1;i<3;i++){
            ints.add(i);
        }

        System.out.println("ints sum is:" + ints.stream().reduce(7, (sum, item) -> sum + item));

        Entity.Student s = new Entity.Student();

        /**
         * toMap 第三个入参是当k重复时，取哪个k的值，
         */
        Stream.of(14,31,2,1,1).distinct().collect(Collectors.toMap(k->k,k->k,(v1,v2)->v2));
    }
}
