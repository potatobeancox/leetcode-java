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


    /**
     *
     * @param grid
     * @return
     */
    public int[] getBiggestThree(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // upRightSum 表示从点 ij开始 一直到右上角边界的对角线的前缀和
        int[][] upRightSum = new int[grid.length][grid[0].length];
        // downRightSum 表示从点 ij开始 一直到右下角的前缀和
        int[][] downRightSum = new int[grid.length][grid[0].length];
        // 枚举每个点作为起始点计算 upRightSum 和 downRightSum
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 计算当前左上节点
                int upRightI = i;
                int upRightJ = m - 1 -j;
                // 计算当前数组
                upRightSum[upRightI][upRightJ] = grid[upRightI][upRightJ];
                if (upRightI > 0 && upRightJ < m-1) {
                    upRightSum[upRightI][upRightJ] += upRightSum[upRightI-1][upRightJ+1];
                }

                // 计算当前生成右下节点
                int downRightI= n - 1 - i;
                int downRightJ= m - 1 - j;
                // 计算当前数组
                downRightSum[downRightI][downRightJ] = grid[downRightI][downRightJ];
                if (downRightI < n-1 && downRightJ < m-1) {
                    downRightSum[downRightI][downRightJ] += downRightSum[downRightI+1][downRightJ+1];
                }

            }
        }
        // TreeSet 维护 有序性 要最大的 所以 不能超过3个
        TreeSet<Integer> treeSet = new TreeSet<>();
        // 枚举每一个位置作为开始位置 内部枚举长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 长度
                for (int k = 0; k < Math.min(n, m); k++) {
                    // 分别计算上线边界和右边界 任意超过限制就返回
                    if (i - k < 0 || i + k >= n || j + k * 2 >= m) {
                        break;
                    }
                    // 节点自己
                    if (k == 0) {
                        treeSet.add(grid[i][j]);
                        continue;
                    }
                    // 最上面的点
                    int upPointI = i - k;
                    int upPointJ = j + k;
                    // 边长
                    int edge1 = upRightSum[i][j];
                    if (upPointI - 1 >= 0 && upPointJ + 1 < m) {
                        edge1 -= upRightSum[upPointI - 1][upPointJ + 1];
                    }
                    // 最右边的点
                    int rightPointI = i;
                    int rightPointJ = j + k * 2;
                    // 上面的点到右边点的边
                    int edge2 = downRightSum[upPointI][upPointJ];
                    if (rightPointI + 1 < n && rightPointJ + 1 < m) {
                        edge2 -= downRightSum[rightPointI+1][rightPointJ+1];
                    }
                    // 最下面的点
                    int downPointI = i + k;
                    int downPointJ = j + k;
                    // 左边的点到下面的点
                    int edge3 = downRightSum[i][j];
                    if (downPointI + 1 < n && downPointJ + 1 < m) {
                        edge3 -= downRightSum[downPointI+1][downPointJ+1];
                    }
                    // 下面的点到右边的点
                    int edge4 = upRightSum[downPointI][downPointJ];
                    if (rightPointI-1 >= 0 && rightPointJ+1 < m) {
                        edge4 -= upRightSum[rightPointI-1][rightPointJ+1];
                    }
                    // 求和 减去 4个点
                    int sum = edge1 + edge2 + edge3 + edge4 - grid[i][j] - grid[upPointI][upPointJ]
                            - grid[downPointI][downPointJ] - grid[rightPointI][rightPointJ];
                    treeSet.add(sum);
                }
            }
        }
        // set 转成 结果
        int[] res;
        if (treeSet.size() >= 3) {
            res = new int[3];
        } else {
            res = new int[treeSet.size()];
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = treeSet.pollLast();
        }
        return res;
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
