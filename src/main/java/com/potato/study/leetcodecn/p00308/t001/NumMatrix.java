package com.potato.study.leetcodecn.p00308.t001;

import org.junit.Assert;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

/**
 * 308. 二维区域和检索 - 可变
 *
 * 给你一个二维矩阵 matrix ，你需要处理下面两种类型的若干次查询：
 *
 * 更新：更新 matrix 中某个单元的值。
 * 求和：计算矩阵 matrix 中某一矩形区域元素的 和 ，该区域由 左上角 (row1, col1) 和 右下角 (row2, col2) 界定。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 用整数矩阵 matrix 初始化对象。
 * void update(int row, int col, int val) 更新 matrix[row][col] 的值到 val 。
 * int sumRegion(int row1, int col1, int row2, int col2) 返回矩阵 matrix 中指定矩形区域元素的 和 ，该区域由 左上角 (row1, col1) 和 右下角 (row2,
 * col2) 界定。
 *  
 *
 * 示例 1：
 *
 *
 * 输入
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2],
 * [2, 1, 4, 3]]
 * 输出
 * [null, 8, null, 10]
 *
 * 解释
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3,
 * 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // 返回 8 (即, 左侧红色矩形的和)
 * numMatrix.update(3, 2, 2);       // 矩阵从左图变为右图
 * numMatrix.sumRegion(2, 1, 4, 3); // 返回 10 (即，右侧红色矩形的和)
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -105 <= matrix[i][j] <= 105
 * 0 <= row < m
 * 0 <= col < n
 * -105 <= val <= 105
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * 最多调用104 次 sumRegion 和 update 方法
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/range-sum-query-2d-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumMatrix {

    private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        // 使用一个前缀和 数组
        this.prefixSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                prefixSum[i][j] = prefixSum[i][j-1] + matrix[i-1][j-1];
            }
        }
    }

    public void update(int row, int col, int val) {
        // 更新 row col 之后的 前缀和 prefix row+1  col+1
        int originVal = prefixSum[row+1][col+1] - prefixSum[row+1][col];
        for (int i = col+1; i < prefixSum[0].length; i++) {
            prefixSum[row+1][i] -= originVal;
            prefixSum[row+1][i] += val;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 计算 范围之内的前缀和
        int sum = 0;
        for (int i = row1 + 1; i <= row2 + 1; i++) {
            // col2 + 1 - col1
            sum += (prefixSum[i][col2+1] - prefixSum[i][col1]);
        }
        return sum;
    }

    public static void main(String[] args) {

        String input = "[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]";
        int[][] ints = LeetcodeInputUtils.inputString2IntArrayTwoDimensional(input);
        NumMatrix numMatrix = new NumMatrix(ints);
        int i = numMatrix.sumRegion(2,1,4,3);
        System.out.println(i);
        Assert.assertEquals(8, i);

        numMatrix.update(3,2,2);

        int j = numMatrix.sumRegion(2,1,4,3);
        System.out.println(j);
        Assert.assertEquals(10, j);


    }
}
