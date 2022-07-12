package com.potato.study.leetcodecn.p01220.t001;


import java.util.Arrays;

import org.junit.Assert;

/**
 * 1220. 统计元音字母序列的数目
 *
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 *
 * 输入：n = 5
 * 输出：68
 *  
 *
 * 提示：
 *
 * 1 <= n <= 2 * 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-vowels-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int countVowelPermutation(int n) {
        // 1220
        // dp 确定 aeiou每个字母之前只能是什么字母 进行转移 dp i 以 a-u结尾的次数
        int[] dp = new int[5];
        // 初始化 只有一个字母的时候 只有一种可能
        Arrays.fill(dp, 1);
        // aeiou 从0 开始 对应的数组值 a-0  e-1 i-2 o-3 u-4
        int mod = 1_000_000_000 + 7;
        for (int i = 2; i <= n; i++) {
            int[] next = new int[5];
            // a 前面只能是 e iu
            next[0] = dp[1] + dp[2];
            next[0] %= mod;
            next[0] += dp[4];
            next[0] %= mod;
            // e 前面只能是 ai
            next[1] = dp[0] + dp[2];
            next[1] %= mod;
            // i 前面只能是 eo
            next[2] = dp[1] + dp[3];
            next[2] %= mod;
            // o 前面只能是 i
            next[3] = dp[2];
            // u 前面只能是 io
            next[4] = dp[2] + dp[3];
            next[4] %= mod;

            dp = next;
        }
        // 统计每个结尾对应的值
        long count = 0;
        for (int i = 0; i < 5; i++) {
            count += dp[i];
            count %= mod;
        }
        return (int) (count % mod);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countVowelPermutation(1);
        System.out.println(i);
        Assert.assertEquals(5, i);


        i = solution.countVowelPermutation(144);
        System.out.println(i);
        Assert.assertEquals(18208803, i);
    }
}
