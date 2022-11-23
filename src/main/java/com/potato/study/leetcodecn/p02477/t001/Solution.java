package com.potato.study.leetcodecn.p02477.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2477. 到达首都的最少油耗
 *
 * 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 *
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 *
 * 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
 *
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
 *
 * 请你返回到达首都最少需要多少升汽油。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：roads = [[0,1],[0,2],[0,3]], seats = 5
 * 输出：3
 * 解释：
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 2 直接到达首都，消耗 1 升汽油。
 * - 代表 3 直接到达首都，消耗 1 升汽油。
 * 最少消耗 3 升汽油。
 * 示例 2：
 *
 *
 *
 * 输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * 输出：7
 * 解释：
 * - 代表 2 到达城市 3 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 5 直接到达首都，消耗 1 升汽油。
 * - 代表 6 到达城市 4 ，消耗 1 升汽油。
 * - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
 * 最少消耗 7 升汽油。
 * 示例 3：
 *
 *
 *
 * 输入：roads = [], seats = 1
 * 输出：0
 * 解释：没有代表需要从别的城市到达首都。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 105
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads 表示一棵合法的树。
 * 1 <= seats <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long totalCost;

    public long minimumFuelCost(int[][] roads, int seats) {
        // 有 n - 1 条路
        int n = roads.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 将 roads 转成 list list 形式 里边存 链接的index
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        this.totalCost = 0;
        // 所有店都到达第一个参数对应的点需要的花费
        int count = dfs(0, -1, graph, seats);
        // 统计之后 返回的是 当前点有多少人 还可以往下一个节点走  过程中记录 从其他点过来的人需要的油耗
        return this.totalCost;
    }

    private int dfs(int current, int parent, List<List<Integer>> graph, int seats) {
        // 从 0位置开始 dfs 遍历每个 连接的点 中间排除 parent 节点 对于每个点 统计有多少个点需要从这个点到 当前点
        List<Integer> list = graph.get(current);
        if (list == null || list.size() == 0) {
            return 1;
        }
        int nextCountSum = 0;
        for (int next : list) {
            // 找到了父亲 遍历过
            if (next == parent) {
                continue;
            }
            // 这个点目前有多少个点
            int nextCount = dfs(next, current, graph, seats);
            if (nextCount > 0) {
                nextCountSum += nextCount;
                // 计算这些点转移的花费
                totalCost += (nextCount /seats);
                if (nextCount % seats != 0) {
                    totalCost++;
                }
            }
        }
        return nextCountSum + 1;
    }
}
