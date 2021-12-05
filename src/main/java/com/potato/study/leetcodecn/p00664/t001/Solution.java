package com.potato.study.leetcodecn.p00664.t001;

import org.junit.Assert;

/**
 * 664. 奇怪的打印机
 *
 * 有台奇怪的打印机有以下两个特殊要求：

 打印机每次只能打印由 同一个字符 组成的序列。
 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。

  
 示例 1：

 输入：s = "aaabbb"
 输出：2
 解释：首先打印 "aaa" 然后打印 "bbb"。
 示例 2：

 输入：s = "aba"
 输出：2
 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
  

 提示：

 1 <= s.length <= 100
 s 由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/strange-printer
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        // dp ij 打印ij 需要的最小次数
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            // 只有一个字母直接删除
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                // 如果 ij 位置对应的字母 相同 那么 dp ij = dp ij-1
                if (s.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i][j-1];
                    continue;
                }
                // 不相同
                dp[i][j] = j-i+1;
                for (int k = i; k < j; k++) {
                    // 如果不同，从 i到j开始枚举么个位置 k dp ij = min dp ik + dpk+1 j
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaabbb";
        int i = solution.strangePrinter(s);
        System.out.println(i);
        Assert.assertEquals(2, i);


        s = "tbgtgb";
        i = solution.strangePrinter(s);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
