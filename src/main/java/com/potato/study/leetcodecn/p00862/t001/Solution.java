package com.potato.study.leetcodecn.p00862.t001;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 862. 和至少为 K 的最短子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestSubarray(int[] nums, int k) {
        // 862
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= k && index <= i) {
                min = Math.min(min, i - index + 1);
                sum -= nums[index];
                index++;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
            1
        };
        int k = 1;
        int i = solution.shortestSubarray(nums, k);
        System.out.println(i);
        Assert.assertEquals(1, i);
    }
}
