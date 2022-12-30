package com.potato.study.leetcodecn.p01682.t001;

/**
 * 1682. 最长回文子序列 II
 *
 * 字符串 s 的某个子序列符合下列条件时，称为“好的回文子序列”：

 它是 s 的子序列。
 它是回文序列（反转后与原序列相等）。
 长度为偶数。
 除中间的两个字符外，其余任意两个连续字符不相等。
 例如，若 s = "abcabcabb"，则 "abba" 可称为“好的回文子序列”，而 "bcb" （长度不是偶数）和 "bbbb" （含有相等的连续字符）不能称为“好的回文子序列”。

 给定一个字符串 s， 返回 s 的最长“好的回文子序列”的长度。

  

 示例 1:

 输入: s = "bbabab"
 输出: 4
 解释: s 的最长“好的回文子序列”是 "baab"。
 示例 2:

 输入: s = "dcbccacdb"
 输出: 4
 解释: s 的最长“好的回文子序列”是 "dccd"。
  

 提示:

 1 <= s.length <= 250
 s 包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-palindromic-subsequence-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1682
    public int longestPalindromeSubseq(String s) {
        // dp ij k=1-26 已 ij作为 子串的两个端点 端点为 k字母最长 序列长度
        int len = s.length();
        int[][][] dp = new int[len][len][26];
        // 初始化 dp ij k 为 -1
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < 26; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        // 对于每个字母 从 0 找到 n-1 维护其中最大值
        int max = -1;
        for (int i = 0; i < 26; i++) {
            int current = getLongestPalindromeSubseq(0, len-1, i, s, dp);
            max = Math.max(max, current);
        }
        return max;
    }


    /**
     *
     * @param left
     * @param right
     * @param charIndex
     * @param s
     * @param dp
     * @return
     */
    private int getLongestPalindromeSubseq(int left, int right, int charIndex, String s, int[][][] dp) {
        // 如果当前ij 已经有了 对应的结果 直接返回
        if (dp[left][right][charIndex] != -1) {
            return dp[left][right][charIndex];
        }
        if (left >= right) {
            dp[left][right][charIndex] = 0;
            return 0;
        }

        // 如果当前左边点不是 charindex 往右边找
        if (s.charAt(left) - 'a' != charIndex) {
            dp[left+1][right][charIndex] = getLongestPalindromeSubseq(left+1, right, charIndex, s, dp);
            return dp[left+1][right][charIndex];
        }

        // 如果 当前右边点 不是 charIndex 往左边找
        if (s.charAt(right) - 'a' != charIndex) {
            dp[left][right-1][charIndex] = getLongestPalindromeSubseq(left, right-1, charIndex, s, dp);
            return dp[left][right-1][charIndex];
        }
        // 如果这种情况是 左右 都是 charindex 找到内部 递归获取到的最大值 + 2
        int maxLen = -1;
        for (int i = 0; i < 26; i++) {
            // i是内部的 char
            maxLen = Math.max(maxLen, 2 + getLongestPalindromeSubseq(left+1, right-1, i, s, dp));
        }
        dp[left][right][charIndex] = maxLen;
        return maxLen;
    }


}
