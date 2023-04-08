package com.potato.study.leetcodecn.other.swordoffer2.p0096.t001;

import java.util.Arrays;

/**
 * 剑指 Offer II 096. 字符串交织
 *
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。

 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

 s = s1 + s2 + ... + sn
 t = t1 + t2 + ... + tm
 |n - m| <= 1
 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 提示：a + b 意味着字符串 a 和 b 连接。

  

 示例 1：



 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 输出：true
 示例 2：

 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 输出：false
 示例 3：

 输入：s1 = "", s2 = "", s3 = ""
 输出：true
  

 提示：

 0 <= s1.length, s2.length <= 100
 0 <= s3.length <= 200
 s1、s2、和 s3 都由小写英文字母组成
  

 注意：本题与主站 97 题相同： https://leetcode-cn.com/problems/interleaving-string/

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/IY6buf
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        // dp ij
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int index = i + j - 1;

                if (i >= 1 && s3.charAt(index) == s1.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j] || dp[i][j];
                }

                if (j >= 1 && s3.charAt(index) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i][j] || dp[i][j-1];
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
