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
        // 统计
        return 0;
    }


}
