package com.potato.study.leetcodecn.p00775.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

/**
 * 775. 全局倒置与局部倒置
 *
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 *
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 *
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 *
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2：
 *
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有 2 个全局倒置，和 1 个局部倒置。
 *  
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 5000
 * 0 <= nums[i] < n
 * nums 中的所有整数 互不相同
 * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/global-and-local-inversions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isIdealPermutation(int[] nums) {
        // 从后往前 维护当前最小值 如果 i-2 大于最小值 说明 不是
        int min = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 2; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i-2] > min) {
                return false;
            }
        }
        return true;
    }

}
