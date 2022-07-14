package com.potato.study.leetcodecn.p00827.t001;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 827. 最大人工岛
 *
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 *
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 *
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 *
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 *
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *  
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/making-a-large-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 827
    public int largestIsland(int[][] grid) {
        // dfs 陆地 将其 改成 2 - 往上的编号 并维护 每个号码对应的 岛屿大小 放入map 中
        int value = 2;
        int maxArea = 0;
        Map<Integer, Integer> areaMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                // dfs 获取面积 并更新 值为value
                int area = dfsGetArea(i, j, value, grid);
                // 用于剪枝
                maxArea = Math.max(maxArea, area);
                areaMap.put(value, area);
                // value 用过了
                value++;
            }
        }
        // 剪枝 没有岛屿的极端情况
        if (maxArea == 0) {
            return 1;
        }
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        // 遍历水面 对每一种求和 + 1 求最大值
        int globalMaxArea = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                // 4个方向
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int di = i + dir[k][0];
                    int dj = j + dir[k][1];
                    // 边界条件
                    if (di < 0 || di >= grid.length
                            || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }
                    if (grid[di][dj] != 0) {
                        set.add(grid[di][dj]);
                    }
                }
                // 计算最大值
                int thisArea = 1;
                for (int val : set) {
                    thisArea += areaMap.get(val);
                }
                globalMaxArea = Math.max(globalMaxArea, thisArea);
            }
        }
        return Math.max(globalMaxArea, maxArea);
    }

    /**
     *
     * @param i
     * @param j
     * @param value
     * @param grid
     * @return
     */
    private int dfsGetArea(int i, int j, int value, int[][] grid) {
        // 防呆
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        // 从 ij 开始 遍历
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int total = 1;
        grid[i][j] = value;
        for (int k = 0; k < 4; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];
            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                continue;
            }
            if (grid[di][dj] != 1) {
                continue;
            }
            total += dfsGetArea(di, dj, value, grid);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,0],[0,1]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.largestIsland(grid);
        System.out.println(i);
        Assert.assertEquals(3, i);


        input = "[[1,1],[1,1]]";
        grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.largestIsland(grid);
        System.out.println(i);
        Assert.assertEquals(4, i);


        input = "[[1,1],[1,0]]";
        grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.largestIsland(grid);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
