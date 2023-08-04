package com.potato.study.leetcodecn.other.lcr.p0008.t001;

import org.junit.Assert;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *  
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/2VG8Kg
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    // jianzhi2 offer 008
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口
        int left = 0;
        int window = 0;
        int minWindow = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            window += nums[right];
            while (window >= target && left <= right) {
                minWindow = Math.min(minWindow, right - left + 1);
                window -= nums[left];
                left++;
            }
        }
        return minWindow == Integer.MAX_VALUE ? 0 : minWindow;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 7;
        int[] nums = new int[]{2,3,1,2,4,3};
        int len = solution.minSubArrayLen(target, nums);
        System.out.println(len);
        Assert.assertEquals(2, len);
    }


}
