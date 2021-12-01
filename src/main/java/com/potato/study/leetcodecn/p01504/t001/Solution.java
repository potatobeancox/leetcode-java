package com.potato.study.leetcodecn.p01504.t001;

import java.util.Arrays;

/**
 * 1504. 统计全 1 子矩形
 *
 * 给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。

  

 示例 1：

 输入：mat = [[1,0,1],
             [1,1,0],
             [1,1,0]]
 输出：13
 解释：
 有 6 个 1x1 的矩形。
 有 2 个 1x2 的矩形。
 有 3 个 2x1 的矩形。
 有 1 个 2x2 的矩形。
 有 1 个 3x1 的矩形。
 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
 示例 2：

 输入：mat = [[0,1,1,0],
             [0,1,1,1],
             [1,1,1,0]]
 输出：24
 解释：
 有 8 个 1x1 的子矩形。
 有 5 个 1x2 的子矩形。
 有 2 个 1x3 的子矩形。
 有 4 个 2x1 的子矩形。
 有 2 个 2x2 的子矩形。
 有 2 个 3x1 的子矩形。
 有 1 个 3x2 的子矩形。
 矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
 示例 3：

 输入：mat = [[1,1,1,1,1,1]]
 输出：21
 示例 4：

 输入：mat = [[1,0,1],[0,1,0],[1,0,1]]
 输出：5
  

 提示：

 1 <= rows <= 150
 1 <= columns <= 150
 0 <= mat[i][j] <= 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-submatrices-with-all-ones
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numSubmat(int[][] mat) {
        // 使用 一个 row ij 标识 以ij为最后一个节点向左最长可以延续的1的长度
        int[][] row = new int[mat.length][mat[0].length];
        // 遍历 mat 生成结果
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    row[i][j] = 1;
                    if (j > 0) {
                        row[i][j] += row[i][j-1];
                    }
                }
            }
        }
        // 每次 从 i开始 往上找 直到0 找过程中 最小的长度，加上这个长度 （多少中开始的可能）
        int totalCount = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // 上面控制 右下角
                if (row[i][j] == 0) {
                    continue;
                }
                int minColumnLength = row[i][j];
                for (int k = i; k >= 0; k--) {
                    minColumnLength = Math.min(minColumnLength, row[k][j]);
                    totalCount += minColumnLength;
                }
            }
        }
        return totalCount;
    }

}
