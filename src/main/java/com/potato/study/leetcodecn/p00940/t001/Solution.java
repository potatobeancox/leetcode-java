package com.potato.study.leetcodecn.p00940.t001;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 940. 不同的子序列 II
 *
 *
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 *
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 *
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 * 示例 2：
 *
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 * 示例 3：
 *
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/distinct-subsequences-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     *
     * @param s
     * @return
     */
    public int distinctSubseqII(String s) {
        // 使用一个数组 26个位置 记录 每个字母出现上一次 以i结尾的个数
        long[] lastCount = new long[26];
        // dp i 到当前位置 i 包括i 能构成的不同子序列的个数
        int mod = 1_000_000_000 + 7;
        long[] dp = new long[s.length()];
        // 遍历 s 对于每个位置 计算本次新增的个数 减去 重复的个数 新增的个数就是上一个位置的总个数 重复的个数就是 上一次出现字符的总个数
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (i == 0) {
                // 第一个位置的字符
                dp[i] = 1 + 1;
                lastCount[index] = 1;
            } else {
                // 不带有 i的个数 加上 带上i新增的个数 减去
                dp[i] = (dp[i-1] + dp[i-1]) % mod - lastCount[index];
                dp[i] %= mod;
                if (dp[i] <= 0) {
                    dp[i] += mod;
                }
                lastCount[index] = dp[i-1];
            }
        }
        // 空集不能算
        return (int) (dp[s.length() - 1] - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.distinctSubseqII("aba");
        System.out.println(res);
        Assert.assertEquals(6, res);
    }

}
