package com.potato.study.leetcodecn.p02128.t001;

import java.util.PriorityQueue;

/**
 * 2128. 通过翻转行或列来去除所有的 1
 *
 * 给你一个大小为 m x n 的二进制矩阵 grid。
 *
 * 每次操作，你可以选择 任意 一行 或者 一列，然后将其中的所有值翻转（0 变成 1， 1变成 0）。
 *
 * 如果经过 任意次 操作，你能将矩阵中所有的 1 去除，那么返回 true；否则返回 false。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: grid = [[0,1,0],[1,0,1],[0,1,0]]
 * 输出: true
 * 解释: 一种去除所有 1 的可行方法是:
 * - 翻转矩阵的中间的行
 * - 翻转矩阵的中间的列
 * 示例 2:
 *
 *
 * 输入: grid = [[1,1,0],[0,0,0],[0,0,0]]
 * 输出: false
 * 解释: 不可能去除矩阵中所有的 1。
 * 示例 3:
 *
 *
 * 输入: grid = [[0]]
 * 输出: true
 * 解释: 矩阵中不存在 1，已经符合要求。
 *  
 *
 * 提示:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 是 0 或者 1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-all-ones-with-row-and-column-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean removeOnes(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            if (!isSame(grid[0], grid[i]) && !isXorSame(grid[0], grid[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否 01全不相等
     * @param line
     * @param otherLine
     * @return
     */
    private boolean isXorSame(int[] line, int[] otherLine) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] == otherLine[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isSame(int[] line, int[] otherLine) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] != otherLine[i]) {
                return false;
            }
        }
        return true;
    }
}
