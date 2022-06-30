package com.potato.study.leetcodecn.p01914.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1914. 循环轮转矩阵
 *
 * 给你一个大小为 m x n 的整数矩阵 grid​​​ ，其中 m 和 n 都是 偶数 ；另给你一个整数 k 。

 矩阵由若干层组成，如下图所示，每种颜色代表一层：



 矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。轮转示例如下：


 返回执行 k 次循环轮转操作后的矩阵。

  

 示例 1：


 输入：grid = [[40,10],[30,20]], k = 1
 输出：[[10,20],[40,30]]
 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 示例 2：


 输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
  

 提示：

 m == grid.length
 n == grid[i].length
 2 <= m, n <= 50
 m 和 n 都是 偶数
 1 <= grid[i][j] <= 5000
 1 <= k <= 109

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/cyclically-rotating-a-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[][] rotateGrid(int[][] grid, int k) {
        for (int i = 0; i <= Math.min(grid.length / 2, grid[0].length / 2); i++) {
            Queue<Integer> queue = new LinkedList<>();
            // 从左 - 》 右边
            for (int j = i; j < grid[0].length - i; j++) {
                queue.add(grid[i][j]);
            }
            // 从上到下
            for (int j = i+1; j < grid.length - i; j++) {
                queue.add(grid[j][grid[0].length - i - 1]);
            }
            // 从右到左
            for (int j = grid[0].length - i -2; j >= i; j--) {
                queue.add(grid[grid.length - i - 1][j]);
            }
            // 从下到上
            for (int j = grid.length - i - 2; j > i; j--) {
                queue.add(grid[j][i]);
            }
            // 旋转k
            if (queue.size() == 0) {
                break;
            }
            int times = k % queue.size();
            for (int j = 0; j < times; j++) {
                queue.add(queue.poll());
            }
            // 放回去
            // 从左 - 》 右边
            for (int j = i; j < grid[0].length - i; j++) {
                grid[i][j] = queue.poll();
            }
            // 从上到下
            for (int j = i+1; j < grid.length - i; j++) {
                grid[j][grid[0].length - i - 1] = queue.poll();
            }
            // 从右到左
            for (int j = grid[0].length - i -2; j >= i; j--) {
                grid[grid.length - i - 1][j] = queue.poll();
            }
            // 从下到上
            for (int j = grid.length - i - 2; j > i; j--) {
                grid[j][i] = queue.poll();
            }

        }
        return grid;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[40,10],[30,20]]";
        int k = 1;
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[][] ints = solution.rotateGrid(grid, k);
        System.out.println(Arrays.deepToString(ints));


        input = "[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]";
        k = 2;
        grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        ints = solution.rotateGrid(grid, k);
        System.out.println(Arrays.deepToString(ints));

        input = "[[10,1,4,8],[6,6,3,10],[7,4,7,10],[1,10,6,1],[2,1,1,10],[3,8,9,2],[7,1,10,10],[7,1,4,9],[2,2,4,2],[10,7,5,10]]";
        k = 1;
        grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        ints = solution.rotateGrid(grid, k);
        // [[1,4,8,10],[10,3,7,10],[6,6,6,1],[7,4,1,10],[1,10,9,2],[2,1,10,10],[3,8,4,9],[7,1,4,2],[7,1,2,10],[2,10,7,5]]
        System.out.println(Arrays.deepToString(ints));
    }

}
