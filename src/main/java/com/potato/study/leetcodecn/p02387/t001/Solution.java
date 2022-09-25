package com.potato.study.leetcodecn.p02387.t001;


/**
 * 2387. Median of a Row Wise Sorted Matrix
 *
 * Given an m x n matrix grid containing an odd number of integers where each row is sorted in non-decreasing order, return the median of the matrix.

 You must solve the problem in less than O(m * n) time complexity.

  

 Example 1:

 Input: grid = [[1,1,2],[2,3,3],[1,3,4]]
 Output: 2
 Explanation: The elements of the matrix in sorted order are 1,1,1,2,2,3,3,3,4. The median is 2.
 Example 2:

 Input: grid = [[1,1,3,3,4]]
 Output: 3
 Explanation: The elements of the matrix in sorted order are 1,1,3,3,4. The median is 3.
  

 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 500
 m and n are both odd.
 1 <= grid[i][j] <= 106
 grid[i] is sorted in non-decreasing order.

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/median-of-a-row-wise-sorted-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int matrixMedian(int[][] grid) {
        // 从上往下 遍历一遍 grid 获取 max 和min
        int min = grid[0][0];
        int max = grid[0][grid[0].length-1];
        for (int[] g : grid) {
            min = Math.min(min, g[0]);
            max = Math.max(max, g[grid[0].length-1]);
        }
        // 求个数 和 target 个数
        int count = grid.length * grid[0].length;
        int target = count / 2;
        // 二分法判断每次有多少个元素小于等于 mid

        // 如果元素 数量就是 target 那么可能就是 这个结果 大于的话 往左找 等于的话 返回 小于 的话 还可以大一点
        return -1;
    }


}
