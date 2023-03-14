package com.potato.study.leetcodecn.p01786.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 1786. 从第一个节点出发到最后一个节点的受限路径数
 *
 * 现有一个加权无向连通图。给你一个正整数 n ，表示图中有 n 个节点，并按从 1 到 n 给节点编号；另给你一个数组 edges ，其中每个 edges[i] = [ui, vi, weighti] 表示存在一条位于节点 ui 和 vi 之间的边，这条边的权重为 weighti 。

 从节点 start 出发到节点 end 的路径是一个形如 [z0, z1, z2, ..., zk] 的节点序列，满足 z0 = start 、zk = end 且在所有符合 0 <= i <= k-1 的节点 zi 和 zi+1 之间存在一条边。

 路径的距离定义为这条路径上所有边的权重总和。用 distanceToLastNode(x) 表示节点 n 和 x 之间路径的最短距离。受限路径 为满足 distanceToLastNode(zi) > distanceToLastNode(zi+1) 的一条路径，其中 0 <= i <= k-1 。

 返回从节点 1 出发到节点 n 的 受限路径数 。由于数字可能很大，请返回对 109 + 7 取余 的结果。

  

 示例 1：


 输入：n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 输出：3
 解释：每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。三条受限路径分别是：
 1) 1 --> 2 --> 5
 2) 1 --> 2 --> 3 --> 5
 3) 1 --> 3 --> 5
 示例 2：


 输入：n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 输出：1
 解释：每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。唯一一条受限路径是：1 --> 3 --> 7 。
  

 提示：

 1 <= n <= 2 * 104
 n - 1 <= edges.length <= 4 * 104
 edges[i].length == 3
 1 <= ui, vi <= n
 ui != vi
 1 <= weighti <= 105
 任意两个节点之间至多存在一条边
 任意两个节点之间至少存在一条路径

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-restricted-paths-from-first-to-last-node
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1786
    public int countRestrictedPaths(int n, int[][] edges) {
        // 并按从 1 到 n 给节点编号 key 是触发的点 value-key是 临接节点 value 是这条路径的权重
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        // edges[i] = [ui, vi, weighti] 将这个转换成map的形式 注意是没有方向的
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            Map<Integer, Integer> map1 = graph.getOrDefault(from, new HashMap<>());
            map1.put(to, cost);
            graph.put(from, map1);

            Map<Integer, Integer> map2 = graph.getOrDefault(to, new HashMap<>());
            map2.put(from, cost);
            graph.put(to, map2);
        }
        // 求每个点到点n的最端路径 dijstra 数组 dist i 从 i到n的最小花费
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 数组中 第一个是当前需要
        Queue<int[]> queue = new LinkedList<>();
        // n->n 没有花费
        dist[n] = 0;
        // 从 n开始 找
        queue.add(new int[] {n, 0});
        // 状态位 记录 某个点是否已经被访问过，使用 queue最开始从n开始访问
        boolean[] used = new boolean[n+1];
        used[n] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nodeIndex = poll[0];
            int currentCost = poll[1];
            // 找到临界
            Map<Integer, Integer> adjacentMap = graph.get(nodeIndex);
            if (null == adjacentMap) {
                continue;
            }
            for (int nextNodeIndex : adjacentMap.keySet()) {
                // 看看 是不是访问过
                if (used[nextNodeIndex]) {
                    continue;
                }
                used[nextNodeIndex] = true;
                // 每次找到没有访问过的 记录路程过程中的 目前的点和花费
                dist[nextNodeIndex] = Math.min(dist[nextNodeIndex], currentCost + adjacentMap.get(nextNodeIndex));
            }
        }
        // 对 dist 生成一个index cost 顺序的情况 注意要吧0 去掉
        int[][] indexCost = new int[n][2];
        // 第一维度是 index 第二维是cost
        for (int i = 1; i <= n; i++) {
            indexCost[i-1] = new int[] {i, dist[i]};
        }
        // 最后的dp 过程 按照点i到n的距离生序排序 dp i i节点到n的可以走的路径的种类数
        Arrays.sort(indexCost, Comparator.comparingInt((int[] ic) -> ic[1]).thenComparingInt(ic -> ic[0]));
        // 然后便利 生序 找到当前点和花费，找到临界点的点 如果当前点的花费大于 临界点的到n的花费
        long[] count = new long[n+1];
        // 那么可以转移 记录 种类的和 最开始 fn = 1
        count[n] = 1;
        // 最终返回 走后的累加结果 从1开始走 到n有多少种 路径数量
        int mod = 1_000_000_000 + 7;
        for (int i = 0; i < indexCost.length; i++) {
            int currentNodeIndex = indexCost[i][0];
            int cost = indexCost[i][1];

            // 找到临界
            Map<Integer, Integer> adjacentMap = graph.get(currentNodeIndex);
            if (null == adjacentMap) {
                continue;
            }
            for (int nextNodeIndex : adjacentMap.keySet()) {
                // 看看 是不是访问过
                if (cost <= dist[nextNodeIndex]) {
                    continue;
                }
                // 累计个数
                count[currentNodeIndex] += count[nextNodeIndex];
                count[currentNodeIndex] %= mod;
            }
        }
        return (int) count[1];
    }
}
