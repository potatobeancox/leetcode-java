package com.potato.study.leetcodecn.p02378.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2378. 选择边来最大化树的得分
 *
 * 给定一个 加权 树，由 n 个节点组成，从 0 到 n - 1。
 *
 * 该树以节点 0 为 根，用大小为 n 的二维数组 edges 表示，其中 edges[i] = [pari, weighti] 表示节点 pari 是节点 i 的 父 节点，它们之间的边的权重等于 weighti。因为根结点 没有 父结点，所以有 edges[0] = [-1, -1]。
 *
 * 从树中选择一些边，使所选的两条边都不 相邻，所选边的权值之 和 最大。
 *
 *  
 *
 * 返回所选边的 最大 和。
 *
 * 注意:
 *
 * 你可以 不选择 树中的任何边，在这种情况下权值和将为 0。
 * 如果树中的两条边 Edge1 和 Edge2 有一个 公共 节点，它们就是 相邻 的。
 * 换句话说，如果 Edge1连接节点 a 和 b, Edge2 连接节点 b 和 c，它们是相邻的。
 *  
 *
 * 示例 1:
 *
 *
 * 输入: edges = [[-1,-1],[0,5],[0,10],[2,6],[2,4]]
 * 输出: 11
 * 解释: 上面的图表显示了我们必须选择红色的边。
 * 总分是 5 + 6 = 11.
 * 可以看出，没有更好的分数可以获得。
 * 示例 2:
 *
 *
 * 输入: edges = [[-1,-1],[0,5],[0,-6],[0,7]]
 * 输出: 7
 * 解释: 我们选择权值为 7 的边。
 * 注意，我们不能选择一条以上的边，因为所有的边都是彼此相邻的。
 *  
 *
 * 提示:
 *
 * n == edges.length
 * 1 <= n <= 105
 * edges[i].length == 2
 * par0 == weight0 == -1
 * i >= 1 时 0 <= pari <= n - 1 。
 * pari != i
 * i >= 1 时 -106 <= weighti <= 106 。
 * edges 表示有效的树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/choose-edges-to-maximize-score-in-a-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public long maxScore(int[][] edges) {
        // edges[i] = [pari, weighti] 转换成临界矩阵模式
        List<Map<Integer, Integer>> graph = new ArrayList<>();
        // n == edges.length
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            graph.add(new HashMap<>());
        }

        // 其中的每个map key 是 临界的边对应的点 value 是临界的消耗
        for (int i = 0; i < edges.length; i++) {
            int child = i;
            int parent = edges[i][0];
            int value = edges[i][1];
            if (parent == -1) {
                continue;
            }
            // 插入graph中
            graph.get(parent).put(child, value);
        }
        // 从根开始0 往下找
        return getMaxScore(0, graph)[1];
    }

    /**
     * 返回值 0表示用了这个点能获得的最大值， 1是不用这个点能获得的最大值 ，这个点作为终点
     * @param node  从哪个节点开始找
     * @return
     */
    private long[] getMaxScore(int node, List<Map<Integer, Integer>> graph) {
        // 从 node 开始 遍历每个 child 找到其得分 记录2个得分 1选择node 作为 终点的得分 不选择的得分 '
        long choose = 0;
        long ignore = 0;
        Map<Integer, Integer> childrenMap = graph.get(node);

        PriorityQueue<Long> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int next : childrenMap.keySet()) {
            // 不选当前node 那么孩子都能选
            long[] nextMaxScore = getMaxScore(next, graph);
            // 选择了 后续都跳过 ，不选择，后续要选一个最大的
            choose += nextMaxScore[1];
            // 不选这个的话 其他next的都不能选临街的，选不临街的 ，同时这个点需要选择一下
            ignore += nextMaxScore[1];
            // 如果选择 node -》 next 点 那么 得选一个最大的
            priorityQueue.add(graph.get(node).get(next) + nextMaxScore[0] - nextMaxScore[1]);
        }
        if (!priorityQueue.isEmpty()) {
            // 如果已经是小雨0的 那就不选了
            ignore += Math.max(0, priorityQueue.peek());
        }
        return new long[] {choose, ignore};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[-1,-1],[0,5],[0,10],[2,6],[2,4]]");
        long l = solution.maxScore(edges);
        System.out.println(l);
        Assert.assertEquals(11, l);
    }

}
