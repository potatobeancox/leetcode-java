package com.potato.study.leetcodecn.p01746.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1746. 经过一次操作后的最大子数组和

 *
 * 你有一个整数数组 nums。你只能将一个元素 nums[i] 替换为 nums[i] * nums[i]。
 *
 * 返回替换后的最大子数组和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,-1,-4,-3]
 * 输出：17
 * 解释：你可以把-4替换为16(-4*(-4))，使nums = [2,-1,16,-3]. 现在，最大子数组和为 2 + -1 + 16 = 17.
 * 示例 2：
 *
 * 输入：nums = [1,-1,1,1,-1,-1,1]
 * 输出：4
 * 解释：你可以把第一个-1替换为1，使 nums = [1,1,1,1,-1,-1,1]. 现在，最大子数组和为 1 + 1 + 1 + 1 = 4.
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-subarray-sum-after-one-operation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {















    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        // dp1 没有做平方操作的 最大和
        int dp1 = nums[0];
        // dp2 做了1次平方操作的最大和
        int dp2 = nums[0] * nums[0];
        int max = dp2;
        for (int i = 1; i < n; i++) {
            // 以i 结尾的最大做了一次平方的值
            dp2 = Math.max(dp2 + nums[i], dp1 + nums[i] * nums[i]);
            dp1 = Math.max(dp1 + nums[i], 0);
            max = Math.max(max, dp2);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                2,-1,-4,-3
        };
        int i = solution.maxSumAfterOperation(nums);
        System.out.println(i);
        Assert.assertEquals(17, i);
    }
}
