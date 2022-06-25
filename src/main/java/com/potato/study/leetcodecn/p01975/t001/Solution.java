package com.potato.study.leetcodecn.p01975.t001;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1975. 最大方阵和
 *
 * 给你一个 n x n 的整数方阵 matrix 。你可以执行以下操作 任意次 ：
 *
 * 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。
 * 如果两个元素有 公共边 ，那么它们就是 相邻 的。
 *
 * 你的目的是 最大化 方阵元素的和。请你在执行以上操作之后，返回方阵的 最大 和。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,-1],[-1,1]]
 * 输出：4
 * 解释：我们可以执行以下操作使和等于 4 ：
 * - 将第一行的 2 个元素乘以 -1 。
 * - 将第一列的 2 个元素乘以 -1 。
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * 输出：16
 * 解释：我们可以执行以下操作使和等于 16 ：
 * - 将第二行的最后 2 个元素乘以 -1 。
 *  
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -105 <= matrix[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-matrix-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1975
    public long maxMatrixSum(int[][] matrix) {
        // 数学 如多有 0 说明都可以按照 绝对值 最大 的来
        int zeroCount = 0;
        int negativeCount = 0;
        int minAbsoluteValue = Math.abs(matrix[0][0]);
        long totalAbsoluteValueSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                totalAbsoluteValueSum += Math.abs(matrix[i][j]);
                if (matrix[i][j] == 0) {
                    zeroCount++;
                } else if (matrix[i][j] < 0) {
                    negativeCount++;
                    minAbsoluteValue = Math.min(minAbsoluteValue, Math.abs(matrix[i][j]));
                } else {
                    minAbsoluteValue = Math.min(minAbsoluteValue, Math.abs(matrix[i][j]));
                }
            }
        }
        // 如果有 偶数个负数，直接 返回绝对值和
        if (zeroCount > 0 || negativeCount % 2 == 0) {
            return totalAbsoluteValueSum;
        }
        // 如果只有奇数 个 找到最小的绝对值 - 2倍
        return totalAbsoluteValueSum - 2 * minAbsoluteValue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2,3],[-1,-2,-3],[1,2,3]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        long l = solution.maxMatrixSum(ints);
        System.out.println(l);
        Assert.assertEquals(16, l);
    }

}
