package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.demo.algorithm.sort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

  

 示例：

 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/3sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by whvixd on 2020/6/12.
 */
public enum Q15 {
    instance;

    // O(n^4)
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> lists=new ArrayList<>();
        if(nums==null||nums.length<3)return lists;
        int l=nums.length;
        QuickSort.sort(nums);
        for(int i=0;i<l;i++){
            if(nums[i]>0){break;}
            for(int j=i+1;j<l;j++){
                for(int k=j+1;k<l;k++){
                    // -1 -1 3 4 没必要
                    if(-(nums[i]+nums[j])<nums[k]){
                        break;
                    }
                    if(!isRepetitive(lists,nums[i],nums[j],nums[k])&&
                            nums[i]+nums[j]+nums[k]==0){
                        lists.add(buildList(nums[i],nums[j],nums[k]));
                    }

                }
            }
        }

        return lists;
    }

    private List<Integer> buildList(int... num){
        List<Integer> list=new ArrayList<>();
        if(num==null||num.length==0)return list;
        for(int i:num){
            list.add(i);
        }
        return list;
    }

    private boolean isRepetitive(List<List<Integer>> lists,int i,int j,int k){
        return lists.stream().anyMatch(list-> {
            boolean b = list.stream().allMatch(e -> e == i);
            boolean c = ((i != j || i != k || i != 0) || b) && (list.contains(i) && list.contains(j) && list.contains(k));
            return b||c;
        });
    }


    public static void main(String[] args) {
        System.out.println(Q15.instance.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(Q15.instance.threeSum1(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(Q15.instance.threeSum1(new int[]{0,0,0}));
        System.out.println(Q15.instance.threeSum1(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0,0}));
        System.out.println(Q15.instance.threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0,0}));
    }

}
