package com.potato.study.leetcodecn.p02316.t001;

import java.util.HashMap;
import java.util.Map;

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
        // 并查集 统计 parent
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        // 遍历 parent 找到每个parent 对应次数
        Map<Integer, Integer> parentCountMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = unionFind.find(i);
            int count = parentCountMap.getOrDefault(p, 0);
            count++;
            parentCountMap.put(p, count);
        }
        long count = 0;
        // map计算
        for (int nodeCount : parentCountMap.values()) {
            count += ((long)nodeCount * ((long)n-nodeCount));
        }
        return count / 2;
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }
    }

}
