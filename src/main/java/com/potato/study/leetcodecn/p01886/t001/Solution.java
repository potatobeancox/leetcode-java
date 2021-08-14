package com.potato.study.leetcodecn.p01886.t001;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Assert;

/**
 * 1886. 判断矩阵经轮转后是否一致
 *
 * 给你两个大小为 n x n 的二进制矩阵 mat 和 target 。现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * 输出：true
 * 解释：顺时针轮转 90 度一次可以使 mat 和 target 一致。
 * 示例 2：
 *
 *
 * 输入：mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
 * 输出：false
 * 解释：无法通过轮转矩阵中的元素使 equal 与 target 一致。
 * 示例 3：
 *
 *
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
 * 输出：true
 * 解释：顺时针轮转 90 度两次可以使 mat 和 target 一致。
 *  
 *
 * 提示：
 *
 * n == mat.length == target.length
 * n == mat[i].length == target[i].length
 * 1 <= n <= 10
 * mat[i][j] 和 target[i][j] 不是 0 就是 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 比较3次
     * @param mat
     * @param target
     * @return
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            // 将 mat 顺时针转 90度 转4次
            mat = rotation90(mat);
            // 每转一次 与target 进行对比
            boolean isSame = true;
            kk:
            for (int j = 0; j < mat.length; j++) {
                for (int k = 0; k < mat[0].length; k++) {
                    if (mat[j][k] != target[j][k]) {
                        isSame = false;
                        break kk;
                    }
                }
            }
            if (isSame) {
                return true;
            }
        }
        return false;
    }


    /**
     * 将 mat 顺时针旋转90度 返回
     * @param mat
     * @return
     */
    private int[][] rotation90(int[][] mat) {
        // 上线翻转
        for (int i = 0; i < mat.length / 2; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int tmp = mat[i][j];
                mat[i][j] = mat[mat.length - 1 - i][j];
                mat[mat.length - 1 - i][j] = tmp;
            }
        }
        // 沿着对角线翻转
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = tmp;
            }
        }
        return mat;
    }



}
