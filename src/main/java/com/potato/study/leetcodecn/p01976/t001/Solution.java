package com.potato.study.leetcodecn.p01976.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1976. 到达目的地的方案数
 *
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。

 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。

 请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 109 + 7 取余 后返回。

  

 示例 1：


 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 输出：4
 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 四条花费 7 分钟的路径分别为：
 - 0 ➝ 6
 - 0 ➝ 4 ➝ 6
 - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 示例 2：

 输入：n = 2, roads = [[1,0,10]]
 输出：1
 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
  

 提示：

 1 <= n <= 200
 n - 1 <= roads.length <= n * (n - 1) / 2
 roads[i].length == 3
 0 <= ui, vi <= n - 1
 1 <= timei <= 109
 ui != vi
 任意两个路口之间至多有一条路。
 从任意路口出发，你能够到达其他任意路口。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/solution/dijkstrasuan-chu-zui-duan-lu-de-tong-shi-izlt/
     * @param n
     * @param roads
     * @return
     */
    public int countPaths(int n, int[][] roads) {
        // 将 roads 转成 list 形式 0节点 1花费
        List<long[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            // [ui, vi, timei]
            int from = road[0];
            int to = road[1];

            int cost = road[2];

            graph[from].add(new long[]{to, cost});
            graph[to].add(new long[]{from, cost});
        }
        // 记录 int dist 0 到某个点 i的最小值 count 记录最小值情况下 的种类数
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        int mod = 1_000_000_000 + 7;
        long[] count = new long[n];
        // 从 0 开始 找到 找到当前 可以达到 最小 耗时点 i 更新 i的next 点 并对更新的重新放到堆中
        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>(
                Comparator.comparingLong(array -> array[1])
        );
        count[0] = 1;
        dist[0] = 0;
        boolean[] visit = new boolean[n];
        priorityQueue.add(new long[]{0, 0});
        // 注意 等于的时候 要做数量的加和
        while (!priorityQueue.isEmpty()) {
            long[] poll = priorityQueue.poll();
            int nodeIndex = (int) poll[0];
            if (visit[nodeIndex]) {
                continue;
            }
            visit[nodeIndex] = true;
            // 找到 临界点 进行更新
            List<long[]> nextList = graph[nodeIndex];
            for (long[] nextNode : nextList) {
                int nextNodeIndex = (int) nextNode[0];
                // 时间
                long costTime = nextNode[1];

                if (dist[nodeIndex] + costTime < dist[nextNodeIndex]) {
                    // 修改次数
                    count[nextNodeIndex] = count[nodeIndex];
                    dist[nextNodeIndex] = dist[nodeIndex] + costTime;
                    // 改了的点 也改一下
                    priorityQueue.add(new long[]{nextNodeIndex, dist[nextNodeIndex]});
                } else if (dist[nodeIndex] + costTime == dist[nextNodeIndex]) {
                    // 已经是最小了 累次次数
                    count[nextNodeIndex] += count[nodeIndex];
                    count[nextNodeIndex] %= mod;
                }
            }
        }
        return (int) count[n-1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int[][] roads = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,0,10]]");
        int i = solution.countPaths(n, roads);
        System.out.println(i);
        Assert.assertEquals(1, i);


        n = 5;
        roads = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1,1],[1,2,4],[0,4,3],[3,2,5],[3,4,1],[3,0,5],[1,3,1]]");
        i = solution.countPaths(n, roads);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }


}
