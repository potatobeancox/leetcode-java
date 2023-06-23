package com.potato.study.leetcodecn.p01278.t001;


import org.junit.Assert;

import java.util.Arrays;

/**
 * 1278. 分割回文串 III
 *
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。

 请你按下面的要求分割字符串：

 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 请返回以这种方式分割字符串所需修改的最少字符数。

  

 示例 1：

 输入：s = "abc", k = 2
 输出：1
 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
 示例 2：

 输入：s = "aabbc", k = 3
 输出：0
 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
 示例 3：

 输入：s = "leetcode", k = 8
 输出：0
  

 提示：

 1 <= k <= s.length <= 100
 s 中只含有小写英文字母。


 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/palindrome-partitioning-iii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param s
     * @param k 分成k个
     * @return
     */
    public int palindromePartition(String s, int k) {
        // 遍历s的每个开始和结束位置 计算要成为 回文需要修改的次数 用一个二维数据缓存
        int n = s.length();
        // 包含两端坐标
        int[][] minEdit = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                minEdit[i][j] = getCostToPalindrome(s, i, j);
            }
        }
        // dp ij s的前i个字符分成k个需要最少改多少个字母
        int[][] dp = new int[n][k+1];
        // 设置最大值
        for (int[] d: dp) {
            Arrays.fill(d, n);
        }
        // 枚举i 作为终止位置 内部枚举j 首先j要保证比i+1 小 每次往枚举最后几个字符 比较小最小值是多少
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // j不能比i+1大 否则不够分
                if (j > i+1) {
                    break;
                }
                // 只分成一个 直接获取结果
                if (j == 1) {
                    dp[i][j] = minEdit[0][i];
                    continue;
                }
                // j大于1 枚举之前j-1的结束位置i
                for (int l = 0; l+1 <= i; l++) {
                    dp[i][j] = Math.min(dp[i][j], dp[l][j-1] + minEdit[l+1][i]);
                }
            }
        }
        return dp[n-1][k];
    }

    /**
     * 计算s 从 i到j 变成回文最小修改
     * @param s
     * @param i
     * @param j
     * @return
     */
    private int getCostToPalindrome(String s, int i, int j) {
        int cost = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                cost++;
            }
            i++;
            j--;
        }
        return cost;
    }


}
