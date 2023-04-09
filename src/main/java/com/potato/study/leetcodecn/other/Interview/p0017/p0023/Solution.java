package com.potato.study.leetcodecn.other.Interview.p0017.p0023;


import java.util.*;

/**
 * 面试题 17.23. 最大黑方阵
 *
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。

 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。

 示例 1:

 输入:
 [
    [1,0,1],
    [0,0,1],
    [0,0,1]
 ]
 输出: [1,0,2]
 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
 示例 2:

 输入:
 [
    [0,1,1],
    [1,0,1],
    [1,1,0]
 ]
 输出: [0,0,1]
 提示：

 matrix.length == matrix[0].length <= 200

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/max-black-square-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int[] findSquare(int[][] matrix) {
        // 上方和 左边长度 连续 的0的长度
        int m = matrix.length;
        int n = matrix[0].length;
        // 0 是上面 1是左边
        int[][][] length = new int[m][n][2];
        // 生成len
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // ij 作为右下角
                length[i][j] = new int[2];
                if (matrix[i][j] == 1) {
                    continue;
                }
                // 当前就是 1 看看
                length[i][j][0] = 1;
                length[i][j][1] = 1;
                // 看看能不能
                if (i > 0) {
                    // 上
                    length[i][j][0] += length[i-1][j][0];
                }
                if (j > 0) {
                    // 左
                    length[i][j][1] += length[i][j-1][1];
                }
            }
        }
        int maxSize = 0;
        // 左上角的
        int r = 0;
        int c = 0;
        //[r, c, size] 按照 枚举右下角 判断
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前 能往前延伸的最小距离
                int min = Math.min(length[i][j][0], length[i][j][1]);

                for (int k = min; k > 0; k--) {
                    if (k <= maxSize) {
                        break;
                    }
                    // 左边点的上面
                    // 上边点的左边
                    if  (length[i - k + 1][j][1] >= k && length[i][j - k + 1][0] >= k) {
                        maxSize = k;
                        r = i - k + 1;
                        c = j - k + 1;
                        break;
                    }

                }

            }
        }
        if (maxSize == 0) {
            return new int[]{};
        }
        return new int[] {r, c, maxSize};
    }
}
