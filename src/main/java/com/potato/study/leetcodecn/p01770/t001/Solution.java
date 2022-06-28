package com.potato.study.leetcodecn.p01770.t001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * 1770. 执行乘法运算的最大分数
 *
 * 给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
 *
 * 初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
 *
 * 选择数组 nums 开头处或者末尾处 的整数 x 。
 * 你获得 multipliers[i] * x 分，并累加到你的分数中。
 * 将 x 从数组 nums 中移除。
 * 在执行 m 步操作后，返回 最大 分数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], multipliers = [3,2,1]
 * 输出：14
 * 解释：一种最优解决方案如下：
 * - 选择末尾处的整数 3 ，[1,2,3] ，得 3 * 3 = 9 分，累加到分数中。
 * - 选择末尾处的整数 2 ，[1,2] ，得 2 * 2 = 4 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[1] ，得 1 * 1 = 1 分，累加到分数中。
 * 总分数为 9 + 4 + 1 = 14 。
 * 示例 2：
 *
 * 输入：nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * 输出：102
 * 解释：一种最优解决方案如下：
 * - 选择开头处的整数 -5 ，[-5,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[-2,7,1] ，得 1 * 4 = 4 分，累加到分数中。
 * - 选择末尾处的整数 7 ，[-2,7] ，得 7 * 6 = 42 分，累加到分数中。
 * 总分数为 50 + 15 - 9 + 4 + 42 = 102 。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 1770
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int[][] dp = new int[m+1][m+1];
        // dp 0i   dp i0
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] + multipliers[i-1] * nums[i-1];
            dp[0][i] = dp[0][i-1] + multipliers[m-i] * nums[i-1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; i + j <= m; j++) {
                dp[i][j] = Math.max(dp[i][j-1] * multipliers[i+j-2],
                        dp[i][j-1] * multipliers[i+j-2]);
            }
        }
        // 找到最大值
        int max = 0;
        for (int i = 0; i <= m; i++) {
            max = Math.max(max, dp[i][m-i]);
        }
        return max;
    }
}
