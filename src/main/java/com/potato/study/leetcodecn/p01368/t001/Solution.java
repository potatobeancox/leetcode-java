package com.potato.study.leetcodecn.p01368.t001;


import com.potato.study.leetcode.domain.ListNode;
import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1368. 使网格图至少有一条有效路径的最小代价
 *
 * 给你一个 m x n 的网格图 grid 。 grid 中每个格子都有一个数字，对应着从该格子出发下一步走的方向。 grid[i][j] 中的数字可能为以下几种情况：

 1 ，下一步往右走，也就是你会从 grid[i][j] 走到 grid[i][j + 1]
 2 ，下一步往左走，也就是你会从 grid[i][j] 走到 grid[i][j - 1]
 3 ，下一步往下走，也就是你会从 grid[i][j] 走到 grid[i + 1][j]
 4 ，下一步往上走，也就是你会从 grid[i][j] 走到 grid[i - 1][j]
 注意网格图中可能会有 无效数字 ，因为它们可能指向 grid 以外的区域。

 一开始，你会从最左上角的格子 (0,0) 出发。我们定义一条 有效路径 为从格子 (0,0) 出发，每一步都顺着数字对应方向走，最终在最右下角的格子 (m - 1, n - 1) 结束的路径。有效路径 不需要是最短路径 。

 你可以花费 cost = 1 的代价修改一个格子中的数字，但每个格子中的数字 只能修改一次 。

 请你返回让网格图至少有一条有效路径的最小代价。

  

 示例 1：



 输入：grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 输出：3
 解释：你将从点 (0, 0) 出发。
 到达 (3, 3) 的路径为： (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) 花费代价 cost = 1 使方向向下 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) 花费代价 cost = 1 使方向向下 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) 花费代价 cost = 1 使方向向下 --> (3, 3)
 总花费为 cost = 3.
 示例 2：



 输入：grid = [[1,1,3],[3,2,2],[1,1,4]]
 输出：0
 解释：不修改任何数字你就可以从 (0, 0) 到达 (2, 2) 。
 示例 3：



 输入：grid = [[1,2],[4,3]]
 输出：1
 示例 4：

 输入：grid = [[2,2,2],[2,2,2]]
 输出：3
 示例 5：

 输入：grid = [[4]]
 输出：0
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/solution/by-stormsunshine-430l/
     * @param grid
     * @return
     */
    public int minCost(int[][] grid) {
        // dist 创建二维 表示从 00 出发 要走最少多少不才能走到
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        // 初始 dist都是最大值 只有开始点为 0 也放优先级进去
        for (int[] line : dist) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        // 可以到达的节点集合 还没有被遍历
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        priorityQueue.add(new int[] {0,0,0});
        // 每次pop 找到四个方向看看 方向和对应的值是不是匹配 不匹配要 + 1
        int[][] dir = new int[][] {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int x = poll[0];
            int y = poll[1];

            int alreadyCost = poll[2];
            // 找到临界的四个方向
            for (int i = 0; i < dir.length; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];
                // 坐标合法性
                if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                    continue;
                }
                // 判断新的cost 是不是需要增加
                int newCost = alreadyCost;
                if (grid[x][y] != i + 1) {
                    newCost++;
                }
                // 如果当前的要短
                if (newCost < dist[dx][dy]) {
                    // 判断是不是新的最小
                    dist[dx][dy] = newCost;
                    priorityQueue.add(new int[] {dx, dy, newCost});
                }
            }

        }
        return dist[m-1][n-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[1,1,3],[3,2,2],[1,1,4]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.minCost(grid);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }



}
