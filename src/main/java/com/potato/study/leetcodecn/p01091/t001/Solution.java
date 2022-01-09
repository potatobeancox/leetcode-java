package com.potato.study.leetcodecn.p01091.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1091. 二进制矩阵中的最短路径
 *
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。

 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：

 路径途经的所有单元格都的值都是 0 。
 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 畅通路径的长度 是该路径途经的单元格总数。

  

 示例 1：


 输入：grid = [[0,1],[1,0]]
 输出：2
 示例 2：


 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 输出：4
 示例 3：

 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 输出：-1
  

 提示：

 n == grid.length
 n == grid[i].length
 1 <= n <= 100
 grid[i][j] 为 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestPathBinaryMatrix(int[][] grid) {
        //  第一个个 是否为 1
        if (grid[0][0] == 1) {
            return -1;
        }
        // 只有一个点
        if (grid.length == 1 && grid[0].length == 1) {
            return 1;
        }

        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {1, 1},
                {0, -1},
                {-1, 0},
                {-1, -1},
                {-1, 1},
                {1, -1},

        };

        // 内部存放坐标
        Queue<int[]> queue = new LinkedList<>();
        // queue 记录 之后要访问的点 set 放本层已经 背放入 queue 中的节点
        int pathLength = 0;
        queue.add(new int[]{0, 0});
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            // 直接搞右 下 和右边下边
            int size = queue.size();
            pathLength++;
            for (int i = 0; i < size; i++) {
                // 目前走到的点 path len 为到这个点需要走的次数
                int[] poll = queue.poll();

                for (int j = 0; j < directions.length; j++) {
                    // 判断 临界点
                    int di = poll[0] + directions[j][0];
                    int dj = poll[1] + directions[j][1];
                    if (di < 0 || di >= grid.length
                            || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }

                    if (grid[di][dj] == 1) {
                        continue;
                    }

                    if (visit[di][dj]) {
                        continue;
                    }

                    // 去重复
                    visit[di][dj] = true;
                    // 如果已经时终点了 。。。。
                    if (di == grid.length - 1
                            && dj == grid[0].length - 1) {
                        if (grid[di][dj] == 0) {
                            return pathLength + 1;
                        } else {
                            return -1;
                        }
                    }
                    // 没到终点
                    queue.add(new int[] {di, dj});

                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,1],[1,0]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.shortestPathBinaryMatrix(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);


        input = "[[0,1,1,0,0,0],[0,1,0,1,1,0],[0,1,1,0,1,0],[0,0,0,1,1,0],[1,1,1,1,1,0],[1,1,1,1,1,0]]";
        arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        i = solution.shortestPathBinaryMatrix(arr);
        System.out.println(i);
        Assert.assertEquals(14, i);
    }
}
