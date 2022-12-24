package com.potato.study.leetcodecn.p02510.t001;

import org.junit.Assert;

/**
 * 2510. Check if There is a Path With Equal Number of 0's And 1's
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells (row + 1, col) or (row, col + 1).

 Return true if there is a path from (0, 0) to (m - 1, n - 1) that visits an equal number of 0's and 1's. Otherwise return false.

  

 Example 1:


 Input: grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
 Output: true
 Explanation: The path colored in blue in the above diagram is a valid path because we have 3 cells with a value of 1 and 3 with a value of 0. Since there is a valid path, we return true.
 Example 2:


 Input: grid = [[1,1,0],[0,0,1],[1,0,0]]
 Output: false
 Explanation: There is no path in this grid with an equal number of 0's and 1's.
  

 Constraints:

 m == grid.length
 n == grid[i].length
 2 <= m, n <= 100
 grid[i][j] is either 0 or 1.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isThereAPath(int[][] grid) {
        // 最多有多少个1 或者多少个0 一行 一列，转角
        int maxLen = grid.length + grid[0].length - 1;
        // 三维状态数据 dp ij k 到达ij 1的数量 - 0的数量 + maxLen 是否是可达的
        boolean[][][] dp = new boolean[grid.length][grid[0].length][2 * maxLen + 1];
        // 处理第一个点
        int base = maxLen;
        if (grid[0][0] == 1) {
            base++;
        } else {
            base--;
        }
        dp[0][0][base] = true;
        // ij 从0 开始 k 从0 到 2 * maxLen 计算可达性
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < 2 * maxLen; k++) {
                    if (i > 0 && dp[i-1][j][k]) {
                        if (grid[i][j] == 1) {
                            dp[i][j][k+1] = true;
                        } else {
                            dp[i][j][k-1] = true;
                        }
                    }

                    if (j > 0 && dp[i][j-1][k]) {
                        if (grid[i][j] == 1) {
                            dp[i][j][k+1] = true;
                        } else {
                            dp[i][j][k-1] = true;
                        }
                    }
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1][maxLen];
    }



}
