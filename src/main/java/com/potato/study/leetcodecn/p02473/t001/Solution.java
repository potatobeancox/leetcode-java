package com.potato.study.leetcodecn.p02473.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

/**
 * 2473. 购买苹果的最低成本
 *
 * 给你一个正整数  n，表示从 1 到 n 的 n 个城市。还给你一个 二维 数组 roads，其中 roads[i] = [ai, bi, costi] 表示在城市 ai 和 bi 之间有一条双向道路，其旅行成本等于 costi。

  

 你可以在 任何 城市买到苹果，但是有些城市买苹果的费用不同。给定数组 appleCost ，其中 appleCost[i] 是从城市 i 购买一个苹果的成本。

 你从某个城市开始，穿越各种道路，最终从 任何一个 城市买 一个 苹果。在你买了那个苹果之后，你必须回到你 开始的 城市，但现在所有道路的成本将 乘以 一个给定的因子 k。

 给定整数 k，返回一个大小为 n 的数组 answer，其中 answer[i] 是从城市 i 开始购买一个苹果的 最小 总成本。

  

 示例 1:


 输入: n = 4, roads = [[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]], appleCost = [56,42,102,301], k = 2
 输出: [54,42,48,51]
 解释: 每个起始城市的最低费用如下:
 - 从城市 1 开始:你走路径 1 -> 2，在城市 2 买一个苹果，最后走路径 2 -> 1。总成本是 4 + 42 + 4 * 2 = 54。
 - 从城市 2 开始:你直接在城市 2 买一个苹果。总费用是 42。
 - 从城市 3 开始:你走路径 3 -> 2，在城市 2 买一个苹果，最后走路径 2 -> 3。总成本是 2 + 42 + 2 * 2 = 48。
 - 从城市 4 开始:你走路径 4 -> 3 -> 2，然后你在城市 2 购买，最后走路径 2 -> 3 -> 4。总成本是 1 + 2 + 42 + 1 * 2 + 2 * 2 = 51。
 示例 2:


 输入: n = 3, roads = [[1,2,5],[2,3,1],[3,1,2]], appleCost = [2,3,1], k = 3
 输出: [2,3,1]
 解释: 在起始城市买苹果总是最优的。
  

 提示:

 2 <= n <= 1000
 1 <= roads.length <= 1000
 1 <= ai, bi <= n
 ai != bi
 1 <= costi <= 105
 appleCost.length == n
 1 <= appleCost[i] <= 105
 1 <= k <= 100
 没有重复的边。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-cost-to-buy-apples
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/minimum-cost-to-buy-apples/solution/by-steven2018-oh8u/
     * @param n
     * @param roads
     * @param appleCost
     * @param k
     * @return
     */
    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // 将 roads 转换成 gird  list【】  int【】 第一个位置 是 to 第二个是cost
        for (int[] road : roads) {
            // roads[i] = [ai, bi, costi]
            int from = road[0]-1;
            int to = road[1]-1;
            int cost = road[2];

            graph[from].add(new int[]{to, cost});
            graph[to].add(new int[]{from, cost});
        }
        long[] res = new long[n];
        // 从每个点开始 左djstra
        for (int i = 0; i < n; i++) {
            res[i] = minCost(i, n, graph, k, appleCost);
        }
        return res;
    }


    /**
     * 从 start 点开始 进行狄杰斯特拉 求最小值
     * @param start
     * @return
     */
    private long minCost(int start, int n, List<int[]>[] graph, int k, int[] appleCost) {
        // dist i
        long[] dist = new long[n];
        // 开始 dist start 等于 0
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[start] = 0;
        // 优先级队列 按照 cost 升序排列 对于每个 队列中的元素 pop 一下
        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((p1, p2) -> Long.compare(p1[1], p2[1]));
        priorityQueue.add(new long[]{start, 0});
        long min = Long.MAX_VALUE;
        while (!priorityQueue.isEmpty()) {
            long[] poll = priorityQueue.poll();
            int point = (int) poll[0];
            long cost = poll[1];
            // 如果 这个点的花费已经小于 的inf 说明 可以更新下 在这个点买苹果 的花费
            if (cost < Long.MAX_VALUE / 2) {
                // update 在这个点买需要话多少前 先是到这个点要花多少前 然后就是
                min = Math.min(min, cost * (k+1) + appleCost[point]);
            }
            // 历史花费已经比当前小了 没有必要往这个点走了
            if (dist[point] < cost) {
                continue;
            }
            // 对于相邻的 往队列之中放置
            // 对于当前点的临界点 计算下 并加入 队列记性下一个计算
            List<int[]> nextList = graph[point];
            for (int[] next : nextList) {
                int nextPoint = next[0];
                int nextCost = next[1];
                // 到上个点 point 需要的花费 + 两袋呢之间的花费
                long thisCost = dist[point] + nextCost;
                if (thisCost < dist[nextPoint]) {
                    dist[nextPoint] = thisCost;
                    priorityQueue.add(new long[]{nextPoint, thisCost});
                }

            }
        }
        return min;
    }
}
