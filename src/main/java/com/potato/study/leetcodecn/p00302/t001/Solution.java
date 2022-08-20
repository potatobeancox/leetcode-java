package com.potato.study.leetcodecn.p00302.t001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 302. 包含全部黑色像素的最小矩形
 *
 * 图片在计算机处理中往往是使用二维矩阵来表示的。

 给你一个大小为 m x n 的二进制矩阵 image 表示一张黑白图片，0 代表白色像素，1 代表黑色像素。

 黑色像素相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素。像素点是水平或竖直方向连接的。

 给你两个整数 x 和 y 表示某一个黑色像素的位置，请你找出包含全部黑色像素的最小矩形（与坐标轴对齐），并返回该矩形的面积。

 你必须设计并实现一个时间复杂度低于 O(mn) 的算法来解决此问题。

  

 示例 1：


 输入：image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 输出：6
 示例 2：

 输入：image = [["1"]], x = 0, y = 0
 输出：1
  

 提示：

 m == image.length
 n == image[i].length
 1 <= m, n <= 100
 image[i][j] 为 '0' 或 '1'
 1 <= x < m
 1 <= y < n
 image[x][y] == '1'
 image 中的黑色像素仅形成一个 组件

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/smallest-rectangle-enclosing-black-pixels
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public int minArea(char[][] image, int x, int y) {
        // 遍历 image 找到 行出现最小值和 列出现最小值
        this.x1 = Integer.MAX_VALUE;
        this.y1 = Integer.MAX_VALUE;

        this.x2 = Integer.MIN_VALUE;
        this.y2 = Integer.MIN_VALUE;

        dfs(image, x, y);

        return Math.abs(x2 - x1 + 1) * Math.abs(y2 - y1 + 1);
    }

    private void dfs(char[][] image, int x, int y) {
        if (image[x][y] != '1') {
            return;
        }

        image[x][y] = '2';
        // 黑色
        x1 = Math.min(x, x1);
        x2 = Math.max(x, x2);
        y1 = Math.min(y, y1);
        y2 = Math.max(y, y2);

        int[][] dir = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            // 坐标是否合理
            if (dx < 0 || dx >= image.length || dy < 0 || dy >= image[0].length) {
                continue;
            }

            // 白色 或者已经访问过
            if (image[dx][dy] != '1') {
                continue;
            }
            dfs(image, dx, dy);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
        String input = "[[\"0\",\"0\",\"1\",\"0\"],[\"0\",\"1\",\"1\",\"0\"],[\"0\",\"1\",\"0\",\"0\"]]";
        char[][] image = LeetcodeInputUtils.inputString2CharArrayTwoDimensional(input);
        int x = 0;
        int y = 2;
        int area = solution.minArea(image, x, y);
        System.out.println(area);
        Assert.assertEquals(6, area);
    }
}
