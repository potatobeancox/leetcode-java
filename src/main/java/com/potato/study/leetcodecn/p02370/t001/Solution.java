package com.potato.study.leetcodecn.p02370.t001;


import org.junit.Assert;

/**
 * 2370. 最长理想子序列
 *
 * 给你一个由小写字母组成的字符串 s ，和一个整数 k 。如果满足下述条件，则可以将字符串 t 视作是 理想字符串 ：

 t 是字符串 s 的一个子序列。
 t 中每两个 相邻 字母在字母表中位次的绝对差值小于或等于 k 。
 返回 最长 理想字符串的长度。

 字符串的子序列同样是一个字符串，并且子序列还满足：可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。

 注意：字母表顺序不会循环。例如，'a' 和 'z' 在字母表中位次的绝对差值是 25 ，而不是 1 。

  

 示例 1：

 输入：s = "acfgbd", k = 2
 输出：4
 解释：最长理想字符串是 "acbd" 。该字符串长度为 4 ，所以返回 4 。
 注意 "acfgbd" 不是理想字符串，因为 'c' 和 'f' 的字母表位次差值为 3 。
 示例 2：

 输入：s = "abcd", k = 3
 输出：4
 解释：最长理想字符串是 "abcd" ，该字符串长度为 4 ，所以返回 4 。
  

 提示：

 1 <= s.length <= 105
 0 <= k <= 25
 s 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-ideal-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestIdealString(String s, int k) {
        // dp ij  从 0-i字符串中 以 j作为几位的最长理想序列长度
        int[][] dp = new int[s.length()][26];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            int left = Math.max(0, index - k);
            int right = Math.min(26 - 1, index + k);
            // 看一下当前字母 前后 k个字符
            dp[i][index] = 1;
            for (int j = left; j <= right; j++) {
                if (i == 0) {
                    dp[i][index] = 1;
                } else {
                    dp[i][index] = Math.max(dp[i-1][j] + 1, dp[i][index]);
                }
            }
            // 不是index的其他位置
            if (i != 0) {
                for (int j = 0; j < 26; j++) {
                    if (j == index) {
                        continue;
                    }
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            max = Math.max(max, dp[s.length() - 1][i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "eduktdb";
        int k = 15;
        int i = solution.longestIdealString(word, k);
        System.out.println(i);
        Assert.assertEquals(5, i);
    }

}
