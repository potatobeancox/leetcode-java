package com.potato.study.leetcodecn.p02835.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.List;

/**
 *
 * 2835. 使子序列的和等于目标的最少操作次数

 *
 * 给你一个下标从 0 开始的数组 nums ，它包含 非负 整数，且全部为 2 的幂，同时给你一个整数 target 。

 一次操作中，你必须对数组做以下修改：

 选择数组中一个元素 nums[i] ，满足 nums[i] > 1 。
 将 nums[i] 从数组中删除。
 在 nums 的 末尾 添加 两个 数，值都为 nums[i] / 2 。
 你的目标是让 nums 的一个 子序列 的元素和等于 target ，请你返回达成这一目标的 最少操作次数 。如果无法得到这样的子序列，请你返回 -1 。

 数组中一个 子序列 是通过删除原数组中一些元素，并且不改变剩余元素顺序得到的剩余数组。



 示例 1：

 输入：nums = [1,2,8], target = 7
 输出：1
 解释：第一次操作中，我们选择元素 nums[2] 。数组变为 nums = [1,2,4,4] 。
 这时候，nums 包含子序列 [1,2,4] ，和为 7 。
 无法通过更少的操作得到和为 7 的子序列。
 示例 2：

 输入：nums = [1,32,1,2], target = 12
 输出：2
 解释：第一次操作中，我们选择元素 nums[1] 。数组变为 nums = [1,1,2,16,16] 。
 第二次操作中，我们选择元素 nums[3] 。数组变为 nums = [1,1,2,16,8,8] 。
 这时候，nums 包含子序列 [1,1,2,8] ，和为 12 。
 无法通过更少的操作得到和为 12 的子序列。
 示例 3：

 输入：nums = [1,32,1], target = 35
 输出：-1
 解释：无法得到和为 35 的子序列。


 提示：

 1 <= nums.length <= 1000
 1 <= nums[i] <= 230
 nums 只包含非负整数，且均为 2 的幂。
 1 <= target < 231
 *
 */
public class Solution {



    public int minOperations(List<Integer> nums, int target) {
        // 用一个 32 位置数组记录每个位置 出现的次数
        long[] count = new long[32];
        // 遍历 nums 完善上面那个数组计数 并记录总出现大小
        long sum = 0;
        for (int num : nums) {
            // num 默认是一个二进制数
            sum += num;
            // https://blog.csdn.net/cnds123321/article/details/117340044
            int index = Integer.numberOfTrailingZeros(num);
            count[index]++;
        }
        // 如果总和小于 target 直接返回不可能
        if (sum < target) {
            return -1;
        }
        int i = 0;
        // 比 target 最高非0位小的每个位置都要看一遍
        long partSum = 0;
        int step = 0;
        while (i < 32 && (1 << i) <= target) {
            // 记录当前从低位开始遍历位置 i 看看之前的和是不是大于 大于的话可以直接看下个位置i
            // 如果当前之前的和 大于等于 2（i+1）-1 说明都能弄出来 直接比较下一个 i位置
            partSum += (count[i] << i);
            if (partSum >= (((1 << (i+1)) - 1) & target)) {
                i++;
                continue;
            }
            // 已经说明无法满足只能进位
            i++;
            step++;
            while (i < 32 && count[i] == 0) {
                i++;
                step++;
            }
            if (i >= 32) {
                return -1;
            }
        }
        return step;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = LeetcodeInputUtils.inputString2IngeterList("[1,2,8]");
        int target = 7;
        int i = solution.minOperations(nums, target);
        System.out.println(i);
        Assert.assertEquals(1, i);


        nums = LeetcodeInputUtils.inputString2IngeterList("[64,128,128]");
        target = 2;
        i = solution.minOperations(nums, target);
        System.out.println(i);
        Assert.assertEquals(5, i);


        nums = LeetcodeInputUtils.inputString2IngeterList("[128,1,256,1,1,1,32]");
        target = 6;
        i = solution.minOperations(nums, target);
        System.out.println(i);
        Assert.assertEquals(3, i);


        nums = LeetcodeInputUtils.inputString2IngeterList("[64,32,2,8]");
        target = 5;
        i = solution.minOperations(nums, target);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }


}
