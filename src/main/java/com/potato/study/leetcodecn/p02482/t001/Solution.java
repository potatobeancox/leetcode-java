package com.potato.study.leetcodecn.p02482.t001;

/**
 * 2482. 行和列中一和零的差值
 *
 * 给你一个下标从 0 开始的 m x n 二进制矩阵 grid 。

 我们按照如下过程，定义一个下标从 0 开始的 m x n 差值矩阵 diff ：

 令第 i 行一的数目为 onesRowi 。
 令第 j 列一的数目为 onesColj 。
 令第 i 行零的数目为 zerosRowi 。
 令第 j 列零的数目为 zerosColj 。
 diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
 请你返回差值矩阵 diff 。

  

 示例 1：



 输入：grid = [[0,1,1],[1,0,1],[0,0,1]]
 输出：[[0,0,4],[0,0,4],[-2,-2,2]]
 解释：
 - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 2 + 1 - 1 - 2 = 0
 - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 2 + 1 - 1 - 2 = 0
 - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 2 + 3 - 1 - 0 = 4
 - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 2 + 1 - 1 - 2 = 0
 - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 2 + 1 - 1 - 2 = 0
 - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 2 + 3 - 1 - 0 = 4
 - diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 = 1 + 1 - 2 - 2 = -2
 - diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 = 1 + 1 - 2 - 2 = -2
 - diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 = 1 + 3 - 2 - 0 = 2
 示例 2：



 输入：grid = [[1,1,1],[1,1,1]]
 输出：[[5,5,5],[5,5,5]]
 解释：
 - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 3 + 2 - 0 - 0 = 5
 - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 3 + 2 - 0 - 0 = 5
 - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 3 + 2 - 0 - 0 = 5
 - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 3 + 2 - 0 - 0 = 5
 - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 3 + 2 - 0 - 0 = 5
 - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 3 + 2 - 0 - 0 = 5
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 105
 1 <= m * n <= 105
 grid[i][j] 要么是 0 ，要么是 1 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/difference-between-ones-and-zeros-in-row-and-column
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] onesMinusZeros(int[][] grid) {
        // 开一个数组 存储 行中 0的和 减去1的个数
        int rowCount = grid.length;
        int[] row = new int[rowCount];
        // 开一个数组 存储 列中 0的和 减去1的个数
        for (int i = 0; i < grid.length; i++) {
            // 统计1个个数
            int oneCount = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    oneCount++;
                }
            }
            row[i] = oneCount - (grid[0].length - oneCount);
        }
        int colCount = grid[0].length;
        int[] col = new int[colCount];
        // 开一个数组 存储 列中 0的和 减去1的个数
        for (int i = 0; i < grid[0].length; i++) {
            // 统计1个个数
            int oneCount = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) {
                    oneCount++;
                }
            }
            col[i] = oneCount - (grid.length - oneCount);
        }
        // diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
        int[][] diff = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                diff[i][j] = row[i] + col[j];
            }
        }
        return diff;
    }
}
