package com.potato.study.leetcodecn.p00910.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 910. 最小差值 II
 *
 * 给你一个整数数组 nums，和一个整数 k 。
 *
 * 对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成 nums[i] + k 或 nums[i] - k 。
 *
 * nums 的 分数 是 nums 中最大元素和最小元素的差值。
 *
 * 在更改每个下标对应的值之后，返回 nums 的最小 分数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 0
 * 输出：0
 * 解释：分数 = max(nums) - min(nums) = 1 - 1 = 0 。
 * 示例 2：
 *
 * 输入：nums = [0,10], k = 2
 * 输出：6
 * 解释：将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
 * 示例 3：
 *
 * 输入：nums = [1,3,6], k = 3
 * 输出：3
 * 解释：将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 104
 * 0 <= k <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-range-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        // 全局分数最大
        int totalMax = nums[nums.length - 1];
        // 全局分数最小
        int totalMin = nums[0];
        // 维护历史最小分数
        int minScore = Integer.MAX_VALUE;
        for (int i = 0; i < 1; i++) {

        }


        return minScore;
    }

}
