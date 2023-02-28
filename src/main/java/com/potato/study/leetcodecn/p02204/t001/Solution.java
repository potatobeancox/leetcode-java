package com.potato.study.leetcodecn.p02204.t001;

import java.util.*;

/**
 * 2204. 无向图中到环的距离
 *
 * 给定一个正整数 n，表示一个 连通无向图 中的节点数，该图 只包含一个 环。节点编号为 0 ~ n - 1(含)。

 你还得到了一个二维整数数组 edges，其中 edges[i] = [node1i, node2i] 表示有一条 双向 边连接图中的 node1i 和 node2i。

 两个节点 a 和 b 之间的距离定义为从 a 到 b 所需的 最小边数。

 返回一个长度为 n 的整数数组 answer，其中 answer[i] 是第 i 个节点与环中任何节点之间的最小距离。

 示例 1:


 输入: n = 7, edges = [[1,2],[2,4],[4,3],[3,1],[0,1],[5,2],[6,5]]
 输出: [1,0,0,0,0,1,2]
 解释:
 节点 1, 2, 3, 和 4 来自环。
 0 到 1 的距离是 1。
 1 到 1 的距离是 0。
 2 到 2 的距离是 0。
 3 到 3 的距离是 0。
 4 到 4 的距离是 0。
 5 到 2 的距离是 1。
 6 到 2 的距离是 2。
 示例 2:


 输入: n = 9, edges = [[0,1],[1,2],[0,2],[2,6],[6,7],[6,8],[0,3],[3,4],[3,5]]
 输出: [0,0,0,1,2,2,1,2,2]
 解释:
 节点 0, 1, 和 2 来自环.
 0 到 0 的距离是 0。
 1 到 1 的距离是 0。
 2 到 2 的距离是 0。
 3 到 1 的距离是 1。
 4 到 1 的距离是 2。
 5 到 1 的距离是 2。
 6 到 2 的距离是 1。
 7 到 2 的距离是 2。
 8 到 2 的距离是 2。
  

 提示:

 3 <= n <= 105
 edges.length == n
 edges[i].length == 2
 0 <= node1i, node2i <= n - 1
 node1i != node2i
 图是连通的。
 这个图只有一个环。
 任何顶点对之间最多只有一条边。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/distance-to-a-cycle-in-undirected-graph
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] distanceToCycle(int n, int[][] edges) {
        // 将 edges 转换成 list【】 形式 过程中记录每个点的度
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            grid[from].add(to);
            grid[to].add(from);

            degree[from]++;
            degree[to]++;
        }
        // 将度为1的点方入队列中 依次出队并修改临接点的入度，如果临接点也为1 继续放入队列中
        Queue<Integer> indexQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                indexQueue.add(i);
            }
        }
        while (!indexQueue.isEmpty()) {
            int pollIndex = indexQueue.poll();
            // 临接点
            List<Integer> nextIndexList = grid[pollIndex];
            for (int next : nextIndexList) {
                degree[pollIndex]--;
                degree[next]--;

                if (degree[next] == 1) {
                    indexQueue.add(next);
                }
            }
        }
        // 遍历 入度 找到 度还是2的点 就是 环中的点 他们的结果为 0 利用他们依次bfs 改临界大小 只要没有结果的
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        Queue<Integer> newQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 2) {
                newQueue.add(i);
                res[i] = 0;
            }
        }
        // bfs 开始
        int step = 1;
        while (!newQueue.isEmpty()) {
            int size = newQueue.size();
            for (int i = 0; i < size; i++) {
                int pollIndex = newQueue.poll();
                // 找到临界 看看 有美誉复制过
                List<Integer> nextIndexList = grid[pollIndex];
                for (int next : nextIndexList) {
                    if (res[next] != Integer.MAX_VALUE) {
                        continue;
                    }
                    res[next] = step;
                    newQueue.add(next);
                }
            }
            step++;
        }
        return res;
    }

}
