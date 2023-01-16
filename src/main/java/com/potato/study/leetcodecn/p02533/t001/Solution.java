package com.potato.study.leetcodecn.p02533.t001;


/**
 * 2533. Number of Good Binary Strings
 *
 * You are given four integers minLenght, maxLength, oneGroup and zeroGroup.
 *
 * A binary string is good if it satisfies the following conditions:
 *
 * The length of the string is in the range [minLength, maxLength].
 * The size of each block of consecutive 1's is a multiple of oneGroup.
 * For example in a binary string 00110111100 sizes of each block of consecutive ones are [2,4].
 * The size of each block of consecutive 0's is a multiple of zeroGroup.
 * For example, in a binary string 00110111100 sizes of each block of consecutive ones are [2,1,2].
 * Return the number of good binary strings. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Note that 0 is considered a multiple of all the numbers.
 *
 *  
 *
 * Example 1:
 *
 * Input: minLength = 2, maxLength = 3, oneGroup = 1, zeroGroup = 2
 * Output: 5
 * Explanation: There are 5 good binary strings in this example: "00", "11", "001", "100", and "111".
 * It can be proven that there are only 5 good strings satisfying all conditions.
 * Example 2:
 *
 * Input: minLength = 4, maxLength = 4, oneGroup = 4, zeroGroup = 3
 * Output: 1
 * Explanation: There is only 1 good binary string in this example: "1111".
 * It can be proven that there is only 1 good string satisfying all conditions.
 *  
 *
 * Constraints:
 *
 * 1 <= minLength <= maxLength <= 105
 * 1 <= oneGroup, zeroGroup <= maxLength
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-good-binary-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup,
                                 int zeroGroup) {
        // dp i 到达 i 位置的种类数
        int[] dp = new int[maxLength + 1];
        dp[0] = 1;
        int mod = 1_000_000_000 + 7;
        for (int i = 0; i < maxLength + 1; i++) {
            if (i >= oneGroup) {
                dp[i] += dp[i-oneGroup];
            }
            if (i >= zeroGroup) {
                dp[i] += dp[i-zeroGroup];
            }
            dp[i] %= mod;
        }
        //  统计 最大到最小
        int total = 0;
        for (int i = minLength; i <= maxLength; i++) {
            total += dp[i];
            total %= mod;
        }
        return total;
    }



}
