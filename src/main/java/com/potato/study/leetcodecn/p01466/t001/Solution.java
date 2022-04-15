package com.potato.study.leetcodecn.p01466.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1466. 重新规划路线
 *
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 *
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 *
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 *
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 *
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 3：
 *
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int minStep;
    private boolean[] visited;
    private List<Integer>[] connected;
    private List<Integer>[] connectedUndirected;

    public int minReorder(int n, int[][] connections) {
        // 将 connections 转换成 邻接矩阵 有和有方向的邻接矩阵 从 0开始 dfs 记录visited

        this.connected = new List[n];
        this.connectedUndirected = new List[n];

        for (int i = 0; i < n; i++) {
            connected[i] = new ArrayList<>();
            connectedUndirected[i] = new ArrayList<>();
        }

        for (int i = 0; i < connections.length; i++) {
            // connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
            int a = connections[i][0];
            int b = connections[i][1];

            connected[a].add(b);

            connectedUndirected[a].add(b);
            connectedUndirected[b].add(a);
        }

        this.visited = new boolean[n];
        this.minStep = 0;

        dfs(0, n);

        return minStep;
    }

    private void dfs(int index, int n) {
        // 找到这个点邻接的点
        visited[index] = true;
        List<Integer> nextList = connectedUndirected[index];

        for (int i : nextList) {
            if (visited[i]) {
                continue;
            }
            // 没有访问过 从 i到index
            if (!connectedUndirected[i].contains(index)) {
                // 没有相邻
                continue;
            }
            // 方向不同 从 i能走到 index
            if (!connected[i].contains(index)) {
                this.minStep++;
            }
            // 往后visited
            dfs(i, n);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        String str = "[[0,1],[1,3],[2,3],[4,0],[4,5]]";
        int[][] connections = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.minReorder(n, connections);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
