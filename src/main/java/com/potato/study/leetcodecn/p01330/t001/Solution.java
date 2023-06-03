package com.potato.study.leetcodecn.p01330.t001;

import org.junit.Assert;

/**
 * 1330. 翻转子数组得到最大的数组值
 *
 * 给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。

 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。

 请你找到可行的最大 数组值 。

  

 示例 1：

 输入：nums = [2,3,1,5,4]
 输出：10
 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
 示例 2：

 输入：nums = [2,4,9,24,2,1,10]
 输出：68
  

 提示：

 1 <= nums.length <= 3*10^4
 -10^5 <= nums[i] <= 10^5

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxValueAfterReverse(int[] nums) {
        // 遍历求 nums 计算差值和 并统计 过程中的最大值和最小值 并计算只有一端 需要更新的最大值
        long base = 0;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int oneSideMax = 0;
        for (int i = 0; i < n - 1; i++) {
            base += Math.abs(nums[i+1]-nums[i]);
            min = Math.min(min, Math.max(nums[i], nums[i+1]));
            max = Math.max(max, Math.min(nums[i], nums[i+1]));

            if (i > 0) {
                // 假设翻转的左边是从 0开始的
                oneSideMax = Math.max(oneSideMax,
                        Math.abs(nums[i+1]-nums[0]) - Math.abs(nums[i+1] - nums[i]));
                // 翻转的右边是从 n-1开始的
                oneSideMax = Math.max(oneSideMax,
                        Math.abs(nums[n-1]-nums[i-1]) - Math.abs(nums[i] - nums[i-1]));
            }
        }
        return (int) (base + Math.max(2 * (max - min), oneSideMax));
    }
}
