package com.potato.study.leetcodecn.p02608.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import com.potato.study.leetcode.util.LeetcodeUtils;
import org.junit.Assert;

import java.util.*;

/**
 *
 * 2608. 图中的最短环
 *
 * 现有一个含 n 个顶点的 双向 图，每个顶点按从 0 到 n - 1 标记。图中的边由二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和 vi 之间存在一条边。每对顶点最多通过一条边连接，并且不存在与自身相连的顶点。
 *
 * 返回图中 最短 环的长度。如果不存在环，则返回 -1 。
 *
 * 环 是指以同一节点开始和结束，并且路径中的每条边仅使用一次。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
 * 输出：3
 * 解释：长度最小的循环是：0 -> 1 -> 2 -> 0
 * 示例 2：
 *
 *
 * 输入：n = 4, edges = [[0,1],[0,2]]
 * 输出：-1
 * 解释：图中不存在循环
 *  
 *
 * 提示：
 *
 * 2 <= n <= 1000
 * 1 <= edges.length <= 1000
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * 不存在重复的边
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-cycle-in-a-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findShortestCycle(int n, int[][] edges) {
        // 将 edges  变成临界点的形式
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            grid[from].add(to);
            grid[to].add(from);
        }
        // bfs 从每个点开始 进行bfs 途中需要记录上一个和到底的点 如果当前达到的点已经遇到过那么直接计算环的大小
        int shortestLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int current = bfs(i, grid);
            shortestLen = Math.min(shortestLen, current);
        }
        if (shortestLen == Integer.MAX_VALUE) {
            return -1;
        }
        return shortestLen;
    }

    /**
     * 以i节点作为起点开始找 环（i点作为环中的一点）的最小大小 （边的长度或者点的个数）
     * @param i
     * @param grid
     * @return
     */
    private int bfs(int i, List<Integer>[] grid) {
        // index0 当前所在节点 第二位上一个点从哪来 -1 是当前点是起始点
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, -1});
        int n = grid.length;
        // 从i点到当前点位置
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[i] = 0;

        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int currentNode = poll[0];
            int lastNode = poll[1];

            for (int nextNode : grid[currentNode]) {
                // 如果下一个位置有2挑不同的路径
                if (dist[nextNode] != -1 && lastNode != nextNode) {
                    int target = 1 + dist[currentNode] + dist[nextNode];
                    min = Math.min(min, target);
                    continue;
                }
                if (dist[nextNode] != -1) {
                    continue;
                }
                int currentDist = dist[currentNode] + 1;
                if (dist[nextNode] == -1) {
                    dist[nextNode] = currentDist;
                }
                queue.add(new int[]{nextNode, currentNode});
            }
        }
        return min;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 7;
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]");
        int shortestCycle = solution.findShortestCycle(n, edges);
        System.out.println(shortestCycle);
        Assert.assertEquals(3, shortestCycle);


        n = 4;
        edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1],[0,2]]");
        shortestCycle = solution.findShortestCycle(n, edges);
        System.out.println(shortestCycle);
        Assert.assertEquals(-1, shortestCycle);


        n = 6;
        edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[4,2],[5,1],[5,0],[0,3],[5,2],[1,4],[1,3],[3,4]]");
        shortestCycle = solution.findShortestCycle(n, edges);
        System.out.println(shortestCycle);
        Assert.assertEquals(3, shortestCycle);
    }

}
