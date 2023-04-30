package com.potato.study.leetcodecn.p02642.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 *
 * 2642. 设计可以求最短路径的图类
 *
 * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi 有一条代价为 edgeCosti 的边。

 请你实现一个 Graph 类：

 Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。一条路径的代价是路径中所有边代价之和。
  

 示例 1：



 输入：
 ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
 [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
 输出：
 [null, 6, -1, null, 6]

 解释：
 Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
 g.shortestPath(3, 2); // 返回 6 。从 3 到 2 的最短路径如第一幅图所示：3 -> 0 -> 1 -> 2 ，总代价为 3 + 2 + 1 = 6 。
 g.shortestPath(0, 3); // 返回 -1 。没有从 0 到 3 的路径。
 g.addEdge([1, 3, 4]); // 添加一条节点 1 到节点 3 的边，得到第二幅图。
 g.shortestPath(0, 3); // 返回 6 。从 0 到 3 的最短路径为 0 -> 1 -> 3 ，总代价为 2 + 4 = 6 。
  

 提示：

 1 <= n <= 100
 0 <= edges.length <= n * (n - 1)
 edges[i].length == edge.length == 3
 0 <= fromi, toi, from, to, node1, node2 <= n - 1
 1 <= edgeCosti, edgeCost <= 106
 图中任何时候都不会有重边和自环。
 调用 addEdge 至多 100 次。
 调用 shortestPath 至多 100 次。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-graph-with-shortest-path-calculator
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Graph {


    private int[][] grid;

    /**
     * https://leetcode.cn/problems/design-graph-with-shortest-path-calculator/solution/dijkstra-suan-fa-mo-ban-pythonjavacgo-by-unmv/
     * @param n
     * @param edges
     */
    public Graph(int n, int[][] edges) {
        // 用 grid ij 记录 ij 之间的距离 不通时，按照-1 进行设置
        this.grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], -1);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            int val = edge[2];

            grid[from][to] = val;
        }
    }

    public void addEdge(int[] edge) {
        // 修改 grid
        int from = edge[0];
        int to = edge[1];

        int val = edge[2];
        grid[from][to] = val;
    }

    public int shortestPath(int start, int end) {
        // 用一个 dist 记录 从 start 到某个点 最小花费 visit 记录是否访问过
        int n = grid.length;
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        boolean[] visit = new boolean[n];
        while (true) {
            int targetIndex = -1;
            // 每次遍历 dist 找到 最小的点
            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    continue;
                }
                if (dist[i] == Long.MAX_VALUE) {
                    continue;
                }
                if (targetIndex == -1 || dist[i] < dist[targetIndex]) {
                    targetIndex = i;
                }
            }
            if (targetIndex == -1) {
                // 没找到
                break;
            }
            // 找到这个点 邻接的其他点 分别计算 dist 变化之后的情况 标记访问
            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    continue;
                }
                if (grid[targetIndex][i] == -1) {
                    continue;
                }
                dist[i] = Math.min(dist[i], dist[targetIndex] + grid[targetIndex][i]);
            }
            // 下次 找最小dist继续找 最终范围 end
            visit[targetIndex] = true;
        }
        if (Long.MAX_VALUE == dist[end]) {
            return -1;
        }
        return (int) dist[end];
    }
}
