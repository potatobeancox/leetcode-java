package com.potato.study.leetcodecn.p02245.t001;

import java.util.ArrayList;
import java.util.List;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

/**
 * 2245. 转角路径的乘积中最多能有几个尾随零
 *
 * 给你一个二维整数数组 grid ，大小为 m x n，其中每个单元格都含一个正整数。
 *
 * 转角路径 定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全 向水平方向 或者 向竖直方向 移动过弯（如果存在弯），而不能访问之前访问过的单元格。在过弯之后，路径应当完全朝 另一个
 * 方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。
 *
 * 一条路径的 乘积 定义为：路径上所有值的乘积。
 *
 * 请你从 grid 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。
 *
 * 注意：
 *
 * 水平 移动是指向左或右移动。
 * 竖直 移动是指向上或下移动。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * 输出：3
 * 解释：左侧的图展示了一条有效的转角路径。
 * 其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
 * 可以证明在这条转角路径的乘积中尾随零数目最多。
 *
 * 中间的图不是一条有效的转角路径，因为它有不止一个弯。
 * 右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[4,3,2],[7,6,1],[8,8,8]]
 * 输出：0
 * 解释：网格如上图所示。
 * 不存在乘积含尾随零的转角路径。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= grid[i][j] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-trailing-zeros-in-a-cornered-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maxTrailingZeros(int[][] grid) {
        // count ijkl  以ij作为中心 k=0  2的个数 k=1 5的个数， l 四个方向 北西南东
        int n = grid[0].length;
        int[][][][] count = new int[grid.length][n][2][4];
        // 遍历一遍 生成 北和西
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < n; j++) {
                int count2 = 0;
                int count5 = 0;

                int value = grid[i][j];

                while (value > 0 && value % 2 == 0) {
                    value /= 2;
                    count2++;
                }

                while (value > 0 && value % 5 == 0) {
                    value /= 5;
                    count5++;
                }
                count[i][j][0][0] = count2;
                count[i][j][1][0] = count5;
                if (i > 0) {
                    count[i][j][0][0] += count[i-1][j][0][0];
                    count[i][j][1][0] += count[i-1][j][1][0];
                }
                count[i][j][0][1] = count2;
                count[i][j][1][1] = count5;
                if (j > 0) {
                    count[i][j][0][1] += count[i][j-1][0][1];
                    count[i][j][1][1] += count[i][j-1][1][1];
                }
            }
        }
        // 遍历一遍 生成 西南
        for (int i = grid.length-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int count2 = 0;
                int count5 = 0;

                int value = grid[i][j];

                while (value > 0 && value % 2 == 0) {
                    value /= 2;
                    count2++;
                }

                while (value > 0 && value % 5 == 0) {
                    value /= 5;
                    count5++;
                }
                count[i][j][0][2] = count2;
                count[i][j][1][2] = count5;
                if (i < grid.length-1) {
                    count[i][j][0][2] += count[i+1][j][0][2];
                    count[i][j][1][2] += count[i+1][j][1][2];
                }

                count[i][j][0][3] = count2;
                count[i][j][1][3] = count5;
                if (j < n-1) {
                    count[i][j][0][3] += count[i][j+1][0][3];
                    count[i][j][1][3] += count[i][j+1][1][3];
                }
            }
        }
        // 最后遍历一遍 找到以这个点作为拐点 25 个数和的最小值 找 南北 最大 找 找东西最大 找加和最大
        int maxCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < n; j++) {
                int count2 = 0;
                int count5 = 0;

                int value = grid[i][j];

                while (value > 0 && value % 2 == 0) {
                    value /= 2;
                    count2++;
                }

                while (value > 0 && value % 5 == 0) {
                    value /= 5;
                    count5++;
                }
                // l 四个方向 北西南东
                // 东3北0
                int count2en = count[i][j][0][0] + count[i][j][0][3] - count2;
                int count5en = count[i][j][1][0] + count[i][j][1][3] - count5;
                int max1 = Math.min(count2en, count5en);
                // 西1北0
                int count2wn = count[i][j][0][0] + count[i][j][0][1] - count2;
                int count5wn = count[i][j][1][0] + count[i][j][1][1] - count5;
                int max2 = Math.min(count2wn, count5wn);
                // 东3南2
                int count2es = count[i][j][0][2] + count[i][j][0][3] - count2;
                int count5es = count[i][j][1][2] + count[i][j][1][3] - count5;
                int max3 = Math.min(count2es, count5es);

                // 西1南2
                int count2ws = count[i][j][0][2] + count[i][j][0][1] - count2;
                int count5ws = count[i][j][1][2] + count[i][j][1][1] - count5;
                int max4 = Math.min(count2ws, count5ws);

                int current = Math.max(max1, max2);
                current = Math.max(current, max3);
                current = Math.max(current, max4);
                maxCount = Math.max(maxCount, current);
            }
        }
        return maxCount;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]";
        int[][] grid = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.maxTrailingZeros(grid);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
