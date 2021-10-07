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
        // 控制当前 ij 的和 从 0开始到  行数 + 列数 -1
        int m = mat.length;
        int n = mat[0].length;
//        if (m == 1) {
//            return mat[0];
//        }

        int[] result = new int[m * n];
        int current = 0;
//        if (n == 1) {
//            for (int i = 0; i < m; i++) {
//                result[current++] = mat[i][0];
//            }
//            return result;
//        }

        int index = 0;
        // 上半部分 遍历第0 行 作为开始节点
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int start1 = 0; start1 <= index; start1++) {
                int start2 = index - start1;
                list.add(mat[start1][start2]);
            }
            // 判断是否需要 reverse 添加
            if (i % 2 == 0) {
                for (int j = list.size() - 1; j >=0 ; j--) {
                    result[current++] = list.get(j);
                }
            } else {
                for (int j = 0; j < list.size() ; j++) {
                    result[current++] = list.get(j);
                }
            }
            index++;
        }
        // 下半部分
        for (int i = 1; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int start1 = i; start1 < m; start1++) {
                int start2 = index - start1;
                list.add(mat[start1][start2]);
            }
            // 判断是否需要 reverse 添加 偶数需要翻转
            if (i % 2 == 0) {
                for (int j = list.size() - 1; j >=0 ; j--) {
                    result[current++] = list.get(j);
                }
            } else {
                for (int j = 0; j < list.size() ; j++) {
                    result[current++] = list.get(j);
                }
            }
            index++;
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
