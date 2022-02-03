package com.potato.study.leetcodecn.p01895.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1895. 最大的幻方
 *
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。

 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。

  

 示例 1：


 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 输出：3
 解释：最大幻方尺寸为 3 。
 每一行，每一列以及两条对角线的和都等于 12 。
 - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 - 对角线的和：5+4+3 = 6+4+2 = 12
 示例 2：


 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 输出：2
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 50
 1 <= grid[i][j] <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-magic-square
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/largest-magic-square/solution/doocskai-yuan-she-qu-qian-zhui-he-bu-yao-yk38/
     * @param grid
     * @return
     */
    public int largestMagicSquare(int[][] grid) {
        // 用个矩阵 计算前缀和 行 列
        int[][] prefixRow = new int[grid.length][grid[0].length + 1];
        int[][] prefixColumn = new int[grid.length + 1][grid[0].length];
        // 最开始的 prefixRow i0 表示一个结点没有的值
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 每一行的累加
                prefixRow[i][j+1] = prefixRow[i][j] + grid[i][j];
                // 每一列的累加
                prefixColumn[i+1][j] = prefixColumn[i][j] + grid[i][j];
            }
        }
        // 用一个 变量枚举 长度k  内部 两重循环找到开始点的 xy 坐标 对于起点和终点 比较一下是否可以 对角线和 横竖一致
        int max = 0;
        for (int k = Math.min(grid.length, grid[0].length); k >= 1; k--) {
            // 枚举每个开始位置
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    // 坐标合法性
                    if (i + k - 1 >= grid.length || j + k - 1 >= grid[0].length) {
                        continue;
                    }
                    if (check(i, j, i + k - 1, j + k - 1, grid, prefixRow, prefixColumn)) {
                        max = Math.max(k, max);
                    }
                }
            }
        }
        return max;
    }


    /**
     * 判断 grid 的 i1 i2  i2， j2 是否时 最大幻方
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     * @return
     */
    private boolean check(int i1, int j1, int i2, int j2, int[][] grid,
                          int[][] prefixRow, int[][] prefixColumn) {
        // 行 值
        int targetSum = prefixRow[i1][j2+1] - prefixRow[i1][j1];
        for (int i = i1; i <= i2; i++) {
            if (targetSum != prefixRow[i][j2+1] - prefixRow[i][j1]) {
                return false;
            }
        }
        // 列 值
        for (int j = j1; j <= j2; j++) {
            if (targetSum != prefixColumn[i2+1][j] - prefixColumn[i1][j]) {
                return false;
            }
        }
        // 行列是否相等
        int index1 = i1;
        int index2 = j1;
        int tmpSum = 0;
        // 正对角线
        while (index1 <= i2 && index2 <= j2) {
            tmpSum += grid[index1][index2];
            index1++;
            index2++;
        }
        if (tmpSum != targetSum) {
            return false;
        }
        // 对角线累计值
        index1 = i1;
        index2 = j2;
        tmpSum = 0;
        // 负对角线
        while (index1 <= i2 && index2 >= j1) {
            tmpSum += grid[index1][index2];

            index1++;
            index2--;
        }
        if (tmpSum != targetSum) {
            return false;
        }
        // 所有边和对角线都相等
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]";
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.largestMagicSquare(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(3, i);



        input = "[[5,1,3,1],[9,3,3,1],[1,3,3,8]]";
        arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.largestMagicSquare(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }


}
