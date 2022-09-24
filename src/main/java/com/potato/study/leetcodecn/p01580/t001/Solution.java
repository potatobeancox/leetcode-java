package com.potato.study.leetcodecn.p01580.t001;

/**
 * 1582. 二进制矩阵中的特殊位置
 *
 * 给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。

 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。

  

 示例 1：

 输入：mat = [[1,0,0],
             [0,0,1],
             [1,0,0]]
 输出：1
 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 示例 2：

 输入：mat = [[1,0,0],
             [0,1,0],
             [0,0,1]]
 输出：3
 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 示例 3：

 输入：mat = [[0,0,0,1],
             [1,0,0,0],
             [0,1,1,0],
             [0,0,0,0]]
 输出：2
 示例 4：

 输入：mat = [[0,0,0,0,0],
             [1,0,0,0,0],
             [0,1,0,0,0],
             [0,0,1,0,0],
             [0,0,0,1,1]]
 输出：3
  

 提示：

 rows == mat.length
 cols == mat[i].length
 1 <= rows, cols <= 100
 mat[i][j] 是 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/special-positions-in-a-binary-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 遍历行列 找到 都是0的行或者列
     * 遍历 mat
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        boolean[] zeroLine = new boolean[mat.length];
        boolean[] zeroCol = new boolean[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            int notZeroCount = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    notZeroCount++;
                }
            }
            zeroLine[i] = notZeroCount == 1;
        }
        for (int j = 0; j < mat[0].length; j++) {
            int notZeroCount = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][j] != 0) {
                    notZeroCount++;
                }
            }
            zeroCol[j] = notZeroCount == 1;
        }
        // 遍历 mat
        int special = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (zeroLine[i] && zeroCol[j] && mat[i][j] == 1) {
                    special++;
                }
            }
        }
        return special;
    }
}
