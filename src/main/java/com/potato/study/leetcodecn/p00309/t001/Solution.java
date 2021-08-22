package com.potato.study.leetcodecn.p00309.t001;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 示例:

 输入: [1,2,3,0,2]
 输出: 3
 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * dp i 代表 第i-1的最大收益
     * dp 0  = 0
     * dp i =  当天卖出
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0 - prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        // dp i0 第i天处于 已经买入状态最大值 dp i0 = max (dpi-1 0,  dp i-1 2 - value i)
        // dp i1 第i天处于 冷却状态的最大值 dp i1 = max (dp i-10 + val i， dp i-1，1)
        // dp i2 第i天处于 非冷冻状态卖出的最大值 dp i2 = max (dp i-1 2， dpi-1, 1)
        for (int i = 1; i < prices.length; i++) {
            // 之前买了，今天买
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            // 今天卖了，之前就卖了
            dp[i][1] = Math.max(dp[i-1][0] + prices[i], dp[i-1][1]);
            // 之前冷却了，之前卖了
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }
}
