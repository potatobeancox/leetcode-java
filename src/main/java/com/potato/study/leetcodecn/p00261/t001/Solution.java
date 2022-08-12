package com.potato.study.leetcodecn.p00261.t001;

import java.util.Arrays;

/**
 * 261. 以图判树
 *
 * 给定编号从 0 到 n - 1 的 n 个结点。给定一个整数 n 和一个 edges 列表，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间存在一条无向边。
 *
 * 如果这些边能够形成一个合法有效的树结构，则返回 true ，否则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * 输出: true
 * 示例 2:
 *
 *
 *
 * 输入: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * 输出: false
 *  
 *
 * 提示：
 *
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 不存在自循环或重复的边
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/graph-valid-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean validTree(int n, int[][] edges) {
        // 并查集   且没有环
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            boolean res = unionFind.union(from, to);
            if (!res) {
                return false;
            }
        }
        // 最终连通分量为1
        return unionFind.getCount() == 1;
    }

    class UnionFind {

        private int[] parent;
        private int n;


        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         *
         * @param target1
         * @param target2
         * @return false 已经链接过 ， true 第一次链接
         */
        public boolean union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return false;
            }

            parent[p1] = p2;
            n--;
            return true;
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }

        public int getCount() {
            return this.n;
        }
    }
}
