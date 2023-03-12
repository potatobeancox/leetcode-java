package com.potato.study.leetcodecn.p01878.t001;

import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1878. 矩阵中最大的三个菱形和
 *
 * 给你一个 m x n 的整数矩阵 grid 。
 *
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 *
 *
 *  
 *
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 *
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * 输出：[228,216,211]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：20 + 3 + 200 + 5 = 228
 * - 红色：200 + 2 + 10 + 4 = 216
 * - 绿色：5 + 200 + 4 + 2 = 211
 * 示例 2：
 *
 *
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[20,9,8]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：4 + 2 + 6 + 8 = 20
 * - 红色：9 （右下角红色的面积为 0 的菱形）
 * - 绿色：8 （下方中央面积为 0 的菱形）
 * 示例 3：
 *
 * 输入：grid = [[7,7,7]]
 * 输出：[7]
 * 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 1 <= grid[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int[] getBiggestThree(int[][] grid) {
        // 0 计算 每个位置 ij 开始 想右上和 右下两个对角线的和
        int[][] upSum = new int[grid.length][grid[0].length];
        // 往
        int[][] downSum = new int[grid.length][grid[0].length];
        // 上边界和 右边界 是他自身
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == 0 || j == grid[0].length - 1) {
                    upSum[i][j] = grid[i][j];
                } else {
                    upSum[i][j] = grid[i][j] + upSum[i-1][j+1];
                }
            }
        }
        // 下边界和 左边界 等于自身
        for (int i = grid.length-1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length-1 || j == grid[0].length - 1) {
                    upSum[i][j] = grid[i][j];
                } else {
                    upSum[i][j] = grid[i][j] + upSum[i+1][j+1];
                }
            }
        }
        // 1.枚举每个位置 以这个位置为中心 依次往外扩知道4个边某一个 达到边界
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 2. 1的过程中 计算每个 半径对应的和 插入 treeSet中
                treeSet.add(grid[i][j]);
                // 依次往外扩知道4个边某一个 达到边界
                for (int k = 1; k <= Math.min(i, j) ; k++) {
                    // 当前半径已经超过了限制
                    if (i - k < 0 || j - k < 0
                            || i + k >= grid.length || j + k >= grid[0].length) {
                        break;
                    }
                    // 4个方向的和
                    int sum = upSum[i][j-k] - upSum[i-k][j] + grid[i-k][j];
                    sum += upSum[i+k][j] - upSum[i][j+k] + grid[i][j+k];
                    sum += downSum[i][j-k] - downSum[i+k][j] + grid[i+k][j];
                    sum += downSum[i-k][j] - downSum[i][j+k] + grid[i][j+k];

                    treeSet.add(sum);
                }

            }
        }
        // 3.获取最大的3个
        int[] result;
        if (treeSet.size() >= 3) {
            result = new int[3];
            for (int i = 0; i < 3; i++) {
                result[i] = treeSet.pollLast();
            }
        } else {
            result = new int[treeSet.size()];
            int size = treeSet.size();
            for (int i = 0; i < size; i++) {
                result[i] = treeSet.pollLast();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int[] biggestThree = solution.getBiggestThree(grid);
        System.out.println(Arrays.toString(biggestThree));
        Assert.assertArrayEquals(new int[] {
                228,216,211
        }, biggestThree);
    }
}
