package com.potato.study.leetcodecn.p00764.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 764. 最大加号标志
 *
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 *
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 *
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为
 * 0 也可能为 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 * 示例 2：
 *
 *
 *
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * 每一对 (xi, yi) 都 不重复
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-plus-sign
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 764
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // 将 mines 处理成 set
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < mines.length; i++) {
            long num = mines[i][0] * n + mines[i][1];
            set.add(num);
        }
        int[][][] dp = new int[n][n][4];
        // dp ij k 标识 已ij为中心 的最长延伸的1的长度 0上1下2左3右
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long num = i * n + j;
                if (set.contains(num)) {
                    continue;
                }
                // 上 和左
                if (i == 0 || j == 0) {
                    dp[i][j][0] = Math.max(dp[i][j][0], 1);
                    dp[i][j][2] = Math.max(dp[i][j][2], 1);
                } else {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i-1][j][0] + 1);
                    dp[i][j][2] = Math.max(dp[i][j][2], dp[i][j-1][2] + 1);
                }
            }
        }
        // 从左上和右下开始遍历
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                long num = i * n + j;
                if (set.contains(num)) {
                    continue;
                }
                // 上 和左
                if (i == n-1 || j == n-1) {
                    dp[i][j][1] = Math.max(dp[i][j][1], 1);
                    dp[i][j][3] = Math.max(dp[i][j][3], 1);
                } else {
                    // 下
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i+1][j][1] + 1);
                    // 右
                    dp[i][j][3] = Math.max(dp[i][j][3], dp[i][j+1][3] + 1);
                }
            }
        }
        // 遍历 找四个方向最小的
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Math.min(dp[i][j][0], dp[i][j][1]);
                min = Math.min(min, dp[i][j][2]);
                min = Math.min(min, dp[i][j][3]);
                max = Math.max(max, min);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        String str = "[[4, 2]]";
        int[][] arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        int i = solution.orderOfLargestPlusSign(n, arr);
        System.out.println(i);
        Assert.assertEquals(2, i);

        n = 1;
        str = "[[0, 0]]";
        arr = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(str);
        i = solution.orderOfLargestPlusSign(n, arr);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}
