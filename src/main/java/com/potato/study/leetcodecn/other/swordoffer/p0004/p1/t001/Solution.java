package com.potato.study.leetcodecn.other.swordoffer.p0004.p1.t001;

import org.junit.Assert;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

  

 示例:

 现有矩阵 matrix 如下：

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 给定 target = 5，返回 true。

 给定 target = 20，返回 false。

  

 限制：

 0 <= n <= 1000

 0 <= m <= 1000

  

 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 类似 这个问题 找一个角开始 从
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0) {
            return false;
        }
        // 左下角
        int index1 = matrix.length - 1;
        int index2 = 0;
        while (index1 >= 0 && index2 < matrix[0].length) {
            if (matrix[index1][index2] > target) {
                index1--;
            } else if (matrix[index1][index2] < target) {
                index2++;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] matrix = new int[][] {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        boolean res = solution.findNumberIn2DArray(matrix, target);
        System.out.println(res);
        Assert.assertEquals(true, res);

        target = 20;
        res = solution.findNumberIn2DArray(matrix, target);
        System.out.println(res);
        Assert.assertEquals(false, res);
    }

}
