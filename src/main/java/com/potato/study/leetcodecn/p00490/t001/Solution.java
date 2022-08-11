package com.potato.study.leetcodecn.p00490.t001;


/**
 * 490. 迷宫
 *
 * 由空地（用 0 表示）和墙（用 1 表示）组成的迷宫 maze 中有一个球。球可以途经空地向 上、下、左、右 四个方向滚动，且在遇到墙壁前不会停止滚动。当球停下时，可以选择向下一个方向滚动。
 * 给你一个大小为 m x n 的迷宫 maze ，以及球的初始位置 start 和目的地 destination ，其中 start = [startrow, startcol] 且 destination =
 * [destinationrow, destinationcol] 。请你判断球能否在目的地停下：如果可以，返回 true ；否则，返回 false 。
 *
 * 你可以 假定迷宫的边缘都是墙壁（参考示例）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * 输出：true
 * 解释：一种可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
 * 示例 2：
 *
 *
 * 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * 输出：false
 * 解释：不存在能够使球停在目的地的路径。注意，球可以经过目的地，但无法在那里停驻。
 * 示例 3：
 *
 * 输入：maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * 球和目的地都在空地上，且初始时它们不在同一位置
 * 迷宫 至少包括 2 块空地
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/the-maze
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        int i = start[0];
        int j = start[1];
        // maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
        boolean res = dfs(maze, visit, i, j, destination);
        return res;
    }

    private boolean dfs(int[][] maze, boolean[][] visit, int i, int j, int[] destination) {
        // 如果当前点就是 终点 返回终点
        if (i == destination[0] && j == destination[1]) {
            return true;
        }
        // 找到了 重复的节点
        if (visit[i][j]) {
            return false;
        }
        visit[i][j] = true;
        // 四个方向往前走
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, -1},
                {0, 1}
        };
        for (int k = 0; k < 4; k++) {
            // 往某一个方向走到终点 找
            int di = i;
            int dj = j;
            while (di + dir[k][0] >= 0 && di + dir[k][0] < maze.length
                    && dj + dir[k][1] >= 0 && dj + dir[k][1] < maze[i].length
                    && maze[di+dir[k][0]][dj+dir[k][1]] == 0) {
                di += dir[k][0];
                dj += dir[k][1];
            }
            boolean dfs = dfs(maze, visit, di, dj, destination);
            if (dfs) {
                return true;
            }
        }
        return false;
    }


}
