package com.potato.study.leetcodecn.p00931.t001;

import org.junit.Assert;

/**
 * 931. 下降路径最小和
 *
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。

 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。

  

 示例 1：

 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 输出：13
 解释：下面是两条和最小的下降路径，用加粗+斜体标注：
 [[2,1,3],      [[2,1,3],
 [6,5,4],       [6,5,4],
 [7,8,9]]       [7,8,9]]
 示例 2：

 输入：matrix = [[-19,57],[-40,-5]]
 输出：-59
 解释：下面是一条和最小的下降路径，用加粗+斜体标注：
 [[-19,57],
 [-40,-5]]
 示例 3：

 输入：matrix = [[-48]]
 输出：-48
  

 提示：

 n == matrix.length
 n == matrix[i].length
 1 <= n <= 100
 -100 <= matrix[i][j] <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minFallingPathSum(int[][] matrix) {
        // dp dpij 代表进行到 ij的下降路径最小和
        int[][] dp = new int[matrix.length][matrix[0].length];
        // 0 行处理 是这个value本身
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
        }
        // dp ij = value ij + min {dp i-1, j-1 ,, dp i-1, j,, dp i-1, j+1}
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                int min = dp[i-1][j];
                if (j != 0) {
                    min = Math.min(min, dp[i-1][j-1]);
                }
                if (j != matrix[0].length - 1) {
                    min = Math.min(min, dp[i-1][j+1]);
                }
                dp[i][j] = matrix[i][j] + min;
            }
        }
        // 遍历最后一行 找到最小值
        int minFalling = dp[matrix.length - 1][0];
        for (int i = 0; i < matrix[0].length; i++) {
            minFalling = Math.min(minFalling, dp[matrix.length - 1][i]);
        }
        return minFalling;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
                {2,1,3},
                {6,5,4},
                {7,8,9}
        };
        int i = solution.minFallingPathSum(matrix);
        System.out.println(i);
        Assert.assertEquals(13, i);
    }


}
