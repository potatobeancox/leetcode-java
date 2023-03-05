package com.potato.study.leetcodecn.p02371.t001;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2371. 最小化网格中的最大值
 *
 * 给定一个包含 不同 正整数的 m × n 整数矩阵 grid。

 必须将矩阵中的每一个整数替换为正整数，且满足以下条件:

 在替换之后，同行或同列中的每两个元素的 相对 顺序应该保持 不变。
 替换后矩阵中的 最大 数目应尽可能 小。
 如果对于原始矩阵中的所有元素对，使 grid[r1][c1] > grid[r2][c2]，其中要么 r1 == r2 ，要么 c1 == c2，则相对顺序保持不变。那么在替换之后一定满足 grid[r1][c1] > grid[r2][c2]。

 例如，如果 grid = [[2, 4, 5], [7, 3, 9]]，那么一个好的替换可以是 grid = [[1, 2, 3], [2, 1, 4]] 或 grid = [[1, 2, 3], [3, 1, 4]]。

 返回 结果 矩阵。如果有多个答案，则返回其中 任何 一个。

  

 示例 1:


 输入: grid = [[3,1],[2,5]]
 输出: [[2,1],[1,2]]
 解释: 上面的图显示了一个有效的替换。
 矩阵中的最大值是 2。可以证明，不能得到更小的值。
 示例 2:

 输入: grid = [[10]]
 输出: [[1]]
 解释: 我们将矩阵中唯一的数字替换为 1。
  

 提示:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 1000
 1 <= m * n <= 105
 1 <= grid[i][j] <= 109
 grid 由不同的整数组成。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/minimize-maximum-value-in-a-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] minScore(int[][] grid) {
        // 对坐标按照 val 进行从小到大排序 记录每行和每列的最小值 从1 开始时间 如果使用过了 按照最小值+1进行
        int m = grid.length;
        int n = grid[0].length;

        int[] maxLine = new int[m];
        int[] maxCol = new int[n];

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(grid[o1[0]][o1[1]], grid[o2[0]][o2[1]]);
        });

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                priorityQueue.add(new int[] {i, j});
            }
        }

        // 依次获取 当前行列的最大值
        int[][] res = new int[m][n];
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int i = poll[0];
            int j = poll[1];

            int max = Math.max(maxLine[i], maxCol[j]);

            res[i][j] = max + 1;
            maxLine[i] = res[i][j];
            maxCol[j] = res[i][j];
        }
        return res;
    }

}
