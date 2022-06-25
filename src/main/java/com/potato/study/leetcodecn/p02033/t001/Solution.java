package com.potato.study.leetcodecn.p02033.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * 2033. 获取单值网格的最小操作数
 *
 * 给你一个大小为 m x n 的二维整数网格 grid 和一个整数 x 。每一次操作，你可以对 grid 中的任一元素 加 x 或 减 x 。
 *
 * 单值网格 是全部元素都相等的网格。
 *
 * 返回使网格化为单值网格所需的 最小 操作数。如果不能，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,4],[6,8]], x = 2
 * 输出：4
 * 解释：可以执行下述操作使所有元素都等于 4 ：
 * - 2 加 x 一次。
 * - 6 减 x 一次。
 * - 8 减 x 两次。
 * 共计 4 次操作。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,5],[2,3]], x = 1
 * 输出：5
 * 解释：可以使所有元素都等于 3 。
 * 示例 3：
 *
 *
 *
 * 输入：grid = [[1,2],[3,4]], x = 2
 * 输出：-1
 * 解释：无法使所有元素相等。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= x, grid[i][j] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 2033
    public int minOperations(int[][] grid, int x) {
        // 找到中位数
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
        int target;
        if (m * n % 2 == 1) {
            int i = m * n / 2;
            target = arr[i];
        } else {
            int i = m * n / 2;
            target = (arr[i-1] + arr[i-1]) / 2;
        }
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i] - target) % x != 0) {
                return -1;
            }
            total += (Math.abs(arr[i] - target) / x);
        }
        return total;
    }
}
