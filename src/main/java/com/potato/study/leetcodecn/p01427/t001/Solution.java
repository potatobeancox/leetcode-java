package com.potato.study.leetcodecn.p01427.t001;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1427. 字符串的左右移
 *
 * 给定一个包含小写英文字母的字符串 s 以及一个矩阵 shift，其中 shift[i] = [direction, amount]：
 *
 * direction 可以为 0 （表示左移）或 1 （表示右移）。
 * amount 表示 s 左右移的位数。
 * 左移 1 位表示移除 s 的第一个字符，并将该字符插入到 s 的结尾。
 * 类似地，右移 1 位表示移除 s 的最后一个字符，并将该字符插入到 s 的开头。
 * 对这个字符串进行所有操作后，返回最终结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", shift = [[0,1],[1,2]]
 * 输出："cab"
 * 解释：
 * [0,1] 表示左移 1 位。 "abc" -> "bca"
 * [1,2] 表示右移 2 位。 "bca" -> "cab"
 * 示例 2：
 *
 * 输入：s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * 输出："efgabcd"
 * 解释：
 * [1,1] 表示右移 1 位。 "abcdefg" -> "gabcdef"
 * [1,1] 表示右移 1 位。 "gabcdef" -> "fgabcde"
 * [0,2] 表示左移 2 位。 "fgabcde" -> "abcdefg"
 * [1,3] 表示右移 3 位。 "abcdefg" -> "efgabcd"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 只包含小写英文字母
 * 1 <= shift.length <= 100
 * shift[i].length == 2
 * 0 <= shift[i][0] <= 1
 * 0 <= shift[i][1] <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/perform-string-shifts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String stringShift(String s, int[][] shift) {
        int totalShift = 0;
        for (int[] eachShift : shift) {
            // direction 可以为 0 （表示左移）或 1 （表示右移）。
            int direction = eachShift[0];
            //  amount 表示 s 左右移的位数。
            int step = eachShift[1];
            if (direction == 0) {
                totalShift += step;
            } else {
                totalShift -= step;
            }
        }
        // totalShift > 0 左移动
        if (totalShift == 0) {
            return s;
        }
        if (totalShift > 0) {
            String left = s.substring(0, totalShift);
            String right = s.substring(totalShift);
            return right + left;
        } else {
            // totalShift < 0
            String right = s.substring(s.length() + totalShift);
            String left = s.substring(0, s.length() + totalShift);
            return right + left;
        }
    }
}
