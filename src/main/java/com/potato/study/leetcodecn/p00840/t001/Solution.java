package com.potato.study.leetcodecn.p00840.t001;

import com.google.common.collect.Lists;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 840. 矩阵中的幻方
 *
 * 3 x 3 的幻方是一个填充有 从 1 到 9  的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

 给定一个由整数组成的row x col 的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

  

 示例 1：



 输入: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]
 输出: 1
 解释:
 下面的子矩阵是一个 3 x 3 的幻方：

 而这一个不是：

 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 示例 2:

 输出: grid = [[8]]
 输入: 0
  

 提示:

 row == grid.length
 col == grid[i].length
 1 <= row, col <= 10
 0 <= grid[i][j] <= 15

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/magic-squares-in-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 840
    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        // 枚举每个中心点 检验是不是 5
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (isMagicSquare(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 以 ij 为中心 是否满足条件
     * @param grid
     * @param i
     * @param j
     * @return
     */
    private boolean isMagicSquare(int[][] grid, int i, int j) {
        if (grid[i][j] != 5) {
            return false;
        }
        // 上下 左右 对角线 相等
        if (grid[i-1][j] + grid[i+1][j] != 10
                || grid[i][j-1] + grid[i][j+1] != 10
                || grid[i-1][j-1] + grid[i+1][j+1] != 10
                || grid[i+1][j-1] + grid[i-1][j+1] != 10) {
            return false;
        }
        // 每行 每列 都是 15
        if (grid[i-1][j-1] + grid[i-1][j] + grid[i-1][j+1] != 15
                || grid[i+1][j-1] + grid[i+1][j] + grid[i+1][j+1] != 15
                || grid[i-1][j-1] + grid[i][j-1] + grid[i+1][j-1] != 15
                || grid[i-1][j+1] + grid[i][j+1] + grid[i+1][j+1] != 15) {
            return false;
        }

        // 1-9
        int[] count = new int[10];
        for (int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                if (grid[k][l] < 1 || grid[k][l] > 9) {
                    return false;
                }
                count[grid[k][l]]++;
            }
        }
        for (int k = 1; k <= 9; k++) {
            if (count[k] != 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[4,3,8,4],[9,5,1,9],[2,7,6,2]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.numMagicSquaresInside(ints);
        System.out.println(i);
        Assert.assertEquals(1, i);


        input = "[[4,7,8],[9,5,1],[2,3,6]]";
        ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.numMagicSquaresInside(ints);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
