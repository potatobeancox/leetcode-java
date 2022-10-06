package com.potato.study.leetcodecn.p00533.t001;


import org.junit.Assert;

import java.util.Arrays;

/**
 * 533. 孤独像素 II
 *
 * 给你一个大小为 m x n 的二维字符数组 picture ，表示一张黑白图像，数组中的 'B' 表示黑色像素，'W' 表示白色像素。另给你一个整数 target ，请你找出并返回符合规则的 黑色 孤独像素的数量。
 *
 * 黑色孤独像素是指位于某一特定位置 (r, c) 的字符 'B' ，其中：
 *
 * 行 r 和列 c 中的黑色像素恰好有 target 个。
 * 列 c 中所有黑色像素所在的行必须和行 r 完全相同。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
 * 输出：6
 * 解释：所有绿色的 'B' 都是我们所求的像素(第 1 列和第 3 列的所有 'B' )
 * 以行 r = 0 和列 c = 1 的 'B' 为例：
 * - 规则 1 ，行 r = 0 和列 c = 1 都恰好有 target = 3 个黑色像素
 * - 规则 2 ，列 c = 1 的黑色像素分别位于行 0，行 1 和行 2。和行 r = 0 完全相同。
 * 示例 2：
 *
 *
 * 输入：picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
 * 输出：0
 *  
 *
 * 提示：
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 200
 * picture[i][j] 为 'W' 或 'B'
 * 1 <= target <= min(m, n)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lonely-pixel-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findBlackPixel(char[][] picture, int target) {
        // 统计 每行每列 有多少个黑的
        int m = picture.length;
        int n = picture[0].length;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        String[] rowStringArray = new String[m];
        for (int i = 0; i < m; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }
                builder.append(picture[i][j]);
            }
            // 将每行 用 builder 存储起来
            rowStringArray[i] = builder.toString();
        }
        // 遍历 每个位置 如果是黑的 比较一下 看看 其他行跟着行是不是一致 黑色元素所在行
        int picCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'W') {
                    continue;
                }
                if (target != rowCount[i]|| target != colCount[j]) {
                    continue;
                }
                // 列 中黑的元素所在行与这个都一致
                boolean allBlackRowSame = true;
                for (int k = 0; k < m; k++) {
                    if (k == i) {
                        continue;
                    }
                    // 这行的 j 位置不是黑的
                    if (picture[k][j] != 'B') {
                        continue;
                    }
                    if (!rowStringArray[k].equals(rowStringArray[i])) {
                        allBlackRowSame = false;
                        break;
                    }
                }
                if (allBlackRowSame) {
                    picCount++;
                }
            }
        }
        return picCount;
    }


}
