package com.potato.study.leetcodecn.other.Interview.p0016.p0006;


import java.util.Arrays;

/**
 * 面试题 16.06. 最小差
 *
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 *
 *  
 *
 * 示例：
 *
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 *  
 *
 * 提示：
 *
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-difference-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16.06
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        // 找小的往前挪
        int i = 0;
        int j = 0;
        long minDiff = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            long diff = (long) a[i] - b[j];
            if (diff == 0) {
                return 0;
            }
            minDiff = Math.min(minDiff, Math.abs(diff));
            if (diff > 0) {
                // i大 看看 j大点行不行
                j++;
            } else {
                i++;
            }
        }
        return (int) minDiff;
    }
}
