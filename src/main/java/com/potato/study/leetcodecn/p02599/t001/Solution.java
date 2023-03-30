package com.potato.study.leetcodecn.p02599.t001;

import org.junit.Assert;

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
        int index = 0;
        long sum = 0;
        // 找到末尾第一个正数
        int lastPosIndex = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] >= 0) {
                lastPosIndex = i;
                break;
            }
        }
        while (index < nums.length) {
            // 找到了 最后一个 index 大于等于 正数了 可以直接返回了
            if (lastPosIndex != -1 && index >= lastPosIndex) {
                return count;
            }
            // 当前数组加上这个数字是不是负数 如果是负数的话 要在末尾找到一个正数交换
            boolean isSumNeg = (sum + nums[index] < 0);
            // 不可能达成
            if (lastPosIndex == -1) {
                return -1;
            }
            // 交换index 和 last 计算 sum 修改 count 和last
            int tmp = nums[lastPosIndex];
            nums[lastPosIndex] = nums[index];
            nums[index] = tmp;

            sum += nums[index];
            count++;

            boolean hasFound = false;
            for (int i = lastPosIndex - 1; i > index; i--) {
                if (nums[i] >= 0) {
                    lastPosIndex = i;
                    hasFound = true;
                    break;
                }
            }

            if (!hasFound) {
                lastPosIndex = -1;
            }

            index++;
        }
        return count;
    }

}
