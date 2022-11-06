package com.potato.study.leetcodecn.p01730.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1730. 获取食物的最短路径
 *
 * 你现在很饿，想要尽快找东西吃。你需要找到最短的路径到达一个食物所在的格子。

 给定一个 m x n 的字符矩阵 grid ，包含下列不同类型的格子：

 '*' 是你的位置。矩阵中有且只有一个 '*' 格子。
 '#' 是食物。矩阵中可能存在多个食物。
 'O' 是空地，你可以穿过这些格子。
 'X' 是障碍，你不可以穿过这些格子。
 返回你到任意食物的最短路径的长度。如果不存在你到任意食物的路径，返回 -1。

  

 示例 1:


 输入： grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 输出： 3
 解释： 要拿到食物，你需要走 3 步。
 Example 2:


 输入： grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 输出： -1
 解释： 你不可能拿到食物。
 示例 3:


 输入: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 输出: 6
 解释: 这里有多个食物。拿到下边的食物仅需走 6 步。
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 200
 grid[row][col] 是 '*'、 'X'、 'O' 或 '#' 。
 grid 中有且只有一个 '*' 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/shortest-path-to-get-food
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int getFood(char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        // 找到 * 位置
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '*') {
                    queue.add(new int[] {i, j});
                    visit[i][j] = true;
                    break;
                }
            }
            if (queue.size() > 0) {
                break;
            }
        }
        // 开始找 直到 遇到了 #
        int step = 0;
        int[][] direction = new int[][] {
                {-1, 0},
                {1, 0},
                {0, 1},
                {0, -1}
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 不是饼干 计算路程 继续
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 是饼干 返回目前路程
                // 往后找
                for (int j = 0; j < direction.length; j++) {
                    int di = poll[0] + direction[j][0];
                    int dj = poll[1] + direction[j][1];
                    if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length) {
                        continue;
                    }
                    if (visit[di][dj]) {
                        continue;
                    }
                    if (grid[di][dj] == '#') {
                        return step + 1;
                    }

                    if (grid[di][dj] == 'O') {
                        queue.add(new int[] {di, dj});
                        visit[di][dj] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = LeetcodeInputUtils.inputString2CharArrayTwoDimensional("[[\"X\",\"X\",\"X\",\"X\",\"X\",\"X\"],[\"X\",\"*\",\"O\",\"O\",\"O\",\"X\"],[\"X\",\"O\",\"O\",\"#\",\"O\",\"X\"],[\"X\",\"X\",\"X\",\"X\",\"X\",\"X\"]]");
        int food = solution.getFood(grid);
        System.out.println(food);
        Assert.assertEquals(3, food);
    }
}
