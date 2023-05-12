package com.potato.study.leetcodecn.p00903.t001;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 903. DI 序列的有效排列
 *
 * 给定一个长度为 n 的字符串 s ，其中 s[i] 是:

 “D” 意味着减少，或者
 “I” 意味着增加
 有效排列 是对有 n + 1 个在 [0, n]  范围内的整数的一个排列 perm ，使得对所有的 i：

 如果 s[i] == 'D'，那么 perm[i] > perm[i+1]，以及；
 如果 s[i] == 'I'，那么 perm[i] < perm[i+1]。
 返回 有效排列  perm的数量 。因为答案可能很大，所以请返回你的答案对 109 + 7 取余。

  

 示例 1：

 输入：s = "DID"
 输出：5
 解释：
 (0, 1, 2, 3) 的五个有效排列是：
 (1, 0, 3, 2)
 (2, 0, 3, 1)
 (2, 1, 3, 0)
 (3, 0, 2, 1)
 (3, 1, 2, 0)
 示例 2:

 输入: s = "D"
 输出: 1
  

 提示:

 n == s.length
 1 <= n <= 200
 s[i] 不是 'I' 就是 'D'


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/valid-permutations-for-di-sequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/valid-permutations-for-di-sequence/solution/di-xu-lie-de-you-xiao-pai-lie-by-leetcode/
     * @param s
     * @return
     */
    public int numPermsDISequence(String s) {
        int mod = 1_000_000_000 + 7;
        // dp ij 遍历到 i-1位置 最终数字为j 的可能数
        int n = s.length();
        long[][] dp = new long[n+1][n+1];
        // 以0为结尾 开始的数字有一种
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 枚举每个位置
            for (int j = 0; j <= i; j++) {
                // 枚举没一种可能的结尾
                char ch = s.charAt(i - 1);
                // 过程中 如果当前是D 那么就是 比当前j小的数的累加 反之就是大的累加 记得mod
                if (ch == 'D') {
                    // 将从 j+1 到
                    for (int k = j; k < i; k++) {
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] %= mod;
                    }
                } else {
                    // 枚举比j xiao 的
                    for (int k = 0; k < j; k++) {
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] %= mod;
                    }
                }
            }
        }
        // 最终需要累加 dp n 【0-n】
        long result = 0;
        for (long count : dp[n]) {
            result += count;
            result %= mod;
        }
        return (int) result;
    }
}
