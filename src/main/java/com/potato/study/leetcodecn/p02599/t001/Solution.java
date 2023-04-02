package com.potato.study.leetcodecn.p02599.t001;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 *
 * 2599. Make the Prefix Sum Non-negative
 *
 * You are given a 0-indexed integer array nums. You can apply the following operation any number of times:
 *
 * Pick any element from nums and put it at the end of nums.
 * The prefix sum array of nums is an array prefix of the same length as nums such that prefix[i] is the sum of all the integers nums[j] where j is in the inclusive range [0, i].
 *
 * Return the minimum number of operations such that the prefix sum array does not contain negative integers. The test cases are generated such that it is always possible to make the prefix sum array non-negative.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [2,3,-5,4]
 * Output: 0
 * Explanation: we do not need to do any operations.
 * The array is [2,3,-5,4]. The prefix sum array is [2, 5, 0, 4].
 * Example 2:
 *
 * Input: nums = [3,-5,-2,6]
 * Output: 1
 * Explanation: we can do one operation on index 1.
 * The array after the operation is [3,-2,6,-5]. The prefix sum array is [3, 1, 7, 2].
 *  
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-the-prefix-sum-non-negative
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2599
    public int makePrefSumNonNegative(int[] nums) {
        // 每次操作 可以交换一个 负数 到最后 如果当前加上 小于等于 指定值
        int count = 0;
        long sum = 0;
        // 记录负数sum
        long negSum = 0;
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // sum 非负数
            if (nums[i] < 0) {
                neg.add(nums[i]);
            }
            if (sum < 0) {
                if (neg.isEmpty()) {
                    return -1;
                }
                count++;
                Integer poll = neg.poll();
                negSum += poll;
                sum -= poll;
            }
        }
        // 结算
        sum += negSum;
        if (sum >= 0) {
            return count;
        }
        return -1;
    }

}
