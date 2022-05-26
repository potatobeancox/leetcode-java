package com.potato.study.leetcodecn.p01129.t001;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

/**
 * 1129. 颜色交替的最短路径
 *
 * 在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
 *
 * red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
 *
 * 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * 输出：[0,1,-1]
 * 示例 2：
 *
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * 输出：[0,1,-1]
 * 示例 3：
 *
 * 输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * 输出：[0,-1,-1]
 * 示例 4：
 *
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * 输出：[0,1,2]
 * 示例 5：
 *
 * 输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * 输出：[0,1,1]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-path-with-alternating-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int n;
    /**
     * https://leetcode.cn/problems/shortest-path-with-alternating-colors/solution/java-shen-du-you-xian-sou-suo-by-capta1n/
     * @param n
     * @param redEdges
     * @param blueEdges
     * @return
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        this.n = n;
        // 将 redEdges 和 blueEdges 转换成 list 存储 index 的第一个点
        List<Integer>[] redEdgeList = new List[n];
        List<Integer>[] blueEdgesList = new List[n];
        for (int i = 0; i < n; i++) {
            redEdgeList[i] = new ArrayList<>();
            blueEdgesList[i] = new ArrayList<>();
        }
        // 遍历 redEdges blueEdges 生成结果 有向图
        for (int[] edge : redEdges) {
            int from = edge[0];
            int to = edge[1];
            redEdgeList[from].add(to);
        }
        for (int[] edge : blueEdges) {
            int from = edge[0];
            int to = edge[1];
            blueEdgesList[from].add(to);
        }
        //  ans i 表示从 0 开始 到 i 最小路径长度 0 表示最后一个点选择 红的 1表示最后一个点选择蓝色的
        int[][] ans = new int[n][2];
        // 初始值
        for (int i = 0; i < n; i++) {
            Arrays.fill(ans[i], Integer.MAX_VALUE / 2);
        }
        // ans 0 =0 从 0到0 当然不需要节点
        ans[0][0] = 0;
        ans[0][1] = 0;
        // 分别从 0开始 选择 红色和蓝色，往下走 更新 最小距离 dfs 两次 dfs 生成结果 ans
        int currentIndex = 0;
        // 下一个点选择 红色
        dfs(redEdgeList, blueEdgesList, ans, currentIndex, 0);
        // 下一个点选择 蓝色
        dfs(redEdgeList, blueEdgesList, ans, currentIndex, 1);
        // 通过结果生成最小的距离
        int[] minDis = new int[n];
        for (int i = 0; i < n; i++) {
            minDis[i] = Math.min(ans[i][0], ans[i][1]);
            if (minDis[i] ==  Integer.MAX_VALUE / 2) {
                minDis[i] = -1;
            }
        }
        return minDis;
    }

    private void dfs(List<Integer>[] redEdgeList, List<Integer>[] blueEdgesList, int[][] ans,
            int currentIndex, int color) {
        if (currentIndex >= this.n) {
            return;
        }
        boolean selectRed = color == 0;
        // 找到下一个 选择的节点
        List<Integer>[] list;
        if (selectRed) {
            list = redEdgeList;
        } else {
            list = blueEdgesList;
        }
        // 当前点的邻接点
        for (int nextIndex : list[currentIndex]) {
            // 如果   currentIndex 和  nextIndex 链接 比较一下 也是用 同一个颜色结尾的大小
            int currentLength = ans[currentIndex][1 - color] + 1;
            // 没有找到 更小的 不再dfs
            if (currentLength > ans[nextIndex][color]) {
                continue;
            }
            ans[nextIndex][color] = currentLength;
            // 继续dfs 找下一个节点
            dfs(redEdgeList, blueEdgesList, ans, nextIndex, 1-color);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] redEdges = new int[][] {
                {0,1},
                {1,2}
        };
        int[][] blueEdges = new int[0][0];
        int[] ints = solution.shortestAlternatingPaths(n, redEdges, blueEdges);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[] {
                0,1,-1
        }, ints);
    }
}
