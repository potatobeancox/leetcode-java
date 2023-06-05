package com.potato.study.leetcodecn.p01335.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1335. 工作计划的最低难度
 *
 * 你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
 *
 * 你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
 *
 * 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
 *
 * 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：jobDifficulty = [6,5,4,3,2,1], d = 2
 * 输出：7
 * 解释：第一天，您可以完成前 5 项工作，总难度 = 6.
 * 第二天，您可以完成最后一项工作，总难度 = 1.
 * 计划表的难度 = 6 + 1 = 7
 * 示例 2：
 *
 * 输入：jobDifficulty = [9,9,9], d = 4
 * 输出：-1
 * 解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
 * 示例 3：
 *
 * 输入：jobDifficulty = [1,1,1], d = 3
 * 输出：3
 * 解释：工作计划为每天一项工作，总难度为 3 。
 * 示例 4：
 *
 * 输入：jobDifficulty = [7,1,7,1,7,1], d = 3
 * 输出：15
 * 示例 5：
 *
 * 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * 输出：843
 *  
 *
 * 提示：
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {















    public int minDifficulty(int[] jobDifficulty, int d) {
        // 如果数组长度小于 d 直接返回 -1
        int jobCount = jobDifficulty.length;
        if (jobCount < d) {
            return -1;
        }
        // dp ij 对于前i+1天 完成 j+1项工作需要花费的最小时间
        int[][] dp = new int[d][jobCount];
        // 处理第一天
        int maxDifficulty = Integer.MIN_VALUE;
        for (int i = 0; i < jobCount; i++) {
            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);
            dp[0][i] = maxDifficulty;
        }
        // 先枚举结束的天
        for (int i = 1; i < d; i++) {
            // 枚举 当前完成的工作数量
            for (int j = i; j < jobCount; j++) {
                // 在上面2个之间枚举最大值
                int max = Integer.MIN_VALUE;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j; k >= i; k--) {
                    // dp ij 等于 min dp k j-1 + 从k到i 期间的最大值
                    max = Math.max(max, jobDifficulty[k]);
                    // 前i-1天完成 的最小值 + 后k天完成的最大值
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + max);
                }
            }
        }
        return dp[d-1][jobCount-1];
    }

    public static void main(String[] args) {
        // jobDifficulty = [6,5,4,3,2,1], d = 2
        Solution solution = new Solution();
        int[] jobDifficulty = new int[] {
                6,5,4,3,2,1
        };
        int d = 2;
        int i = solution.minDifficulty(jobDifficulty, d);
        System.out.println(i);
        Assert.assertEquals(7, i);
    }
}
