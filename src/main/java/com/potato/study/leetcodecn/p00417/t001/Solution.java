package com.potato.study.leetcodecn.p00417.t001;

import java.util.List;

import org.junit.Assert;

/**
 * 417. 太平洋大西洋水流问题
 *
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *  
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *  
 *
 * 示例：
 *
 *  
 *
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Solution {












    // 417
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // dp 从 第一个开始 往右下
        boolean[][] dp1 = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp1[i][j] = true;
                    continue;
                }
                if (heights[i][j] >= heights[i-1][j]) {
                    dp1[i][j] |= dp1[i-1][j];
                }
                if (heights[i][j] >= heights[i][j-1]) {
                    dp1[i][j] |= dp1[i][j-1];
                }
            }
        }

        boolean[][] dp2 = new boolean[heights.length][heights[0].length];
        for (int i = heights.length - 1; i >= 0; i--) {
            for (int j = heights[0].length - 1; j < heights[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp2[i][j] = true;
                    continue;
                }
                if (heights[i][j] >= heights[i+1][j]) {
                    dp2[i][j] |= dp2[i+1][j];
                }
                if (heights[i][j] >= heights[i][j+1]) {
                    dp2[i][j] |= dp2[i][j+1];
                }
            }
        }

        // dp 从最后一个位置开始 往坐上
        return null;
    }
}
