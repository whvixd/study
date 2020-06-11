package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.util.SystemUtil;

import java.util.*;

/**
 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/daily-temperatures
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhx on 2020/6/11.
 */
public enum Q739 {
    instance;

    // O(n^2)
    public int[] dailyTemperatures(int[] T) {
        if(T==null||T.length==0)return null;

        for(int i=0;i<T.length;i++){
            int count=0;
            // 若后面的数都小于当前的数，则count=0
            int letterCount=0;
            for(int j=i+1;j<T.length;j++){
                if(T[i]>=T[j]){
                    letterCount++;
                }else {
                    // 若有大于当前数的,则两个数之和
                    if(j-i!=letterCount){
                        count+=++letterCount;
                    }
                    break;
                }
            }
            T[i]=count;
        }
        return T;
    }

    // 官方题解 O(n)
    public int[] dailyTemperatures1(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            // 当前温度大于队头温度
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;


    }

    public static void main(String[] args) {
        // assert [1,1,4,2,1,1,0,0]
        SystemUtil.print(Q739.instance.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        // assert [2,1,1,4,3,2,1,0]
        SystemUtil.print(Q739.instance.dailyTemperatures(new int[]{44, 33, 45, 49, 43, 40, 39, 52}));
        // assert [1,1,4,2,1,1,0,0]
        SystemUtil.print(Q739.instance.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        SystemUtil.print(Q739.instance.dailyTemperatures1(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
}
