package com.potato.study.leetcodecn.p00516.t001;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 516. 最长回文子序列
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。

  

 示例 1：

 输入：s = "bbbab"
 输出：4
 解释：一个可能的最长回文子序列为 "bbbb" 。
 示例 2：

 输入：s = "cbbd"
 输出：2
 解释：一个可能的最长回文子序列为 "bb" 。
  

 提示：

 1 <= s.length <= 1000
 s 仅由小写英文字母组成


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        // dp ij ij 之间的最长会问子串 ij相同
        int[][] dp = new int[s.length()][s.length()];
        // 初始化 ii
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        // dp ij = dp i+1,j-1 +2 否则 i或者j没用 最大值
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int n = 2;
//        int i = solution.countArrangement(n);
//        System.out.println(i);
//        Assert.assertEquals(2, i);
    }
}
