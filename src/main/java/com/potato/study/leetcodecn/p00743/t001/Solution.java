package com.potato.study.leetcodecn.p00743.t001;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 743. 网络延迟时间
 *
 * 有 n 个网络节点，标记为 1 到 n。

 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。

 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。

  

 示例 1：



 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 输出：2
 示例 2：

 输入：times = [[1,2,1]], n = 2, k = 1
 输出：1
 示例 3：

 输入：times = [[1,2,1]], n = 2, k = 2
 输出：-1
  

 提示：

 1 <= k <= n <= 100
 1 <= times.length <= 6000
 times[i].length == 3
 1 <= ui, vi <= n
 ui != vi
 0 <= wi <= 100
 所有 (ui, vi) 对都 互不相同（即，不含重复边）

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/network-delay-time
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 将 times 转换成 邻接矩阵 表示形式
        int[][] graph = new int[n+1][n+1];
        // -1 代表不可达
        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }
        for (int i = 0; i < times.length; i++) {
            int ii = times[i][0];
            int jj = times[i][1];
            int value = times[i][2];
            graph[ii][jj] = value;
        }
        boolean[] visit = new boolean[n+1];
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE - 1);
        dis[k] = 0;
        // 控制遍历 n 次 每次先找到 未访问过的最小的点开始进行 以这个点作为开始点 判断 是够有更小的可能 有的话 更新 （枚举 邻接点）
        for (int i = 0; i < n; i++) {
            // 每次先找到 未访问过的最小的点开始进行
            int from = -1;
            for (int j = 1; j <= n; j++) {
                if (visit[j]) {
                    continue;
                }
                if (from == - 1 || dis[from] > dis[j]) {
                    from = j;
                }
            }
            // 遍历其他点
            for (int to = 1; to <= n; to++) {
                if (graph[from][to] == -1) {
                    continue;
                }
                if (dis[to] > dis[from] + graph[from][to]) {
                    dis[to] = dis[from] + graph[from][to];
                }
            }
            visit[from] = true;
        }
        // 遍历 dis 找到最大的点
        int max = dis[1];
        for (int i = 1; i < dis.length; i++) {
            max = Math.max(dis[i], max);
        }
        return max == Integer.MAX_VALUE - 1 ? -1 : max;
    }

    public static void main(String[] args) {
        int[][] times = new int[][] {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        };
        int n = 4;
        int k = 2;
        Solution solution = new Solution();
        int i = solution.networkDelayTime(times, n, k);
        System.out.println(i);
        Assert.assertEquals(2, i);


    }


}
