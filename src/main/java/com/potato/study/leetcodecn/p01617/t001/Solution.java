package com.potato.study.leetcodecn.p01617.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1617. 统计子树中城市之间最大距离
 *
 * 给你 n 个城市，编号为从 1 到 n 。同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi 之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 树 。

 一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。

 对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。

 请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。

 请注意，两个城市间距离定义为它们之间需要经过的边的数目。

  

 示例 1：



 输入：n = 4, edges = [[1,2],[2,3],[2,4]]
 输出：[3,4,0]
 解释：
 子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
 子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
 不存在城市间最大距离为 3 的子树。
 示例 2：

 输入：n = 2, edges = [[1,2]]
 输出：[1]
 示例 3：

 输入：n = 3, edges = [[1,2],[2,3]]
 输出：[2,1]
  

 提示：

 2 <= n <= 15
 edges.length == n-1
 edges[i].length == 2
 1 <= ui, vi <= n
 题目保证 (ui, vi) 所表示的边互不相同。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // dist ij  边的个数 初始编号 1-n 需要转化
        int[][] dist = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        int limit = (1 << n);
        // n 个点 dp i， i表示一个子树中有哪些节点 dpi 表示这个子树的直径 两个点之间的最大距离
        int[] dp = new int[limit];
        // 遍历 edges 生成 dist
        for (int[] edge : edges) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;

            dist[from][to] = 1;
            dist[to][from] = 1;

            // 两个点构成最小的一颗树
            int thisState = ((1 << from) + (1 << to));
            dp[thisState] = 1;

        }
        // floyd 三重循环 生成 dist
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // 枚举每一种状态
        for (int state = 1; state < limit; state++) {
            if (dp[state] == 0) {
                continue;
            }
            // 枚举 每一个不在状态内部的点 且枚举之后的记过没生成过
            for (int i = 0; i < n; i++) {
                if (isInTree(state, i)) {
                    continue;
                }
                int nextState = (state | (1 << i));
                // 已经找到最大距离
                if (dp[nextState] != 0) {
                    continue;
                }
                // 针对上面的枚举 找到状态内部与之相连的点 有的话
                for (int k = 0; k < n; k++) {
                    // 相邻 且在树里边
                    if (isInTree(state, k) && dist[i][k] == 1) {
                        // 先设置一个兜底
                        dp[nextState] = dp[state];
                        break;
                    }
                }
                // i加不进去
                if (dp[nextState] == 0) {
                    continue;
                }
                // 通过枚举内部的点 找到最大的距离 作为直径
                for (int k = 0; k < n; k++) {
                    if (isInTree(state, k)) {
                        dp[nextState] = Math.max(dp[nextState], dist[i][k]);
                    }
                }

            }
        }
        // 根据dp 对应生成结果
        int[] res = new int[n-1];
        for (int diameter: dp) {
            if (diameter == 0) {
                continue;
            }
            res[diameter-1]++;
        }
        return res;
    }

    /**
     * i 号节点是否在 树内部
     * @param state
     * @param i
     * @return
     */
    private boolean isInTree(int state, int i) {
        return ((1 << i) & state) != 0;
    }




    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 7;
        String str = "[[1,4],[1,3],[2,5],[2,6],[3,6],[6,7]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int[] ints = solution.countSubgraphsForEachDiameter(n, edges);
        System.out.println(Arrays.toString(ints));
    }
}
