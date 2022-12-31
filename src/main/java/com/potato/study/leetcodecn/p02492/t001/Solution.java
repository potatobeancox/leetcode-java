package com.potato.study.leetcodecn.p02492.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2492. 两个城市间路径的最小分数
 *
 * 给你一个正整数 n ，表示总共有 n 个城市，城市从 1 到 n 编号。给你一个二维数组 roads ，其中 roads[i] = [ai, bi, distancei] 表示城市 ai 和 bi 之间有一条 双向 道路，道路距离为 distancei 。城市构成的图不一定是连通的。

 两个城市之间一条路径的 分数 定义为这条路径中道路的 最小 距离。

 城市 1 和城市 n 之间的所有路径的 最小 分数。

 注意：

 一条路径指的是两个城市之间的道路序列。
 一条路径可以 多次 包含同一条道路，你也可以沿着路径多次到达城市 1 和城市 n 。
 测试数据保证城市 1 和城市n 之间 至少 有一条路径。
  

 示例 1：



 输入：n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 输出：5
 解释：城市 1 到城市 4 的路径中，分数最小的一条为：1 -> 2 -> 4 。这条路径的分数是 min(9,5) = 5 。
 不存在分数更小的路径。
 示例 2：



 输入：n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 输出：2
 解释：城市 1 到城市 4 分数最小的路径是：1 -> 2 -> 1 -> 3 -> 4 。这条路径的分数是 min(2,2,4,7) = 2 。
  

 提示：

 2 <= n <= 105
 1 <= roads.length <= 105
 roads[i].length == 3
 1 <= ai, bi <= n
 ai != bi
 1 <= distancei <= 104
 不会有重复的边。
 城市 1 和城市 n 之间至少有一条路径。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int minScore;
    private List<int[]>[] graph;
    private boolean[] visit;

    public int minScore(int n, int[][] roads) {
        this.minScore = Integer.MAX_VALUE;
        // 从 1号位置开始遍历
        graph = new List[n+1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        // 将roads 转化成 graph
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int cost = road[2];

            graph[from].add(new int[] {to, cost});
            graph[to].add(new int[] {from, cost});
        }
        visit = new boolean[n+1];
        dfs(1);
        return minScore;
    }

    private void dfs(int currentIndex) {
        // 访问
        visit[currentIndex] = true;
        // 还没访问完 找邻接点继续
        List<int[]> nextList = graph[currentIndex];
        for (int[] nextInfo : nextList) {
            int next = nextInfo[0];
            int cost = nextInfo[1];
            this.minScore = Math.min(minScore, cost);
            if (visit[next]) {
                continue;
            }
            dfs(next);

        }
    }

}
