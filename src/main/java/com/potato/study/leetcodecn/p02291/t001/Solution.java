package com.potato.study.leetcodecn.p02291.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2291. Maximum Profit From Trading Stocks
 *
 * You are given two 0-indexed integer arrays of the same length present and future where present[i] is the current price of the ith stock and future[i] is the price of the ith stock a year in the future. You may buy each stock at most once. You are also given an integer budget representing the amount of money you currently have.

 Return the maximum amount of profit you can make.

  

 Example 1:

 Input: present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
 Output: 6
 Explanation: One possible way to maximize your profit is to:
 Buy the 0th, 3rd, and 4th stocks for a total of 5 + 2 + 3 = 10.
 Next year, sell all three stocks for a total of 8 + 3 + 5 = 16.
 The profit you made is 16 - 10 = 6.
 It can be shown that the maximum profit you can make is 6.
 Example 2:

 Input: present = [2,2,5], future = [3,4,10], budget = 6
 Output: 5
 Explanation: The only possible way to maximize your profit is to:
 Buy the 2nd stock, and make a profit of 10 - 5 = 5.
 It can be shown that the maximum profit you can make is 5.
 Example 3:

 Input: present = [3,3,12], future = [0,3,15], budget = 10
 Output: 0
 Explanation: One possible way to maximize your profit is to:
 Buy the 1st stock, and make a profit of 3 - 3 = 0.
 It can be shown that the maximum profit you can make is 0.
  

 Constraints:

 n == present.length == future.length
 1 <= n <= 1000
 0 <= present[i], future[i] <= 100
 0 <= budget <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-profit-from-trading-stocks
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maximumProfit(int[] present, int[] future, int budget) {
        // 计算出来每个收益
        int[] diff = new int[present.length];
        for (int i = 0; i < present.length; i++) {
            diff[i] = future[i] - present[i];
        }
        // dp i 花费 i元能获得的最大利润
        int[] dp = new int[budget+1];
        // dp 0 = 0
        dp[0] = 0;
        // dp[i] = dp [i-present[j]] + diff[j]
        for (int i = 0; i < present.length; i++) {
            // 买了的最大值
            int[] temp = new int[budget+1];

            // 当前这个收益为负数
            if (diff[i] <= 0) {
                continue;
            }

            // 每个都要尝试一下
            for (int j = 0; j < budget + 1; j++) {
                // 还不够买这个 当然不能买了
                if (j < present[i]) {
                    temp[j] = Math.max(temp[j], dp[j]);
                } else {
                    temp[j] = Math.max(dp[j-present[i]] + diff[i], dp[j]);
                }
            }

            // 变成买过的最大值
            dp = temp;

        }
        int maxProfit = 0;
        for (int profit : dp) {
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] present = LeetcodeInputUtils.inputString2IntArray("[2,0,0]");
        int[] future = LeetcodeInputUtils.inputString2IntArray("[3,3,0]");
        int budget = 18;
        int i = solution.maximumProfit(present, future, budget);
        System.out.println(i);
        Assert.assertEquals(4, i);



        present = LeetcodeInputUtils.inputString2IntArray("[5,4,6,2,3]");
        future = LeetcodeInputUtils.inputString2IntArray("[8,5,4,3,5]");
        budget = 10;
        i = solution.maximumProfit(present, future, budget);
        System.out.println(i);
        Assert.assertEquals(6, i);
    }



}
