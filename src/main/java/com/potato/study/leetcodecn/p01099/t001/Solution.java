package com.potato.study.leetcodecn.p01099.t001;

import java.util.Arrays;

/**
 * 1099. 小于 K 的两数之和
 *
 * 给你一个整数数组 nums 和整数 k ，返回最大和 sum ，满足存在 i < j 使得 nums[i] + nums[j] = sum 且 sum < k 。如果没有满足此等式的 i,j 存在，则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [34,23,1,24,75,33,54,8], k = 60
 * 输出：58
 * 解释：
 * 34 和 24 相加得到 58，58 小于 60，满足题意。
 * 示例 2：
 *
 * 输入：nums = [10,20,30], k = 15
 * 输出：-1
 * 解释：
 * 我们无法找到和小于 15 的两个元素。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 * 1 <= k <= 2000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int max = -1;
        while (left < right) {
            int current = nums[left] + nums[right];
            if (current < k) {
                //  小了
                max = Math.max(max, current);
                left++;
            } else {
                right--;
            }
        }
        return max;
    }


}
