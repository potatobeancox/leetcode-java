package com.potato.study.leetcodecn.p00694.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 694. 不同岛屿的数量
 *
 * 给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。

 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。

  

 示例 1：

 11000
 11000
 00011
 00011
 给定上图，返回结果 1 。

 示例 2：

 11011
 10000
 00001
 11011
 给定上图，返回结果 3 。

 注意：

 11
 1
 和

 1
 11
 是不同的岛屿，因为我们不考虑旋转、翻转操作。

  

 提示：二维数组每维的大小都不会超过 50 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/number-of-distinct-islands
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numDistinctIslands(int[][] grid) {
        // 每个位置 开始 dfs 找到岛屿 范围 中间记录 来的方向 1234 这种 用set 记录字符串
        Set<String> set = new HashSet<>();
        // 从每个位置 开始 找 如果是 0 continu
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                dfs(i, j, builder, 5, grid);
                set.add(builder.toString());
            }
        }
        return set.size();
    }

    private void dfs(int i, int j, StringBuilder builder, int from, int[][] grid) {
        builder.append(from);
        // 防止之后 再次遍历
        grid[i][j] = 0;
        int[][] dir = new int[][] {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        for (int k = 0; k < 4; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];

            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                continue;
            }

            if (grid[di][dj] == 0) {
                continue;
            }
            dfs(di, dj, builder, k, grid);
        }

        builder.append(from * -1);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.numDistinctIslands(grid);
        System.out.println(i);
        Assert.assertEquals(3, i);


        input =
                "[[0,0,1,0,1,0,1,1,1,0,0,0,0,1,0,0,1,0,0,1,1,1,0,1,1,1,0,0,0,1,1,0,1,1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0]," +
                "[0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,1,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,1,0,1,0,0,0]," +
                "[0,1,0,1,0,1,1,1,0,0,1,1,0,0,0,0,1,0,1,0,1,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,1,0,0,1,0]," +
                "[1,0,1,0,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,0,0,1,0,1,0,0,1,0,1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1]]";
        grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.numDistinctIslands(grid);
        System.out.println(i);
        Assert.assertEquals(15, i);
    }
}
