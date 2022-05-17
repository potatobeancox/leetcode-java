package com.potato.study.leetcodecn.p01391.t001;


import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1391. 检查网格中是否存在有效路径
 *
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：

 1 表示连接左单元格和右单元格的街道。
 2 表示连接上单元格和下单元格的街道。
 3 表示连接左单元格和下单元格的街道。
 4 表示连接右单元格和下单元格的街道。
 5 表示连接左单元格和上单元格的街道。
 6 表示连接右单元格和上单元格的街道。


 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。

 注意：你 不能 变更街道。

 如果网格中存在有效的路径，则返回 true，否则返回 false 。

  

 示例 1：



 输入：grid = [[2,4,3],[6,5,2]]
 输出：true
 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 示例 2：



 输入：grid = [[1,2,1],[1,2,1]]
 输出：false
 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 示例 3：

 输入：grid = [[1,1,2]]
 输出：false
 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 示例 4：

 输入：grid = [[1,1,1,1,1,1,3]]
 输出：true
 示例 5：

 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 输出：true
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 300
 1 <= grid[i][j] <= 6

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 初始化 记录 mn
    private int m;
    private int n;
    // 初始化 记录 4个方向
    private int[][] dir = new int[][] {
            {0, -1}, // 往左 0
            {0, 1},  // 往右 1
            {-1, 0}, // 往上 2
            {1, 0}   // 往下 3
    };
    // 初始化 每个 街道对应的方向改变
    private int[][] streets = new int[][] {
            {},
            {0, 1},  // street 1
            {2, 3},  // street 2
            {0, 3},  // street 3
            {1, 3},  // street 4
            {0, 2},  // street 5
            {1, 2}   // street 6
    };

    /**
     *
     * https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/solution/1391-jian-cha-wang-ge-zhong-shi-fou-cun-r8mp3/
     * @param grid
     * @return
     */
    public boolean hasValidPath(int[][] grid) {
        // 记录每个方向
        this.m = grid.length;
        this.n = grid[0].length;
        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int i, int j, int pre, int[][] grid) {
        // ij 是否越界
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        // ij 是否已经 找过了
        if (grid[i][j] == 0) {
            return false;
        }
        int current = grid[i][j];
        grid[i][j] = 0;
        // pre 跟当前点是否连通
        if (!check(pre, streets[current][0]) && !check(pre, streets[current][1])) {
            return false;
        }
        // 找到m-1 n-1
        if (i==m-1 && j==n-1) {
            return true;
        }
        // 找到当前点的两个位置 往后dfs 找
        for (int dirIndex : streets[current]) {
            // 找到当前点的两个方向
            int di = i + dir[dirIndex][0];
            int dj = j + dir[dirIndex][1];
            boolean res = dfs(di, dj, dirIndex, grid);
            if (res) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断从 pre 到 cur
     * @param pre
     * @param cur
     * @return
     */
    private boolean check(int pre, int cur) {
        //  pre 从最开始 位置来 一定能连通
        if (pre == 0) {
            return true;
        }
        // 左右 对上 或者 上下对上了 就是 连通
        return pre + cur == 1 || pre + cur == 5;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[2,4,3],[6,5,2]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        boolean b = solution.hasValidPath(grid);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
