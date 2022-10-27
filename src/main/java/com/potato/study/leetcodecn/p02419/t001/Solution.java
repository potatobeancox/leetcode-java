package com.potato.study.leetcodecn.p02419.t001;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2419. 按位与最大的最长子数组
 *
 * 给你一个长度为 n 的整数数组 nums 。

 考虑 nums 中进行 按位与（bitwise AND）运算得到的值 最大 的 非空 子数组。

 换句话说，令 k 是 nums 任意 子数组执行按位与运算所能得到的最大值。那么，只需要考虑那些执行一次按位与运算后等于 k 的子数组。
 返回满足要求的 最长 子数组的长度。

 数组的按位与就是对数组中的所有数字进行按位与运算。

 子数组 是数组中的一个连续元素序列。

  

 示例 1：

 输入：nums = [1,2,3,3,2,2]
 输出：2
 解释：
 子数组按位与运算的最大值是 3 。
 能得到此结果的最长子数组是 [3,3]，所以返回 2 。
 示例 2：

 输入：nums = [1,2,3,4]
 输出：1
 解释：
 子数组按位与运算的最大值是 4 。
 能得到此结果的最长子数组是 [4]，所以返回 1 。
  

 提示：

 1 <= nums.length <= 105
 1 <= nums[i] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestSubarray(int[] nums) {
        // 根据 & 的性质，不一样的 & 只能比最大的小
        int currentLen = 1;
        int max = nums[0];
        int maxLen = currentLen;
        for (int i = 1; i < nums.length; i++) {
            // 因此 遍历 nums 过程中 维护max ，并且从最大值开始找连续的子数组个数
            int current = nums[i];
            if (current > max) {
                max = current;
                currentLen = 1;
                maxLen = 1;
            } else if (current == max) {
                currentLen++;
            } else {
                // current < max
                currentLen = 0;
            }
            maxLen = Math.max(currentLen, maxLen);
        }
        return maxLen;
    }

}
