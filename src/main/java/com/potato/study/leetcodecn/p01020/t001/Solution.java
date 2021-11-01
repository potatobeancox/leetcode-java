package com.potato.study.leetcodecn.p01020.t001;

import com.potato.study.leetcode.util.ArrayUtil;
import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 1020. 飞地的数量
 *
 * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。

 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。

 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。

  

 示例 1：

 输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 输出：3
 解释：
 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 示例 2：

 输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 输出：0
 解释：
 所有 1 都在边界上或可以到达边界。
  

 提示：

 1 <= A.length <= 500
 1 <= A[i].length <= 500
 0 <= A[i][j] <= 1
 所有行的大小都相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/number-of-enclaves
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int[][] direction;

    /**
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        // 从每一个 grid 位置开始走 走完就水替代
        this.direction = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                int res = dfs(grid, i, j);
                if (res > 0) {
                    total += res;
                }
            }
        }
        return total;
    }



    /**
     *  从ij 位置开始走 并记录数量，如果遇到 边界 返回-1
     * @param grid
     * @param i
     * @param j
     * @return
     */
    private int dfs(int[][] grid, int i, int j) {
        // 遇到边界
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return -1;
        }
        // 没有到边界 判断一下当前值
        if (grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        // 往4个方向走
        int total = 1;
        boolean isToLimit = false;
        for (int k = 0; k < 4; k++) {
            int di = i + direction[k][0];
            int dj = j + direction[k][1];
            int res = dfs(grid, di, dj);
            if (res >= 0) {
                total += res;
            } else {
                // 遇到了陆地
                isToLimit = true;
            }
        }

        if (isToLimit) {
            return -1;
        }

        // 被水包围
        return total;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.numEnclaves(arr);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }


}
