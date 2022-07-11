package com.potato.study.leetcodecn.other.swordoffer.p0041.p1.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

 例如，

 [2,3,4] 的中位数是 3

 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：

 void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 double findMedian() - 返回目前所有元素的中位数。
 示例 1：

 输入：
 ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 [[],[1],[2],[],[3],[]]
 输出：[null,null,null,1.50000,null,2.00000]
 示例 2：

 输入：
 ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 [[],[2],[],[3],[]]
 输出：[null,null,2.00000,null,2.50000]
  

 限制：

 最多会对 addNum、findMedian 进行 50000 次调用。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MedianFinder {

    private PriorityQueue<Integer> priorityQueue1;
    private PriorityQueue<Integer> priorityQueue2;

    /** initialize your data structure here. */
    public MedianFinder() {
        // 两个 堆 后面 小根队 前面大根堆 保证 前面的 size 等于 后面 或者比后面多一个
        this.priorityQueue2 = new PriorityQueue<>();
        // 大根堆
        this.priorityQueue1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
    }

    public void addNum(int num) {
        if (priorityQueue1.isEmpty()) {
            priorityQueue1.add(num);
            return;
        }
        priorityQueue1.add(num);
        // 先搞定个数
        while (priorityQueue1.size() != priorityQueue2.size()
                && priorityQueue1.size() != priorityQueue2.size() + 1) {
            // 只可能 priorityQueue1 多
            priorityQueue2.add(priorityQueue1.poll());
        }
        if (priorityQueue2.isEmpty()) {
            return;
        }
        // 个数相等比较两个peek
        if (priorityQueue1.peek() <= priorityQueue2.peek()) {
            return;
        }
        int poll1 = priorityQueue1.poll();
        int poll2 = priorityQueue2.poll();
        priorityQueue1.add(poll2);
        priorityQueue2.add(poll1);
    }

    public double findMedian() {
        int length = priorityQueue1.size() + priorityQueue2.size();
        if (length == 0) {
            return 0.0;
        }
        if (length % 2 == 1) {
            int peek = priorityQueue1.peek();
            return peek;
        } else {
            int p1 = priorityQueue1.peek();
            int p2 = priorityQueue2.peek();
            return (1.0 * p1 + p2) / 2;
        }
    }
}
