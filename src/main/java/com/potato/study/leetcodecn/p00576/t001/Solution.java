package com.potato.study.leetcodecn.p00576.t001;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 576. 出界的路径数
 *
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 *
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 * 示例 2：
 *
 *
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/out-of-boundary-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 576
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // dp ijk 走了i步 达到jk 点的种类数
        int[][][] dp = new int[maxMove+1][m][n];
        // 开始点 不用走 只有一种可能
        dp[0][startRow][startColumn] = 1;
        // 遍历每个位置 如果当前大于 0 往四个方向上加 如果 四个方向出界了 就改成 往结果集合上加
        int[][] directions = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        long totalPath = 0;
        int mod = 1_000_000_000 + 7;
        // (a + b) % p = (a % p + b % p) % p
        for (int i = 1; i <= maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (dp[i-1][j][k] == 0) {
                        continue;
                    }
                    // 四个方向
                    for (int l = 0; l < 4; l++) {
                        int dj = j + directions[l][0];
                        int dk = k + directions[l][1];
                        // 找到了 出界的方式
                        if (dj < 0 || dj >= m
                                || dk < 0 || dk >= n) {
                            totalPath += dp[i-1][j][k];
                            totalPath %= mod;
                            continue;
                        }
                        dp[i][dj][dk] += dp[i-1][j][k];
                        dp[i][dj][dk] %= mod;
                    }
                }
            }
        }
        return (int)totalPath;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 2;
        int n = 2;
        int maxMove = 2;
        int startRow = 0;
        int startColumn = 0;
        int paths = solution.findPaths(m, n, maxMove, startRow, startColumn);
        System.out.println(paths);
        Assert.assertEquals(6, paths);



        m = 1;
        n = 3;
        maxMove = 3;
        startRow = 0;
        startColumn = 1;
        paths = solution.findPaths(m, n, maxMove, startRow, startColumn);
        System.out.println(paths);
        Assert.assertEquals(12, paths);
    }

}
