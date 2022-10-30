package com.potato.study.leetcodecn.p02036.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 2036. 最大交替子数组和
 *
 * 子数组是以0下标开始的数组的连续非空子序列，从 i 到 j（0 <= i <= j < nums.length）的 子数组交替和 被定义为 nums[i] - nums[i+1] + nums[i+2] - ... +/- nums[j] 。

 给定一个以0下标开始的整数数组nums，返回它所有可能的交替子数组和的最大值。

  

 示例 1：

 输入：nums = [3,-1,1,2]
 输出：5
 解释：
 子数组 [3,-1,1]有最大的交替子数组和3 - (-1) + 1 = 5.
 示例 2：

 输入：nums = [2,2,2,2,2]
 输出：2
 解释：
 子数组 [2], [2,2,2]和 [2,2,2,2,2]有相同的最大交替子数组和为2
 [2]: 2.
 [2,2,2]: 2 - 2 + 2 = 2.
 [2,2,2,2,2]: 2 - 2 + 2 - 2 + 2 = 2.
 示例 3：

 输入：nums = [1]
 输出：1
 解释：
 仅有一个非空子数组，为 [1]，它的交替子数组和为 1
  

 提示：

 1 <= nums.length <= 105
 -105 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-alternating-subarray-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long maximumAlternatingSubarraySum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        // dp ij 以i为结尾 j=0 减   j=1时加和的 最大值
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        long max = dp[0][1];
        for (int i = 1; i < n; i++) {
            // 要么 都不用 要么 用之前的
            dp[i][0] = Math.max(dp[i-1][1] - nums[i], 0);
            // 之前是 - 或者 ， 之前就没有
            dp[i][1] = Math.max(dp[i-1][0] + nums[i], nums[i]);

            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[3,-1,1,2]");
        long l = solution.maximumAlternatingSubarraySum(nums);
        System.out.println(l);
        Assert.assertEquals(5, l);


        nums = LeetcodeInputUtils.inputString2IntArray("[-1]");
        l = solution.maximumAlternatingSubarraySum(nums);
        System.out.println(l);
        Assert.assertEquals(-1, l);


        nums = LeetcodeInputUtils.inputString2IntArray("[-5,-100]");
        l = solution.maximumAlternatingSubarraySum(nums);
        System.out.println(l);
        Assert.assertEquals(95, l);
    }

}

