package com.potato.study.leetcodecn.p01775.t001;

import org.junit.Assert;

import java.lang.ref.SoftReference;

/**
 * 1775. 通过最少操作次数使数组的和相等
 *
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。

 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。

 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。

  

 示例 1：

 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 输出：3
 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
 - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
 - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
 示例 2：

 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 输出：-1
 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
 示例 3：

 输入：nums1 = [6,6], nums2 = [1]
 输出：3
 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
 - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
 - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
  

 提示：

 1 <= nums1.length, nums2.length <= 105
 1 <= nums1[i], nums2[i] <= 6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/equal-sum-arrays-with-minimum-number-of-operations
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int minOperations(int[] nums1, int[] nums2) {
        // 统计 1-6 出现次数 两个数组 并计算 sum1和 sum2
        int[] count1 = new int[7];
        int[] count2 = new int[7];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum1 += nums1[i];
            count1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            sum2 += nums2[i];
            count2[nums2[i]]++;

        }
        // 计算差值 从 1-6 个数遍历 每次计算 当前是否可以满足 差值达到了 改修改的大小，达到了计数 没达到返回
        int diff = Math.abs(sum1 - sum2);
        int step = 0;
        for (int i = 1; i <= 6; i++) {
            while (count1[i] > 0 || count2[i] > 0) {
                if (diff >= (6 - i)) {
                    diff -= (6-i);
                    if (count1[i] > 0) {
                        count1[i]--;
                    } else {
                        count2[i]--;
                    }
                    step++;
                } else {
                    return step + 1;
                }
            }
        }
        if (diff != 0) {
            return -1;
        }
        return step;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] {
                1,1,1,1,1,1,1
        };
        int[] nums2 = new int[] {
            6
        };
        int i = solution.minOperations(nums1, nums2);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }
}
