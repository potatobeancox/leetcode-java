package com.potato.study.leetcodecn.p00265.t001;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 265. 粉刷房子 II

 *
 * 假如有一排房子共有 n 幢，每个房子可以被粉刷成 k 种颜色中的一种。房子粉刷成不同颜色的花费成本也是不同的。你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 每个房子粉刷成不同颜色的花费以一个 n x k 的矩阵表示。
 *
 * 例如，costs[0][0] 表示第 0 幢房子粉刷成 0 号颜色的成本；costs[1][2] 表示第 1 幢房子粉刷成 2 号颜色的成本，以此类推。
 * 返回 粉刷完所有房子的最低成本 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: costs = [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释:
 * 将房子 0 刷成 0 号颜色，房子 1 刷成 2 号颜色。花费: 1 + 4 = 5;
 * 或者将 房子 0 刷成 2 号颜色，房子 1 刷成 0 号颜色。花费: 3 + 2 = 5.
 * 示例 2:
 *
 * 输入: costs = [[1,3],[2,4]]
 * 输出: 5
 *  
 *
 * 提示：
 *
 * costs.length == n
 * costs[i].length == k
 * 1 <= n <= 100
 * 2 <= k <= 20
 * 1 <= costs[i][j] <= 20
 *  
 *
 * 进阶：您能否在 O(nk) 的时间复杂度下解决此问题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/paint-house-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int minCostII(int[][] costs) {
        // dp ij
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        // dp 0 就是 cost 0
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                // 不是 j 的min
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        min = Math.min(min, dp[i-1][l]);
                    }
                }
                dp[i][j] = min + costs[i][j];
            }
        }

        int totalMin = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            totalMin = Math.min(totalMin, dp[n-1][i]);
        }
        return totalMin;
    }


}
