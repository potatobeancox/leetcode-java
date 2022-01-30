package com.potato.study.leetcodecn.p01473.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 1473. 粉刷房子 III
 *
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不可以被重新涂色。

 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）

 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：

 houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。

  

 示例 1：

 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 输出：9
 解释：房子涂色方案为 [1,2,2,1,1]
 此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
 涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
 示例 2：

 输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 输出：11
 解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
 此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
 给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
 示例 3：

 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
 输出：5
 示例 4：

 输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 输出：-1
 解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
  

 提示：

 m == houses.length == cost.length
 n == cost[i].length
 1 <= m <= 100
 1 <= n <= 20
 1 <= target <= m
 0 <= houses[i] <= n
 1 <= cost[i][j] <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/paint-house-iii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/paint-house-iii/solution/gong-shui-san-xie-san-wei-dong-tai-gui-h-ud7m/
     * @param houses
     * @param cost
     * @param m         房子数量
     * @param n         颜色数量
     * @param target    组成街区的数量
     * @return
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // dp ijk 将 第i个之前的房子 其中 i粉刷成j这个颜色，并形成了 k个社区 需要多少钱 最少
        int[][][] dp = new int[m+1][n+1][target+1];
        // 初始化 一下 所有0个街区都是不存在的
        int MAXINT = 0x3f3f3f3f; // 参考 https://www.cnblogs.com/DearDongchen/p/6917060.html
        // 初始化 所有 target 为0的情况
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = MAXINT;
            }
        }
        // 开始转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {// 当前房子是否已经有颜色
                if (houses[i-1] == j) {
//                    dp[][][] = ;
                    continue;
                }
                for (int k = 1; k <= target; k++) {
//                    else {
//
//                    }
                }
            }
        }
        // 遍历 最终结果 颜色 取最小的
        int min = MAXINT;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, dp[m][i][target]);
        }
        return min;

    }
}
