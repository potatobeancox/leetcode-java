package com.potato.study.leetcodecn.p00685.t001;

/**
 * 685. 冗余连接 II
 *
 * 在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。

 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。

 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。

 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

  

 示例 1：


 输入：edges = [[1,2],[1,3],[2,3]]
 输出：[2,3]
 示例 2：


 输入：edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 输出：[4,1]
  

 提示：

 n == edges.length
 3 <= n <= 1000
 edges[i].length == 2
 1 <= ui, vi <= n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/redundant-connection-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 685
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 并查集
        UnionFind unionFind = new UnionFind(edges.length);
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            int p1 = unionFind.find(from);
            int p2 = unionFind.find(to);

            if (p1 == p2) {
                return edges[i];
            } else {
                unionFind.union(from, to);
            }
        }
        return null;
    }

    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n+1];
            for (int i = 0; i < n + 1; i++) {
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
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

}
