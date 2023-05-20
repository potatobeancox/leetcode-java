package com.potato.study.leetcodecn.p02685.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 2685. 统计完全连通分量的数量
 *
 * 给你一个整数 n 。现有一个包含 n 个顶点的 无向 图，顶点按从 0 到 n - 1 编号。给你一个二维整数数组 edges 其中 edges[i] = [ai, bi] 表示顶点 ai 和 bi 之间存在一条 无向 边。
 *
 * 返回图中 完全连通分量 的数量。
 *
 * 如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 连通分量 。
 *
 * 如果连通分量中每对节点之间都存在一条边，则称其为 完全连通分量 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
 * 输出：3
 * 解释：如上图所示，可以看到此图所有分量都是完全连通分量。
 * 示例 2：
 *
 *
 *
 * 输入：n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
 * 输出：1
 * 解释：包含节点 0、1 和 2 的分量是完全连通分量，因为每对节点之间都存在一条边。
 * 包含节点 3 、4 和 5 的分量不是完全连通分量，因为节点 4 和 5 之间不存在边。
 * 因此，在图中完全连接分量的数量是 1 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 50
 * 0 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 不存在重复的边
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-the-number-of-complete-components
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2685
    public int countCompleteComponents(int n, int[][] edges) {
        // 完全联通分量 要求分量中的任意两个点都有路径
        // 将 edges 改造成list 临接入的方式形式 只插入一端就行
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // 主要用来记录边个数 所以当作有向图记录
            grid[from].add(to);

            // 遍历 edges 插入 并且
            unionFind.union(from, to);
        }
        // 遍历节点计算 联通分量点的个数和 边的个数之间关系是不是相等
        int[] pointCount = new int[n];
        // parent 对应的节点为1对应联通分量 中点的个数和边的个数
        int[] edgeCount = new int[n];
        for (int i = 0; i < n; i++) {
            int p = unionFind.find(i);
            pointCount[p]++;
            edgeCount[p] += grid[i].size();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (pointCount[i] != 0
                    && edgeCount[i] != 0
                    && pointCount[i] * (pointCount[i] -1) / 2 == edgeCount[i]) {
                count++;
            }
        }
        return count;
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
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }
}
