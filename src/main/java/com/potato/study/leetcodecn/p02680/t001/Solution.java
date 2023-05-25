package com.potato.study.leetcodecn.p02680.t001;

import java.util.Arrays;

/**
 *
 * 2680. 最大或值
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k 。每一次操作中，你可以选择一个数并将它乘 2 。
 *
 * 你最多可以进行 k 次操作，请你返回 nums[0] | nums[1] | ... | nums[n - 1] 的最大值。
 *
 * a | b 表示两个整数 a 和 b 的 按位或 运算。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [12,9], k = 1
 * 输出：30
 * 解释：如果我们对下标为 1 的元素进行操作，新的数组为 [12,18] 。此时得到最优答案为 12 和 18 的按位或运算的结果，也就是 30 。
 * 示例 2：
 *
 * 输入：nums = [8,1,2], k = 2
 * 输出：35
 * 解释：如果我们对下标 0 处的元素进行操作，得到新数组 [32,1,2] 。此时得到最优答案为 32|1|2 = 35 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 15
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-or
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2680
    public long maximumOr(int[] nums, int k) {
        // 有个结论 不如将所有 k 都给一个数
        int len = nums.length;
        long[] right = new long[len];
        long state = 0;
        for (int i = len - 1; i >= 0; i--) {
            right[i] = state;
            state |= nums[i];
        }
        // 那么就需要记录前后的状态 前面的或和后面的或 或上当前位置左移k
        long left = 0;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            long target = (left | ((long)nums[i] << k) | right[i]);
            max = Math.max(max, target);
            left |= nums[i];
        }
        return max;
    }
}
