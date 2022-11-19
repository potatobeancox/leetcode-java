package com.potato.study.leetcodecn.p01230.t001;


/**
 * 1230. 抛掷硬币
 *
 * 有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。
 *
 * 请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：prob = [0.4], target = 1
 * 输出：0.40000
 * 示例 2：
 *
 * 输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * 输出：0.03125
 *  
 *
 * 提示：
 *
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * 如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/toss-strange-coins
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double probabilityOfHeads(double[] prob, int target) {
        int length = prob.length;
        // dp ij i个球中 一共有j个正面的百分比
        double[][] dp = new double[length+1][target+1];
        // 先计算 0的百分比
        dp[0][0] = 1;
        for (int i = 1; i <= prob.length; i++) {
            dp[i][0] = dp[i-1][0] * (1-prob[i-1]);
        }

        for (int i = 1; i <= prob.length; i++) {
            for (int j = 1; j <= target && j <= i; j++) {
                dp[i][j] = dp[i-1][j] * (1-prob[i-1]) + dp[i-1][j-1] * prob[i-1];
            }
        }
        return dp[length][target];
    }


}
