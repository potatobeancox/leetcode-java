package com.potato.study.leetcodecn.other.Interview.p0004.p0001;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 04.01. 节点间通路
 *
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 *
 * 示例1:
 *
 *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 *  输出：true
 * 示例2:
 *
 *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0,
 *  target = 4
 *  输出 true
 * 提示：
 *
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/route-between-nodes-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        // 面试题04.01
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < graph.length; i++) {
            int from = graph[i][0];
            int to = graph[i][1];

            unionFind.union(from, to);
        }

        int p1 = unionFind.find(start);
        int p2 = unionFind.find(target);
        return p1 == p2;
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
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
