package com.potato.study.leetcodecn.other.Interview.p0003.p0003;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 面试题 03.03. 堆盘子
 *
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 *
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 *
 * 示例1:
 *
 *  输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 *  输出：
 * [null, null, null, 2, 1, -1]
 * 示例2:
 *
 *  输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, 3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stack-of-plates-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
// 03.03
public class StackOfPlates {


    // list list 结构
    private List<List<Integer>> stackList;

    private int cap;

    public StackOfPlates(int cap) {
        this.stackList = new ArrayList<>();
        stackList.add(new LinkedList<>());
        this.cap = cap;
    }

    public void push(int val) {
        // 获取到当前的 栈  如果还没满就向内部插入
        List<Integer> list = stackList.get(stackList.size() - 1);
        if (list.size() == cap) {
            list = new LinkedList<>();
            stackList.add(list);
        }
        list.add(val);
    }

    public int pop() {
        // 处理之前的残留
        while (!stackList.isEmpty() && stackList.get(stackList.size() - 1).isEmpty()) {
            stackList.remove(stackList.size() - 1);
        }
        if (stackList.isEmpty()) {
            return -1;
        }
        List<Integer> list = stackList.get(stackList.size() - 1);
        int val = list.remove(list.size() - 1);

        // 处理空洞
        if (list.isEmpty()) {
            stackList.remove(stackList.size() - 1);
        }
        return val;
    }

    /**
     * 可以在中间搞一下
     * @param index
     * @return
     */
    public int popAt(int index) {
        List<Integer> list = stackList.get(index);
        if (list.isEmpty()) {
            return -1;
        }
        int remove = list.remove(list.size() - 1);
        // 当前list 空了
        if (list.isEmpty()) {
            stackList.remove(list);
        }
        return remove;
    }
}
