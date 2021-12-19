package com.potato.study.leetcodecn.p00498.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 498. 对角线遍历
 *
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 *
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diagonal-traverse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 对角线遍历
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        // 直接从第一行和最后一列每个点作为起点开始对角线遍历
        int n = mat.length;
        int m = mat[0].length;
        int[] result = new int[m * n];
        int index = 0;
        int lineIndex = 0;
        // 第一行
        for (int i = 0; i < m; i++) {
            int startI = 0;
            int startJ = i;
            List<Integer> thisList = new ArrayList<>();
            while (startI < n && startJ >= 0) {
                thisList.add(mat[startI][startJ]);
                startI += 1;
                startJ += -1;
            }
            if (lineIndex % 2 == 0) {
                for (int j = thisList.size() - 1; j >= 0; j--) {
                    result[index++] = thisList.get(j);
                }
            } else {
                for (int j = 0; j < thisList.size(); j++) {
                    result[index++] = thisList.get(j);
                }
            }
            lineIndex++;
        }
        // 最后一列
        for (int i = 1; i < n; i++) {
            int startI = i;
            int startJ = m-1;
            List<Integer> thisList = new ArrayList<>();
            while (startI < n && startJ >= 0) {
                thisList.add(mat[startI][startJ]);
                startI += 1;
                startJ += -1;
            }
            // 将每次的遍历结果 放入数组中 考虑是否翻转
            if (lineIndex % 2 == 0) {
                for (int j = thisList.size() - 1; j >= 0; j--) {
                    result[index++] = thisList.get(j);
                }
            } else {
                for (int j = 0; j < thisList.size(); j++) {
                    result[index++] = thisList.get(j);
                }
            }
            lineIndex++;
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };
        int[] diagonalOrder = solution.findDiagonalOrder(mat);
        System.out.println(Arrays.toString(diagonalOrder));
        Assert.assertArrayEquals(new int[] {
                1,2,4,7,5,3,6,8,9
        }, diagonalOrder);


        mat = new int[][] {
                {2,3}
        };
        diagonalOrder = solution.findDiagonalOrder(mat);
        System.out.println(Arrays.toString(diagonalOrder));
        Assert.assertArrayEquals(new int[] {
                2,3
        }, diagonalOrder);
    }

}
