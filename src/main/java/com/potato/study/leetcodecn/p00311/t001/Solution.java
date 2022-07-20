package com.potato.study.leetcodecn.p00311.t001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 311. 稀疏矩阵的乘法
 *
 * 给定两个 稀疏矩阵 ：大小为 m x k 的稀疏矩阵 mat1 和大小为 k x n 的稀疏矩阵 mat2 ，返回 mat1 x mat2 的结果。你可以假设乘法总是可能的。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * 输出：[[7,0,0],[-7,0,3]]
 *  示例 2:
 *
 * 输入：mat1 = [[0]], mat2 = [[0]]
 * 输出：[[0]]
 *  
 *
 * 提示:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sparse-matrix-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[][] multiply(int[][] mat1, int[][] mat2) {
        // 用set1 记录 mat1 行号 全是 0的
        Set<Integer> zeroLineIndexSet = new HashSet<>();
        for (int i = 0; i < mat1.length; i++) {
            boolean isAllZero = true;
            for (int j = 0; j < mat1[0].length; j++) {
                if (mat1[i][j] != 0) {
                    isAllZero = false;
                    break;
                }
            }
            if (isAllZero) {
                zeroLineIndexSet.add(i);
            }
        }
        // 用set2 记录全是 0的 列号
        Set<Integer> zeroColumnIndexSet = new HashSet<>();
        for (int i = 0; i < mat2[0].length; i++) {
            boolean isAllZero = true;
            for (int j = 0; j < mat2.length; j++) {
                if (mat2[j][i] != 0) {
                    isAllZero = false;
                    break;
                }
            }
            if (isAllZero) {
                zeroColumnIndexSet.add(i);
            }
        }
        // 结果
        int[][] result = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                // 这个位置 全是 0
                if (zeroLineIndexSet.contains(i) || zeroColumnIndexSet.contains(j)) {
                    continue;
                }
                // 不全是 0 求和
                int sum = 0;
                for (int k = 0; k < mat1[0].length; k++) {
                    sum += (mat1[i][k] * mat2[k][j]);
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat1 = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[1,0,0],[-1,0,3]]");
        int[][] mat2 = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[7,0,0],[0,0,0],[0,0,1]]");
        int[][] multiply = solution.multiply(mat1, mat2);
        System.out.println(Arrays.deepToString(multiply));
    }
}
