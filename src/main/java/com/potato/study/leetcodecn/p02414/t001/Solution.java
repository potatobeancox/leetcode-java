package com.potato.study.leetcodecn.p02414.t001;

import sun.jvm.hotspot.utilities.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 2414. 最长的字母序连续子字符串的长度
 *
 * 字母序连续字符串 是由字母表中连续字母组成的字符串。换句话说，字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是
 * 字母序连续字符串 。

 例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。
 给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。

  

 示例 1：

 输入：s = "abacaba"
 输出：2
 解释：共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
 "ab" 是最长的字母序连续子字符串。
 示例 2：

 输入：s = "abcde"
 输出：5
 解释："abcde" 是最长的字母序连续子字符串。
  

 提示：

 1 <= s.length <= 105
 s 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/length-of-the-longest-alphabetical-continuous-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestContinuousSubstring(String s) {
        // 记录当前连续长度
        int len = 0;
        char[] chars = s.toCharArray();
        // 要最长的
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 遍历 判断 当前和之前是否连续
            if (i > 0 && chars[i] - chars[i-1] == 1) {
                len++;
            } else {
                len = 1;
            }
            // 结算子串长度 以当前字母为结尾的子串有多少个
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.longestContinuousSubstring("abacaba");
        System.out.println(i);
        org.junit.Assert.assertEquals(2, i);
    }

}
