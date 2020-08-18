package com.github.whvixd.demo.jdk.Java8;

import com.github.whvixd.demo.Entity;
import com.github.whvixd.util.GsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.BiConsumer;
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

    public void test() {
        String[] strings = {"a", "a", "a", "a", "a"};

        Stream stream = Stream.of("a", "a", "a", "a", "a");
        Stream stream1 = Arrays.stream(strings);
        Stream stream2 = Stream.of(strings);

        int[] ii = new int[]{1, 3, 2};
//        IntStream.of(ii).forEach(value -> {for(int i:ii){System.out.print(i);}});

//        IntStream.range(1,10).forEach(System.out::print);//[1,10)

        /**
         * 九九乘法表
         */
        IntStream.range(1, 10).forEach(i -> {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
            }
            System.out.println();
        });

        IntStream.rangeClosed(3, 4).forEach(System.out::print);//[3,4]

        String[] strings1 = (String[]) stream.toArray(String[]::new);//类型转换
        System.out.println(strings1[3]);

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        for (Integer i : evens) {
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
        Stream.of("a", "b").peek(System.out::print).filter(n -> n == "a").map(String::valueOf).collect(Collectors.toList()).forEach(System.out::print);

        Stream.of(14, 31, 2, 1).filter(n -> n / 10 == 0).forEach(System.out::print);

        System.out.println("----------------------------------------");

        List<Integer> ints = new ArrayList();
        for (int i = 1; i < 3; i++) {
            ints.add(i);
        }

        System.out.println("ints sum is:" + ints.stream().reduce(7, (sum, item) -> sum + item));

        Entity.Student s = new Entity.Student();

        /**
         * toMap 第三个入参是当k重复时，取哪个k的值，
         */
        Stream.of(14, 31, 2, 1, 1).distinct().collect(Collectors.toMap(k -> k, k -> k, (v1, v2) -> v2));

        /**
         * toMap,v为空则NPE，k为空不会
         */
        List<String> list = Lists.newArrayList(null, "b", "c");
        Map<String, Object> map = Maps.newHashMap();
        System.out.println(list.stream().collect(Collectors.toMap(k->k,k->"1")));

    }

    private void testOrElse() {
        int i = IntStream.range(0, 10).filter(k -> 2 == k).findFirst().orElse(1);
        int i1 = IntStream.range(0, 10).filter(k -> -1 == k).findFirst().orElseGet(() -> 3);
        System.out.println(i);
        System.out.println(i1);
        boolean present = IntStream.range(0, 10).filter(k -> -1 == k).findFirst().isPresent();
        System.out.println(present);
    }

    private void testToMap() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", 1);
        map.put("b", "2");
        Map<String, Object> map1 = list.stream()
                .collect(HashMap::new, (m, v) -> m.put(v, v), HashMap::putAll);

        Map<String, Object> map2 = list.stream()
                /**
                 * 第三个参数：组合器—用于组合两个值的关联的、不干涉的、无状态的函数，必须与累加器函数兼容
                 * .parallelStream()
                 */
                .collect(Maps::newHashMap, (m, k) -> m.put(k, map.get(k)), Map::putAll);
        System.out.println(map2);
        /**
         * merge 操作判空 NPE
         */
        Map<String, Object> map3 = list.stream().collect(Collectors.toMap(k -> k, map::get, (k1, k2) -> k1));

        /**
         * 每个stream元素的类型为String，接着我们调用了collect方法，collect方法第一个参数是创建一个StringBuilder对象，在第二个参数中
         * ，我们打印了当前的线程id,和t,u的值方便调试。执行的操作也只是把String加入到StringBuilder中，第三个参数则把两个StringBuilder合并。
         * 从输出结果中我们可以看见，在执行accumulator操作的时候t的值是空的，并且是4个线程同时进行了accumulator操作，
         * 每个线程都把String加入到了StringBuilder中，而在执行combiner操作的时候，就由4个线程变成了2个，然后进行合并操作。最终结果为1234。由于是多线程的，所以每次输出的顺序是不一样的。
         */
        System.out.println(Arrays.asList("1","2","3","4").parallelStream().collect(
                StringBuilder::new,
                (t, u) -> {
                    System.out.println("accumulator operate current thread:"+Thread.currentThread().getId()+"   t:"+t+" u:"+u);
                    t.append(u);
                    System.out.println("accumulator operate current thread:"+Thread.currentThread().getId()+"   result t:"+t+" u:"+u);
                }
                , (BiConsumer<StringBuilder, StringBuilder>) (t, u) -> {
                    System.out.println("combiner operate current thread:"+Thread.currentThread().getId()+"   t:"+t+" u:"+u);
                    t.append(u);
                    System.out.println("combiner operate current thread:"+Thread.currentThread().getId()+"   result t:"+t+" u:"+u);
                }));
    }

    public static void main(String[] args) {
//        Map<String,Object> map = Maps.newHashMap();
//        map.put("name","a");
//        map.put("age",20);
//        System.out.println(JacksonUtil.fromJson(JacksonUtil.toJson(map),S.class));

        S s1=new S(1,1,"g_1_1",2);
        S s2=new S(2,1,"g_1_1",2);
        S s3=new S(3,2,"g_2_1",2);
        S s4=new S(4,2,"g_2_2",2);

        List<S> sList=Lists.newArrayList(s1,s2,s3,s4);
        Map<Integer,List<S>> map=Maps.newHashMap();
        for(S s:sList){
            int groupId = s.getGroupId();
            if(map.get(groupId)!=null){
                map.get(groupId).add(s);
            }else {
                List<S> s5 = Lists.newArrayList(s);
                map.put(groupId,s5);
            }
        }
        List<ArrayList<S>> collect = map.values().stream().map(Lists::newArrayList).collect(Collectors.toList());
        System.out.println(GsonUtil.toJson(collect));

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class S{
        private int id;
        private int groupId;
        private String name;
        private int age;
    }
}
