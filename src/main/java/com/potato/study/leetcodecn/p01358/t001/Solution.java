package com.potato.study.leetcodecn.p01358.t001;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1358. 包含所有三种字符的子字符串数目
 *
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 *
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。
 * 示例 2：
 *
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。
 * 示例 3：
 *
 * 输入：s = "abc"
 * 输出：1
 *  
 *
 * 提示：
 *
 * 3 <= s.length <= 5 x 10^4
 * s 只包含字符 a，b 和 c 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-substrings-containing-all-three-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1358
    public int numberOfSubstrings(String s) {
        // 维护 一个窗口 枚举右边 每次 能够达成是 往回收左边 计算最终的数量
        int total = 0;
        int[] count = new int[3];
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            count[ch-'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0 ) {
                total += (s.length() - right);
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return total;
    }
}
