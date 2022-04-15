package com.potato.study.leetcodecn.p01139.t001;


/**
 * 1139. 最大的以 1 为边界的正方形
 *
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 *
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-1-bordered-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int largest1BorderedSquare(int[][] grid) {
        // 记录每个 点对应的 左边 和 上面 的长度 按照每个点开始 找 对应的 最小值，
        int n = grid.length;
        // 0 是左边 长度 1是上面长度
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 上面
                if (i > 0) {
                    dp[i][j][0] = 1 + dp[i-1][j][0];
                } else {
                    dp[i][j][0] = 1;
                }
                // 左面
                if (j > 0) {
                    dp[i][j][1] = 1 + dp[i][j-1][1];
                } else {
                    dp[i][j][1] = 1;
                }
            }
        }
        // 遍历 每个 开始的点 找到 对应 点的 左边个 上面 是否有足够的长度  需要 逐渐往里边找
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int minLen = Math.min(dp[i][j][0], dp[i][j][1]);
                for (int k = minLen; k > 0; k--) {
                    if (k >= dp[i-k+1][j][1] && k >= dp[i][j-k+1][0]) {
                        max = Math.max(max, k);
                        break;
                    }
                }
            }
        }
        return max;
    }
}
