package com.potato.study.leetcodecn.p02397.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 2397. 被列覆盖的最多行数
 *
 * 给你一个下标从 0 开始的 m x n 二进制矩阵 mat 和一个整数 cols ，表示你需要选出的列数。
 *
 * 如果一行中，所有的 1 都被你选中的列所覆盖，那么我们称这一行 被覆盖 了。
 *
 * 请你返回在选择 cols 列的情况下，被覆盖 的行数 最大 为多少。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
 * 输出：3
 * 解释：
 * 如上图所示，覆盖 3 行的一种可行办法是选择第 0 和第 2 列。
 * 可以看出，不存在大于 3 行被覆盖的方案，所以我们返回 3 。
 * 示例 2：
 *
 *
 *
 * 输入：mat = [[1],[0]], cols = 1
 * 输出：2
 * 解释：
 * 选择唯一的一列，两行都被覆盖了，原因是整个矩阵都被覆盖了。
 * 所以我们返回 2 。
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 12
 * mat[i][j] 要么是 0 要么是 1 。
 * 1 <= cols <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-rows-covered-by-columns
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int maximumRows(int[][] matrix, int numSelect) {
        // 将 matrix 每一行 转换成 一个二进制的数字
        List<Integer> list = new ArrayList<>();

        for (int[] arr : matrix) {
            int target = convert2Int(arr);
            list.add(target);
        }
        // 遍历每个数组 如果 当前数字的 bit count 等于 numSelect
        int max = 0;
        int n = matrix[0].length;
        int limit = (1 << n);
        for (int i = 0; i < limit; i++) {
            int bitCount = Integer.bitCount(i);
            // 如果当前 字节数量 等于 字母数量
            if (bitCount == numSelect) {
                // 找一下其他数字 看 | 是否等于 本身
                int rowCount = 0;
                for (int j = 0; j < list.size(); j++) {
                    if ((list.get(j) & i) == list.get(j)) {
                        rowCount++;
                    }
                }
                max = Math.max(max, rowCount);
            }

        }
        return max;
    }

    private int convert2Int(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp <<= 1;
            temp += array[i];
        }
        return temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]");
        int numSelect = 2;
        int i = solution.maximumRows(matrix, numSelect);
        System.out.println(i);
        Assert.assertEquals(3, i);



        matrix = LeetcodeInputUtils.inputString2IntArrayTwoDimensional("[[0,1],[1,0]]");
        numSelect = 2;
        i = solution.maximumRows(matrix, numSelect);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }

}
