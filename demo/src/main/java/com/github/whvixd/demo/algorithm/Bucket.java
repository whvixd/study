package com.github.whvixd.demo.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by wangzhixiang on 2020/9/8.
 */
public class Bucket {
    private static final ForkJoinPool FORKJOIN_POOL =  ForkJoinPool.commonPool();
    // 单桶初始化大小
    private static final int MAXINITPERBUCKET = 100000;
    // 桶的数量
    private static final int MAXBUCKET = 1000;
    private static int resources[] = new int[MAXBUCKET *MAXINITPERBUCKET ];

    static {
        // 随机生成待排序的数组元素
        Random random = new Random();
        int len = MAXINITPERBUCKET * MAXBUCKET;
        for(int index = 0 ; index < len; index++) {
            resources[index] = random.nextInt(len);
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 操作步骤如下：
         * 1、首先开始分桶
         * 2、然后将这些桶放入fork/join pool中进行归并计算
         * 3、待所有桶的排序完成后，再将这些有序集合，排列成一个新的有序集合
         * */
        // 1、==========
        // 初始化分桶（还是单线程的）
        System.out.println("开始计算===== ");
        long beginTime = System.currentTimeMillis();
        // 桶的大小是固定的，为了避免数组超界，在实际应用中单桶大小应设置一个合理的值
        int[][] bucketArrays = new int[MAXBUCKET][MAXINITPERBUCKET *10];
        // 单桶目前已存储的元素个数
        int[] bucketIndexs = new int[MAXBUCKET];
        int resourceLen = MAXBUCKET * MAXINITPERBUCKET;
        for(int index = 0 ; index < resourceLen ; index++) {
            int bucketIndex = resources[index] / MAXINITPERBUCKET;
            // 放入相应的桶，并更新这个桶“已存储的元素个数”
            bucketArrays[bucketIndex][bucketIndexs[bucketIndex]++] = resources[index];
        }
        System.out.println("完成分桶===== ");

        // 2、==========
        List<ForkJoinTask<int[]>> results = new ArrayList<>();
        for(int index = 0 ; index < MAXBUCKET ; index++) {
            int[] itemBucket =  bucketArrays[index];
            BucketSortTask bucketSortTask = new BucketSortTask(itemBucket , 0 , bucketIndexs[index] - 1);
            ForkJoinTask<int[]> taskFeature =  FORKJOIN_POOL.submit(bucketSortTask);
            results.add(taskFeature);
        }

        // 3、==========
        // 依次获得每个桶的结果就是一个有序集合了，这个步骤必须要算在时间内
        System.out.println("开始最后排序===== ");
        int[] resultAll = new int[MAXBUCKET * MAXINITPERBUCKET];
        int destPos = 0;
        for(int index = 0 ; index < MAXBUCKET ; index++) {
            int[] bucketResults = results.get(index).get();
            System.arraycopy(bucketResults, 0, resultAll,destPos , bucketIndexs[index]);
            destPos += bucketIndexs[index];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时=" + (endTime - beginTime) + " | ");
        // 开始检查
//        System.out.println("检查结果：" + SortResultCheck.scan(resultAll));
    }

    // 桶排序任务，每个桶中采用快速排序法进行
    static class  BucketSortTask extends RecursiveTask<int[]> {
        private int resources[];
        private int startIndex , endIndex;
        public BucketSortTask(int resources[] , int startIndex , int endIndex) {
            this.resources = resources;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected  int[] compute() {
            QuickSortTask quickSortTask = new QuickSortTask(resources, this.startIndex, this.endIndex );
            ForkJoinTask<int[]> result =  quickSortTask.fork();
            // 等待这个任务
            int[] resultArrays;
            try {
                resultArrays = result.get();
                return resultArrays;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // 快速排序任务（子任务）
    static class QuickSortTask extends RecursiveTask<int[]> {
        private int resources[];
        private int beginIndex;
        private int endIndex;
        public QuickSortTask(int resources[] ,  Integer beginIndex , Integer endIndex) {
            this.resources = resources;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected int[] compute() {
            this.scan(resources, beginIndex, endIndex);
            return resources;
        }

        private void  scan(int[] resources, int startIndexs , int endIndexs) {
            // 这部分代码请参见之前的快速排序算法代码
//            ......
        }
    }
}
