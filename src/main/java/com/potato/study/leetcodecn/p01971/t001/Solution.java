package com.potato.study.leetcodecn.p01971.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1971. 寻找图中是否存在路径
 *
 * 有一个具有 n个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。

 请你确定是否存在从顶点 start 开始，到顶点 end 结束的 有效路径 。

 给你数组 edges 和整数 n、start和end，如果从 start 到 end 存在 有效路径 ，则返回 true，否则返回 false 。

  

 示例 1：


 输入：n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
 输出：true
 解释：存在由顶点 0 到顶点 2 的路径:
 - 0 → 1 → 2
 - 0 → 2
 示例 2：


 输入：n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
 输出：false
 解释：不存在由顶点 0 到顶点 5 的路径.
  

 提示:

 1 <= n <= 2 * 105
 0 <= edges.length <= 2 * 105
 edges[i].length == 2
 0 <= ui, vi <= n - 1
 ui != vi
 0 <= start, end <= n - 1
 不存在双向边
 不存在指向顶点自身的边

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-if-path-exists-in-graph
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean validPath(int n, int[][] edges, int start, int end) {
        if (start == end) {
            return true;
        }
        // 临街链表
        List<Integer>[] graph = new List[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            graph[a].add(b);
            graph[b].add(a);
        }
        boolean[] visit = new boolean[n];
        // bfs 使用 set 记录 visited 情况
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int a = queue.poll();
            visit[a] = true;
            // 临街
            List<Integer> next = graph[a];
            for (int b : next) {
                if (end == b) {
                    return true;
                }
                if (visit[b]) {
                    continue;
                }
                queue.add(b);
            }
        }
        return false;
    }

}
