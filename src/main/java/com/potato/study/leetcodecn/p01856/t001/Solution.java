package com.potato.study.leetcodecn.p01856.t001;

import java.util.Stack;

import org.junit.Assert;

/**
 * 1856. 子数组最小乘积的最大值
 *
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 *
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 *
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 *
 * 子数组 定义为一个数组的 连续 部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 * 示例 2：
 *
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 * 示例 3：
 *
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-subarray-min-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1856
    public int maxSumMinProduct(int[] nums) {
        // 单调栈 + 前缀和 先对 nums 求前缀和
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        // 使用单调栈 记录 nums 中i 节点 之前 第一个 小于 这个nums i 对应的 index 和 第一个 大于 的index
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < left.length; i++) {
            while (!stack.isEmpty()
                    && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.add(i);
        }
        // 遍历 nums i 找到 这个区间 以 i为最小值的 乘积 的最大值
        stack = new Stack<>();
        for (int i = right.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()
                    && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.peek();
            }
            stack.add(i);
        }
        // 求最大值
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int leftIndex = left[i];
            int rightIndex = right[i];
            // 全部数组
            long target;
            if (leftIndex == -1 && rightIndex == n) {
                target = num * sum[n];
            } else if (leftIndex == -1) {
                target = num * sum[rightIndex];
            } else if (rightIndex == n) {
                target = num * (sum[n] - sum[leftIndex + 1]);
            } else {
                target = num * (sum[rightIndex] - sum[leftIndex + 1]);
            }
            max = Math.max(max, target);
        }
        int mod = 1_000_000_000 + 7;
        return (int) (max % mod);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                1,2,3,2
        };
        int i = solution.maxSumMinProduct(nums);
        System.out.println(i);
        Assert.assertEquals(14, i);

        nums = new int[] {
                2,3,3,1,2
        };
        i = solution.maxSumMinProduct(nums);
        System.out.println(i);
        Assert.assertEquals(18, i);
    }
}
