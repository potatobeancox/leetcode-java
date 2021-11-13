package com.potato.study.leetcodecn.other.Interview.p0003p0002;


import java.util.Stack;

/**
 * 面试题 03.02. 栈的最小值
 *
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。


 示例：

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> 返回 -3.
 minStack.pop();
 minStack.top();      --> 返回 0.
 minStack.getMin();   --> 返回 -2.
 通过次数18,903提交次数31,185


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/min-stack-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MinStack {

    // 最小值栈
    private Stack<Integer> minStack;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        this.minStack = new Stack<>();
        this.stack = new Stack<>();
    }

    /**
     * stack 记录当前位置 及之后的最小值
     那么 每次push 如果当前值比栈顶小 循环出栈  然后push 进去

     否则 num 大 直接push 进去

     每次pop 时 如果pop 元素 是stak 的栈低 同时pop

     min 直接去栈低
     * @param x
     */
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            // 后出现了 更小的 之前可以一直pop 没用了 当前值 小 插入deque 记录min
            if (minStack.isEmpty()) {
                minStack.add(x);
                return;
            }
            if (minStack.peek() >= x) {
                minStack.add(x);
            }
        }
    }

    public void pop() {
        int target = stack.pop();
        if (target == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        // --> 返回 -3.
        minStack.getMin();
        minStack.pop();
        // --> 返回 0.
        minStack.top();
        // --> 返回 -2.
        minStack.getMin();


        minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        // --> 返回 -3.
        minStack.pop();
        minStack.getMin();
        // --> 返回 0.
        minStack.pop();
        minStack.getMin();

        minStack.pop();
        minStack.getMin();
    }
}
