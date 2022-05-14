package com.potato.study.leetcodecn.p01959.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1959. K 次调整数组大小浪费的最小总空间
 *
 * 你正在设计一个动态数组。给你一个下标从 0 开始的整数数组 nums ，其中 nums[i] 是 i 时刻数组中的元素数目。除此以外，你还有一个整数 k ，表示你可以 调整 数组大小的 最多 次数（每次都可以调整成 任意 大小）。
 *
 * t 时刻数组的大小 sizet 必须大于等于 nums[t] ，因为数组需要有足够的空间容纳所有元素。t 时刻 浪费的空间 为 sizet - nums[t] ，总 浪费空间为满足 0 <= t < nums
 * .length 的每一个时刻 t 浪费的空间 之和 。
 *
 * 在调整数组大小不超过 k 次的前提下，请你返回 最小总浪费空间 。
 *
 * 注意：数组最开始时可以为 任意大小 ，且 不计入 调整大小的操作次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [10,20], k = 0
 * 输出：10
 * 解释：size = [20,20].
 * 我们可以让数组初始大小为 20 。
 * 总浪费空间为 (20 - 10) + (20 - 20) = 10 。
 * 示例 2：
 *
 * 输入：nums = [10,20,30], k = 1
 * 输出：10
 * 解释：size = [20,20,30].
 * 我们可以让数组初始大小为 20 ，然后时刻 2 调整大小为 30 。
 * 总浪费空间为 (20 - 10) + (20 - 20) + (30 - 30) = 10 。
 * 示例 3：
 *
 * 输入：nums = [10,20,15,30,20], k = 2
 * 输出：15
 * 解释：size = [10,20,20,30,30].
 * 我们可以让数组初始大小为 10 ，时刻 1 调整大小为 20 ，时刻 3 调整大小为 30 。
 * 总浪费空间为 (10 - 10) + (20 - 20) + (20 - 15) + (30 - 30) + (30 - 20) = 15 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 106
 * 0 <= k <= nums.length - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-total-space-wasted-with-k-resizing-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minSpaceWastedKResizing(int[] nums, int k) {
        // 使用一个 数组 proMax ij 记录 ij 的max * ij之间的长度
        int n = nums.length;
        long [][] proMax = new long[n][n];
        long sum = 0;
        // 开始位置
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                proMax[i][j] = max * (j - i + 1);
            }
            sum += nums[i];
        }
        // dp ij 表示 前i个数组 被分成 k 组 最小代价 最后一组是k+1
        long [][] dp = new long[n][k+2];
        // 填充最大值
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // dp ij = min dp k-1 j-1 + proMax k, i   k == 1 时 为 0
        for (int i = 0; i < n; i++) {
            // 控制分成多少组
            for (int j = 1; j <= k+1; j++) {
                // 0 - i 中间取一个点
                for (int l = 0; l <= i; l++) {
                    if (l == 0) {
                        dp[i][j] = Math.min(dp[i][j], proMax[l][i]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[l-1][j-1] + proMax[l][i]);
                    }
                }
            }
        }
        return (int) (dp[n-1][k+1] - sum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = LeetcodeInputUtils.inputString2IntArray("[10,20]");
        int k = 0;
        int i = solution.minSpaceWastedKResizing(nums, k);
        System.out.println(i);
        Assert.assertEquals(10, i);
    }

}
