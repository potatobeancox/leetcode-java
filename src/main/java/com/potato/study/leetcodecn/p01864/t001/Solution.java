package com.potato.study.leetcodecn.p01864.t001;

import org.junit.Assert;

/**
 * 1864. 构成交替字符串需要的最小交换次数
 *
 * 给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
 *
 * 交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
 *
 * 任意两个字符都可以进行交换，不必相邻 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "111000"
 * 输出：1
 * 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。
 * 示例 2：
 *
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替字符串了，不需要交换。
 * 示例 3：
 *
 * 输入：s = "1110"
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s[i] 的值为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1864
    public int minSwaps(String s) {
        // 遍历 s 统计两个字母 不同的次数 分别不同
        int start0Miss0Count = 0;
        int start0Miss1Count = 0;
        int start1Miss0Count = 0;
        int start1Miss1Count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char ch = chars[i];
            if (i % 2 == 0) {
                // 010101 or 1010101
                if (chars[i]== '1') {
                    start0Miss0Count++;
                } else {
                    // == '0'
                    start1Miss1Count++;
                }
            } else {
                // 010101 or 1010101
                if (chars[i]== '1') {
                    start1Miss0Count++;
                } else {
                    // i == 0
                    start0Miss1Count++;
                }
            }
        }
        // 是否有 不需要变化的
        if (start0Miss0Count + start0Miss1Count == 0 || start1Miss0Count + start1Miss1Count == 0) {
            return 0;
        }
        if (start0Miss0Count != start0Miss1Count && start1Miss0Count != start1Miss1Count) {
            return -1;
        }

        if ((start0Miss0Count + start0Miss1Count) % 2 != 0 && (start1Miss0Count + start1Miss1Count) % 2 != 0) {
            return -1;
        } else if ((start0Miss0Count + start0Miss1Count) % 2 != 0) {
            return (start1Miss0Count + start1Miss1Count) / 2;
        } else if ((start1Miss0Count + start1Miss1Count) % 2 != 0) {
            return (start0Miss0Count + start0Miss1Count) / 2;
        } else {
            return Math.min((start0Miss0Count + start0Miss1Count) / 2,
                    (start1Miss0Count + start1Miss1Count) / 2);
        }
    }
}
