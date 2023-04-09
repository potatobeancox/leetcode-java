package com.potato.study.leetcodecn.other.swordoffer2.p0090.t001;

/**
 * 剑指 Offer II 090. 环形房屋偷盗
 *
 * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

  

 示例 1：

 输入：nums = [2,3,2]
 输出：3
 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 示例 2：

 输入：nums = [1,2,3,1]
 输出：4
 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
      偷窃到的最高金额 = 1 + 3 = 4 。
 示例 3：

 输入：nums = [0]
 输出：0
  

 提示：

 1 <= nums.length <= 100
 0 <= nums[i] <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/PzWKhm
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int rob(int[] nums) {
        // 先偷了 第一个家
        int n = nums.length;
        // 偷到 i位置 包含最多能获得多少钱 第一个屋子不偷
        int[] dp1 = new int[n];
        // 第一个偷
        int[] dp2 = new int[n];
        dp2[0] = nums[0];

        if (nums.length == 1) {
            return Math.max(dp1[n-1], dp2[n-1]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (i == 1) {
                // 抢 不抢
                dp1[i] = Math.max(nums[i], dp1[i-1]);
                dp2[i] = Math.max(nums[i], dp2[i-1]);
            } else {
                // i >
                dp1[i] = Math.max(nums[i] + dp1[i-2], dp1[i-1]);
                dp2[i] = Math.max(nums[i] + dp2[i-2], dp2[i-1]);
            }
        }

        if (nums.length == 2) {
            // 最后一个点
            dp1[nums.length - 1] = Math.max(nums[nums.length-1], dp1[nums.length-1-1]);
            // 最后一个不能抢了
            dp2[nums.length - 1] = dp2[nums.length - 2];

            return Math.max(dp1[n-1], dp2[n-1]);
        }

        // 最后一个点
        dp1[nums.length - 1] = Math.max(nums[nums.length-1] + dp1[nums.length-1-2], dp1[nums.length-1-1]);
        // 最后一个不能抢了
        dp2[nums.length - 1] = dp2[nums.length - 2];

        return Math.max(dp1[n-1], dp2[n-1]);
    }
}
