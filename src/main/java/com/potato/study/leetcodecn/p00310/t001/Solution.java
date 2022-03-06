package com.potato.study.leetcodecn.p00310.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.lang.ref.SoftReference;
import java.util.*;

/**
 * 310. 最小高度树
 *
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。

 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。

 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。

 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。

 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
  

 示例 1：


 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 输出：[1]
 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 示例 2：


 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 输出：[3,4]
 示例 3：

 输入：n = 1, edges = []
 输出：[0]
 示例 4：

 输入：n = 2, edges = [[0,1]]
 输出：[0,1]
  

 提示：

 1 <= n <= 2 * 104
 edges.length == n - 1
 0 <= ai, bi < n
 ai != bi
 所有 (ai, bi) 互不相同
 给定的输入 保证 是一棵树，并且 不会有重复的边

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-height-trees
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/minimum-height-trees/solution/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }
        // 将 nums 改造成 邻接表 map key 点， value：list 相邻点
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        // 在上一步过程中 计算每个点的 度 （因为是无向图） 出度等于 入度
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            List<Integer> fromList = adjacencyMap.getOrDefault(from, new ArrayList<>());
            fromList.add(to);
            adjacencyMap.put(from, fromList);


            List<Integer> toList = adjacencyMap.getOrDefault(to, new ArrayList<>());
            toList.add(from);
            adjacencyMap.put(to, toList);

            degree[from]++;
            degree[to]++;
        }
        // 找到 度为1的queue
        Queue<Integer> indexQueue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                indexQueue.add(i);
            }
        }
        // 遍历 每次 都将 pop 一个 并将其 删去 计算度 并将 相邻剩余度为 1 的 进队 记录 当前删除的点 最后一次删除的点就是结果
        List<Integer> result = new ArrayList<>();
        while (!indexQueue.isEmpty()) {
            // dfs
            int size = indexQueue.size();
            result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int leaf = indexQueue.poll();
                degree[leaf]--;
                result.add(leaf);
                // 找到邻接点 遍历 修改
                List<Integer> nextList = adjacencyMap.get(leaf);
                for (int nextIndex : nextList) {
                    // leaf 被删除 修改 出度
                    degree[nextIndex]--;
                    if (degree[nextIndex] == 1) {
                        indexQueue.add(nextIndex);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        String input = "[[1,0],[1,2],[1,3]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        List<Integer> minHeightTrees = solution.findMinHeightTrees(n, edges);
        System.out.println(minHeightTrees); // 1
    }
}
