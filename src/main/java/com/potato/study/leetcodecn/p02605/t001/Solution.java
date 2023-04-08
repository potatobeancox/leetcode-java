package com.potato.study.leetcodecn.p02605.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * 2605. 从两个数字数组里生成最小数字
 *
 * 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [4,1,3], nums2 = [5,7]
 * 输出：15
 * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
 * 示例 2：
 *
 * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
 * 输出：3
 * 解释：数字 3 的数位 3 在两个数组中都出现了。
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * 每个数组中，元素 互不相同 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/form-smallest-number-from-two-digit-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2605
    public int minNumber(int[] nums1, int[] nums2) {
        // 1-9
        int[] count1 = new int[9];
        for (int n1 : nums1) {
            count1[n1-1]++;
        }
        int[] count2 = new int[9];
        for (int n2 : nums2) {
            count2[n2-1]++;
        }
        // 1-9 遍历奇数器 找到最小的值
        int first1 = -1;
        int first2 = -1;
        for (int i = 0; i < 9; i++) {
            if (count1[i] != 0 && count2[i] != 0) {
                return i+1;
            }
            if (first1 == -1 && count1[i] != 0) {
                first1 = i+1;
            }
            if (first2 == -1 && count2[i] != 0) {
                first2 = i+1;
            }
        }
        // 否则小的 * 10 + 大的
        int min = Math.min(first1, first2);
        int max = Math.max(first1, first2);

        return min * 10 + max;
    }


}
