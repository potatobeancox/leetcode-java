package com.potato.study.leetcodecn.p00688.t001;

/**
 * 688. 骑士在棋盘上的概率
 *
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 *
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 *
 *
 *
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 *
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 *
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 *
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *  
 *
 * 提示:
 *
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public double knightProbability(int n, int k, int row, int column) {
        // 按照 8 个方向 计算概率
        int[][] direction = new int[][] {
                {1, 2},
                {-1, 2},
                {1, -2},
                {-1, -2},
                {2, 1},
                {2, -1},
                {-2, 1},
                {-2, -1}
        };
        // dp step ij 从ij 出发 走了 step 还在棋盘上 的概率
        double[][][] dp = new double[k+1][n][n];
        // 最开始 ij 0 就是 1
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                        continue;
                    }
                    for (int l = 0; l < direction.length; l++) {
                        int di = i + direction[l][0];
                        int dj = j + direction[l][1];
                        if (di < 0 || di >= n || dj < 0 || dj >= n) {
                            continue;
                        }
                        dp[step][i][j] += dp[step-1][di][dj] / 8.0;
                    }
                }
            }
        }
        return dp[k][row][column];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int k = 2;
        int row = 0;
        int column = 0;
        double v = solution.knightProbability(n, k, row, column);
        System.out.println(v);

    }
}
