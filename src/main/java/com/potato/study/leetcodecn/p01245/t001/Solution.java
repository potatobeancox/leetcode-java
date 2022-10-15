package com.potato.study.leetcodecn.p01245.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1245. 树的直径
 *
 * 给你这棵「无向树」，请你测算并返回它的「直径」：这棵树上最长简单路径的 边数。
 *
 * 我们用一个由所有「边」组成的数组 edges 来表示一棵无向树，其中 edges[i] = [u, v] 表示节点 u 和 v 之间的双向边。
 *
 * 树上的节点都已经用 {0, 1, ..., edges.length} 中的数做了标记，每个节点上的标记都是独一无二的。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：edges = [[0,1],[0,2]]
 * 输出：2
 * 解释：
 * 这棵树上最长的路径是 1 - 0 - 2，边数为 2。
 * 示例 2：
 *
 *
 *
 * 输入：edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * 输出：4
 * 解释：
 * 这棵树上最长的路径是 3 - 2 - 1 - 4 - 5，边数为 4。
 *  
 *
 * 提示：
 *
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * edges 会形成一棵无向树
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/tree-diameter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int treeDiameter(int[][] edges) {
        // 将 edges 转换成 graph
        List<List<Integer>> graph = new ArrayList<>();
        int n = 0;
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            n = Math.max(n, a);
            n = Math.max(n, b);
        }
        n++;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 先从某一个点出发 找到最远的点 bfs
        Queue<Integer> queue = new LinkedList<>();
        int start = 0;
        queue.add(start);
        boolean[] visit = new boolean[n];
        visit[start] = true;
        int target = -1;
        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();
            target = currentIndex;

            List<Integer> nextList = graph.get(currentIndex);
            for (int nextIndex : nextList) {
                if (visit[nextIndex]) {
                    continue;
                }
                visit[nextIndex] = true;
                queue.add(nextIndex);
            }
        }
        // 再从这个点出发 找到最远的点
        if (target == -1) {
            return -1;
        }
        // 从 target出发
        queue = new LinkedList<>();
        queue.add(target);
        visit = new boolean[n];
        visit[target] = true;
        int layer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                int currentIndex = queue.poll();
                List<Integer> nextList = graph.get(currentIndex);
                for (int nextIndex : nextList) {
                    if (visit[nextIndex]) {
                        continue;
                    }
                    visit[nextIndex] = true;
                    queue.add(nextIndex);
                }

            }
            layer++;
        }
        return layer - 1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,1],[0,2]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.treeDiameter(edges);
        System.out.println(i);
        Assert.assertEquals(2, i);


        input = "[[0,1],[1,2],[2,3],[1,4],[4,5]]";
        edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.treeDiameter(edges);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}