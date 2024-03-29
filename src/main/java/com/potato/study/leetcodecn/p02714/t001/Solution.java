package com.potato.study.leetcodecn.p02714.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 *
 * 2714. 找到最短路径的 K 次跨越
 *
 * 现给定一个正整数 n ，它表示一个 索引从 0 开始的无向带权连接图 的节点数，以及一个 索引从 0 开始的二维数组 edges ，其中 edges[i] = [ui, vi, wi] 表示节点 ui 和 vi 之间存在权重为 wi 的边。
 *
 * 还给定两个节点 s 和 d ，以及一个正整数 k ，你的任务是找到从 s 到 d 的 最短 路径，但你可以 最多 跨越 k 条边。换句话说，将 最多 k 条边的权重设为 0，然后找到从 s 到 d 的 最短 路径。
 *
 * 返回满足给定条件的从 s 到 d 的 最短 路径的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4, edges = [[0,1,4],[0,2,2],[2,3,6]], s = 1, d = 3, k = 2
 * 输出：2
 * 解释：在这个例子中，只有一条从节点1（绿色节点）到节点3（红色节点）的路径，即（1->0->2->3），其长度为4 + 2 + 6 = 12。现在我们可以将两条边的权重设为 0，即将蓝色边的权重设为 0，那么路径的长度就变为 0 + 2 + 0 = 2。可以证明 2 是我们在给定条件下能够达到的最小路径长度。
 *
 *
 * 示例 2：
 *
 * 输入：n = 7, edges = [[3,1,9],[3,2,4],[4,0,9],[0,5,6],[3,6,2],[6,0,4],[1,2,4]], s = 4, d = 1, k = 2
 * 输出：6
 * 解释：在这个例子中，有两条从节点4（绿色节点）到节点1（红色节点）的路径，分别是（4->0->6->3->2->1）和（4->0->6->3->1）。第一条路径的长度为 9 + 4 + 2 + 4 + 4 = 23，第二条路径的长度为 9 + 4 + 2 + 9 = 24。现在，如果我们将蓝色边的权重设为 0，那么最短路径的长度就变为 0 + 4 + 2 + 0 = 6。可以证明 6 是我们在给定条件下能够达到的最小路径长度。
 *
 *
 * 示例 3：
 *
 * 输入：n = 5, edges = [[0,4,2],[0,1,3],[0,2,1],[2,1,4],[1,3,4],[3,4,7]], s = 2, d = 3, k = 1
 * 输出：3
 * 解释：在这个例子中，从节点2（绿色节点）到节点3（红色节点）有4条路径，分别是（2->1->3）、（2->0->1->3）、（2->1->0->4->3）和（2->0->4->3）。前两条路径的长度为4 + 4 = 1 + 3 + 4 = 8，第三条路径的长度为4 + 3 + 2 + 7 = 16，最后一条路径的长度为1 + 2 + 7 = 10。现在，如果我们将蓝色边的权重设为 0，那么最短路径的长度就为1 + 2 + 0 = 3。可以证明在给定条件下，3 是我们能够达到的最小路径长度。
 *
 *
 *  
 *
 * 提示：
 *
 * 2 <= n <= 500
 * n - 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length = 3
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * 1 <= edges[i][2] <= 106
 * 0 <= s, d, k <= n - 1
 * s != d
 * 输入的生成确保图是 连通 的，并且没有 重复的边 或 自环。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-shortest-path-with-k-hops
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
        // 将 edges 转成临接 的grid
        Map<Integer, Integer>[] grid = new Map[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            // 无向图
            int from  = edge[0];
            int to  = edge[1];

            int cost = edge[2];

            grid[from].put(to, cost);
            grid[to].put(from, cost);
        }
        // dist ij 从s开始 走到 i 使用j次忽略的最小值
        int[][] dist = new int[n][k+1];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        // 优先级队列 记录到达的点 话费的忽略次数 花费的距离 最开始是 0 先按照距离升序 再按照次数升序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[2]).thenComparingInt(a -> a[1])
        );
        dist[s][0] = 0;
        // 第一维终点 第二唯 忽略了多少次 第三维 到达第一个点已经花了多少钱
        priorityQueue.add(new int[] {s, 0, 0});
        // 每次找到节点对应的next 生成两种 1忽略掉这个边 2不忽略
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int current = poll[0];
            int hopTime = poll[1];
            int currentCost = poll[2];

            // current 连接的点
            for (int nextNode : grid[current].keySet()) {
                int pathCost = grid[current].get(nextNode);
                // 已经没有办法忽略了
                if (currentCost + pathCost < dist[nextNode][hopTime]) {
                    dist[nextNode][hopTime] = currentCost + pathCost;
                    priorityQueue.add(new int[]{nextNode, hopTime, currentCost + pathCost});
                }
                // 如果还能忽略 忽略一下试试
                if (hopTime < k) {
                    if (currentCost < dist[nextNode][hopTime+1]) {
                        dist[nextNode][hopTime+1] = currentCost;
                        priorityQueue.add(new int[]{nextNode, hopTime+1, currentCost});
                    }
                }
            }
        }
        // 遍历 d
        int min = Integer.MAX_VALUE;
        for (int path : dist[d]) {
            min = Math.min(path, min);
        }

        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1,4],[0,2,2],[2,3,6]]");
        int s = 1;
        int d = 3;
        int k = 2;

        int i = solution.shortestPathWithHops(n, edges, s, d, k);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
