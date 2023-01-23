package com.potato.study.leetcodecn.p02378.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        // 其中的每个map key 是 临界的边对应的点 value 是临界的消耗
        for (int i = 0; i < edges.length; i++) {
            int parent = i;
            int child = edges[i][0];
            int value = edges[i][1];

            if (child == -1) {
                continue;
            }

            // 插入graph中
            if (!graph.contains(parent)) {
            }

        }
        // 从根开始0 往下找
//        return getMaxScore(0)[1];
        return -1;
    }

}
