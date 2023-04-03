package com.potato.study.leetcodecn.other.Interview.p0016.p0021;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 面试题 16.21. 交换和
 *
 * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 *
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 * 示例:
 *
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 * 提示：
 *
 * 1 <= array1.length, array2.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-swap-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16。21
    public int[] findSwapValues(int[] array1, int[] array2) {
        // 用set 记录一侧 并且求 总sum 之间的差 看看
        Set<Integer> set = new HashSet<>();
        int sum1 = 0;
        for (int ele : array1) {
            set.add(ele);
            sum1 += ele;
        }
        int sum2 = 0;
        for (int ele : array2) {
            sum2 += ele;
        }
        int diff = sum1 - sum2;
        if (diff % 2 != 0) {
            return new int[0];
        }
        // a = (sum1 - sum2) / 2 + b
        for (int ele : array2) {
            int target = diff / 2 + ele;
            if (set.contains(target)) {
                return new int[] {target, ele};
            }
        }
        return new int[0];
    }
}
