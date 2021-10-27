package com.potato.study.leetcodecn.p01072.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 1072. 按列翻转得到最大值等行数
 *
 * 给定由若干 0 和 1 组成的矩阵 matrix，从中选出任意数量的列并翻转其上的 每个 单元格。翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。
 *
 * 回经过一些翻转后，行与行之间所有值都相等的最大行数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 * 示例 2：
 *
 * 输入：[[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 * 示例 3：
 *
 * 输入：[[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 *  
 *
 * 提示：
 *
 * 1 <= matrix.length <= 300
 * 1 <= matrix[i].length <= 300
 * 所有 matrix[i].length 都相等
 * matrix[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flip-columns-for-maximum-number-of-equal-rows
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1072
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        // 遍历 matrix 如果 第一个数字 是 0 的话 就直接顺序变成key 计数 否则 与1 进行异或 （取反生成 str 计数）
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            StringBuilder builder = new StringBuilder();
            if (row[0] == 0) {
                for (int num : row) {
                    builder.append(num);
                }
            } else {
                for (int num : row) {
                    builder.append((num ^ 1));
                }
            }
            Integer count = countMap.getOrDefault(builder.toString(), 0);
            count++;
            countMap.put(builder.toString(), count);
        }
        // 遍历 计数 数组 找到最大值 countMap
        int max = 0;
        for (int count : countMap.values()) {
            max = Math.max(max, count);
        }
        return max;
    }
}
