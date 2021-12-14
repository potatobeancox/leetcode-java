package com.potato.study.leetcodecn.p00329.t001;

import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.util.ListNodeUtil;

/**
 * 329. 矩阵中的最长递增路径
 *
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * 示例 2：
 *
 *
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 示例 3：
 *
 * 输入：matrix = [[1]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int[][] direction;

    public int longestIncreasingPath(int[][] matrix) {
        direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // 329 从每个点开始 dfs 并更新 最长点 如果当前点最长 说明
        int[][] pathLen = new int[matrix.length][matrix[0].length];
        int maxLen = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int thisLen = dfs(matrix, pathLen, i, j);
                maxLen = Math.max(maxLen, thisLen);
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int[][] pathLen, int i, int j) {
        if (pathLen[i][j] > 0) {
            return pathLen[i][j];
        }
        // 至少为 1
        pathLen[i][j] = 1;
        // 四个方向
        for (int k = 0; k < direction.length; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            if (di < 0 || di >= matrix.length || dj < 0 || dj >= matrix[0].length) {
                continue;
            }
            // 不是递增
            if (matrix[di][dj] <= matrix[i][j]) {
                continue;
            }
            pathLen[i][j] = Math.max(pathLen[i][j], dfs(matrix, pathLen, di, dj) + 1);
        }
        return pathLen[i][j];
    }
}
