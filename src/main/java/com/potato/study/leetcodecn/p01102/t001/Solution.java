package com.potato.study.leetcodecn.p01102.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 1102. 得分最高的路径
 *
 * 给定一个 m x n 的整数矩阵 grid，返回从 (0,0) 开始到 (m - 1, n - 1) 在四个基本方向上移动的路径的最大 分数 。

 一条路径的 分数 是该路径上的最小值。

 例如，路径 8 → 4 → 5 → 9 的得分为 4 。
  

 示例 1：



 输入：grid = [[5,4,5],[1,2,6],[7,4,6]]
 输出：4
 解释：得分最高的路径用黄色突出显示。
 示例 2：



 输入：grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 输出：2
 示例 3：



 输入：grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 输出：3
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 100
 0 <= grid[i][j] <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/path-with-maximum-minimum-value
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maximumMinimumPath(int[][] grid) {
        // 开始和结束点 的最小分数 最为上限值 0 作为下限值
        int left = 0;
        int right = Math.min(grid[0][0], grid[grid.length - 1][grid[0].length - 1]);
        // 二分查找 以 分数 k作为最小分数 是否能到达终点 可以的话 继续小，不能的话 往大了找
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean canReach = dfs(grid, mid, new boolean[grid.length][grid[0].length], 0, 0);
            if (canReach) {
                // 当前mid 大于等于 路径最小值 往大的尝试
                res = mid;
                left = mid + 1;
            } else {
                // 需要变小
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 以 target 作为最小分数 是否能到达 最后一个位置 从 00
     * @param grid
     * @param target
     * @return
     */
    private boolean dfs(int[][] grid, int target, boolean[][] visit,
            int i, int j) {
        // 如果当前 已经被访问 返回 false
        if (visit[i][j]) {
            return false;
        }
        // 如果当前 的 值小于 target 值返回 false
        if (grid[i][j] < target) {
            return false;
        }
        // 往之后的几个方向 走 如果遇到最后一个位置 那么直接返回true
        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        visit[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];
            // 边界
            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                continue;
            }
            // 是不是已经到了 位置
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                return true;
            }
            // 递归
            boolean dfsResult = dfs(grid, target, visit, di, dj);
            if (dfsResult) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[5,4,5],[1,2,6],[7,4,6]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.maximumMinimumPath(ints);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
