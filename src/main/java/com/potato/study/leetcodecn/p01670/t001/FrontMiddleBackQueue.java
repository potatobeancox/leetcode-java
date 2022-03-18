package com.potato.study.leetcodecn.p01670.t001;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;

/**
 * 1670. 设计前中后队列
 *
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 *
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle",
 * "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 *  
 *
 * 提示：
 *
 * 1 <= val <= 109
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-front-middle-back-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class FrontMiddleBackQueue {

    private Deque<Integer> deque1;
    private Deque<Integer> deque2;

    // 两个 deque 保证 deque1 比前 deque2 至多1 需要 reblance
    public FrontMiddleBackQueue() {
        this.deque1 = new LinkedList<>();
        this.deque2 = new LinkedList<>();
    }

    public void pushFront(int val) {
        deque1.addFirst(val);
        rebalance();
    }

    public void pushMiddle(int val) {
        deque1.addLast(val);
        rebalance();
    }

    public void pushBack(int val) {
        deque2.addLast(val);
        rebalance();
    }

    public int popFront() {
        if (isEmpty()) {
            return -1;
        }
        Integer integer = deque1.pollFirst();
        rebalance();
        return integer;
    }

    public int popMiddle() {
        if (isEmpty()) {
            return -1;
        }
        Integer integer = deque1.pollLast();
        rebalance();
        return integer;
    }

    public int popBack() {
        if (isEmpty()) {
            return -1;
        }
        if (deque2.isEmpty()) {
            return deque1.pollLast();
        }
        Integer integer = deque2.pollLast();
        rebalance();
        return integer;
    }

    private void rebalance() {
        int diff = deque1.size() - deque2.size();
        if (0 <= diff && diff <= 1) {
            return;
        }
        while (deque1.size() < deque2.size()) {
            Integer poll = deque2.pollFirst();
            deque1.addLast(poll);
        }
        while (deque1.size() > deque2.size() + 1) {
            Integer poll = deque1.pollLast();
            deque2.addFirst(poll);
        }
    }

    private boolean isEmpty() {
        return deque1.size() + deque2.size() == 0;
    }
}
