package com.potato.study.leetcodecn.other.Interview.p0017.p0009;


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 面试题 17.09. 第 k 个数
 *
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

 示例 1:

 输入: k = 5

 输出: 9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getKthMagicNumber(int k) {
        // 小根堆
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        // 3 5 7
        int count = 0;
        Set<Long> set = new HashSet<>();
        set.add(1L);
        while (!queue.isEmpty()) {
            Long poll = queue.poll();
            count++;

            if (count == k) {
                return poll.intValue();
            }

            if (!set.contains(poll * 3)) {
                queue.add(poll * 3);
                set.add(poll * 3);
            }
            if (!set.contains(poll * 5)) {
                queue.add(poll * 5);
                set.add(poll * 5);

            }
            if (!set.contains(poll * 7)) {
                queue.add(poll * 7);
                set.add(poll * 7);
            }
        }
        return -1;
    }

}
