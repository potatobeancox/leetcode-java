package com.potato.study.leetcodecn.p01690.t001;

import org.junit.Assert;

/**
 * 1690. 石子游戏 VII
 *
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。

 有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。

 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。

 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。

  

 示例 1：

 输入：stones = [5,3,1,4,2]
 输出：6
 解释：
 - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
 - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
 - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
 - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
 - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
 得分的差值 18 - 12 = 6 。
 示例 2：

 输入：stones = [7,90,5,1,100,10,10,2]
 输出：122
  

 提示：

 n == stones.length
 2 <= n <= 1000
 1 <= stones[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/stone-game-vii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/stone-game-vii/solution/java-dpjie-fa-by-zzq-_-i2s0s/
     *
     * dp ij 从第i个到第j个石头能获得的最大值
     * 固定 长度 计算两侧
     * len i
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        // 计算一个前缀和 preSum
        int[] preSum = new int[stones.length];
        preSum[0] = stones[0];
        for (int i = 1; i < stones.length; i++) {
            preSum[i] = stones[i] + preSum[i-1];
        }
        // dp ij alice 从 ij 中能获取的最大差值
        int n = stones.length;
        int[][] dp = new int[n][n];
        // dp ii = 0 dp i i+1 = Math.max(i, i+1)
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                if (len == 1) {
                    dp[i][i+len] = Math.max(stones[i], stones[i+1]);
                    continue;
                }
                int j = i + len;
                // 两种可能 拿了 i (i+1-j)或者 拿了j
                int tmpSum = 0;
                if (i > 0) {
                    tmpSum = preSum[i-1];
                }
                dp[i][j] = Math.max(preSum[j] - preSum[i] - dp[i+1][j], preSum[j-1] - tmpSum - dp[i][j-1]);
            }
        }
        return dp[0][stones.length-1];
    }

}
