package com.potato.study.leetcodecn.p02643.t001;

import java.util.Arrays;

/**
 *
 * 2643. 一最多的行
 *
 * 给你一个大小为 m x n 的二进制矩阵 mat ，请你找出包含最多 1 的行的下标（从 0 开始）以及这一行中 1 的数目。

 如果有多行包含最多的 1 ，只需要选择 行下标最小 的那一行。

 返回一个由行下标和该行中 1 的数量组成的数组。

  

 示例 1：

 输入：mat = [[0,1],[1,0]]
 输出：[0,1]
 解释：两行中 1 的数量相同。所以返回下标最小的行，下标为 0 。该行 1 的数量为 1 。所以，答案为 [0,1] 。
 示例 2：

 输入：mat = [[0,0,0],[0,1,1]]
 输出：[1,2]
 解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。
 示例 3：

 输入：mat = [[0,0],[1,1],[0,0]]
 输出：[1,2]
 解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。
  

 提示：

 m == mat.length 
 n == mat[i].length 
 1 <= m, n <= 100 
 mat[i][j] 为 0 或 1

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/row-with-maximum-ones
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] rowAndMaximumOnes(int[][] mat) {
        int maxIndex = 0;
        int maxCount = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int num : mat[i]) {
                if (num == 1) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxIndex = i;
            }
        }
        return new int[] {maxIndex, maxCount};
    }



}
