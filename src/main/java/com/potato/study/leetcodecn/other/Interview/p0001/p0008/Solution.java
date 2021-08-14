package com.potato.study.leetcodecn.other.Interview.p0001.p0008;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.08. 零矩阵
 *
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2：
 *
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 面试题01.08
    public void setZeroes(int[][] matrix) {
        Set<Integer> zeroLine = new HashSet<>();
        Set<Integer> zeroColumn = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroLine.add(i);
                    zeroColumn.add(j);
                }
            }
        }
        // 行置0
        for (int i = 0; i < matrix.length; i++) {
            if (!zeroLine.contains(i)) {
                continue;
            }
            Arrays.fill(matrix[i], 0);
        }
        // 列置0
        for (int i = 0; i < matrix[0].length; i++) {
            if (!zeroColumn.contains(i)) {
                continue;
            }
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
