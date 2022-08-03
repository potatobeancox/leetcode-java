package com.potato.study.leetcodecn.p00531.t001;


import java.util.HashSet;
import java.util.Set;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 531. 孤独像素 I
 *
 * 给你一个大小为 m x n 的图像 picture ，图像由黑白像素组成，'B' 表示黑色像素，'W' 表示白色像素，请你统计并返回图像中 黑色 孤独像素的数量。
 *
 * 黑色孤独像素 的定义为：如果黑色像素 'B' 所在的同一行和同一列不存在其他黑色像素，那么这个黑色像素就是黑色孤独像素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * 输出：3
 * 解释：全部三个 'B' 都是黑色的孤独像素
 * 示例 2：
 *
 *
 * 输入：picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 500
 * picture[i][j] 为 'W' 或 'B'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lonely-pixel-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int findLonelyPixel(char[][] picture) {
        int n = picture.length;
        int m = picture[0].length;
        Set<Integer> lineNoSet = new HashSet<>();
        Set<Integer> columnNoSet = new HashSet<>();

        for (int i = 0; i < picture.length; i++) {
            // 每一行
            int count = 0;
            for (int j = 0; j < picture[i].length; j++) {
                if ('B' == picture[i][j]) {
                    count++;
                }
            }
            if (count == 1) {
                lineNoSet.add(i);
            }
        }
        for (int i = 0; i < picture[0].length; i++) {
            // 每一行
            int count = 0;
            for (int j = 0; j < picture.length; j++) {
                if ('B' == picture[j][i]) {
                    count++;
                }
            }
            if (count == 1) {
                columnNoSet.add(i);
            }
        }
        // 每个位置 是 B 且 行列都只有一个
        int lonelyCount = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if ('B' != picture[i][j]) {
                    continue;
                }
                if (columnNoSet.contains(j) && lineNoSet.contains(i)) {
                    lonelyCount++;
                }
            }
        }
        return lonelyCount;
    }
}
