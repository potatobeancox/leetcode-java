package com.potato.study.leetcodecn.p00321.t001;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
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
            if (k-i > nums2.length || k < i) {
                continue;
            }
            int[] max2 = getMaxNumberSubString(nums2, k-i);
            // 对于 上面求的 num1和 num2 需求 merge 一下
            int[] current = merge(max1, max2);
            // 比较并更新 merge 结果
            if (max == null || compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

    /**
     * 将 max1 与 max2 合并 保证合并后的数字最大
     * @param max1
     * @param max2
     * @return
     */
    private int[] merge(int[] max1, int[] max2) {

        if (max1 == null) {
            return max2;
        }
        if (max2 == null) {
            return max1;
        }

        // 合并
        int index1 = 0;
        int index2 = 0;
        int[] res = new int[max1.length + max2.length];
        int index = 0;
        while (index1 < max1.length || index2 < max2.length) {
            if (index1 >= max1.length) {
                res[index++] = max2[index2++];
            } else if (index2 >= max2.length) {
                res[index++] = max1[index1++];
            } else {
                int compare = compare(max1, max2, index1, index2);
                if (compare >= 0) {
                    res[index] = max1[index1];
                    index1++;
                } else {
                    res[index] = max2[index2];
                    index2++;
                }
                index++;
            }
        }
        return res;
    }


    /**
     * 比较两个相同长度的数组 -1 前边的小 0 相等 1前面的大
     * @param array1
     * @param array2
     * @param index1
     * @param index2
     * @return
     */
    private int compare(int[] array1, int[] array2, int index1, int index2) {
        int n1 = array1.length;
        int n2 = array2.length;

        while (index1 < n1 && index2 < n2) {
            int compare = Integer.compare(array1[index1], array2[index2]);
            if (compare != 0) {
                return compare;
            }

            index1++;
            index2++;
        }
        // 比较剩余多少
        return Integer.compare(n1 - index1, n2 - index2);
    }



    /**
     * 比较两个相同长度的数组 -1 前边的小 0 相等 1前面的大
     * @param current
     * @param max
     * @return
     */
    private int compare(int[] current, int[] max) {
        int n = current.length;
        for (int i = 0; i < n; i++) {
            int compare = Integer.compare(current[i], max[i]);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /**
     * 获取 nums1 中长度为i 的最大 子自负串
     * @param nums
     * @param len
     * @return
     */
    private int[] getMaxNumberSubString(int[] nums, int len) {
        if (len == 0) {
            return new int[0];
        }
        // deque
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (deque.isEmpty()) {
                deque.add(nums[i]);
                continue;
            }
            while (!deque.isEmpty()
                    && deque.peekLast() < nums[i]
                    && deque.size() - 1 + nums.length - i >= len) {
                deque.pollLast();
            }
            deque.add(nums[i]);
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        int k = 5;
        int[] ints = solution.maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                9, 8, 6, 5, 3
        }, ints);


        nums1 = new int[]{6, 7};
        nums2 = new int[]{6, 0, 4};
        k = 5;
        ints = solution.maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                6, 7, 6, 0, 4
        }, ints);
    }

}
