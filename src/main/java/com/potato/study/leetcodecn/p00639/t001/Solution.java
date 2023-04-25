package com.potato.study.leetcodecn.p00639.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 639. 解码方法 II
 *
 * 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
 *
 * "AAJF" 对应分组 (1 1 10 6)
 * "KJF" 对应分组 (11 10 6)
 * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
 *
 * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
 *
 * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
 *
 * 由于答案数目可能非常大，返回 109 + 7 的 模 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "*"
 * 输出：9
 * 解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
 * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
 * 因此，"*" 总共有 9 种解码方法。
 * 示例 2：
 *
 * 输入：s = "1*"
 * 输出：18
 * 解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
 * 每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
 * 因此，"1*" 共有 9 * 2 = 18 种解码方法。
 * 示例 3：
 *
 * 输入：s = "2*"
 * 输出：15
 * 解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
 * "21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
 * 因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 是 0 - 9 中的一位数字或字符 '*'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decode-ways-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        // dp i 到了i的时候有多少种可能
        long[] dp = new long[length];
        // 只有一个字母有多少种
        // s[i] 是 0 - 9 中的一位数字或字符 '*'
        char c = s.charAt(0);
        if (c == '*') {
            dp[0] = 9;
        } else {
            dp[0] = 1;
        }
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i < length; i++) {
            dp[i] += getCount(s.charAt(i)) * dp[i-1];
            dp[i] %= mod;
            if (i >= 2) {
                dp[i] += getCount(s.charAt(i), s.charAt(i-1)) * dp[i-2];
            } else {
                dp[i] += getCount(s.charAt(i), s.charAt(i-1));
            }
            dp[i] %= mod;
        }
        return (int) dp[length - 1];
    }

    private long getCount(char ch1, char ch2) {
        if (ch1 == '0') {
            return 0;
        }
        if (ch1 == '1') {
            if (ch2 == '*') {
                // 10- 19
                return 10;
            } else {
                return 1;
            }
        }
        if (ch1 == '2') {
            if (ch2 == '*') {
                // 20 - 26
                return 7;
            } else {
                return 1;
            }
        }
        if (ch1 == '*') {
            if (ch2 == '*') {
                return 17;
            }
            if (ch2 <= '6') {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private long getCount(char ch) {
        if (ch == '*') {
            return 9;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {

    }
}
