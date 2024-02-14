package com.potato.study.leetcodecn.p02863.t001;


import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 *
 * 2863. 最长半递减数组
 *
 * 给定一个整数数组 nums。
 *
 * 返回 nums 的 最长半递减子数组 的长度，如果没有这样的子数组则返回 0。
 *
 * 子数组 是数组内的连续非空元素序列。
 * 一个非空数组是 半递减 的，如果它的第一个元素 严格大于 它的最后一个元素。
 *
 *
 * 示例 1：
 *
 * 输入： nums = [7,6,5,4,3,2,1,6,10,11]
 * 输出： 8
 * 解释： 取子数组 [7,6,5,4,3,2,1,6]。
 * 第一个元素是 7，最后一个元素是 6，因此满足条件。
 * 因此，答案是子数组的长度，即 8。
 * 可以证明，在给定条件下，没有长度大于 8 的子数组。
 * 示例 2：
 *
 * 输入： nums = [57,55,50,60,61,58,63,59,64,60,63]
 * 输出： 6
 * 解释： 取子数组 [61,58,63,59,64,60].
 * 第一个元素是 61，最后一个元素是 60，因此满足条件。
 * 因此，答案是子数组的长度，即 6。
 * 可以证明，在给定条件下，没有长度大于 6 的子数组。
 * 示例 3:
 *
 * 输入： nums = [1,2,3,4]
 * 输出： 0
 * 解释： 由于给定数组中没有半递减子数组，答案为 0。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 */
public class Solution {


    /**
     * 什么是最长半递减字符串: 子字符串
     * @param nums
     * @return
     */
    public int maxSubarrayLength(int[] nums) {
        return -1;
    }


    public static void main(String[] args) {

    }

}
