package com.potato.study.leetcodecn.p02609.t001;

import java.util.TreeSet;

/**
 *
 * 2609. 最长平衡子字符串
 *
 * 给你一个仅由 0 和 1 组成的二进制字符串 s 。  
 *
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。 
 *
 * 返回  s 中最长的平衡子字符串长度。
 *
 * 子字符串是字符串中的一个连续字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "01000111"
 * 输出：6
 * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
 * 示例 2：
 *
 * 输入：s = "00111"
 * 输出：4
 * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
 * 示例 3：
 *
 * 输入：s = "111"
 * 输出：0
 * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2609
    public int findTheLongestBalancedSubstring(String s) {
        int oneCount = 0;
        int zeroCount = 0;

        int index = 0;
        int maxLength = 0;
        while (index <= s.length()) {
            // 先判断 当前index 是不是末尾或者 刚好从 1-》0
            if (index == s.length()
                    || (index > 0 && s.charAt(index) == '0' && s.charAt(index - 1) == '1')) {
                // 结算
                int current = Math.min(oneCount, zeroCount);
                maxLength = Math.max(maxLength, current * 2);

                oneCount = 0;
                zeroCount = 0;
            }

            if (index == s.length()) {
                break;
            }

            // 先找 0 再找 1
            while (index < s.length() && s.charAt(index) == '0') {
                zeroCount++;
                index++;
            }

            while (index < s.length() && s.charAt(index) == '1') {
                oneCount++;
                index++;
            }

        }
        return maxLength;
    }


}
