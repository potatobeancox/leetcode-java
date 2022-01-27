package com.potato.study.leetcodecn.p01653.t001;

import java.util.Arrays;

import org.junit.Assert;

/**
 * 1653. 使字符串平衡的最少删除次数
 *
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 *
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。我们称 s 平衡的 当不存在下标对 (i,j) 满足 i < j 且 s[i] = 'b' 同时 s[j]= 'a' 。
 *
 * 请你返回使 s 平衡 的 最少 删除次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * 示例 2：
 *
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'​ 。​
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-deletions-to-make-string-balanced
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * https://leetcode-cn.com/problems/minimum-deletions-to-make-string-balanced/solution/1653-shi-zi-fu-chuan-ping-heng-de-zui-sh-ikvp/
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        // dp i 0 到i位置 处理成a 需要的最小次数   dp i 0 到i位置 处理成b 需要的最小次数
        int[][] dp = new int[s.length()][2];
        char startCh = s.charAt(0);
        if (startCh == 'a') {
            dp[0][0] = 0;
            dp[0][1] = 1;
        } else {
            // b
            dp[0][0] = 1;
            dp[0][1] = 0;
        }
        // 判断 当前i 是哪个字母 决定状态转移
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                // 不用变
                dp[i][0] = dp[i-1][0];
                dp[i][1] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1);
            } else {
                // ch == 'b'
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }
        // min dp len 0  dp len 1
        return Math.min(dp[s.length()-1][0], dp[s.length()-1][1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aababbab";
        int i = solution.minimumDeletions(s);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
