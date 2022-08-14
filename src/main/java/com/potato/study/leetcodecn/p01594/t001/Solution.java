package com.potato.study.leetcodecn.p01594.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1594. 矩阵的最大非负积
 *
 * 给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。

 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。

 返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。

 注意，取余是在得到最大积之后执行的。

  

 示例 1：

 输入：grid = [[-1,-2,-3],
              [-2,-3,-3],
              [-3,-3,-2]]
 输出：-1
 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
 示例 2：

 输入：grid = [[1,-2,1],
              [1,-2,1],
              [3,-4,1]]
 输出：8
 解释：最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 = 8)
 示例 3：

 输入：grid = [[1, 3],
              [0,-4]]
 输出：0
 解释：最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0)
 示例 4：

 输入：grid = [[ 1, 4,4,0],
              [-2, 0,0,1],
              [ 1,-1,1,1]]
 输出：2
 解释：最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 * -1 * 1 * 1 = 2)
  

 提示：

 1 <= rows, cols <= 15
 -4 <= grid[i][j] <= 4

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxProductPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][] min = new long[n][m];
        long[][] max = new long[n][m];

        min[0][0] = grid[0][0];
        max[0][0] = grid[0][0];
        // 初始
        for (int i = 1; i < grid[0].length; i++) {
            min[0][i] = Math.min(grid[0][i] * min[0][i-1], grid[0][i] * max[0][i-1]);
            max[0][i] = Math.max(grid[0][i] * min[0][i-1], grid[0][i] * max[0][i-1]);
        }
        for (int i = 1; i < grid.length; i++) {
            min[i][0] = Math.min(grid[i][0] * min[i-1][0], grid[i][0] * max[i-1][0]);
            max[i][0] = Math.max(grid[i][0] * min[i-1][0], grid[i][0] * max[i-1][0]);
        }
        // 转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                min[i][j] = Math.min(grid[i][j] * min[i][j-1], grid[i][j] * min[i-1][j]);
                min[i][j] = Math.min(min[i][j], grid[i][j] * max[i-1][j]);
                min[i][j] = Math.min(min[i][j], grid[i][j] * max[i][j-1]);

                max[i][j] = Math.max(grid[i][j] * min[i][j-1], grid[i][j] * min[i-1][j]);
                max[i][j] = Math.max(max[i][j], grid[i][j] * max[i-1][j]);
                max[i][j] = Math.max(max[i][j], grid[i][j] * max[i][j-1]);
            }
        }

        if (max[n-1][m-1] < 0) {
            return -1;
        }
        int mod = 1_000_000_000 + 7;
        return (int)(max[n-1][m-1] % mod);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.maxProductPath(ints);
        System.out.println(i);
        Assert.assertEquals(-1, i);
    }

}
