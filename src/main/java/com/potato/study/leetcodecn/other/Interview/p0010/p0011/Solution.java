package com.potato.study.leetcodecn.other.Interview.p0010.p0011;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 10.11. 峰与谷
 *
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 *
 * 示例:
 *
 * 输入: [5, 3, 1, 2, 3]
 * 输出: [5, 1, 3, 2, 3]
 * 提示：
 *
 * nums.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/peaks-and-valleys-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 10.11
    public void wiggleSort(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(clone);
        // 左右取 一个最大的一个最小的
        int rightIndex = nums.length - 1;
        int leftIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = clone[rightIndex];
                rightIndex--;
            } else {
                nums[i] = clone[leftIndex];
                leftIndex++;
            }
        }
    }
}
