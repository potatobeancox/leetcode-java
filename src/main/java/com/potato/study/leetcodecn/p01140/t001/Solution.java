package com.potato.study.leetcodecn.p01140.t001;


import org.junit.Assert;

/**
 * 1140. 石子游戏 II
 *
 * 亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
 *
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 *
 * 游戏一直持续到所有石子都被拿走。
 *
 * 假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
 *
 *  
 *
 * 示例：
 *
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：
 * 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
 * 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
 * 所以我们返回更大的 10。
 *  
 *
 * 提示：
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/stone-game-ii/solution/java-dong-tai-gui-hua-qing-xi-yi-dong-17xing-by-lg/
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        // n = pile.len dp[n][n+1] , 剩余i位置 到 最后位置 一堆，最多能拿到的个数 （先手），第二个参数是 M
        int n = piles.length;
        // 先手 从 i位置到末尾，最大拿j 堆，最多能拿多少
        int[][] dp = new int[n][n+1];
        // 从末尾开始遍历
        int suffixSum = 0;
        for (int i = n-1; i >= 0; i--) {
            // 当前还剩多少
            suffixSum += piles[i];
            // 规定当前一次能拿多少
            for (int m = 1; m <= n; m++) {
                // 初始 dp[n-1][n] = 0 没有任何东西 从后往遍历 i 从前往后遍历 j（m） 从i位置到最后 include 都没有 2*m个 全拿了  转移方程 dp i j = 如果当前 i + j * 2 大于 最终位置
                if (i + m * 2 >= n) {
                    dp[i][m] = suffixSum;
                    continue;
                }
                // 说明i之后的 位置都可以拿 否则 只能拿 sum - dp i+x 被人家拿了  枚举每个x 可能拿的个数
                for (int x = 1; x <= m * 2; x++) {
                    dp[i][m] = Math.max(dp[i][m], suffixSum - dp[i+x][Math.max(m, x)]);
                }
            }
        }
        return dp[0][1];
    }
}
