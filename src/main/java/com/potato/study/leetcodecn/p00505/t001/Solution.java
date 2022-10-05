package com.potato.study.leetcodecn.p00505.t001;


import java.util.Arrays;

/**
 * 505. 迷宫 II
 *
 * 由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 *
 * 给定球的起始位置，目的地和迷宫，找出让球停在目的地的最短距离。距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。如果球无法停在目的地，返回 -1。
 *
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 *
 *  
 *
 * 示例 1:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
 *
 * 输出: 12
 *
 * 解析: 一条最短路径 : left -> down -> left -> down -> right -> down -> right。
 *              总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。
 *
 * 示例 2:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (3, 2)
 *
 * 输出: -1
 *
 * 解析: 没有能够使球停在目的地的路径。
 *
 *  
 *
 * 注意:
 *
 * 迷宫中只有一个球和一个目的地。
 * 球和目的地都在空地上，且初始时它们不在同一位置。
 * 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
 * 迷宫至少包括2块空地，行数和列数均不超过100。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-maze-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dp = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        dfs(maze, start[0], start[1], destination, dp, visit);
        if (Integer.MAX_VALUE == dp[destination[0]][destination[1]]) {
            return -1;
        }
        return dp[destination[0]][destination[1]];
    }

    private void dfs(int[][] maze, int i, int j, int[] destination, int[][] dp,  boolean[][] visit) {
        // 终止条件 已经到了最后一个点
        if (i == dp.length - 1 && j == dp[0].length - 1) {
            return;
        }
        if (visit[i][j]) {
            return;
        }
        if (i == destination[0] && j == destination[1]) {
            return;
        }
        // 找到一个方向 往前走到 边界或者遇到 1 转向
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        visit[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];

            // 一直走到头
            while (di >= 0 && di < maze.length && dj >= 0 && dj < maze[0].length
                    && !visit[di][dj] && maze[di][dj] == 0) {
                di += dir[k][0];
                dj += dir[k][1];
            }
            dfs(maze, di - dir[k][0], dj - dir[k][1], destination, dp, visit);
        }
        visit[i][j] = false;
    }

}
