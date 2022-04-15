package com.potato.study.leetcodecn.p01155.t001;


import org.junit.Assert;

/**
 * 1155. 掷骰子的N种方法
 *
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 *
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 *
 * 答案可能很大，你需要对 109 + 7 取模 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有6张脸的骰子。
 * 得到3的和只有一种方法。
 * 示例 2：
 *
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有6个面。
 * 得到7的和有6种方法1+6 2+5 3+4 4+3 5+2 6+1。
 * 示例 3：
 *
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对 109 + 7 取模。
 *  
 *
 * 提示：
 *
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 关键条件 target 《= 1000
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget(int n, int k, int target) {
        // dp i个骰子 和为 j的种类数  target <= 1000
        int[][] dp = new int[n+1][1001];
        // 使用 k 初始化 投1次的种类数
        for (int i = 1; i <= k; i++) {
            dp[1][i] = 1;
        }
        int mod = 1_000_000_000 + 7;
        // 每次投掷
        for (int i = 2; i <= n; i++) {
            // 当前投掷了多少
            for (int j = 1; j <= target; j++) {
                // 0- k
                for (int l = 1; l <= k; l++) {
                    if (j <= l) {
                        break;
                    }
                    dp[i][j] += dp[i-1][j-l];
                    dp[i][j] %= mod;
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
    }
}
