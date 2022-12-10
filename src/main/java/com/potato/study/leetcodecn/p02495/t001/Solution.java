package com.potato.study.leetcodecn.p02495.t001;

/**
 * 2495. Number of Subarrays Having Even Product
 *
 * Given an integer array nums, return the number of subarrays of nums having an even product.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [9,6,7,13]
 * Output: 6
 * Explanation: There exist 6 subarrays with even product:
 * - Subarray starting at index 0 and ending at index 1: [9,6,7,13].
 * - Subarray starting at index 1 and ending at index 1: [9,6,7,13].
 * - Subarray starting at index 1 and ending at index 2: [9,6,7,13].
 * - Subarray starting at index 1 and ending at index 3: [9,6,7,13].
 * - Subarray starting at index 0 and ending at index 3: [9,6,7,13].
 * It can be proven that there exist only 6 such subarrays.
 * Example 2:
 *
 * Input: nums = [7,3,5]
 * Output: 0
 * Explanation: There do not exist any subarrays with even product.
 *  
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-subarrays-having-even-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public long evenProduct(int[] nums) {
        // 遍历 nums 每一个位置 求根据每一个位置
        long totalCount = 0;
        long lastEvenCount = 0;
        // 以当前位置为终点的子数组，有多少个 要看当前数字是否是 偶数，如果是偶数的话 那就是之前全部的数量，如果是基数的话 那就是之前的偶数数量（前一个结果）
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                totalCount += (i+1);
                lastEvenCount = i+1;
            } else {
                // 当前是奇数
                totalCount += lastEvenCount;
            }
        }
        return totalCount;
    }

}
