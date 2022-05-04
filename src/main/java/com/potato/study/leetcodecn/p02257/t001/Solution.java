package com.potato.study.leetcodecn.p02257.t001;

import org.junit.Assert;

/**
 * 2257. 统计网格图中没有被保卫的格子数
 *
 * 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。同时给你两个二维整数数组 guards 和 walls ，其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j 座墙所在的位置。

 一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。

 请你返回空格子中，有多少个格子是 没被保卫 的。

  

 示例 1：



 输入：m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
 输出：7
 解释：上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
 总共有 7 个没有被保卫的格子，所以我们返回 7 。
 示例 2：



 输入：m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 输出：4
 解释：上图中，没有被保卫的格子用绿色表示。
 总共有 4 个没有被保卫的格子，所以我们返回 4 。
  

 提示：

 1 <= m, n <= 105
 2 <= m * n <= 105
 1 <= guards.length, walls.length <= 5 * 104
 2 <= guards.length + walls.length <= m * n
 guards[i].length == walls[j].length == 2
 0 <= rowi, rowj < m
 0 <= coli, colj < n
 guards 和 walls 中所有位置 互不相同 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-unguarded-cells-in-the-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/count-unguarded-cells-in-the-grid/solution/by-relll-1037-bxay/
     * @param m
     * @param n
     * @param guards
     * @param walls
     * @return
     */
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] graph = new char[m][n];
        // 放 guards walls
        for (int[] guard: guards) {
            graph[guard[0]][guard[1]] = 'G';
        }
        for (int[] wall : walls) {
            graph[wall[0]][wall[1]] = 'W';
        }
        // 遍历 每个 guards 往四个方向找 直到 找到 guards or wall ，每次过程修改 找到 为A
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        for (int[] guard: guards) {
            int x = guard[0];
            int y = guard[1];
            for (int i = 0; i < 4; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                while (0 <= dx && dx < m
                        && 0 <= dy && dy < n && graph[dx][dy] != 'G' && graph[dx][dy] != 'W') {

                    graph[dx][dy] = 'A';

                    dx += dir[i][0];
                    dy += dir[i][1];
                }

            }
        }

        // 遍历 graph 找到空的个数
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 'A' && graph[i][j] != 'G' && graph[i][j] != 'W') {
                    count++;
                }
            }
        }
        return count;
    }
}
