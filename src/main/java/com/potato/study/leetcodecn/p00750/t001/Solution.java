package com.potato.study.leetcodecn.p00750.t001;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;

/**
 * 750. 角矩形的数量
 *
 * 给定一个只包含 0 和 1 的 m x n 整数矩阵 grid ，返回 其中 「角矩形 」的数量 。
 *
 * 一个「角矩形」是由四个不同的在矩阵上的 1 形成的轴对称的矩形。注意只有角的位置才需要为 1。
 *
 * 注意：4 个 1 的位置需要是不同的。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
 * 输出：1
 * 解释：只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]]
 * 输出：9
 * 解释：这里有 4 个 2x2 的矩形，4 个 2x3 和 3x2 的矩形和 1 个 3x3 的矩形。
 * 示例 3：
 *
 *
 *
 * 输入：grid = [[1,1,1,1]]
 * 输出：0
 * 解释：矩形必须有 4 个不同的角。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] 不是 0 就是 1
 * 网格中 1 的个数在 [1, 6000] 范围内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-corner-rectangles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countCornerRectangles(int[][] grid) {
        int totalCount = 0;
        // 从上往下 便利 先找到 第一个1 再往后 找之后的 1 使用一个 oneCount 保存
        int[][] oneCount = new int[grid[0].length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 本行的第一个1 往后找每个1
                for (int k = j + 1; k < grid[0].length; k++) {
                    if (grid[i][k] == 0) {
                        continue;
                    }
                    // 找到第二个的1 计算 总个数 本行作为 下边界 找到上边界的可能
                    if (oneCount[j][k] != 0) {
                        totalCount += oneCount[j][k];
                    }
                    // 更新
                    oneCount[j][k]++;
                }
            }
        }
        // 而数组 count ij 保存 本行之前 第ij列 为1的行数量
        return totalCount;
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String[] deadends = new String[] {"0201","0101","0102","1212","2002"};
//        String target = "0202";
//        int step = solution.openLock(deadends, target);
//        System.out.println(step);
//        Assert.assertEquals(6, step);
//
//
//        deadends = new String[] {"8888"};
//        target = "0009";
//        step = solution.openLock(deadends, target);
//        System.out.println(step);
//        Assert.assertEquals(1, step);
//
//
//        deadends = new String[] {"0000"};
//        target = "8888";
//        step = solution.openLock(deadends, target);
//        System.out.println(step);
//        Assert.assertEquals(-1, step);

    }
}
