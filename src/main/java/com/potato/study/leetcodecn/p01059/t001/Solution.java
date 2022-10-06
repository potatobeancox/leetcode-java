package com.potato.study.leetcodecn.p01059.t001;

import java.util.*;

/**
 * 1059. 从始点到终点的所有路径
 *
 * 给定有向图的边 edges，以及该图的始点 source 和目标终点 destination，确定从始点 source 出发的所有路径是否最终结束于目标终点 destination，即：

 从始点 source 到目标终点 destination 存在至少一条路径
 如果存在从始点 source 到没有出边的节点的路径，则该节点就是路径终点。
 从始点source到目标终点 destination 可能路径数是有限数字
 当从始点 source 出发的所有路径都可以到达目标终点 destination 时返回 true，否则返回 false。

  

 示例 1：



 输入：n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 输出：false
 说明：节点 1 和节点 2 都可以到达，但也会卡在那里。
 示例 2：



 输入：n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 输出：false
 说明：有两种可能：在节点 3 处结束，或是在节点 1 和节点 2 之间无限循环。
 示例 3：



 输入：n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 输出：true
  

 提示：

 1 <= n <= 104
 0 <= edges.length <= 104
 edges.length == 2
 0 <= ai, bi <= n - 1
 0 <= source <= n - 1
 0 <= destination <= n - 1
 给定的图中可能带有自环和平行边。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/all-paths-from-source-lead-to-destination
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        if (n == 1) {
            return true;
        }
        // 将 edges 转换成 list list int 记录每个店的连接点
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.get(from).add(to);
        }
        boolean[] visit = new boolean[n];
        // 从 source 开始 往每个临界点 dfs
        return dfs(graph, source, destination, visit);
    }

    /**
     *
     * @param graph
     * @param index
     * @param destination
     * @return
     */
    private boolean dfs(List<List<Integer>> graph, int index, int destination, boolean[] visit) {
        // dfs 过程中 如果 连接点如果遇到了已经用过的点 返回false 有一个 false 就是 false
        List<Integer> nextList = graph.get(index);
        // 当前点已经到了终点 判断下 终点是否还有临街点
        if (index == destination) {
            return nextList.size() == 0;
        }
        // 中间遇到了 访问过的点 肯定到不了终点了
        if (visit[index]) {
            return false;
        }
        // 这个点标记
        visit[index] = true;
        // 往临街点访问


        // dfs
        return false;
    }


}
