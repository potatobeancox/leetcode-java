package com.potato.study.leetcodecn.other.swordoffer.p0049.p1.t001;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

/**
 * 剑指 Offer 49. 丑数
 *
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int nthUglyNumber(int n) {
        // 小根堆 + set 去重
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        priorityQueue.add(1L);
        set.add(1L);
        int res = -1;
        for (int i = 1; i <= n; i++) {
            long poll = priorityQueue.poll();
            res = (int) poll;
            if (!set.contains(poll * 2)) {
                priorityQueue.add(poll * 2);
                set.add(poll * 2);
            }
            if (!set.contains(poll * 3)) {
                priorityQueue.add(poll * 3);
                set.add(poll * 3);
            }
            if (!set.contains(poll * 5)) {
                priorityQueue.add(poll * 5);
                set.add(poll * 5);
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
