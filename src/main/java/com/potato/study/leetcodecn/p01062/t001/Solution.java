package com.potato.study.leetcodecn.p01062.t001;

/**
 * 1062. 最长重复子串
 *
 * 给定字符串 S，找出最长重复子串的长度。如果不存在重复子串就返回 0。

  

 示例 1：

 输入："abcd"
 输出：0
 解释：没有重复子串。
 示例 2：

 输入："abbaba"
 输出：2
 解释：最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
 示例 3：

 输入："aabcaabdaab"
 输出：3
 解释：最长的重复子串为 "aab"，出现 3 次。
 示例 4：

 输入："aaaaa"
 输出：4
 解释：最长的重复子串为 "aaaa"，出现 2 次。
  

 提示：

 字符串 S 仅包含从 'a' 到 'z' 的小写英文字母。
 1 <= S.length <= 1500

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/longest-repeating-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int longestRepeatingSubstring(String s) {
        int maxLength = 0;
        int n = s.length();
        // dp ij 以ij为结尾的 子串匹配的长度最长是多少
        int[][] dp = new int[n+1][n+1];
        // dp ij = dp i-1 j-1 + 1 如果 ij 相同的话
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (i == j) {
                    continue;
                }
                if (s.charAt(i-1) == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }

}
