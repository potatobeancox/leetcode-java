package com.potato.study.leetcodecn.p00321.t001;

import java.util.Stack;

import org.junit.Assert;

/**
 * 321. 拼接最大数
 *
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 *
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 *
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 *
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class  Solution {


    /**
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // 遍历长度  1-k
        int[] max = null;
        for (int i = 1; i <= nums1.length; i++) {
            // i 表示 nums1 中 最大i个数字串
            int[] max1 = getMaxNumberSubString(nums1, i);
            // 先对 num1 和 num2 找到 最长的 n个子序列
            int[] max2 = getMaxNumberSubString(nums2, k-i);
            // 对于 上面求的 num1和 num2 需求 merge 一下
            int[] current = merge(max1, max2);
            // 比较并更新 merge 结果
            if (compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

    private int[] merge(int[] max1, int[] max2) {
        return new int[0];
    }

    /**
     * 比较两个相同长度的数组 -1 前边的小 0 相等 1前面的大
     * @param current
     * @param max
     * @return
     */
    private int compare(int[] current, int[] max) {
        return 1;
    }

    /**
     * 获取 nums1
     * @param nums1
     * @param i
     * @return
     */
    private int[] getMaxNumberSubString(int[] nums1, int i) {
        return new int[0];
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//        boolean validSerialization = solution.isValidSerialization(str);
//        System.out.println(validSerialization);
//        Assert.assertEquals(true, validSerialization);
//
//        str = "1,#";
//        validSerialization = solution.isValidSerialization(str);
//        System.out.println(validSerialization);
//        Assert.assertEquals(false, validSerialization);
//
//    }
}
