package com.potato.study.leetcodecn.other.swordoffer.p0030.p1.t001;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *  
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 *  
 *
 * 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MinStack {

    private Stack<Integer> stack;
    private Deque<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty() && x > minStack.peekLast()) {
            return;
        }
        // x 小于等于栈顶
        minStack.addLast(x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        Integer pop = stack.pop();
        if (!minStack.isEmpty() && pop == minStack.peekLast()) {
            minStack.pollLast();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peekLast();
    }


    //["MinStack","push","push","push","push","pop","min","pop","min","pop","min"]
    //[[],        [512],[-1024],[-1024],[512],  [],[],[],[],[],[]]

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        // [null,null,null,null,null,null,-1024,null,-1024,null,512]
        minStack.pop();
        System.out.println(minStack.min()); // -1024

        minStack.pop();
        System.out.println(minStack.min()); // -1024

        minStack.pop();
        System.out.println(minStack.min()); // 512



    }
}
