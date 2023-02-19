package com.potato.study.leetcodecn.p02093.t001;

import java.util.*;

/**
 * 2093. 前往目标城市的最小费用
 *
 * 一组公路连接 n 个城市，城市编号为从 0 到 n - 1 。 输入包含一个二维数组 highways ，其中 highways[i] = [city1i, city2i, tolli] 表示有一条连接城市 city1i 和 city2i 的双向公路，允许汽车缴纳值为 tolli 的费用从  city1i 前往 city2i 或 从  city2i 前往 city1i 。

 另给你一个整数 discounts 表示你最多可以使用折扣的次数。你可以使用一次折扣使通过第 ith 条公路的费用降低至 tolli / 2（向下取整）。 最多只可使用 discounts 次折扣， 且 每条公路最多只可使用一次折扣 。

 返回从城市0 前往城市 n - 1 的 最小费用 。如果不存在从城市0 前往城市 n - 1 的路径，返回 -1 。

  

 示例 1：


 输入：n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
 输出：9
 解释：
 从 0 前往 1 ，需要费用为 4 。
 从 1 前往 4 并使用一次折扣，需要费用为 11 / 2 = 5 。
 从 0 前往 4 最小费用为 4 + 5 = 9 。
 示例 2：


 输入：n = 4, highways = [[1,3,17],[1,2,7],[3,2,5],[0,1,6],[3,0,20]], discounts = 20
 输出：8
 解释：
 从 0 前往 1 并使用一次折扣，需要费用为 6 / 2 = 3 。
 从 1 前往 2 并使用一次折扣，需要费用为 7 / 2 = 3 。
 从 2 前往 3 并使用一次折扣，需要费用为 5 / 2 = 2 。
 从 0 前往 3 最小费用为 3 + 3 + 2 = 8 。
 示例 3：


 输入：n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
 输出：-1
 解释：
 不存在从 0 前往 3 的路径，所以返回 -1 。
  

 提示:

 2 <= n <= 1000
 1 <= highways.length <= 1000
 highways[i].length == 3
 0 <= city1i, city2i <= n - 1
 city1i != city2i
 0 <= tolli <= 105
 0 <= discounts <= 500
 任意两个城市之间最多只有一条公路相连

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-cost-to-reach-city-with-discounts
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumCost(int n, int[][] highways, int discounts) {
        // 将 highways 转换成 list int[] 形式 存 双向 第二维度存 cost
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] ways : highways) {
            int from = ways[0];
            int to = ways[1];
            int cost = ways[2];

            graph[from].add(new int[]{to, cost});
            graph[to].add(new int[]{from, cost});
        }
        // 使用 一个数组 int[][] dis 表示从开始点 0 到大 i 点的最小距离 用了用了j次优惠 init 都弄成最大
        int[][] minCost = new int[n][discounts + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE / 2);
        }
        // 最开始 从0点开始 用了 0次优惠 话费了多少钱 每次走到可能的点 更新下target的最小值
        minCost[0][0] = 0;
        // 0；那个点开始 ， 1：用了多少次优惠，2：花了多少钱
        // 先找调用次数少的 相等找话费少的
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (int1, int2) -> {
                    if (int1[1] != int2[1]) {
                        return Integer.compare(int1[1], int2[1]);
                    }
                    return Integer.compare(int1[2], int2[2]);
                }
        );
        queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int node = poll[0];
            int useDiscount = poll[1];
            int currentCost = poll[2];
            // 找到临界的next 看看 用新的路径走到 next 是不是小了
            List<int[]> nextList = graph[node];
            for (int[] next : nextList) {
                int nextIndex = next[0];
                int pathCost = next[1];
                // 没用折扣
                if (pathCost + currentCost < minCost[nextIndex][useDiscount]) {
                    minCost[nextIndex][useDiscount] = pathCost + currentCost;
                    queue.add(new int[] {nextIndex, useDiscount, pathCost + currentCost});
                }
                // 用不了折扣了 因为折扣用完了
                if (useDiscount >= discounts) {
                    continue;
                }
                // 用了折扣
                if (pathCost/2 + currentCost < minCost[nextIndex][useDiscount + 1]) {
                    minCost[nextIndex][useDiscount+1] = pathCost/2 + currentCost;
                    queue.add(new int[] {nextIndex, useDiscount+1, pathCost/2 + currentCost});
                }

            }
        }
        // 从 用了 discounts 次优惠 到 0
        int min = minCost[n-1][0];
        for (int cost : minCost[n-1]) {
            min = Math.min(cost, min);
        }
        if (min == Integer.MAX_VALUE / 2) {
            return -1;
        }
        return min;
    }
}
