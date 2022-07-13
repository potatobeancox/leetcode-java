package com.potato.study.leetcodecn.p01269.t001;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1269. 停在原地的方案数
 *
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 *
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 *
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 *
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * 示例  2：
 *
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * 示例 3：
 *
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 *  
 *
 * 提示：
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numWays(int steps, int arrLen) {
        // 1269
        // dp ij 凑了 i步 停在了 j位置 种类数
        int[][] dp = new int[steps+1][arrLen];
        // dp 00 = 1 走了0步 在最开始位置 只有1种
        dp[0][0] = 1;
        int mod = 1_000_000_000 + 7;
        // 从 1 开始往 steps 转移 dp ij = dp i-1 j-1 + dp i-1 j + dp i-1 j+1
        for (int i = 1; i <= steps; i++) {
            // 控制位置
            for (int j = 0; j < arrLen; j++) {
                dp[i][j] = dp[i-1][j];
                if (j > 0) {
                    dp[i][j] += dp[i-1][j-1];
                    dp[i][j] %= mod;
                }
                if (j < arrLen - 1) {
                    dp[i][j] += dp[i-1][j+1];
                    dp[i][j] %= mod;
                }
            }
        }
        return dp[steps][0];
    }


}
