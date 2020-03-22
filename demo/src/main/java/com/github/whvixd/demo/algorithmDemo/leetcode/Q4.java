package com.github.whvixd.demo.algorithmDemo.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * Created by wangzhx on 2020/3/21.
 */
public enum Q4 {
    instance;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] nums3 = new int[length1 + length2];

        int i = 0, j = 0, k = 0;
        while (i <= length1 - 1 && j <= length2 - 1) {
            if (nums1[i] <= nums2[j]) {
                nums3[k++] = nums1[i++];
            } else {
                nums3[k++] = nums2[j++];
            }
        }
        if (i <= length1 - 1) {
            while (i <= length1 - 1) {
                nums3[k++] = nums1[i++];
            }
        } else if (j <= length2 - 1) {
            while (j <= length2 - 1) {
                nums3[k++] = nums2[j++];
            }
        }

        boolean b = nums3.length % 2 == 1;
        if (b) {
            return nums3[nums3.length / 2];
        } else {
            return (nums3[nums3.length / 2 - 1] + nums3[nums3.length / 2]) / 2D;
        }

    }

    public static void main(String[] args) {
//        int[] nums1 = {};
        int[] nums1 = {1, 3, 6, 8};
        int[] nums2 = {1,2,3,4};
        System.out.println(Q4.instance.findMedianSortedArrays(nums1, nums2));
    }
}
