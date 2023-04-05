package com.potato.study.leetcodecn.other.Interview.p0003.p0005;


import java.util.Stack;

/**
 * 面试题 03.05. 栈排序
 *
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 * 示例2:
 *
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 * 说明:
 *
 * 栈中的元素数目在[0, 5000]范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-of-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SortedStack {
    // 03 05 栈顶是最小的

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public SortedStack() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /**
     * 如果当前不是最小的 将最小的 pop 到另一个栈里边 然后插入 然后再pop回来
     *
     * 是最小的 那就直接 插入
     * @param val
     */
    public void push(int val) {
        if (stack1.isEmpty() || val <= stack1.peek()) {
            stack1.add(val);
            return;
        }
        // 如果当前val 不是最小的 pop到stack2 中 再倒回来
        while (!stack1.isEmpty() && stack1.peek() < val) {
            Integer pop = stack1.pop();
            stack2.add(pop);
        }
        // stack1 中的元素 都大于等于 val
        stack1.add(val);
        // 将 之前stack2 的倒回来
        while (!stack2.isEmpty()) {
            Integer pop = stack2.pop();
            stack1.add(pop);
        }
    }

    public void pop() {
        // 直接从stack1 中pop
        if (stack1.isEmpty()) {
            return;
        }
        stack1.pop();
        return;
    }

    public int peek() {
        if (stack1.isEmpty()) {
            return -1;
        }
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(1);
        sortedStack.push(2);
        // 1
        System.out.println(sortedStack.peek());
        sortedStack.pop();
        // 2
        System.out.println(sortedStack.peek());

    }
}
