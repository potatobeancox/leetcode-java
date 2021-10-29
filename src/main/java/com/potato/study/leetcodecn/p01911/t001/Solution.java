package com.potato.study.leetcodecn.p01911.t001;

import org.junit.Assert;

/**
 * 1911. 最大子序列交替和
 *
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。

 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。

 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。

  

 示例 1：

 输入：nums = [4,2,5,3]
 输出：7
 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 示例 2：

 输入：nums = [5,6,7,8]
 输出：8
 解释：最优子序列为 [8] ，交替和为 8 。
 示例 3：

 输入：nums = [6,2,1,2,4,5]
 输出：10
 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        // dp ij  j=0 or 1 表示 0-i位置 最大的交替和 0表示位置为偶数列，1表示奇数列
        long[][] dp = new long[nums.length][2];
        // 偶数个 啥也不能选了
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 偶数序列，可能是选这个位置或者不选这个位置 同理奇数也是
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - nums[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + nums[i]);
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{
                4,2,5,3
        };
        long l = solution.maxAlternatingSum(arr);
        System.out.println(l);
        Assert.assertEquals(7, l);
    }


}
