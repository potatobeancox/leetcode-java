package com.potato.study.leetcodecn.p02400.t001;

/**
 * 2400. 恰好移动 k 步到达某一位置的方法数目
 *
 * 给你两个 正 整数 startPos 和 endPos 。最初，你站在 无限 数轴上位置 startPos 处。在一步移动中，你可以向左或者向右移动一个位置。
 *
 * 给你一个正整数 k ，返回从 startPos 出发、恰好 移动 k 步并到达 endPos 的 不同 方法数目。由于答案可能会很大，返回对 109 + 7 取余 的结果。
 *
 * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
 *
 * 注意：数轴包含负整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：startPos = 1, endPos = 2, k = 3
 * 输出：3
 * 解释：存在 3 种从 1 到 2 且恰好移动 3 步的方法：
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * 可以证明不存在其他方法，所以返回 3 。
 * 示例 2：
 *
 * 输入：startPos = 2, endPos = 5, k = 10
 * 输出：0
 * 解释：不存在从 2 到 5 且恰好移动 10 步的方法。
 *  
 *
 * 提示：
 *
 * 1 <= startPos, endPos, k <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numberOfWays(int startPos, int endPos, int k) {
        int mod = 1_000_000_000 + 7;
        // 1 <= startPos, endPos, k <= 1000
        long[][] dp = new long[3004][1004];
        // 初始化平移动 00 - 1001 ，1001
        // 往前和往后走一步都是1种
        dp[1001 + startPos + 1][1] = 1;
        dp[1001 + startPos - 1][1] = 1;
        // 从第二部开始到k 每次都可以往前走或者往后走
        for (int i = 2; i <= k; i++) {
            // j 控制走到哪了
            for (int j = 1; j < 3003; j++) {
                // i 控制走了多少步
                dp[j][i] = dp[j+1][i-1] + dp[j-1][i-1];
                dp[j][i] %= mod;

            }

        }
        return (int) dp[1001+endPos][k];
    }

}
