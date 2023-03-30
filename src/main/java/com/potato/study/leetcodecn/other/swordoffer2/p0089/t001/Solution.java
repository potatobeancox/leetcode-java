package com.potato.study.leetcodecn.other.swordoffer2.p0089.t001;

/**
 * 剑指 Offer II 089. 房屋偷盗
 *
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：nums = [2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *  
 *
 * 注意：本题与主站 198 题相同： https://leetcode-cn.com/problems/house-robber/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/Gu0c2T
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 089
    public int rob(int[] nums) {
        // dp i0 不偷 i最多能获得多少钱 dpi1 偷这个能拿多少钱
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        // 从1开始决定偷不偷
        for (int i = 1; i < n; i++) {
            // 这个不偷 之前偷不偷 都行
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            // 偷这个 之前不能偷
            dp[i][1] = nums[i] + dp[i-1][0];
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

}
