package com.potato.study.leetcodecn.p00323.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 323. 无向图中连通分量的数目
 *
 * 你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边。

 返回 图中已连接分量的数目 。

  

 示例 1:



 输入: n = 5, edges = [[0, 1], [1, 2], [3, 4]]
 输出: 2
 示例 2:



 输入: n = 5, edges = [[0,1], [1,2], [2,3], [3,4]]
 输出:  1
  

 提示：

 1 <= n <= 2000
 1 <= edges.length <= 5000
 edges[i].length == 2
 0 <= ai <= bi < n
 ai != bi
 edges 中不会出现重复的边

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-connected-components-in-an-undirected-graph
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countComponents(int n, int[][] edges) {
        // 遍历 edges 直接并查集
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        // get 联通分量
        return unionFind.getConnectedComponentCount();
    }

    class UnionFind {
        private int[] parent;
        private int connectedComponentCount;

        public UnionFind(int connectedComponentCount) {
            this.connectedComponentCount = connectedComponentCount;
            this.parent = new int[connectedComponentCount];
            for (int i = 0; i < connectedComponentCount; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 != p2) {
                parent[p1] = p2;
                connectedComponentCount--;
            }
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

        public int getConnectedComponentCount() {
            return this.connectedComponentCount;
        }

    }


}
