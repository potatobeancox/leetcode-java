package com.potato.study.leetcodecn.p02320.t001;

import java.util.Arrays;

/**
 * 2320. 统计放置房子的方式数
 *
 * 一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。每一边的地块都按从 1 到 n 编号。每个地块上都可以放置一所房子。

 现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。由于答案可能很大，需要对 109 + 7 取余后再返回。

 注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。

  

 示例 1：

 输入：n = 1
 输出：4
 解释：
 可能的放置方式：
 1. 所有地块都不放置房子。
 2. 一所房子放在街道的某一侧。
 3. 一所房子放在街道的另一侧。
 4. 放置两所房子，街道两侧各放置一所。
 示例 2：


 输入：n = 2
 输出：9
 解释：如上图所示，共有 9 种可能的放置方式。
  

 提示：

 1 <= n <= 104

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-number-of-ways-to-place-houses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countHousePlacements(int n) {
        // 先求一侧，另一侧可能就是所求的平方

        // dp[n][2]  dp i0 代表 第i个位置 不放房子的所有可能数 同理1就是放
        long[][] dp = new long[n][2];
        // 初始化 dp 00 = 1 dp01 = 1
        dp[0][0] = 1;
        dp[0][1] = 1;
        // 转移方程 dpi1 放了房子 之前必须不能放 = dp i-1 0 dp i0 = dp i-1 0 + dp i-1 1
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i-1][0];
            dp[i][0] = dp[i-1][0] + dp[i-1][1];


            dp[i][1] %= mod;
            dp[i][0] %= mod;
        }
        long target = (dp[n-1][0] + dp[n-1][1]) * (dp[n-1][0] + dp[n-1][1]) % mod;
        return (int) target;
    }

}
