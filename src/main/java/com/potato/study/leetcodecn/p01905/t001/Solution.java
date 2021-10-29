package com.potato.study.leetcodecn.p01905.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1905. 统计子岛屿
 *
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。

 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。

 请你返回 grid2 中 子岛屿 的 数目 。

  

 示例 1：


 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 输出：3
 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 示例 2：


 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 输出：2
 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
  

 提示：

 m == grid1.length == grid2.length
 n == grid1[i].length == grid2[i].length
 1 <= m, n <= 500
 grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-sub-islands
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        // 遍历 grid2 中每一个 没有访问的陆地位置，是否可以被包围 每次访问过的 grid2 岛屿都变成海水吧
        int islandCount = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 0) {
                    continue;
                }
                // 当前是陆地
                if (dfsIsContain(grid1, grid2, i, j)) {
//                    System.out.println(i + "#" + j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    private int[][] direction = new int[][] {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private boolean dfsIsContain(int[][] grid1, int[][] grid2, int i, int j) {
        // 终止条件 已经是海水了
        if (grid2[i][j] == 0) {
            return true;
        }

        boolean isContain = grid1[i][j] == 1;
        grid2[i][j] = 0;
        // dfs 拿到各个方向结果
        for (int k = 0; k < 4; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            // 坐标合法性
            if (di < 0 || di >= grid2.length
                    || dj < 0 || dj >= grid2[0].length) {
                continue;
            }
            if (grid2[di][dj] == 0) {
                continue;
            }
            boolean res = dfsIsContain(grid1, grid2, di, dj);
            isContain = isContain && res;
        }
        return isContain;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 =
                LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]");
        int[][] grid2 =
                LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]");

        int i = solution.countSubIslands(grid1, grid2);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }

}
