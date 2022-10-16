package com.potato.study.leetcodecn.p01999.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 1999. 最小的仅由两个数组成的倍数
 *
 * 给你三个整数, k, digit1和 digit2, 你想要找到满足以下条件的 最小 整数：

 大于k 且是 k 的倍数
 仅由digit1 和 digit2 组成，即 每一位数 均是 digit1 或 digit2
 请你返回 最小的满足这两个条件的整数，如果不存在这样的整数，或者最小的满足这两个条件的整数不在32位整数范围（0~231-1），就返回 -1 。

  

 示例 1：

 输入：k = 2, digit1 = 0, digit2 = 2
 输出：20
 解释：
 20 是第一个仅有数字0和2组成的，比2大且是2的倍数的整数。
 示例 2：

 输入：k = 3, digit1 = 4, digit2 = 2
 输出：24
 解释：
 24 是第一个仅有数字 2 和 4 组成的，比 3 大且是 3 的倍数的整数。
 示例 3：

 输入：k = 2, digit1 = 0, digit2 = 0
 输出：-1
 解释：
 不存在仅由 0 组成的比 2 大且是 2 的倍数的整数，因此返回 -1 。
  

 提示：

 1 <= k <= 1000
 0 <= digit1 <= 9
 0 <= digit2 <= 9

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-greater-multiple-made-of-two-digits
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findInteger(int k, int digit1, int digit2) {
        // 使用一个 优先级队列 每次都比较下是否是 k的倍数
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add((long)digit1);
        priorityQueue.add((long)digit2);
        Set<Long> set = new HashSet<>();
        set.add((long) digit1);
        set.add((long) digit2);

        while (!priorityQueue.isEmpty()) {
            long poll = priorityQueue.poll();
            // 判断是不是 已经 k
            if (poll > k && poll % k == 0) {
                return (int) poll;
            }
            // 往后添加 digit1 和 digit2
            long temp1 = poll * 10 + digit1;
            if (temp1 <= Integer.MAX_VALUE && !set.contains(temp1)) {
                priorityQueue.add(temp1);
            }

            long temp2 = poll * 10 + digit2;
            if (temp2 <= Integer.MAX_VALUE && !set.contains(temp2)) {
                priorityQueue.add(temp2);
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 2;
        int digit1 = 0;
        int digit2 = 2;
        int res = solution.findInteger(k, digit1, digit2);
        System.out.println(res);
        Assert.assertEquals(20, res);
    }

}
