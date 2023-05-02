package com.potato.study.leetcodecn.p02658.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 *
 * 2658. 网格图中鱼的最大数目
 *
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，其中下标在 (r, c) 处的整数表示：
 *
 * 如果 grid[r][c] = 0 ，那么它是一块 陆地 。
 * 如果 grid[r][c] > 0 ，那么它是一块 水域 ，且包含 grid[r][c] 条鱼。
 * 一位渔夫可以从任意 水域 格子 (r, c) 出发，然后执行以下操作任意次：
 *
 * 捕捞格子 (r, c) 处所有的鱼，或者
 * 移动到相邻的 水域 格子。
 * 请你返回渔夫最优策略下， 最多 可以捕捞多少条鱼。如果没有水域格子，请你返回 0 。
 *
 * 格子 (r, c) 相邻 的格子为 (r, c + 1) ，(r, c - 1) ，(r + 1, c) 和 (r - 1, c) ，前提是相邻格子在网格图内。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
 * 输出：7
 * 解释：渔夫可以从格子 (1,3) 出发，捕捞 3 条鱼，然后移动到格子 (2,3) ，捕捞 4 条鱼。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
 * 输出：1
 * 解释：渔夫可以从格子 (0,0) 或者 (3,3) ，捕捞 1 条鱼。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * 0 <= grid[i][j] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-fish-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findMaxFish(int[][] grid) {
        // 遍历 每个位置 dfs 记录最终能不牢到的鱼 捕捞之后 将空格变成0
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                int res = dfs(grid, i, j);
                max = Math.max(max, res);
            }
        }
        return max;
    }

    /**
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    private int dfs(int[][] grid, int i, int j) {
        // 如果当前是陆地 直接返回 0
        if (grid[i][j] == 0) {
            return 0;
        }
        // 有鱼的话 捕鱼 并往四个方向 dfs
        int fishCount = grid[i][j];

        grid[i][j] = 0;
        // 4 方向
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int k = 0; k < 4; k++) {
            int[] direction = dir[k];

            int di = i + direction[0];
            int dj = j + direction[1];

            // 坐标有效性
            if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length) {
                continue;
            }

            int nextGetFishCount = dfs(grid, di, dj);

            fishCount += nextGetFishCount;
        }
        return fishCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int maxFish = solution.findMaxFish(grid);
        System.out.println(maxFish);
        Assert.assertEquals(7, maxFish);
    }

}
