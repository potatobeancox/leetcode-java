package com.potato.study.leetcodecn.p02684.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 2684. 矩阵中移动的最大次数
 *
 * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
 *
 * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
 *
 * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
 * 返回你在矩阵中能够 移动 的 最大 次数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * 输出：3
 * 解释：可以从单元格 (0, 0) 开始并且按下面的路径移动：
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * 可以证明这是能够移动的最大次数。
 * 示例 2：
 *
 *
 * 输入：grid = [[3,2,4],[2,1,9],[1,1,7]]
 * 输出：0
 * 解释：从第一列的任一单元格开始都无法移动。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 1 <= grid[i][j] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2684
    public int maxMoves(int[][] grid) {
        // 只能往右边走 +1的方向走 看看最多能走多少步 并且只能往大于当前位置的方向走
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 第一列都设置成 0
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        int maxStep = 0;
        // 遍历当前所在位置 往下一列计算
        for (int i = 0; i < n-1; i++) {
            // n 是列
            for (int j = 0; j < m; j++) {
                // 如果当前位置已经是不可达了 直接continue调吧
                if (dp[j][i] == -1) {
                    continue;
                }
                // 遍历下一列的 3个候选位置
                // j-1 j 和 j+1 i-1 三个位置部位-1的最大的 + 1
                for (int k = Math.max(0, j-1); k <= Math.min(m-1, j+1); k++) {
                    if (grid[k][i+1] <= grid[j][i]) {
                        continue;
                    }
                    // 严格大于
                    dp[k][i+1] = Math.max(dp[k][i+1], dp[j][i] + 1);
                    maxStep = Math.max(maxStep, dp[k][i+1]);
                }
            }
        }
        return maxStep;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid =
                LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]");
        int i = solution.maxMoves(grid);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
