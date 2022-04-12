package com.potato.study.leetcodecn.p01930.t001;

import java.util.HashSet;
import java.util.Set;

/**
 * 1930. 长度为 3 的不同回文子序列
 *
 * 给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
 *
 * 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
 *
 * 回文 是正着读和反着读一样的字符串。
 *
 * 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
 *
 * 例如，"ace" 是 "abcde" 的一个子序列。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aabca"
 * 输出：3
 * 解释：长度为 3 的 3 个回文子序列分别是：
 * - "aba" ("aabca" 的子序列)
 * - "aaa" ("aabca" 的子序列)
 * - "aca" ("aabca" 的子序列)
 * 示例 2：
 *
 * 输入：s = "adc"
 * 输出：0
 * 解释："adc" 不存在长度为 3 的回文子序列。
 * 示例 3：
 *
 * 输入：s = "bbcbaba"
 * 输出：4
 * 解释：长度为 3 的 4 个回文子序列分别是：
 * - "bbb" ("bbcbaba" 的子序列)
 * - "bcb" ("bbcbaba" 的子序列)
 * - "bab" ("bbcbaba" 的子序列)
 * - "aba" ("bbcbaba" 的子序列)
 *  
 *
 * 提示：
 *
 * 3 <= s.length <= 105
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-length-3-palindromic-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1930
    public int countPalindromicSubsequence(String s) {
        // 从 a-z 找到 两边的 然后 用set 记录 中间有多少中情况 然后就可以计数了
        int count = 0;
        for (int i = 0; i < 26; i++) {
            char ch = (char)('a' + i);
            // 找到left  和 right
            int left = 0;
            int right = s.length() - 1;
            while (left < s.length() && s.charAt(left) != ch) {
                left++;
            }
            while (right >= 0 && s.charAt(right) != ch) {
                right--;
            }

            if (left == right) {
                continue;
            } else if (left > right) {
                continue;
            } else {
                Set<Character> set = new HashSet<>();
                for (int j = left + 1; j < right; j++) {
                    set.add(s.charAt(j));
                }
                count += set.size();
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        String s = "daabcbaabcbc";
//        String part = "abc";
//        Solution solution = new Solution();
//        String s1 = solution.removeOccurrences(s, part);
//        System.out.println(s1);
//        Assert.assertEquals("dab", s1);
    }


}
