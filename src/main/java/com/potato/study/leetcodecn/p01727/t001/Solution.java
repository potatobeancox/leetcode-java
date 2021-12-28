package com.potato.study.leetcodecn.p01727.t001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 1727. 重新排列后的最大子矩阵
 *
 * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
 *
 * 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * 输出：4
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
 * 示例 2：
 *
 *
 *
 * 输入：matrix = [[1,0,1,0,1]]
 * 输出：3
 * 解释：你可以按照上图方式重新排列矩阵的每一列。
 * 最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
 * 示例 3：
 *
 * 输入：matrix = [[1,1,0],[1,0,1]]
 * 输出：2
 * 解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。
 * 示例 4：
 *
 * 输入：matrix = [[0,0],[0,0]]
 * 输出：0
 * 解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 105
 * matrix[i][j] 要么是 0 ，要么是 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-submatrix-with-rearrangements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1727
    public int largestSubmatrix(int[][] matrix) {
        // 将matrix 处理 成 以当前ij为截止 的 最大到1 的点
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                matrix[i][j] += matrix[i-1][j];
            }
        }
        // 遍历 每一行 进行排序 从小到大排序，遍历到每个点 i 对对应面积 value i * length - i 过程中记录最大面积
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] line = matrix[i];
            Arrays.sort(line);
            // 计算最大面积
            for (int j = 0; j < matrix[0].length; j++) {
                maxArea = Math.max(maxArea, matrix[i][j] * (matrix[0].length - j));
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "[[0,0,1],[1,1,1],[1,0,1]]";
        int[][] matrix = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        int i = solution.largestSubmatrix(matrix);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
