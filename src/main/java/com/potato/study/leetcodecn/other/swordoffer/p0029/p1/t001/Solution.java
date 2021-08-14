package com.potato.study.leetcodecn.other.swordoffer.p0029.p1.t001;

import java.util.Arrays;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

  

 示例 1：

 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 输出：[1,2,3,6,9,8,7,4,5]
 示例 2：

 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
  

 限制：

 0 <= matrix.length <= 100
 0 <= matrix[i].length <= 100
 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
    /**
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;
        // 控制循环次数
        int upMost = 0;
        int downMost = matrix.length - 1;
        int leftMost = 0;
        int rightMost = matrix[0].length - 1;
        int i = 0;
        int j;
        while (index < res.length) {
            // 往右
            for (j = leftMost; j <= rightMost; j++) {
                res[index++] = matrix[i][j];
            }
            upMost++;
            j--;
            if (index >= res.length) {
                break;
            }
            // 往下
            for (i = upMost; i <= downMost; i++) {
                res[index++] = matrix[i][j];
            }
            rightMost--;
            i--;
            if (index >= res.length) {
                break;
            }
            // 往左
            for (j = rightMost; j >= leftMost; j--) {
                res[index++] = matrix[i][j];
            }
            downMost--;
            j++;
            if (index >= res.length) {
                break;
            }
            // 往上
            for (i = downMost; i >= upMost; i--) {
                res[index++] = matrix[i][j];
            }
            leftMost++;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {
                {1,2,3,4},
                {4,5,6,8},
                {7,8,9,10}
        };
        int[] nums = solution.spiralOrder(matrix);
        System.out.println(Arrays.toString(nums));
    }

}
