package com.potato.study.leetcodecn.p02750.t001;


import java.util.Arrays;

/**
 *
 * 2750. 将数组划分成若干好子数组的方式
 *
 * 给你一个二元数组 nums 。
 *
 * 如果数组中的某个子数组 恰好 只存在 一 个值为 1 的元素，则认为该子数组是一个 好子数组 。
 *
 * 请你统计将数组 nums 划分成若干 好子数组 的方法数，并以整数形式返回。由于数字可能很大，返回其对 109 + 7 取余 之后的结果。
 *
 * 子数组是数组中的一个连续 非空 元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,0,0,1]
 * 输出：3
 * 解释：存在 3 种可以将 nums 划分成若干好子数组的方式：
 * - [0,1] [0,0,1]
 * - [0,1,0] [0,1]
 * - [0,1,0,0] [1]
 * 示例 2：
 *
 * 输入：nums = [0,1,0]
 * 输出：1
 * 解释：存在 1 种可以将 nums 划分成若干好子数组的方式：
 * - [0,1,0]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ways-to-split-array-into-good-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param nums
     * @return
     */
    public int numberOfGoodSubarraySplits(int[] nums) {
        return -1;
    }

}
