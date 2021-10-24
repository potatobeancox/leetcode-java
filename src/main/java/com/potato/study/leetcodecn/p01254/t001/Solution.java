package com.potato.study.leetcodecn.p01254.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1254. 统计封闭岛屿的数目
 *
 * 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。

 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。

 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。

 请返回封闭岛屿的数目。

  

 示例 1：



 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 输出：2
 解释：
 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 示例 2：



 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 输出：1
 示例 3：

 输入：grid = [[1,1,1,1,1,1,1],
              [1,0,0,0,0,0,1],
              [1,0,1,1,1,0,1],
              [1,0,1,0,1,0,1],
              [1,0,1,1,1,0,1],
              [1,0,0,0,0,0,1],
 [1,1,1,1,1,1,1]]
 输出：2
  

 提示：

 1 <= grid.length, grid[0].length <= 100
 0 <= grid[i][j] <=1

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-closed-islands
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int closedIsland(int[][] grid) {
        // 使用 visit 记录 是否被访问过 从 grid 每个位置开始遍历 dfs
        int count = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        boolean[][] reachEdgeFlag = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visit[i][j]) {
                    continue;
                }
                if (grid[i][j] != 0) {
                    continue;
                }
                boolean reachEdge = dfs(grid, visit, i, j, reachEdgeFlag);
                if (!reachEdge) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param grid
     * @param visit
     * @param i
     * @param j
     * @return 是否达到边缘
     */
    private boolean dfs(int[][] grid, boolean[][] visit, int i, int j, boolean[][] reachEdgeFlag) {
        int[][] direction = new int[][] {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        visit[i][j] = true;
        boolean reachEdge = false;
        for (int k = 0; k < 4; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            if (di < 0 || di >= grid.length
                    || dj < 0 || dj >= grid[0].length) {
                reachEdge = true;
                continue;
            }
            if (visit[di][dj]) {
                reachEdge = reachEdge || reachEdgeFlag[di][dj];
                continue;
            }
            if (grid[di][dj] != 0) {
                continue;
            }
            reachEdge = reachEdge || dfs(grid, visit, di, dj, reachEdgeFlag);
        }
        reachEdgeFlag[i][j] = reachEdge;
        return reachEdge;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = new int[][] {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };
        // [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]

        int i = solution.closedIsland(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);


        arr = new int[][] {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };
        // [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]

        i = solution.closedIsland(arr);
        System.out.println(i);
        Assert.assertEquals(2, i);

        arr = new int[][] {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };
        String input =
               "[[0,0,1,1,0,1,0,0,1,0]," +
                "[1,1,0,1,1,0,1,1,1,0]," +
                "[1,0,1,1,1,0,0,1,1,0]," +
                "[0,1,1,0,0,0,0,1,0,1]," +
                "[0,0,0,0,0,0,1,1,1,0]," +
                "[0,1,0,1,0,1,0,1,1,1]," +
                "[1,0,1,0,1,1,0,0,0,1]," +
                "[1,1,1,1,1,1,0,0,0,0]," +
                "[1,1,1,0,0,1,0,1,0,1]," +
                "[1,1,1,0,1,1,0,1,1,0]]";
        int[][] arrayTwoDimensional = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);


        i = solution.closedIsland(arrayTwoDimensional);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }
}
