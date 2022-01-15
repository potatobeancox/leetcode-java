package com.potato.study.leetcodecn.p02133.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 2133. 检查是否每一行每一列都包含全部整数
 *
 * 对一个大小为 n x n 的矩阵而言，如果其每一行和每一列都包含从 1 到 n 的 全部 整数（含 1 和 n），则认为该矩阵是一个 有效 矩阵。

 给你一个大小为 n x n 的整数矩阵 matrix ，请你判断矩阵是否为一个有效矩阵：如果是，返回 true ；否则，返回 false 。

  

 示例 1：



 输入：matrix = [[1,2,3],[3,1,2],[2,3,1]]
 输出：true
 解释：在此例中，n = 3 ，每一行和每一列都包含数字 1、2、3 。
 因此，返回 true 。
 示例 2：



 输入：matrix = [[1,1,1],[1,2,3],[1,2,3]]
 输出：false
 解释：在此例中，n = 3 ，但第一行和第一列不包含数字 2 和 3 。
 因此，返回 false 。
  

 提示：

 n == matrix.length == matrix[i].length
 1 <= n <= 100
 1 <= matrix[i][j] <= n

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/check-if-every-row-and-column-contains-all-numbers
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int[] line = matrix[i];
            int max = line[0];
            int min = line[0];
            Set<Integer> set= new HashSet<>();
            for (int num : line) {
                min = Math.min(min, num);
                max = Math.max(max, num);
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
            if (min != 1 || max != n) {
                return false;
            }
        }
        // 列
        for (int i = 0; i < n; i++) {
            int max = matrix[0][i];
            int min = matrix[0][i];
            Set<Integer> set= new HashSet<>();
            for (int j = 0; j < n; j++) {
                min = Math.min(min, matrix[j][i]);
                max = Math.max(max, matrix[j][i]);
                if (set.contains(matrix[j][i])) {
                    return false;
                }
                set.add(matrix[j][i]);
            }
            if (min != 1 || max != n) {
                return false;
            }
        }
        return true;
    }
}
