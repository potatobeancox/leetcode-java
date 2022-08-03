package com.potato.study.leetcodecn.p01135.t001;


import java.util.Arrays;

/**
 * 1135. 最低成本联通所有城市
 *
 * 想象一下你是个城市基建规划者，地图上有 n 座城市，它们按以 1 到 n 的次序编号。
 *
 * 给你整数 n 和一个数组 conections，其中 connections[i] = [xi, yi, costi] 表示将城市 xi 和城市 yi 连接所要的costi（连接是双向的）。
 *
 * 返回连接所有城市的最低成本，每对城市之间至少有一条路径。如果无法连接所有 n 个城市，返回 -1
 *
 * 该 最小成本 应该是所用全部连接成本的总和。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 3, conections = [[1,2,5],[1,3,6],[2,3,1]]
 * 输出：6
 * 解释：选出任意 2 条边都可以连接所有城市，我们从中选取成本最小的 2 条。
 * 示例 2：
 *
 *
 *
 * 输入：n = 4, conections = [[1,2,3],[3,4,4]]
 * 输出：-1
 * 解释：即使连通所有的边，也无法连接所有城市。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 104
 * 1 <= connections.length <= 104
 * connections[i].length == 3
 * 1 <= xi, yi <= n
 * xi != yi
 * 0 <= costi <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/connecting-cities-with-minimum-cost
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minimumCost(int n, int[][] connections) {
        // 对 connections 按照 costi 升序排序
        Arrays.sort(connections, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        UnionFind unionFind = new UnionFind(n);
        int totalCost = 0;
        // 贪心
        for (int[] connection : connections) {
            boolean isNewConnect = unionFind.union(connection[0], connection[1]);
            if (isNewConnect) {
                totalCost += connection[2];
            }
        }
        if (unionFind.getPartCount() == 1) {
            return totalCost;
        }
        return -1;
    }

    class UnionFind {

        private int[] parent;
        private int partCount;

        public UnionFind(int n) {
            this.partCount = n;
            this.parent = new int[n+1];

            for (int i = 0; i < n+1; i++) {
                parent[i] = i;
            }
        }

        public int getPartCount() {
            return this.partCount;
        }

        public boolean union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return false;
            }

            parent[p1] = p2;
            partCount--;
            return true;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

    }
}
