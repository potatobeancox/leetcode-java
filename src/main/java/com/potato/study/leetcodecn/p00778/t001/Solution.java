package com.potato.study.leetcodecn.p00778.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 778. 水位上升的泳池中游泳
 *
 * 在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。

 当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

 你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。

  

 示例 1:



 输入: grid = [[0,2],[1,3]]
 输出: 3
 解释:
 时间为0时，你位于坐标方格的位置为 (0, 0)。
 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 示例 2:



 输入: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 输出: 16
 解释: 最终的路线用加粗进行了标记。
 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
  

 提示:

 n == grid.length
 n == grid[i].length
 1 <= n <= 50
 0 <= grid[i][j] < n2
 grid[i][j] 中每个值 均无重复

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/swim-in-rising-water
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 778
    public int swimInWater(int[][] grid) {
        // 二分法 每次 使用mid 进行bfs 看能不能找到 n-1 n-1
        int n = grid.length;
        int left = 0;
        int right = n * n - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean checkRes = checkSwim(mid, grid);
            if (checkRes) {
                // 能抵达是不是还可以再小
                res = mid;
                right = mid - 1;
            } else {
                // 到不了 还得大
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 是不是 达到了 limit 能游过去
     * @param limit
     * @return
     */
    private boolean checkSwim(int limit, int[][] grid) {
        Queue<int[]> posQueue = new LinkedList<>();
        if (grid[0][0] > limit) {
            return false;
        }
        posQueue.add(new int[]{0, 0});
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        visit[0][0] = true;
        int[][] dir = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int n = grid.length;
        while (!posQueue.isEmpty()) {
            int[] poll = posQueue.poll();
            for (int i = 0; i < 4; i++) {
                int di = poll[0] + dir[i][0];
                int dj = poll[1] + dir[i][1];
                if (di < 0 || di >= n
                        || dj < 0 || dj >= n) {
                    continue;
                }
                if (visit[di][dj]) {
                    continue;
                }
                if (grid[di][dj] > limit) {
                    continue;
                }
                if (di == n-1 && dj == n-1) {
                    return true;
                }
                visit[di][dj] = true;
                posQueue.add(new int[]{di, dj});
            }
        }
        return visit[n-1][n-1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = new int[][] {
                {0}
        };
        int i = solution.swimInWater(grid);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }

}
