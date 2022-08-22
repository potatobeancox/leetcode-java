package com.potato.study.leetcodecn.p00317.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 317. 离建筑物最近的距离
 *
 * 给你一个 m × n 的网格，值为 0 、 1 或 2 ，其中:
 *
 * 每一个 0 代表一块你可以自由通过的 空地 
 * 每一个 1 代表一个你不能通过的 建筑
 * 每个 2 标记一个你不能通过的 障碍 
 * 你想要在一块空地上建造一所房子，在 最短的总旅行距离 内到达所有的建筑。你只能上下左右移动。
 *
 * 返回到该房子的 最短旅行距离 。如果根据上述规则无法建造这样的房子，则返回 -1 。
 *
 * 总旅行距离 是朋友们家到聚会地点的距离之和。
 *
 * 使用 曼哈顿距离 计算距离，其中距离 (p1, p2) = |p2.x - p1.x | + | p2.y - p1.y | 。
 *
 *  
 *
 * 示例  1：
 *
 *
 *
 * 输入：grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 输出：7
 * 解析：给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
 * 由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点。
 * 故返回7。
 * 示例 2:
 *
 * 输入: grid = [[1,0]]
 * 输出: 1
 * 示例 3:
 *
 * 输入: grid = [[1]]
 * 输出: -1
 *  
 *
 * 提示:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 是 0, 1 或 2
 * grid 中 至少 有 一幢 建筑
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-distance-from-all-buildings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestDistance(int[][] grid) {
        // 遍历 grid 记录 1 的个数 并维护一个 能与房子连通的 数量
        int[][] connectCount = new int[grid.length][grid[0].length];
        int buildCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 如果是障碍 或者 恐慌个  continue
                if (grid[i][j] != 1) {
                    continue;
                }
                // bfs 记录与每个 0 的 距离 记录下来
                buildCount++;
                bfs(grid, i, j, connectCount);
            }
        }
        // 遍历 grid 如果 当前是 0
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                // 当前点能够链接的建筑数量 bfs 找到所有 的建筑的最短距离
                if (buildCount == connectCount[i][j]) {
                    int totalCost = bfs(i, j, grid, buildCount);
                    min = Math.min(min, totalCost);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    /**
     * ij 作为 开始点 找到 是否能与 ij连通
     * @param grid
     * @param i
     * @param j
     * @param connectCount
     */
    private void bfs(int[][] grid, int i, int j, int[][] connectCount) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        visit[i][j] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] poll = queue.poll();
                // 邻接的点 如果是 1 就要结算距离 如果是 0 就继续加入
                for (int l = 0; l < 4; l++) {
                    int di = poll[0] + dir[l][0];
                    int dj = poll[1] + dir[l][1];
                    // 坐标合法
                    if (di < 0 || di >= grid.length
                            || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }
                    // 不是墙 且不是建筑图
                    if (grid[di][dj] != 0) {
                        continue;
                    }
                    // 没有 visit
                    if (visit[di][dj]) {
                        continue;
                    }
                    // visit
                    visit[di][dj] = true;
                    // 如果是 0 记录可达状态 同时加入queue
                    connectCount[di][dj]++;
                    queue.add(new int[] {di, dj});
                }
            }
        }
        return;

    }

    /**
     * 以 ij 作为 新房子的点 到各个房子一共需要多少cost
     * @param i
     * @param j
     * @param grid
     * @param buildCount
     * @return
     */
    private int bfs(int i, int j, int[][] grid, int buildCount) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        int step = 0;
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        visit[i][j] = true;
        int totalStep = 0;
        int alreadyFindBuild = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] poll = queue.poll();
                // 邻接的点 如果是 1 就要结算距离 如果是 0 就继续加入
                for (int l = 0; l < 4; l++) {
                    int di = poll[0] + dir[l][0];
                    int dj = poll[1] + dir[l][1];
                    // 坐标合法
                    if (di < 0 || di >= grid.length
                            || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }
                    // 不是墙
                    if (grid[di][dj] == 2) {
                        continue;
                    }
                    // 没有 visit
                    if (visit[di][dj]) {
                        continue;
                    }
                    // visit
                    visit[di][dj] = true;
                    // 如果是 1 建筑 结算 如果是0 空白 加入queue
                    if (grid[di][dj] == 1) {
                        alreadyFindBuild++;
                        totalStep += (step + 1);
                    } else {
                        // grid[i][j] == 0
                        queue.add(new int[] {di, dj});
                    }
                    // 剪枝 找到了 足够的数量
                    if (buildCount == alreadyFindBuild) {
                        return totalStep;
                    }
                }
            }
            step++;
        }
        return totalStep;
    }

    public static void main(String[] args) {
        Solution solution = new Solution() ;

        String input = "[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int distance = solution.shortestDistance(grid);
        System.out.println(distance);
        Assert.assertEquals(7, distance);
    }
}
