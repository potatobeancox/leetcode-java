package com.potato.study.leetcodecn.p00673.t001;

import org.junit.Assert;

/**
 * 673. 最长递增子序列的个数
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。

 示例 1:

 输入: [1,3,5,4,7]
 输出: 2
 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 示例 2:

 输入: [2,2,2,2,2]
 输出: 5
 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findNumberOfLIS(int[] nums) {
        // dp i 以i结尾 的最长串个数 count i 以 i结尾 最长子串个数 弄一个 max
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        dp[0] = 1;
        count[0] = 1;
        // 遍历 count i 找到 等于 max 的个数和
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // dp i = max dp k + 1
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        // 找到max 大小的个数
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (max == dp[i]) {
                total += count[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,3,5,4,7
        };
        int numberOfLIS = solution.findNumberOfLIS(nums);
        System.out.println(numberOfLIS);
        Assert.assertEquals(2, numberOfLIS);

        nums = new int[] {
                2,2,2,2,2
        };
        numberOfLIS = solution.findNumberOfLIS(nums);
        System.out.println(numberOfLIS);
        Assert.assertEquals(5, numberOfLIS);
    }


}
