package com.potato.study.leetcodecn.p01277.t001;



import org.junit.Assert;


/**
 * 1277. 统计全为 1 的正方形子矩阵
 *
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 使用 dp 进行求解
     * dp ij 记录 以ij 点作为右下角的 正方形的最大边长，最大变成就等于 矩阵个数
     *
     * 那么
     * 如果 ij 位置是0 dpij = 0
     * 否则 dp = min {dpi-1 j, dpi, j-1, dp j-1,j-i} + 1
     * 画图就能看出来了
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j-1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                    dp[i][j] += 1;
                }
                // 计算个数
                count += dp[i][j];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        int res = solution.countSquares(matrix);
        System.out.println(res);
        Assert.assertEquals(15, res);
    }
}
