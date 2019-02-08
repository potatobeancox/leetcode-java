package com.potato.study.leetcode.p0885;

import com.potato.study.leetcode.util.ArrayUtil;

/**
 * @author liuzhao11
 *
 *
 * 885. Spiral Matrix III
 *
 *
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid.

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.



Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]




Example 2:

Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]




Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C

 *
 *
 * 题目含义：
 *
 * 思路：
 * 对于旋转
 * https://www.jianshu.com/p/2e083a951361
 * 右 -> 下 -> 左 -> 上
 *  step 记录当前可以走几步 每两次 step++
 */
public class Solution {

    public int[][] spiralMatrixIII(int r, int c, int r0, int c0) {
        int[][] result = new int[r * c][2];
        int index = 0;
        result[index++] = new int[]{r0, c0};

        int count = 1;
        int nodeNum = r * c;
        int dx = 0;
        int dy = 0;
        int step = 1;
        while (count < nodeNum) {
            // 右
            dx = 0;
            dy = 1;
            if (count < nodeNum) {
                for (int i = 0; i < step; i++) {
                    r0 += dx;
                    c0 += dy;
                    if (r0 >= 0 && r0 < r && c0 >= 0 && c0 < c) {
                        result[index++] = new int[]{r0, c0};
                        count++;
                    }
                    if (count >= nodeNum) {
                        break;
                    }
                }
            }
            // 下
            dx = 1;
            dy = 0;
            if (count < nodeNum) {
                for (int i = 0; i < step; i++) {
                    r0 += dx;
                    c0 += dy;
                    if (r0 >= 0 && r0 < r && c0 >= 0 && c0 < c) {
                        result[index++] = new int[]{r0, c0};
                        count++;
                    }
                    if (count >= nodeNum) {
                        break;
                    }
                }
            }
            step++;
            // 左
            dx = 0;
            dy = -1;
            if (count < nodeNum) {
                for (int i = 0; i < step; i++) {
                    r0 += dx;
                    c0 += dy;
                    if (r0 >= 0 && r0 < r && c0 >= 0 && c0 < c) {
                        result[index++] = new int[]{r0, c0};
                        count++;
                    }
                    if (count >= nodeNum) {
                        break;
                    }
                }
            }
            // 上
            dx = -1;
            dy = 0;
            if (count < nodeNum) {
                for (int i = 0; i < step; i++) {
                    r0 += dx;
                    c0 += dy;
                    if (r0 >= 0 && r0 < r && c0 >= 0 && c0 < c) {
                        result[index++] = new int[]{r0, c0};
                        count++;
                    }
                    if (count >= nodeNum) {
                        break;
                    }
                }
            }
            step++;
        }
        return result;
    }


    public static void main(String[] args) {
		Solution solution = new Solution();
		int r = 5;
		int c = 6;
		int r0 = 1;
		int c0 = 4;
        int[][] ints = solution.spiralMatrixIII(r, c, r0, c0);
        ArrayUtil.printMatrix(ints);
    }
}
