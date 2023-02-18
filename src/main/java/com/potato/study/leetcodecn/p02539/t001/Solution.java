package com.potato.study.leetcodecn.p02539.t001;



/**
 * 2539. Count the Number of Good Subsequences
 *
 * A subsequence of a string is good if it is not empty and the frequency of each one of its characters is the same.
 *
 * Given a string s, return the number of good subsequences of s. Since the answer may be too large, return it modulo 109 + 7.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 *  
 *
 * Example 1:
 *
 * Input: s = "aabb"
 * Output: 11
 * Explanation: The total number of subsequences is 24. There are five subsequences which are not good: "aabb", "aabb", "aabb", "aabb", and the empty subsequence. Hence, the number of good subsequences is 24-5 = 11.
 * Example 2:
 *
 * Input: s = "leet"
 * Output: 12
 * Explanation: There are four subsequences which are not good: "leet", "leet", "leet", and the empty subsequence. Hence, the number of good subsequences is 24-4 = 12.
 * Example 3:
 *
 * Input: s = "abcd"
 * Output: 15
 * Explanation: All of the non-empty subsequences are good subsequences. Hence, the number of good subsequences is 24-1 = 15.
 *  
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-the-number-of-good-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



















    public int countGoodSubsequences(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // 统计26个字符 每个字符穿线的次数 过程中 记录最大出现次数
        int[] count = new int[26];
        int max = 1;
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
            max = Math.max(max, count[ch - 'a']);
        }
        // 从 1到 max 出现次数 遍历 每个出现次数，表示每个字母 如果选择的话 要选择的个数
        for (int i = 1; i <= max; i++) {
            // 每个字母的个数
            int eachCharCount = i;
            for (int j = 0; j < 26; j++) {
                // 枚举每个字母
                if (count[i] < eachCharCount) {
                    continue;
                }

            }
        }
        // 内部 遍历 1-26 每个字母 按照出现个数 算一个组合数字 组合也要加上 不选择的种类数 跟之前的 做乘法

        // 减去所有都不选择的个数

        return -1;
    }


    /**
     * 从 base 获取 target个 组合数量
     * @param base
     * @param target
     * @return
     */
    private long combination(int base, int target) {
        long res = 1;
        if (target == 0) {
            return res;
        }
        int mod = 1_000_000_000 + 7;
        for (int i = 1; i <= target; i++) {
            res *= i;
            res %= mod;
            
        }
        return res;
    }
    
    // 首先还要 计算阶乘 



}
