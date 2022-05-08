package com.potato.study.leetcodecn.p01514.t001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1514. 概率最大的路径
 *
 * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。

 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。

 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。

  

 示例 1：



 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 输出：0.25000
 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
 示例 2：



 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 输出：0.30000
 示例 3：



 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 输出：0.00000
 解释：节点 0 和 节点 2 之间不存在路径
  

 提示：

 2 <= n <= 10^4
 0 <= start, end < n
 start != end
 0 <= a, b < n
 a != b
 0 <= succProb.length == edges.length <= 2*10^4
 0 <= succProb[i] <= 1
 每两个节点之间最多有一条边


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/path-with-maximum-probability
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/path-with-maximum-probability/solution/gai-lu-zui-da-de-lu-jing-by-leetcode-solution/
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 将 edges 转换成 邻接矩阵的形式
        List<Node>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double probability = succProb[i];

            graph[a].add(new Node(b, probability));
            graph[b].add(new Node(a, probability));
        }
        // 使用一个 数组 记录 到达 点i 的最大 可能行
        double[] max = new double[n];
        max[start] = 1;
        // 使用有优先队列，按照 可能性 从大到小排列，
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return Double.compare(o2.probability, o1.probability);
                    }
                }
        );
        nodePriorityQueue.add(new Node(start, 1));
        // 每次找到 队列中 最大的可能性 poll 找到临界，更新每个点的可能行，如果 可能性比较大的花
        while (!nodePriorityQueue.isEmpty()) {
            Node poll = nodePriorityQueue.poll();
            double pollProbability = poll.probability;
            // 找到临界
            List<Node> nodes = graph[poll.node];
            for (Node next : nodes) {
                // 如果从 起点到 poll * next 还没有 现在这个大呢 这个点就不用了
                if (pollProbability * next.probability <= max[next.node]) {
                    continue;
                }
                max[next.node] = pollProbability * next.probability;
                nodePriorityQueue.add(new Node(next.node, max[next.node]));
            }
        }
        return max[end];
    }

    class Node {
        public int node;
        public double probability;

        public Node(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}
