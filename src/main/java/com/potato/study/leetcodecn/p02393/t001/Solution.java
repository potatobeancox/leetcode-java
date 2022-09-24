package com.potato.study.leetcodecn.p02393.t001;

import java.util.HashSet;
import java.util.Set;

/**
 * 2393. Count Strictly Increasing Subarrays
 *
 * You are given an array nums consisting of positive integers.
 *
 * Return the number of subarrays of nums that are in strictly increasing order.
 *
 * A subarray is a contiguous part of an array.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,4,6]
 * Output: 10
 * Explanation: The strictly increasing subarrays are the following:
 * - Subarrays of length 1: [1], [3], [5], [4], [4], [6].
 * - Subarrays of length 2: [1,3], [3,5], [4,6].
 * - Subarrays of length 3: [1,3,5].
 * The total number of subarrays is 6 + 3 + 1 = 10.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 15
 * Explanation: Every subarray is strictly increasing. There are 15 possible subarrays that we can take.
 *  
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-strictly-increasing-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long countSubarrays(int[] nums) {
        // len 记录 之前多少连续递增字符串
        long len = 0;
        long totalCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i-1]) {
                len++;
            } else {
                len = 1;
            }
            // 以当前位置 作为最后一个元素的 子串长度
            totalCount += len;
        }
        return totalCount;
    }

}
