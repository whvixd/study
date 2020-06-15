package com.github.whvixd.demo.algorithm.leetcode.moderate;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * <p>
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * <p>
 * 请注意，答案不一定是 arr 中的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by wangzhixiang on 2020/6/15.
 */
public enum Q1300 {
    instance;

    // result 不一定是arr中的数字
    public int findBestValue(int[] arr, int target) {
        int big = 0;
        int sum = 0;
        for (int i : arr) {
            sum += i;
            big = big > i ? big : i;
        }
        if (sum <= target) return big;
        int ans = target / arr.length;
        sum = getSum(arr, ans);
        while (sum < target) {
            int sumn = getSum(arr, ans + 1);
            if (sumn >= target) return target - sum <= sumn - target ? ans : ans + 1;
            sum = sumn;
            ans++;
        }
        return 0;
    }

    public int getSum(int[] arr, int value) {
        int sum = 0;
        for (int i : arr) sum += i < value ? i : value;
        return sum;
    }

    public static void main(String[] args) {
        // assert 3
        System.out.println(Q1300.instance.findBestValue(new int[]{4, 9, 3}, 10));
        // assert 5
        System.out.println(Q1300.instance.findBestValue(new int[]{2,3,5},10));
        // assert 11361
        System.out.println(Q1300.instance.findBestValue(new int[]{60864,25176,27249,21296,20204},56803));
    }
}
