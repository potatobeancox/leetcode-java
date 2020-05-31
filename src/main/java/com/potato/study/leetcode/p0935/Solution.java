package com.potato.study.leetcode.p0935;

import java.util.Arrays;

/**
 * 
 * @author liuzhao11
 * 
 * 	935. Knight Dialer
 *  
 *       A chess knight can move as indicated in the chess diagram below:

.



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.



Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46


Note:

1 <= N <= 5000
 *         
 *         题目含义：
 *
 *
 *         思路：
 *         https://leetcode-cn.com/problems/knight-dialer/solution/qi-shi-bo-hao-qi-by-leetcode/
 *
 * 
 */
public class Solution {

    public int knightDialer(int num) {
        int mod = 1_000_000_007;
        int[][] moves = new int[][]{
                {4,6},{6,8},{7,9},{4,8},{3,9,0},
                {},{1,7,0},{2,6},{1,3},{2,4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < num-1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei: moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= mod;
                }
        }

        long ans = 0;
        for (int x: dp[~num & 1]) {
            ans += x;
        }
        return (int) (ans % mod);
    }
}
