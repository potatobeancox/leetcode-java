package com.potato.study.leetcodecn.other.swordoffer.p0059.p2.t001;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 *
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

 若队列为空，pop_front 和 max_value 需要返回 -1

 示例 1：

 输入:
 ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 [[],[1],[2],[],[],[]]
 输出: [null,null,null,2,1,2]
 示例 2：

 输入:
 ["MaxQueue","pop_front","max_value"]
 [[],[],[]]
 输出: [null,-1,-1]
  

 限制：

 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 1 <= value <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MaxQueue {


    private Queue<Integer> queue;
    private Deque<Integer> deque;




    /**
     * 难点 在 max_value 的 O1 操作
     * stack
     * 每次 push 时，比较下 stack peek 和 push 元素 num，
     *  如果num更大一些 那么 可以直接循环 stack pop （因为当前位置是后来的）
     *  否则 num 进栈
     * push 过程中 维护max ，
     *
     * 如果 pop_front 值为最大值，此时
     *
     *
     * 维护一个最大的队列  双端 如果 当前
     *
     * 入队时
     *  如果当前值 大于之前的值，双堆队列 队列尾部 一直pop 直到没有元素或者不满足条件
     *
     * 出队时
     *  比较当前出队元素是不是 max双端 头部元素，是的话一并删除
     *
     * 调用max 时
     *  直接返回双端队列头部
     *
     *
     */
    public MaxQueue() {
        this.queue = new LinkedList<>();
        this.deque = new LinkedList<>();
    }

    public int max_value() {
        if (queue.isEmpty() || deque.isEmpty()){
            return -1;
        }
        return deque.peekFirst();
    }

    public void push_back(int value) {
        queue.add(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int target = queue.poll();
        if (!deque.isEmpty() && deque.peekFirst() == target) {
            deque.pollFirst();
        }

        return target;
    }
}
