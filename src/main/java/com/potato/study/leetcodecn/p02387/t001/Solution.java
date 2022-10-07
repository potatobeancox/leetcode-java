package com.potato.study.leetcodecn.p02387.t001;


import java.util.Arrays;

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


    /**
     * https://leetcode.cn/problems/median-of-a-row-wise-sorted-matrix/comments/
     * @param grid
     * @return
     */
    public int matrixMedian(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[index++] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        return arr[m * n / 2];
    }


}
