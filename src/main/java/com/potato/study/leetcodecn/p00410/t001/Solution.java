package com.potato.study.leetcodecn.p00410.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 410. 分割数组的最大值
 *
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。

 设计一个算法使得这 m 个子数组各自和的最大值最小。

  

 示例 1：

 输入：nums = [7,2,5,10,8], m = 2
 输出：18
 解释：
 一共有四种方法将 nums 分割为 2 个子数组。
 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 示例 2：

 输入：nums = [1,2,3,4,5], m = 2
 输出：9
 示例 3：

 输入：nums = [1,4,4], m = 3
 输出：4
  

 提示：

 1 <= nums.length <= 1000
 0 <= nums[i] <= 106
 1 <= m <= min(50, nums.length)

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 410
    public int splitArray(int[] nums, int m) {
        // 前缀和
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        int[][] dp = new int[nums.length+1][m+1];
        // 初始化成最大值
        for (int i = 0; i < nums.length + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 0 个元素 分成0组
        dp[0][0] = 0;
        // 到某个位置 i-1位置 分成 j组
        for (int i = 1; i <= nums.length; i++) {
            // 最多不能超过 1 因为 只有一个元素 不能搞太多
            for (int j = 1; j <= Math.min(m, i); j++) {
                // 枚举
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], sum[i] - sum[k]));
                }
            }
        }
        return dp[nums.length-1][m-1];
    }
}
