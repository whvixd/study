package com.github.whvixd.demo.algorithmDemo.sort;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by wangzhx on 2020/4/21.
 */
public class Common {
    public static int[] staticArray = {3, 1, -1, 0, 9, 2, 10};

    private static int[] originArray = generateRandomArray();

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        return random.ints(size).toArray();
    }

    public static int[] generateRandomArray() {
        return generateRandomArray(1000);
    }

    public static <T> void sort(Class<T> clazz) {
        try {
            T o = clazz.newInstance();
            Method sort = clazz.getDeclaredMethod("sort", int[].class);
            if (originArray.length <= 0) {
                System.out.println("sort origin array is empty!");
                return;
            }
            System.out.println("=============" + clazz.getSimpleName() + "=============");
            System.out.println("origin:" + Arrays.toString(originArray));
            sort.setAccessible(true);
            int[] array = Arrays.copyOf(originArray, originArray.length);

            long currentTimeMillis = System.currentTimeMillis();
            sort.invoke(o, array);
            int[] result = array;
            long elapsed = System.currentTimeMillis() - currentTimeMillis;
            System.out.println("result:" + Arrays.toString(result));
            System.out.println("elapsed:" + elapsed + "(ms)");
            System.out.println("        👆");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sort(BubbleSort.class);
        sort(QuickSort.class);
        sort(MergeSort.class);
        sort(HeapSort.class);
        sort(SelectSort.class);
        sort(InsertSort.class);
    }
}
