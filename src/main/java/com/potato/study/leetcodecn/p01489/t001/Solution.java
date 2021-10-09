package com.potato.study.leetcodecn.p01489.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1489. 找到最小生成树里的关键边和伪关键边
 *
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi,
 * weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 *
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。
 *
 * 如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
 * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 *
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：上图描述了给定图。
 * 下图是所有的最小生成树。
 *
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
 * 示例 2 ：
 *
 *
 *
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
 *  
 *
 * 提示：
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * 所有 (fromi, toi) 数对都是互不相同的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 关键边 ：  删除 权重会增加
     * 非关键边： 删除 权重不会增加
     * edges[i] = [fromi, toi, weighti]
     * 无向图
     * https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 将 edges 排序按照权重升序排序 并补充 index 为 3
        int[][] newEdges = new int[edges.length][4];
        // 补充 index 因为结果要用
        for (int i = 0; i < edges.length; i++) {
            newEdges[i][0] = edges[i][0];
            newEdges[i][1] = edges[i][1];
            newEdges[i][2] = edges[i][2];
            newEdges[i][3] = i;
        }
        // 按照 权重生序排序
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        // 克鲁斯卡尔算法 选最小的边进行连通，只有在未
        int minTotal = 0;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < newEdges.length; i++) {
            // 返回true 本次是可以连通的最小边
            if (unionFind.union(newEdges[i][0], newEdges[i][1])) {
                minTotal += newEdges[i][2];
            }
        }
        // 重新遍历 edges 每次遍历到的边去掉 内部带着没有遍历过的边机型并查集，如果接过变大了 说明是关键边 （连通），如果还连通 那么就是非关键边
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        for (int i = 0; i < newEdges.length; i++) {
            // i 就是不选择的那个
            unionFind = new UnionFind(n);
            int currentTotal = 0;
            for (int j = 0; j < newEdges.length; j++) {
                // 不选这个边看 怎么样
                if (i == j) {
                    continue;
                }
                // 返回true 本次是可以连通的最小边
                if (unionFind.union(newEdges[j][0], newEdges[j][1])) {
                    currentTotal += newEdges[j][2];
                }
            }
            // 一边结束后不连通 也是关键边
            if (unionFind.getPartCount() != 1) {
                List<Integer> list = result.get(0);
                list.add(newEdges[i][3]);
                continue;
            }
            // 一边结束后 判断是不是 连通 且增大了 增大了就是关键边
            if (unionFind.getPartCount() == 1 && currentTotal > minTotal) {
                List<Integer> list = result.get(0);
                list.add(newEdges[i][3]);
                continue;
            }
            // 选择这个边 如果 value 不增大 那就是非关键边
            unionFind = new UnionFind(n);
            unionFind.union(newEdges[i][0], newEdges[i][1]);
            currentTotal = newEdges[i][2];
            for (int j = 0; j < newEdges.length; j++) {
                // 不选这个边看 怎么样
                if (i == j) {
                    continue;
                }
                // 返回true 本次是可以连通的最小边
                if (unionFind.union(newEdges[j][0], newEdges[j][1])) {
                    currentTotal += newEdges[j][2];
                }
            }
            // 没增大 说明 有可能是关键边 有可能也不是 判定一下是否出现在所有的子树里边
            if (currentTotal == minTotal) {
                List<Integer> list = result.get(1);
                list.add(newEdges[i][3]);
            }
        }
        return result;
    }


    // 写一个并查集 案例 记录连通分量数量
    class UnionFind {
        private int[] parent;
        private int partCount;


        public int getPartCount() {
            return this.partCount;
        }

        public UnionFind(int n) {
            this.parent = new int[n];
            this.partCount = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int value) {
            int target = value;
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }

        /**
         * 连接 两个数字
         * @param target1
         * @param target2
         * @return true 发生了union false已经是同一个分量中的
         */
        public boolean union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return false;
            }
            this.parent[p2] = p1;
            this.partCount--;
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int[][] edges =
                LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]");
        List<List<Integer>> criticalAndPseudoCriticalEdges = solution.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println(criticalAndPseudoCriticalEdges);
    }
}
