package com.potato.study.leetcodecn.p02316.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.domain.UnionFind;
import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 2316. 统计无向图中无法互相到达点对数
 *
 * 给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。

 请你返回 无法互相到达 的不同 点对数目 。

  

 示例 1：



 输入：n = 3, edges = [[0,1],[0,2],[1,2]]
 输出：0
 解释：所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
 示例 2：



 输入：n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 输出：14
 解释：总共有 14 个点对互相无法到达：
 [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
 所以我们返回 14 。
  

 提示：

 1 <= n <= 105
 0 <= edges.length <= 2 * 105
 edges[i].length == 2
 0 <= ai, bi < n
 ai != bi
 不会有重复边。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {




    // 2316
    public long countPairs(int n, int[][] edges) {
        // 将 edges 转成 list
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph[from].add(to);
            graph[to].add(from);
        }


        // dfs 找到当前 连分量个数 * 之前 的 sum 求和
        boolean[] visited = new boolean[n];
        long totalCount = 0;
        long currentSum = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            long count = dfs(i, visited, graph);
            if (count > 0) {
                totalCount += currentSum * count;
                currentSum += count;
            }
        }

        return totalCount;
    }

    private long dfs(int i, boolean[] visited, List<Integer>[] graph) {
        long count = 1;
        visited[i] = true;
        List<Integer> list = graph[i];
        for (int next : list) {
            if (visited[next]) {
                continue;
            }
            count += dfs(next, visited, graph);
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // n = 3, edges = [[0,1],[0,2],[1,2]]
        int n = 3;
        String input = "[[0,1],[0,2],[1,2]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        long l = solution.countPairs(n, edges);
        System.out.println(l);
        Assert.assertEquals(0, l);

        // n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]] 14
        n = 7;
        input = "[[0,2],[0,5],[2,4],[1,6],[5,4]]";
        edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        l = solution.countPairs(n, edges);
        System.out.println(l);
        Assert.assertEquals(14, l);

    }

}
