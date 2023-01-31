package com.potato.study.leetcodecn.other.Interview.p0004.p0001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        // 遍历 graph 生成每个点的临接点
        for (int[] g : graph) {
            int from = g[0];
            int to = g[1];
            Set<Integer> set = new HashSet<>(grid[from]);
            if (set.contains(to)) {
                continue;
            }
            grid[from].add(to);
        }
        boolean[] visit = new boolean[n];
        return dfs(grid, start, target, visit);
    }

    private boolean dfs(List<Integer>[] grid, int start, int target, boolean[] visit) {
        if (start == target) {
            return true;
        }
        List<Integer> nextList = grid[start];
        for (int next : nextList) {
            if (visit[next]) {
                continue;
            }
            visit[next] = true;
            boolean dfs = dfs(grid, next, target, visit);
            if (dfs) {
                return true;
            }
        }
        return false;
    }
}
