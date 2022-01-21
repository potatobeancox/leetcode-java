package com.potato.study.leetcodecn.p01567.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * 1567. 乘积为正数的最长子数组长度
 *
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 *
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 *
 * 请你返回乘积为正数的最长子数组长度。
 *
 *  
 *
 * 示例  1：
 *
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * 示例 2：
 *
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * 示例 3：
 *
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getMaxLen(int[] nums) {
        // 遍历 nums 记录 最长 正数长度和最长负数长度 连续
        int maxPositiveCount = 0;
        int currentPositiveCount = 0;
        int currentNegativeCount = 0;
        for (int num : nums) {
            if (num == 0) {
                currentPositiveCount = 0;
                currentNegativeCount = 0;
            } else if (num > 0) {
                // 当前大于 0
                currentPositiveCount++;
                if (currentNegativeCount > 0) {
                    currentNegativeCount++;
                }
            } else {
                // 当前小于 0
                int tmp = currentPositiveCount;
                if (currentNegativeCount > 0) {
                    currentPositiveCount = currentNegativeCount + 1;
                } else {
                    currentPositiveCount = 0;
                }

                if (tmp > 0) {
                    currentNegativeCount = tmp + 1;
                } else {
                    currentNegativeCount = 1;
                }
            }
            maxPositiveCount = Math.max(maxPositiveCount, currentPositiveCount);
        }
        return maxPositiveCount;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                0,1,-2,-3,-4
        };
        int maxLen = solution.getMaxLen(arr);
        System.out.println(maxLen);
        Assert.assertEquals(3, maxLen);


        arr = new int[] {
                -1,-2,-3,0,1
        };
        maxLen = solution.getMaxLen(arr);
        System.out.println(maxLen);
        Assert.assertEquals(2, maxLen);
    }


}
