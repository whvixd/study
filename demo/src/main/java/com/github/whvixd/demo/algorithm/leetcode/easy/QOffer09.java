package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *  
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by wangzhixiang on 2020/6/30.
 */
public enum QOffer09 {

    instance;

    static class CQueue {

        private Stack<Integer> inStack = new Stack<>();
        private Stack<Integer> outStack = new Stack<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            // 若out不为空，则全部进in栈中，老的放栈低
            while (!outStack.empty()) {
                inStack.push(outStack.pop());
            }
            // 新的放栈顶
            inStack.push(value);
        }

        public int deleteHead() {
            // 若in栈不为空，则全部进入out栈中，老的在栈顶
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
            // out不为空，则从栈顶取数据
            if (!outStack.empty()) {
                return outStack.pop();
            }
            return -1;
        }
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */

    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(3);
        // assert 3
        System.out.println(obj.deleteHead());
        // assert -1
        System.out.println(obj.deleteHead());

        obj.appendTail(5);
        obj.appendTail(2);
        // assert 5
        System.out.println(obj.deleteHead());
        // assert 2
        System.out.println(obj.deleteHead());
        // assert -1
        System.out.println(obj.deleteHead());

        obj.appendTail(1);
        obj.appendTail(2);
        obj.appendTail(3);
        // assert 1
        System.out.println(obj.deleteHead());
        // assert 2
        System.out.println(obj.deleteHead());

        obj.appendTail(4);
        // assert 3
        System.out.println(obj.deleteHead());

        // assert 4
        System.out.println(obj.deleteHead());

    }
}
