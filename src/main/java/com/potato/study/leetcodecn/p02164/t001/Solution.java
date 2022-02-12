package com.potato.study.leetcodecn.p02164.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 2164. 对奇偶下标分别排序
 *
 * 给你一个下标从 0 开始的整数数组 nums 。根据下述规则重排 nums 中的值：
 *
 * 按 非递增 顺序排列 nums 奇数下标 上的所有值。
 * 举个例子，如果排序前 nums = [4,1,2,3] ，对奇数下标的值排序后变为 [4,3,2,1] 。奇数下标 1 和 3 的值按照非递增顺序重排。
 * 按 非递减 顺序排列 nums 偶数下标 上的所有值。
 * 举个例子，如果排序前 nums = [4,1,2,3] ，对偶数下标的值排序后变为 [2,1,4,3] 。偶数下标 0 和 2 的值按照非递减顺序重排。
 * 返回重排 nums 的值之后形成的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,2,3]
 * 输出：[2,3,4,1]
 * 解释：
 * 首先，按非递增顺序重排奇数下标（1 和 3）的值。
 * 所以，nums 从 [4,1,2,3] 变为 [4,3,2,1] 。
 * 然后，按非递减顺序重排偶数下标（0 和 2）的值。
 * 所以，nums 从 [4,1,2,3] 变为 [2,3,4,1] 。
 * 因此，重排之后形成的数组是 [2,3,4,1] 。
 * 示例 2：
 *
 * 输入：nums = [2,1]
 * 输出：[2,1]
 * 解释：
 * 由于只有一个奇数下标和一个偶数下标，所以不会发生重排。
 * 形成的结果数组是 [2,1] ，和初始数组一样。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 通过
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-even-and-odd-indices-independently
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] sortEvenOdd(int[] nums) {
        int length = nums.length;
        int midLen = length / 2;
        int[] nums1;
        int[] nums2;
        if (length % 2 == 0) {
            nums1 = new int[midLen];
            nums2 = new int[midLen];
        } else {
            nums1 = new int[midLen + 1];
            nums2 = new int[midLen];
        }
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums1[index1++] = nums[i];
            } else {
                nums2[index2++] = nums[i];
            }
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0; i < nums1.length; i++) {
            nums[i * 2] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            nums[i * 2 + 1] = nums2[nums2.length - 1 - i];
        }
        return nums;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {
                4,1,2,3
        };
        int[] ints = solution.sortEvenOdd(nums);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                2,3,4,1
        }, ints);
    }
}
