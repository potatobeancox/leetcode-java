package com.potato.study.leetcodecn.p01377.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1377. T 秒后青蛙的位置
 *
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：

 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 青蛙无法跳回已经访问过的顶点。
 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。

 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。

  

 示例 1：



 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 输出：0.16666666666666666
 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
 示例 2：



 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 输出：0.3333333333333333
 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
  

  

 提示：

 1 <= n <= 100
 edges.length == n - 1
 edges[i].length == 2
 1 <= ai, bi <= n
 1 <= t <= 50
 1 <= target <= n

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/frog-position-after-t-seconds
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        // 将 edges 转换成 顶点编号从 1 到 n 邻接矩阵形式
        List<Integer>[] grid = new List[n+1];
        // init convert edges
        for (int i = 1; i < n+1; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            grid[from].add(to);
            grid[to].add(from);
        }

        // 从1点开始进行 dfs计算
        int startNode = 1;
        // 因为记录的是 1-n的点 是否被访问过
        boolean[] seen = new boolean[n+1];
        return dfs(startNode, grid, t, target, seen);
    }

    /**
     * 深度优先找到 利用 t秒后步骤找到 target点概率
     * @param startNode
     * @param grid
     * @param t
     * @param target
     * @return
     */
    private double dfs(int startNode, List<Integer>[] grid,
                       int t, int target, boolean[] seen) {
        // 如果当前已经用尽了时间 返回 结束节点是不是 target
        if (t == 0 ) {
            if (startNode == target) {
                return 1;
            }
            return 0;
        }
        // 当前已经没有next节点走了 判断是不是到达了 target 到了 就是 1.0
        List<Integer> nextList = grid[startNode];
        if ((startNode != 1 && nextList.size() == 1)
                || (startNode == 1 && nextList.size() == 0)) {
            if (startNode == target) {
                return 1;
            }
            return 0;
        }
        // 当前点标记访问过
        seen[startNode] = true;
        // 往next 地方找 如果哪个找到了就累加起来
        double sum = 0;
        int childSize = nextList.size();
        for (int nextNode : nextList) {
            // 访问过就continue
            if (seen[nextNode]) {
                childSize--;
                continue;
            }
            // 没访问过计算概率
            sum += dfs(nextNode, grid, t-1, target, seen);
        }
        // * 1/next 点个数
        sum /= childSize;
        // 找到当前
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String str = "[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);

        double v = solution.frogPosition(7, edges, 2, 4);
        System.out.println(v);

        v = solution.frogPosition(7, edges, 20, 6);
        System.out.println(v);
    }
}
