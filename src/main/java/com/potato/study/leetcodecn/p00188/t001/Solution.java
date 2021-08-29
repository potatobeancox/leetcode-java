package com.potato.study.leetcodecn.p00188.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 *
 * 提示：
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int n = prices.length ;
        // 第i天已经完成了j次交易买入状态下，最大收益
        int[][] buy = new int[n][k + 1];
        // 第i天已经完成了j次交易卖出状态下，最大收益
        int[][] sell = new int[n][k + 1];
        // 第0 天进行了 j次交易的收益，都弄成 最小值吧
        Arrays.fill(buy[0], Integer.MIN_VALUE);
        buy[0][0] = 0 - prices[0];
        // 控制当前执行交易次数
        for (int j = 0; j <= k; j++) {
            // 控制当前天数
            for (int i = 1; i < n; i++) {
                // 如果之前还是没有进行过交易，那么 就按照当前
                if (j == 0) {
                    buy[i][0] = Math.max(0 - prices[i], buy[i-1][0]);
                    continue;
                }
                // 之前一天已经 完成了 j次交易，和 昨天卖了完成了 j次
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        int max = 0;
        // 找到最大收益
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, sell[n - 1][i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 2;
        int[] prices = new int[]{2,4,1};
        int i = solution.maxProfit(k, prices);
        System.out.println(i);
        Assert.assertEquals(2, i);


        k = 2;
        prices = new int[]{3,2,6,5,0,3};
        i = solution.maxProfit(k, prices);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }
}
