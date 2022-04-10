package com.potato.study.leetcodecn.p01334.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1334. 阈值距离内邻居最少的城市
 *
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。

 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。

 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。

  

 示例 1：



 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 输出：3
 解释：城市分布图如上。
 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 城市 0 -> [城市 1, 城市 2] 
 城市 1 -> [城市 0, 城市 2, 城市 3] 
 城市 2 -> [城市 0, 城市 1, 城市 3] 
 城市 3 -> [城市 1, 城市 2] 
 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 示例 2：



 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 输出：0
 解释：城市分布图如上。 
 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 城市 0 -> [城市 1] 
 城市 1 -> [城市 0, 城市 4] 
 城市 2 -> [城市 3, 城市 4] 
 城市 3 -> [城市 2, 城市 4]
 城市 4 -> [城市 1, 城市 2, 城市 3] 
 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
  

 提示：

 2 <= n <= 100
 1 <= edges.length <= n * (n - 1) / 2
 edges[i].length == 3
 0 <= fromi < toi < n
 1 <= weighti, distanceThreshold <= 10^4
 所有 (fromi, toi) 都是不同的。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 用一个 数组 dis 表示 两个点的距离 相同点距离 为 0
        int[][] dis = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                dis[i][j] = Integer.MAX_VALUE;
            }
        }


        // 遍历 edges 直接相连的距离就是 edges 的距离
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            int value = edges[i][2];

            dis[from][to] = value;
            dis[to][from] = value;
        }


        // 三重循环遍历 点 找到最小值
        for (int k = 0; k < n; k++) {
            // 中间点
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    // ik 和 kj 不可达
                    if (dis[i][k] >= Integer.MAX_VALUE || dis[k][j] >= Integer.MAX_VALUE) {
                        continue;
                    }
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        // 遍历每个临界点 找到 distanceThreshold 个数 计算最小的点
        int minCount = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                minIndex = i;
            }

        }
        return minIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        String str = "[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]";
        int[][] edges = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int distanceThreshold = 2;
        int theCity = solution.findTheCity(n, edges, distanceThreshold);
        System.out.println(theCity);
        Assert.assertEquals(0, theCity);
    }
}
