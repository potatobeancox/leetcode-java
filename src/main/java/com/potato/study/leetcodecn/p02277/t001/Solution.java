package com.potato.study.leetcodecn.p02277.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 2277. 树中最接近路径的节点
 *
 * 给定一个正整数 n，表示树中的节点数，编号从 0 到 n - 1 (含边界)。还给定一个长度为 n - 1 的二维整数数组 edges，其中 edges[i] = [node1i, node2i] 表示有一条 双向 边连接树中的 node1i 和 node2i。

 给定一个长度为 m ，下标从 0 开始 的整数数组 query，其中 query[i] = [starti, endi, nodei] 意味着对于第 i 个查询，您的任务是从 starti 到 endi 的路径上找到 最接近 nodei 的节点。

 返回长度为 m 的整数数组 answer，其中 answer[i] 是第 i 个查询的答案。

  

 示例 1:


 输入: n = 7, edges = [[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]], query = [[5,3,4],[5,3,6]]
 输出: [0,2]
 解释:
 节点 5 到节点 3 的路径由节点 5、2、0、3 组成。
 节点 4 到节点 0 的距离为 2。
 节点 0 是距离节点 4 最近的路径上的节点，因此第一个查询的答案是 0。
 节点 6 到节点 2 的距离为 1。
 节点 2 是距离节点 6 最近的路径上的节点，因此第二个查询的答案是 2。
 示例 2:


 输入: n = 3, edges = [[0,1],[1,2]], query = [[0,1,2]]
 输出: [1]
 解释:
 从节点 0 到节点 1 的路径由节点 0,1 组成。
 节点 2 到节点 1 的距离为 1。
 节点 1 是距离节点 2 最近的路径上的节点，因此第一个查询的答案是 1。
 示例 3:


 输入: n = 3, edges = [[0,1],[1,2]], query = [[0,0,0]]
 输出: [0]
 解释:
 节点 0 到节点 0 的路径由节点 0 组成。
 因为 0 是路径上唯一的节点，所以第一个查询的答案是0。
  

 提示:

 1 <= n <= 1000
 edges.length == n - 1
 edges[i].length == 2
 0 <= node1i, node2i <= n - 1
 node1i != node2i
 1 <= query.length <= 1000
 query[i].length == 3
 0 <= starti, endi, nodei <= n - 1
 这个图是一个树。



 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/closest-node-to-path-in-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] closestNode(int n, int[][] edges, int[][] query) {
        // 将 edges  编号从 0 到 n - 1 转换成 以0作为根的数 用一个 列表存孩子
        List<Integer>[] grid = new List[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                int from = edges[i][0];
                int to = edges[i][1];

                grid[from].add(to);
                grid[to].add(from);

            }
        }
        // 遍历上面生成的结果 生成 2个数组 parent 记录每个index 点的父亲和一个 depth 记录每个点的深度
        int[] parent = new int[n];
        int[] depth = new int[n];

        Arrays.fill(depth, -1);

        // 从 0 开始生成
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        depth[0] = 0;
        parent[0] = 0;

        while (!queue.isEmpty()) {
            int index = queue.poll();
            // 找到邻接点
            for (int nextIndex : grid[index]) {
                // 如果生成过就跳过
                if (depth[nextIndex] != -1) {
                    continue;
                }
                parent[nextIndex] = index;
                depth[nextIndex] = 1 + depth[index];

                queue.add(nextIndex);
            }
        }
        // 遍历 query 对于每2个点 获取他们的 最近公共长辈
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {

            int[] q = query[i];

            int a = q[0];
            int b = q[1];
            int target = q[2];

            int p1 = findCommonParent(a, b, parent, depth);
            int p2 = findCommonParent(a, target, parent, depth);
            int p3 = findCommonParent(b, target, parent, depth);

            // 分别求 目的地点到3个父亲的差值 选择最小的就是返回的点
            int d1 = depth[target] - depth[p1];
            int d2 = depth[target] - depth[p2];
            int d3 = depth[target] - depth[p3];

            int targetIndex;

            if (d1 <= d2 && d1 <= d3) {
                targetIndex = p1;
            } else if (d2 <= d1 && d2 <= d3) {
                targetIndex = p2;
            } else {
                // d3
                targetIndex = p3;
            }

            result[i] = targetIndex;
        }
        return result;
    }

    /**
     * 找到 p1 p2的公共长辈
     * @param p1
     * @param p2
     * @param parent
     * @param depth
     * @return
     */
    private int findCommonParent(int p1, int p2, int[] parent, int[] depth) {
        int i1 = p1;
        int i2 = p2;
        while (depth[i1] < depth[i2]) {
            i2 = parent[i2];
        }
        while (depth[i1] > depth[i2]) {
            i1 = parent[i1];
        }
        // 相等了 一起找
        while (i1 != i2) {
            i1 = parent[i1];
            i2 = parent[i2];
        }
        return i1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]]");
        int[][] query = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[5,3,4],[5,3,6]]");
        int[] ints = solution.closestNode(7, grid, query);
        System.out.println(Arrays.toString(ints));
        Assert.assertArrayEquals(new int[]{

        }, ints);
    }
}