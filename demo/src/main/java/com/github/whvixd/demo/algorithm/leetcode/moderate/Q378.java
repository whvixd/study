package com.github.whvixd.demo.algorithm.leetcode.moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。

  

 示例：

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 返回 13

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/2.
 */
public enum Q378 {
    instance;
    public int kthSmallest(int[][] matrix, int k) {
        List<Integer> list=new ArrayList<>();
        for(int[] array:matrix){
            for(int num:array){
                list.add(num);
            }
        }
        Object[] objects = list.toArray();
        Arrays.sort(objects);
        return (int) objects[k-1];
    }

    public static void main(String[] args) {
        int[] a={1,  5,  9};
        int[] b={10, 11, 13};
        int[] c={12, 13, 15};
        int[][] matrix=new int[][]{a,b,c};
        // assert 13
        System.out.println(Q378.instance.kthSmallest(matrix,8));
    }
}
