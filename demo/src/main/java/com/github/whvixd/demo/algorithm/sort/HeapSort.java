package com.github.whvixd.demo.algorithm.sort;

/**
 * 数据结构:数组，int leftChildIndex = 2*parent+1,rightChildIndex=leftChildIndex
 * 1. 构建大顶堆
 * 2. 堆顶元素与堆尾元素交换
 * 3. 断开堆尾元素(最大值)
 * 4. 执行1
 *
 * 参考:https://www.jianshu.com/p/11655047ab58
 *
 * Created by wangzhx on 2020/4/21.
 */
public class HeapSort {
    private void sort(int[] originArray) {
        //构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (originArray.length) / 2 - 1; i >= 0; i--) {
            headAdjust(originArray, originArray.length, i);
        }
        //排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = originArray.length - 1; i >= 1; i--) {
            int temp = originArray[0];
            originArray[0] = originArray[i];
            originArray[i] = temp;
            headAdjust(originArray, i, 0);
        }
    }

    private void headAdjust(int[] list, int len, int i) {
        int k = i, temp = list[i], index = 2 * k + 1;
        while (index < len) {
            if (index + 1 < len) {
                // 左子数 若小于 右子数，则交换
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }
            // 左右节点中较大的一个与父节点交换
            if (list[index] > temp) {
                list[k] = list[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        list[k] = temp;
    }
}
