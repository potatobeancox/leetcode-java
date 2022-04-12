package com.potato.study.leetcodecn.p01695.t001;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

/**
 * 1695. 删除子数组的最大得分
 *
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 *
 * 返回 只删除一个 子数组可获得的 最大得分 。
 *
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 * 示例 2：
 *
 * 输入：nums = [5,2,1,2,5,2,1,2,5]
 * 输出：8
 * 解释：最优子数组是 [5,2,1] 或 [1,2,5]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-erasure-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1695
    public int maximumUniqueSubarray(int[] nums) {
        // 使用 set 记录 是否有重复 记录 start 窗口 的start 位置
        long maxSum = 0;
        Set<Integer> set = new HashSet<>();
        long tmpSum = 0;
        int startIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                tmpSum += nums[i];
                maxSum = Math.max(maxSum, tmpSum);
            } else {
                // 找到 set 中第一个 与之相同的
                while (startIndex < i && nums[startIndex] != nums[i]) {
                    tmpSum -= nums[startIndex];
                    startIndex++;
                }
                // 把之前set的让出来
                if (nums[startIndex] == nums[i]) {
                    startIndex++;
                }
            }
        }
        return (int) maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                4,2,4,5,6
        };
        int i = solution.maximumUniqueSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(i, 17);


        nums = new int[] {
                5,2,1,2,5,2,1,2,5
        };
        i = solution.maximumUniqueSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(i, 8);

        nums = new int[] {
                538,809,166,809,165,809,610
        };
        i = solution.maximumUniqueSubarray(nums);
        System.out.println(i);
        Assert.assertEquals(i, 16911);
    }

}
