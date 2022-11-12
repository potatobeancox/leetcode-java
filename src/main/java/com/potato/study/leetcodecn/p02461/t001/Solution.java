package com.potato.study.leetcodecn.p02461.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2461. 长度为 K 子数组中的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 *
 * 子数组的长度是 k，且
 * 子数组中的所有元素 各不相同 。
 * 返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。
 *
 * 子数组 是数组中一段连续非空的元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,4,2,9,9,9], k = 3
 * 输出：15
 * 解释：nums 中长度为 3 的子数组是：
 * - [1,5,4] 满足全部条件，和为 10 。
 * - [5,4,2] 满足全部条件，和为 11 。
 * - [4,2,9] 满足全部条件，和为 15 。
 * - [2,9,9] 不满足全部条件，因为元素 9 出现重复。
 * - [9,9,9] 不满足全部条件，因为元素 9 出现重复。
 * 因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
 * 示例 2：
 *
 * 输入：nums = [4,4,4], k = 3
 * 输出：0
 * 解释：nums 中长度为 3 的子数组是：
 * - [4,4,4] 不满足全部条件，因为元素 4 出现重复。
 * 因为不存在满足全部条件的子数组，所以返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long maximumSubarraySum(int[] nums, int k) {
        // 遍历 nums 滑动窗口
        Map<Integer, Integer> countMap = new HashMap<>();
        // 前边k-1个
        long maxSum = 0;
        long currentSum = 0;
        for (int i = 0; i < k - 1; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            currentSum += nums[i];
        }
        // 使用map key 标示 有多少个计数
        for (int i = k-1; i < nums.length; i++) {
            currentSum += nums[i];
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            if (countMap.size() == k && currentSum > maxSum) {
                maxSum = currentSum;
            }
            // 删掉第一个
            int count = countMap.get(nums[i - (k - 1)]);
            count--;
            if (count == 0) {
                countMap.remove(nums[i - (k - 1)]);
            } else {
                countMap.put(nums[i - (k - 1)], count);
            }
            currentSum -= nums[i - (k - 1)];
        }
        return maxSum;
    }
}
