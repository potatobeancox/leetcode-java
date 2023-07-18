package com.potato.study.leetcodecn.p02765.t001;


import org.junit.Assert;

/**
 *
 * 2765. 最长交替子序列
 *
 * 给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子序列 ：
 *
 * m 大于 1 。
 * s1 = s0 + 1 。
 * 下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。
 * 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
 *
 * 子数组是一个数组中一段连续 非空 的元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,4,3,4]
 * 输出：4
 * 解释：交替子数组有 [3,4] ，[3,4,3] 和 [3,4,3,4] 。最长的子数组为 [3,4,3,4] ，长度为4 。
 * 示例 2：
 *
 * 输入：nums = [4,5,6]
 * 输出：2
 * 解释：[4,5] 和 [5,6] 是仅有的两个交替子数组。它们长度都为 2 。
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-alternating-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {




    public int alternatingSubarray(int[] nums) {
        int maxLength = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            // 判断开始的两个位置
            if (nums[i+1] - nums[i] != 1) {
                continue;
            }
            // 找到了开头 往后遍历 对于每个位置
            int startIndex1 = i;
            int startIndex2 = i + 1;
            int length = 2;
            for (int j = i+2; j < nums.length; j++) {
                if ((j - i) % 2 == 0) {
                    // 应该跟 startIndex1 相同
                    if (nums[j] != nums[startIndex1]) {
                        break;
                    }
                } else {
                    // 应该跟 startIndex2 相同
                    if (nums[j] != nums[startIndex2]) {
                        break;
                    }
                }
                length++;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                14,30,29,49,3,23,44,21,26,52
        };
        int i = solution.alternatingSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }

}
