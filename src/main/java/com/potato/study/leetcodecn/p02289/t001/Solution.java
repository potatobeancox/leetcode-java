package com.potato.study.leetcodecn.p02289.t001;

import java.util.Stack;

/**
 * 2289. 使数组按非递减顺序排列
 *
 * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，移除所有满足 nums[i - 1] > nums[i] 的 nums[i] ，其中 0 < i < nums.length 。
 *
 * 重复执行步骤，直到 nums 变为 非递减 数组，返回所需执行的操作数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,3,4,4,7,3,6,11,8,5,11]
 * 输出：3
 * 解释：执行下述几个步骤：
 * - 步骤 1 ：[5,3,4,4,7,3,6,11,8,5,11] 变为 [5,4,4,7,6,11,11]
 * - 步骤 2 ：[5,4,4,7,6,11,11] 变为 [5,4,7,11,11]
 * - 步骤 3 ：[5,4,7,11,11] 变为 [5,7,11,11]
 * [5,7,11,11] 是一个非递减数组，因此，返回 3 。
 * 示例 2：
 *
 * 输入：nums = [4,5,7,7,13]
 * 输出：0
 * 解释：nums 已经是一个非递减数组，因此，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/steps-to-make-array-non-decreasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int totalSteps(int[] nums) {
        // 使用一个 stack 存储 比当前值 大的元素 要被移除的时间 最大
        Stack<int[]> stack = new Stack<>();
        // int[] 0-元素index ； 1- 这个元素被移除的时间 0 说明 不用被移除
        int time = 0;
        // 遍历 nums 对于每个 num 判断 找到栈中第一个大于 它的元素
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            int lastRemoveTime = 0;
            // 找到第一个 大于 target 位置，之前的时间 统计max
            while (!stack.isEmpty() && nums[stack.peek()[0]] <= target) {
                // 上一个删除的元素
                int[] pop = stack.pop();
                lastRemoveTime = Math.max(lastRemoveTime, pop[1]);
            }
            // 判断当前元素删除的时间
            if (!stack.isEmpty()) {
                // 之前没有比这个大的元素 这个元素不用删除 但是要记录 以便于之后的
                time = Math.max(time, lastRemoveTime + 1);
                stack.push(new int[] {i, lastRemoveTime + 1});
            } else {
                // 不用删除
                stack.push(new int[] {i, lastRemoveTime});
            }
        }
        // 当前值 等于 左边 小于等于 这个元素 的移除时间 + 1 如果队伍是 空 那就没法移除了 放入即可
        return time;
    }
}
