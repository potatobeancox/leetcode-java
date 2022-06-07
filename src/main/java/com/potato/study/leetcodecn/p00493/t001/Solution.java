package com.potato.study.leetcodecn.p00493.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 493. 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int reversePairs(int[] nums) {
        // 归并排序
        if (nums.length <= 1) {
            return 0;
        }
        int total = mergeAndSort(nums, 0, nums.length - 1);
        return total;
    }

    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int mergeAndSort(int[] nums, int left, int right) {
        // 只有一个数字
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int leftCount = mergeAndSort(nums, left, mid);
        int rightCount = mergeAndSort(nums, mid + 1, right);

        // 滑动窗口 找  nums[i] > 2*nums[j] 数量
        int result = leftCount + rightCount;
        // 枚举左边的位置 j
        int rightIndex = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (rightIndex <= right
                    && (long) nums[i] > 2L * nums[rightIndex]) {
                rightIndex++;
            }
            // 找到 right index i and [i+1, rightIndex-1]
            if (rightIndex > i) {
                int count = rightIndex - 1 - mid - 1 + 1;
                result += count;
            }
        }
        // 合并有序
        merge(nums, left, right, mid);
        return result;
    }

    private void merge(int[] nums, int left, int right, int mid) {
        int[] sorted = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid || j <= right) {
            // 判断是否消耗完了一个
            if (i > mid) {
                // i 消耗完了
                sorted[index] = nums[j];
                j++;
            } else if (j > right) {
                sorted[index] = nums[i];
                i++;
            } else {
                // 都没有消耗完
                if (nums[i] <= nums[j]) {
                    sorted[index] = nums[i];
                    i++;
                } else {
                    sorted[index] = nums[j];
                    j++;
                }
            }
            index++;
        }

        // 数组置换
        for (int k = 0; k < sorted.length; k++) {
            nums[left + k] = sorted[k];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {
                1,3,2,3,1
        };
        int i = solution.reversePairs(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);

        arr = new int[] {
                2147483647,2147483647,2147483647,2147483647,2147483647,2147483647
        };
        i = solution.reversePairs(arr);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
