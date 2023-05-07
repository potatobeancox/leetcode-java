package com.potato.study.leetcodecn.p02664.t001;

import java.util.Arrays;

/**
 *
 * 2664. 巡逻的骑士
 *
 * 给定两个正整数 m 和 n ，它们是一个 下标从 0 开始 的二维数组 board 的高度和宽度。还有一对正整数 (r, c) ，它们是骑士在棋盘上的起始位置。

 你的任务是找到一个骑士的移动顺序，使得 board 中每个单元格都 恰好 被访问一次（起始单元格已被访问，不应 再次访问）。

 返回数组 board ，其中单元格的值显示从 0 开始访问该单元格的顺序（骑士的初始位置为 0）。

 注意，如果 0 <= r2 <= m-1 且 0 <= c2 <= n-1 ，并且 min(abs(r1-r2), abs(c1-c2)) = 1 且 max(abs(r1-r2), abs(c1-c2)) = 2 ，则骑士可以从单元格 (r1, c1) 移动到单元格 (r2, c2) 。

  

 示例 1 ：

 输入：m = 1, n = 1, r = 0, c = 0
 输出：[[0]]
 解释只有一个单元格，骑士最初在其中，因此 1x1 网格中只有一个 0。
 示例 2 ：

 输入：m = 3, n = 4, r = 0, c = 0
 输出：[[0,3,6,9],[11,8,1,4],[2,5,10,7]]
 解释：按照以下移动顺序，我们可以访问整个棋盘。
 (0,0)->(1,2)->(2,0)->(0,1)->(1,3)->(2,1)->(0,2)->(2,3)->(1,1)->(0,3)->(2,2)->(1,0)
  

 提示：

 1 <= m, n <= 5
 0 <= r <= m - 1
 0 <= c <= n - 1
 输入的数据保证在给定条件下至少存在一种访问所有单元格的移动顺序。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/the-knights-tour
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private boolean solved;
    private int[][] grid;
    private int limit;

    public int[][] tourOfKnight(int m, int n, int r, int c) {
        this.solved = false;
        this.grid = new int[m][n];
        this.limit = m * n;
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], -1);
        }
        grid[r][c] = 0;
        int val = 1;
        backTrack(r, c, val);
        return grid;
    }

    private void backTrack(int i, int j, int val) {
        if (val == this.limit) {
            this.solved = true;
            return;
        }
        // 找到ij 对应的下一个点 设置成val 递归往下找 如果找到了 返回 没找到就 重置
        int[][] dir = new int[][] {
                {1, 2},
                {-1, 2},
                {1, -2},
                {-1, -2},
                {2, 1},
                {-2, -1},
                {-2, 1},
                {2, -1}
        };

        for (int k = 0; k < 8; k++) {
            int di = i + dir[k][0];
            int dj = j + dir[k][1];

            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                continue;
            }

            if (grid[di][dj] != -1) {
                continue;
            }

            grid[di][dj] = val;
            backTrack(di, dj, val + 1);
            if (this.solved) {
                return;
            }
            grid[di][dj] = -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
