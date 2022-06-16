package com.potato.study.leetcodecn.p02290.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 2290. 到达角落需要移除障碍物的最小数目
 *
 * 给你一个下标从 0 开始的二维整数数组 grid ，数组大小为 m x n 。每个单元格都是两个值之一：
 *
 * 0 表示一个 空 单元格，
 * 1 表示一个可以移除的 障碍物 。
 * 你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
 *
 * 现在你需要从左上角 (0, 0) 移动到右下角 (m - 1, n - 1) ，返回需要移除的障碍物的 最小 数目。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,1,1],[1,1,0],[1,1,0]]
 * 输出：2
 * 解释：可以移除位于 (0, 1) 和 (0, 2) 的障碍物来创建从 (0, 0) 到 (2, 2) 的路径。
 * 可以证明我们至少需要移除两个障碍物，所以返回 2 。
 * 注意，可能存在其他方式来移除 2 个障碍物，创建出可行的路径。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * 输出：0
 * 解释：不移除任何障碍物就能从 (0, 0) 到 (2, 4) ，所以返回 0 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * grid[i][j] 为 0 或 1
 * grid[0][0] == grid[m - 1][n - 1] == 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 2290
     * @param grid
     * @return
     */
    public int minimumObstacles(int[][] grid) {
        // bfs 使用一个 二维数组 记录 从 0 点到每个位置 的cost 最小值
        int[][] cost = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            // 初始化成最大值
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        // 使用一个 优先级队列 记录 ij 位置 优先级是 花费 生序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return Integer.compare(cost[o1[0]][o1[1]], cost[o2[0]][o2[1]]);
                    }
                }
        );
        // 使用一个 visit 判断是否访问过
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        // 开始位置
        cost[0][0] = grid[0][0];
        priorityQueue.add(new int[] {0, 0});
        visit[0][0] = true;
        // 4个方向
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int i = poll[0];
            int j = poll[1];
            for (int k = 0; k < 4; k++) {
                int di = i + dir[k][0];
                int dj = j + dir[k][1];
                // 是不是处于边界
                if (di < 0 || di >= grid.length
                        || dj < 0 || dj >= grid[0].length) {
                    continue;
                }
                // 访问过
                if (visit[di][dj]) {
                    continue;
                }
                // 找到了
                if (di == grid.length - 1 && dj == grid[0].length - 1) {
                    return grid[di][dj] + cost[i][j];
                }
                // 访问这个点
                visit[di][dj] = true;
                cost[di][dj] = grid[di][dj] + cost[i][j];
                priorityQueue.add(new int[] {di, dj});
            }
        }
        return cost[grid.length - 1][grid[0].length - 1];
    }


}
