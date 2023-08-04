package com.potato.study.leetcodecn.other.lcr.p0105.t001;

/**
 * 剑指 Offer II 105. 岛屿的最大面积
 *
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。

 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。

  

 示例 1:



 输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 输出: 6
 解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 示例 2:

 输入: grid = [[0,0,0,0,0,0,0,0]]
 输出: 0
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 50
 grid[i][j] is either 0 or 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ZL6zAn
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxAreaOfIsland(int[][] grid) {
        // 便利 grid 从每个不为 0 的位置开始找
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // dfs 遍历并记录 当前 岛屿大小
                grid[i][j] = 0;
                int area = dfs(grid, i, j);
                max = Math.max(max, area);
            }
        }
        // 返回最大
        return max;
    }


    private int dfs(int[][] grid, int i, int j) {
        // 四个方向
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int total = 1;
        for (int k = 0; k < dir.length; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];
            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                continue;
            }
            if (grid[di][dj] == 0) {
                continue;
            }
            grid[di][dj] = 0;
            total += dfs(grid, di, dj);
        }
        return total;
    }
}
