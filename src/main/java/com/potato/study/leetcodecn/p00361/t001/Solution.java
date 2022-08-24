package com.potato.study.leetcodecn.p00361.t001;

import java.util.Arrays;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 361. 轰炸敌人
 *
 * 给你一个大小为 m x n 的矩阵 grid ，其中每个单元格都放置有一个字符：
 *
 * 'W' 表示一堵墙
 * 'E' 表示一个敌人
 * '0'（数字 0）表示一个空位
 * 返回你使用 一颗炸弹 可以击杀的最大敌人数目。你只能把炸弹放在一个空位里。
 *
 * 由于炸弹的威力不足以穿透墙体，炸弹只能击杀同一行和同一列没被墙体挡住的敌人。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * 输出：3
 * 示例 2：
 *
 *
 * 输入：grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 可以是 'W'、'E' 或 '0'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bomb-enemy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 上下左右 dp
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        // 左上 往右下
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是墙continue
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    up[i][j] = 1;
                    left[i][j] = 1;
                }
                // 不是敌人
                if (i > 0) {
                    up[i][j] += up[i-1][j];
                }
                if (j > 0) {
                    left[i][j] += left[i][j-1];
                }

            }
        }

        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                // 如果是墙continue
                if (grid[i][j] == 'W') {
                    continue;
                }
                if (grid[i][j] == 'E') {
                    down[i][j] = 1;
                    right[i][j] = 1;
                }
                // 不是敌人
                if (i < m-1) {
                    down[i][j] += down[i+1][j];
                }
                if (j < n-1) {
                    right[i][j] += right[i][j+1];
                }
            }
        }

        // 上下左右
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    continue;
                }
                max = Math.max(max, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[\"0\",\"E\",\"0\",\"0\"],[\"E\",\"0\",\"W\",\"E\"],[\"0\",\"E\",\"0\",\"0\"]]";
        char[][] chars = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        int i = solution.maxKilledEnemies(chars);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}

