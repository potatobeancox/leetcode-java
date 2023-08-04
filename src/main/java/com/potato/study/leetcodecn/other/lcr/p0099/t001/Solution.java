package com.potato.study.leetcodecn.other.lcr.p0099.t001;

/**
 * 剑指 Offer II 099. 最小路径之和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

 说明：一个机器人每次只能向下或者向右移动一步。

  

 示例 1：



 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 输出：7
 解释：因为路径 1→3→1→1→1 的总和最小。
 示例 2：

 输入：grid = [[1,2,3],[4,5,6]]
 输出：12
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 200
 0 <= grid[i][j] <= 100


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/0i0mDW
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 处理首行首列
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j-1];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = grid[i][j];
                    dp[i][j] = grid[i][j] + dp[i-1][j];
                    continue;
                }
                dp[i][j] = grid[i][j] + Math.min(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
