package com.potato.study.leetcodecn.p00644.t001;


/**
 * 644. 子数组最大平均数 II
 *
 * 给你一个包含 n 个整数的数组 nums ，和一个整数 k 。
 *
 * 请你找出 长度大于等于 k 且含最大平均值的连续子数组。并输出这个最大平均值。任何计算误差小于 10-5 的结果都将被视为正确答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75000
 * 解释：
 * - 当长度为 4 的时候，连续子数组平均值分别为 [0.5, 12.75, 10.5] ，其中最大平均值是 12.75 。
 * - 当长度为 5 的时候，连续子数组平均值分别为 [10.4, 10.8] ，其中最大平均值是 10.8 。
 * - 当长度为 6 的时候，连续子数组平均值分别为 [9.16667] ，其中最大平均值是 9.16667 。
 * 当取长度为 4 的子数组（即，子数组 [12, -5, -6, 50]）的时候，可以得到最大的连续子数组平均值 12.75 ，所以返回 12.75 。
 * 根据题目要求，无需考虑长度小于 4 的子数组。
 * 示例 2：
 *
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= k <= n <= 104
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-average-subarray-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        // 遍历 nums 求出最大和最小值
        double left = nums[0];
        double right = nums[0];
        for (int i = 0; i < nums.length; i++) {
            left = Math.min(left, nums[i]);
            right = Math.max(right, nums[i]);
        }
        // 二分法 枚举出每种可能 对每个最大平均值计算一下 是不是存在于nums
        double diff = right - left;
        while (diff > 0.00001) {
            double mid = (left + right) * 0.5;
            boolean valid = isValid(nums, k, mid);
            if (valid) {
                // 再往大了尝试一下
                left = mid;
            } else {
                right = mid;
            }
            diff = right - left;
        }
        return left;
    }

    /**
     *
     * @param nums
     * @param k
     * @param targetAve 目标的平均值的最大值
     * @return
     */
    private boolean isValid(int[] nums, int k, double targetAve) {
        // 先遍历每个位置 前k个，每个位置 减去 targetAve 求sum
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += (nums[i] - targetAve);
        }
        // 如果sum 大于等于 0说明是可以 组成了
        // double errorValue = 10e-5;
        if (sum >= 0) {
            return true;
        }
        double prefixMin = 0;
        double prefix = 0;
        // 否则记录从k开始往前的最小值 ，并继续滑动计算k 如果 sum - prefixMin 大于等于 0 说明已经找到了 可以达到的子数组
        for (int i = k; i < nums.length; i++) {
            sum += (nums[i] - targetAve);
            prefix += (nums[i-k] - targetAve);
            prefixMin = Math.min(prefixMin, prefix);
            // 如果 sum - prefixMin 大于等于 0 说明已经找到了 可以达到的子数组
            if (sum - prefixMin >= 0) {
                return true;
            }
        }
        return false;
    }

    // [1,12,-5,-6,50,3]
    //4

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,12,-5,-6,50,3
        };
        int k = 4;
        double maxAverage = solution.findMaxAverage(nums, k);
        // 12.75
        System.out.println(maxAverage - 12.75);

    }

}
