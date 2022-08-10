package com.potato.study.leetcodecn.p00562.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 562. 矩阵中最长的连续1线段
 *
 * 给定一个 m x n 的二进制矩阵 mat ，返回矩阵中最长的连续1线段。
 *
 * 这条线段可以是水平的、垂直的、对角线的或者反对角线的。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
 * 输出: 3
 * 示例 2:
 *
 *
 *
 * 输入: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
 * 输出: 4
 *  
 *
 * 提示:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] 不是 0 就是 1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-line-of-consecutive-one-in-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int longestLine(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        // 水平的、
        int[][] dp1 = new int[n][m];
        // 垂直的、对角线的或者反对角线的。
        int[][] dp2 = new int[n][m];
        // 对角线的
        int[][] dp3 = new int[n][m];
        // 反对角线的。
        int[][] dp4 = new int[n][m];
        // 从左上往右下 以每个位置作为最后一个位置 的 最大连续长度
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                if (j > 0) {
                    dp1[i][j] = 1 + dp1[i][j-1];
                } else {
                    dp1[i][j] = 1;
                }
                // 垂直的
                if (i > 0) {
                    dp2[i][j] = 1 + dp2[i-1][j];
                } else {
                    dp2[i][j] = 1;
                }
                // 对角线的
                if (i > 0 && j > 0) {
                    dp3[i][j] = 1 + dp3[i-1][j-1];
                } else {
                    dp3[i][j] = 1;
                }
                // 反对角线的
                if (i > 0 && j < m-1) {
                    dp4[i][j] = 1 + dp4[i-1][j+1];
                } else {
                    dp4[i][j] = 1;
                }

                maxLength = Math.max(maxLength, dp1[i][j]);
                maxLength = Math.max(maxLength, dp2[i][j]);
                maxLength = Math.max(maxLength, dp3[i][j]);
                maxLength = Math.max(maxLength, dp4[i][j]);

            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
        int[][] mat = new int[][]{
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}
        };
        int i = solution.longestLine(mat);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }

}
