package com.potato.study.leetcodecn.other.Interview.p0008.p0011;


import org.junit.Assert;

/**
 * 面试题 08.11. 硬币
 *
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

 示例1:

 输入: n = 5
 输出：2
 解释: 有两种方式可以凑成总金额:
 5=5
 5=1+1+1+1+1
 示例2:

 输入: n = 10
 输出：4
 解释: 有四种方式可以凑成总金额:
 10=10
 10=5+5
 10=5+1+1+1+1+1
 10=1+1+1+1+1+1+1+1+1+1
 说明：

 注意:

 你可以假设：

 0 <= n (总金额) <= 1000000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/coin-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int waysToChange(int n) {
        // dp i 到i 能有多少种
        int[] dp = new int[n+1];
        dp[0] = 1;
        // 先选大的面额，后选小的面额
        int[] money = new int[] {
            25, 10, 5, 1
        };
        int mod = 1_000_000_000 + 7;
        // 先选择使用的硬币
        for (int i = 0; i < 4; i++) {
            int coin = money[i];
            for (int j = coin; j <= n; j++) {
                dp[j] += dp[j-coin];
                dp[j] %= mod;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.waysToChange(10);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
