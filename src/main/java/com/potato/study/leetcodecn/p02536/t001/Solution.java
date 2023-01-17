package com.potato.study.leetcodecn.p02536.t001;


/**
 * 2536. 子矩阵元素加 1
 *
 * 给你一个正整数 n ，表示最初有一个 n x n 、下标从 0 开始的整数矩阵 mat ，矩阵中填满了 0 。
 *
 * 另给你一个二维整数数组 query 。针对每个查询 query[i] = [row1i, col1i, row2i, col2i] ，请你执行下述操作：
 *
 * 找出 左上角 为 (row1i, col1i) 且 右下角 为 (row2i, col2i) 的子矩阵，将子矩阵中的 每个元素 加 1 。也就是给所有满足 row1i <= x <= row2i 和 col1i <= y <= col2i 的 mat[x][y] 加 1 。
 * 返回执行完所有操作后得到的矩阵 mat 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 3, queries = [[1,1,2,2],[0,0,1,1]]
 * 输出：[[1,1,0],[1,2,1],[0,1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵、执行完第二个操作后的矩阵。
 * - 第一个操作：将左上角为 (1, 1) 且右下角为 (2, 2) 的子矩阵中的每个元素加 1 。
 * - 第二个操作：将左上角为 (0, 0) 且右下角为 (1, 1) 的子矩阵中的每个元素加 1 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 2, queries = [[0,0,1,1]]
 * 输出：[[1,1],[1,1]]
 * 解释：上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵。
 * - 第一个操作：将矩阵中的每个元素加 1 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 500
 * 1 <= queries.length <= 104
 * 0 <= row1i <= row2i < n
 * 0 <= col1i <= col2i < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/increment-submatrices-by-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] rangeAddQueries(int n, int[][] queries) {
        // 二位查分数组 前缀是 左上角的和
        int[][] diff = new int[n][n];
        // 遍历 queries 设置
        for (int[] query : queries) {
            int row1i = query[0];
            int col1i = query[1];
            int row2i = query[2];
            int col2i = query[3];

            diff[row1i][col1i] += 1;
            // 右上角
            if (col2i + 1 < n) {
                diff[row1i][col2i+1] -= 1;
            }
            // 左下角落
            if (row2i + 1 < n) {
                diff[row2i+1][col1i] -= 1;
            }
            // 右下角
            if (col2i + 1 < n && row2i + 1 < n) {
                diff[row2i+1][col2i+1] += 1;
            }
        }
        // 重新遍历 生成前缀
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    diff[i][j] += diff[i][j-1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    diff[j][i] += diff[j-1][i];
                }
            }
        }
        return diff;
    }


}
