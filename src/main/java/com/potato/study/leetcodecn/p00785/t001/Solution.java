package com.potato.study.leetcodecn.p00785.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 785. 判断二分图
 *
 * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u
 * 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 *
 * 如果图是二分图，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * 输出：false
 * 解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。
 * 示例 2：
 *
 *
 * 输入：graph = [[1,3],[0,2],[1,3],[0,2]]
 * 输出：true
 * 解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。
 *  
 *
 * 提示：
 *
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] 不会包含 u
 * graph[u] 的所有值 互不相同
 * 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isBipartite(int[][] graph) {
        // 785 判断 二分图 申请一个 数组 存 当前 下标i 对应状态 颜色1 或者2
        int n = graph.length;
        int[] status = new int[n];
        // 初始化邻接矩阵 从0-n-1 计算状态 只遍历 没有遍历过的
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (status[i] != 0) {
                continue;
            }
            res &= dfs(graph, i, status, 1);
        }
        return res;
    }


    /**
     * 返回 false 为不是二分图
     * @param graph
     * @param index
     * @param status
     * @param currentStatus
     * @return
     */
    private boolean dfs(int[][] graph, int index, int[] status, int currentStatus) {
        if (status[index] > 0) {
            // 已经染过色 本次不再遍历
            return status[index] == currentStatus;
        }
        status[index] = currentStatus;
        int[] target = graph[index];
        boolean result = true;
        for (int j = 0; j < target.length; j++) {
            int targetIndex = target[j];
            if (status[targetIndex] > 0) {
                if (status[targetIndex] == currentStatus) {
                    return false;
                } else {
                    //状态相等
                    continue;
                }
            }
            if (currentStatus == 1) {
                result &= dfs(graph, targetIndex, status, 2);
            } else {
                result &= dfs(graph, targetIndex, status, 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,2,3],[0,2],[0,1,3],[0,2]]";
        int[][] graph = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        boolean bipartite = solution.isBipartite(graph);
        System.out.println(bipartite);
        Assert.assertEquals(false, bipartite);


        input = "[[1,3],[0,2],[1,3],[0,2]]";
        graph = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        bipartite = solution.isBipartite(graph);
        System.out.println(bipartite);
        Assert.assertEquals(true, bipartite);


        input = "[[2,3,5,6,7,8,9],[2,3,4,5,6,7,8,9],[0,1,3,4,5,6,7,8,9],[0,1,2,4,5,6,7,8,9],[1,2,3,6,9],[0,1,2,3,7,8,9],[0,1,2,3,4,7,8,9],[0,1,2,3,5,6,8,9],[0,1,2,3,5,6,7],[0,1,2,3,4,5,6,7]]";
        graph = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        bipartite = solution.isBipartite(graph);
        System.out.println(bipartite);
        Assert.assertEquals(false, bipartite);
    }
}
