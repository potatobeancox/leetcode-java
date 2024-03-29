package com.potato.study.leetcodecn.other.lcp.p0019.t001;

import java.util.Arrays;

/**
 * LCP 19. 秋叶收藏集
 *
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

 示例 1：

 输入：leaves = "rrryyyrryyyrr"

 输出：2

 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

 示例 2：

 输入：leaves = "ryr"

 输出：0

 解释：已符合要求，不需要额外操作

 提示：

 3 <= leaves.length <= 10^5
 leaves 中只包含字符 'r' 和字符 'y'

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/UlBDOe
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // lcp 19
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        int[][] dp = new int[n][3];
        // dp ij 当前第i个字符 调整 最小需要执行多少次
        for (int i = 0; i < n; i++) {
            char ch = leaves.charAt(i);
            if (ch == 'r') {
                dp[i][0] = 0;
                dp[i][1] = 1;
                dp[i][2] = 0;
            } else {
                // y
                dp[i][0] = 1;
                dp[i][1] = 0;
                dp[i][2] = 1;

            }
            if (i > 0) {
                dp[i][0] += dp[i-1][0];
                dp[i][1] += Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][2] += Math.min(dp[i-1][1], dp[i-1][2]);
            }
        }
        // 「红、黄、红 ryr 这种
        return dp[n-1][2];
    }

}
