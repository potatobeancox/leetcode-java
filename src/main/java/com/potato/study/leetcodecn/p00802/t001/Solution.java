package com.potato.study.leetcodecn.p00802.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 802. 找到最终的安全状态
 *
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。

 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。

 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。

 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。

  

 示例 1：


 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 输出：[2,4,5,6]
 解释：示意图如上。
 示例 2：

 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 输出：[4]
  

 提示：

 n == graph.length
 1 <= n <= 104
 0 <= graph[i].length <= n
 graph[i] 按严格递增顺序排列。
 图中可能包含自环。
 图中边的数目在范围 [1, 4 * 104] 内。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-eventual-safe-states
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        // dfs 012 表示 0 没有访问 1正在访问 2 安全点
        int[] status = new int[graph.length];
        List<Integer> safeList = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            boolean isSafe = dfs(graph, status, i);
            if (isSafe) {
                safeList.add(i);
            }
        }
        return safeList;
    }


    /**
     *
     * @param graph
     * @param status
     * @param currentIndex
     * @return 当前 currentIndex 是否是安全点
     */
    private boolean dfs(int[][] graph, int[] status, int currentIndex) {
        if (status[currentIndex] == 1) {
            // 1 说明有环了
            return false;
        }
        if (status[currentIndex] == 2) {
            return true;
        }
        // 0 的话开始找
        status[currentIndex] = 1;
        int[] ints = graph[currentIndex];
        if (ints.length == 0) {
            status[currentIndex] = 2;
            return true;
        }
        for (int i = 0; i < ints.length; i++) {
            boolean isSafe = dfs(graph, status, ints[i]);
            if (!isSafe) {
                return false;
            }
        }
        status[currentIndex] = 2;
        return true;
    }
}
