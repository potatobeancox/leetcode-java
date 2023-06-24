package com.potato.study.leetcodecn.p00879.t001;

/**
 * 879. 盈利计划
 *
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。

 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。

  

 示例 1：

 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 输出：2
 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 总的来说，有两种计划。
 示例 2：

 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 输出：7
 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
  

 提示：

 1 <= n <= 100
 0 <= minProfit <= 100
 1 <= group.length <= 100
 1 <= group[i] <= 100
 profit.length == group.length
 0 <= profit[i] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/profitable-schemes
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/profitable-schemes/solution/gong-shui-san-xie-te-shu-duo-wei-fei-yon-7su9/
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // dp ijk 表示 前i个工作 使用不超过j个人力 转了 至少 k元钱 有多少中方式
        int length = group.length;
        long[][][] dp = new long[length+1][n+1][minProfit+1];
        for (int i = 0; i <= n; i++) {
            // 一个任务都不做，一分钱也不赚需要可能都分配数
            dp[0][i][0] = 1;
        }
        // 从 i开始 枚举 并且j从 n和k小的时候开始枚举
        for (int i = 1; i <= length; i++) {
            int people = group[i-1];
            int cost = profit[i-1];
            // 枚举多少人
            int mod = 1_000_000_000 + 7;
            for (int j = 0; j <= n; j++) {
                // 枚举需要达到的花费
                for (int k = 0; k <= minProfit; k++) {
                    // 不做 第1个工作n
                    dp[i][j][k] += dp[i-1][j][k];
                    // 做这个工作需要看看能不能做
                    int targetProfit = Math.max(0, k-cost);
                    if (j >= people) {
                        dp[i][j][k] += dp[i-1][j-people][targetProfit];
                        if (dp[i][j][k] >= mod) {
                            dp[i][j][k] %= mod;
                        }
                    }
                }
            }
        }
        return (int) dp[length][n][minProfit];
    }
}
