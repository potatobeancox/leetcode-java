package com.potato.study.leetcodecn.other.swordoffer2.p0103.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 103. 最少的硬币数目
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *  
 *
 * 注意：本题与主站 322 题相同： https://leetcode-cn.com/problems/coin-change/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gaM7Ch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // ii 103
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(amount);
        int time = 1;
        Set<Integer> seen = new HashSet<>();
        seen.add(amount);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                // 兑换
                for (int coin : coins) {
                    // 剩余的个数
                    int remind = poll - coin;
                    if (remind == 0) {
                        return time;
                    } else if (remind < 0) {
                        continue;
                    }
                    if (seen.contains(remind)) {
                        continue;
                    }
                    queue.add(remind);
                    seen.add(remind);
                }
            }
            // time 个数量
            time++;
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = new int[] {2};
        int amount = 3;
        int i = solution.coinChange(coins, amount);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }
}