package com.potato.study.leetcodecn.p01027.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1027. 最长等差数列
 *
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 *
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1]
 * - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * 示例 2：
 *
 * 输入：nums = [9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * 示例 3：
 *
 * 输入：nums = [20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestArithSeqLength(int[] nums) {
        // dp id 以i为结尾的数 差d的等差数列的最长长度
        int[][] dp = new int[nums.length][1001];
        // 对于当前位置 i来说 如果 i-d 存在
        int maxLength = 1;
        // 开始 初始化一个值
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int d = nums[i] - nums[j] + 500;
                dp[i][d] = Math.max(dp[i][d], Math.max(dp[j][d], 1) + 1);
                maxLength = Math.max(maxLength, dp[i][d]);
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                3,6,9,12
        };
        int count = solution.longestArithSeqLength(nums);
        System.out.println(count);
        Assert.assertEquals(4, count);


        nums = new int[] {
                9,4,7,2,10
        };
        count = solution.longestArithSeqLength(nums);
        System.out.println(count);
        Assert.assertEquals(3, count);

    }
}
