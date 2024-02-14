package com.potato.study.leetcodecn.p00295.t001;


import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

 例如，

 [2,3,4] 的中位数是 3

 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：

 void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 double findMedian() - 返回目前所有元素的中位数。
 示例：

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2
 进阶:

 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MedianFinder {

    /**
     *一个 存min 大根堆
     */
    private PriorityQueue<Integer> minPriorityQueue;
    /**
     * 一个 存max  小根堆
     */
    private PriorityQueue<Integer> maxPriorityQueue;

    /**
     *  两个 heap
     *  一个 存max 一个存min 大的小根堆 小的大根堆
     *  约定 大的存 n+1 小的存 n个 ，
     *
     */
    public MedianFinder() {
        // 大顶堆
        this.minPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 小顶堆
        this.maxPriorityQueue = new PriorityQueue<>();
    }

    /**
     * 先判断 如果当前 num 大于等于 max 堆顶 插入 max堆
     *  如果 max堆 已经不满足 约定大小了 ，max堆 pop min堆 in
     *
     * 如果当前 num 大于 min 堆顶 插入 min堆 调整
     *
     *
     * @param num
     */
    public void addNum(int num) {

        if (maxPriorityQueue.isEmpty()) {
            maxPriorityQueue.add(num);
            return;
        }

        if (num >= maxPriorityQueue.peek()) {
            maxPriorityQueue.add(num);
            // 是否需要调整
            if (maxPriorityQueue.size() - minPriorityQueue.size() > 1) {
                minPriorityQueue.add(maxPriorityQueue.poll());
            }
        } else {
            minPriorityQueue.add(num);
            if (minPriorityQueue.size() > maxPriorityQueue.size()) {
                maxPriorityQueue.add(minPriorityQueue.poll());
            }
        }
    }

    /**
     * 相等是 出两个数 求平均
     * 如果不相等 直接 出 max的堆顶
     * @return
     */
    public double findMedian() {
        if (minPriorityQueue.size() == maxPriorityQueue.size()) {
            double median = 1.0 * (minPriorityQueue.peek() + maxPriorityQueue.peek()) / 2;
            return median;
        } else {
            return maxPriorityQueue.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); // 1.5

//        Assert.assertEquals(1.5, medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // 2
//        Assert.assertEquals(2.0, medianFinder.findMedian());
//        Assert.assert
    }
}
