package com.potato.study.leetcode.p0943;

import java.util.Arrays;

/**
 * 
 * @author liuzhao11
 * 
 * 	943. Find the Shortest Superstring
 *  
 *      Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.


Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"


Note:

1 <= A.length <= 12
1 <= A[i].length <= 20

 *         
 *         题目含义：
 *
 *
 *         思路：
 *          https://leetcode-cn.com/problems/find-the-shortest-superstring/solution/zui-duan-chao-ji-chuan-by-leetcode/
 *
 * 
 */
public class Solution {

    public String shortestSuperstring(String[] aWord) {
        int length = aWord.length;

        // Populate overlaps
        int[][] overlaps = new int[length][length];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) if (i != j) {
                int m = Math.min(aWord[i].length(), aWord[j].length());
                for (int k = m; k >= 0; --k) {
                    if (aWord[i].endsWith(aWord[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
                }
            }
        }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1<<length][length];
        int[][] parent = new int[1<<length][length];
        for (int mask = 0; mask < (1<<length); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < length; ++bit) if (((mask >> bit) & 1) > 0) {
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                int pmask = mask ^ (1 << bit);
                if (pmask == 0) {
                    continue;
                }
                for (int i = 0; i < length; ++i) if (((pmask >> i) & 1) > 0) {
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    int val = dp[pmask][i] + overlaps[i][bit];
                    if (val > dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[length];
        boolean[] seen = new boolean[length];
        int t = 0;
        int mask = (1 << length) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < length; ++j) {
            if (dp[(1<<length) - 1][j] > dp[(1<<length) - 1][p]) {
                p = j;
            }
        }

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < length; ++i) {
            if (!seen[i]) {
                perm[t++] = i;
            }
        }

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(aWord[perm[0]]);
        for (int i = 1; i < length; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(aWord[perm[i]].substring(overlap));
        }

        return ans.toString();
    }
}
