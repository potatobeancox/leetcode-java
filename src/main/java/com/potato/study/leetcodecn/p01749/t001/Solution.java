package com.potato.study.leetcodecn.p01749.t001;

import org.junit.Assert;

/**
 * 1749. 任意子数组和的绝对值的最大值
 *
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 *
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 *
 * abs(x) 定义如下：
 *
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 *
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-absolute-sum-of-any-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 前缀和
     * 然后就是 n2 遍历求 abs 最大值
     * @param nums
     * @return
     */
    public int maxAbsoluteSum(int[] nums) {
        long[] sum = new long[nums.length+1];
        sum[1] = nums[0];
        long max = nums[0];
        long min = nums[0];
        for (int i = 2; i < sum.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
            max = Math.max(sum[i], max);
            min = Math.min(sum[i], min);
        }
        return (int) Math.max(Math.abs(max), Math.max(Math.abs(min), Math.abs(max - min)));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,-3,2,3,-4
        };
        int i = solution.maxAbsoluteSum(arr);
        System.out.println(i);
        Assert.assertEquals(5, i);

        arr = new int[] {
                2,-5,1,-4,3,-2
        };
        i = solution.maxAbsoluteSum(arr);
        System.out.println(i);
        Assert.assertEquals(8, i);

        arr = new int[] {
                -7,-1,0,-2,1,3,8,-2,-6,-1,-10,-6,-6,8,-4,-9,-4,1,4,-9
        };
        i = solution.maxAbsoluteSum(arr);
        System.out.println(i);
        Assert.assertEquals(44, i);
    }
}
