package com.potato.study.leetcodecn.p02489.t001;

import com.potato.study.leetcode.domain.ListNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 2489. Number of Substrings With Fixed Ratio
 *
 * You are given a binary string s, and two integers num1 and num2. num1 and num2 are coprime numbers.
 *
 * A ratio substring is a substring of s where the ratio between the number of 0's and the number of 1's in the substring is exactly num1 : num2.
 *
 * For example, if num1 = 2 and num2 = 3, then "01011" and "1110000111" are ratio substrings, while "11000" is not.
 * Return the number of non-empty ratio substrings of s.
 *
 * Note that:
 *
 * A substring is a contiguous sequence of characters within a string.
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
 *  
 *
 * Example 1:
 *
 * Input: s = "0110011", num1 = 1, num2 = 2
 * Output: 4
 * Explanation: There exist 4 non-empty ratio substrings.
 * - The substring s[0..2]: "0110011". It contains one 0 and two 1's. The ratio is 1 : 2.
 * - The substring s[1..4]: "0110011". It contains one 0 and two 1's. The ratio is 1 : 2.
 * - The substring s[4..6]: "0110011". It contains one 0 and two 1's. The ratio is 1 : 2.
 * - The substring s[1..6]: "0110011". It contains two 0's and four 1's. The ratio is 2 : 4 == 1 : 2.
 * It can be shown that there are no more ratio substrings.
 * Example 2:
 *
 * Input: s = "10101", num1 = 3, num2 = 1
 * Output: 0
 * Explanation: There is no ratio substrings of s. We return 0.
 *  
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * 1 <= num1, num2 <= s.length
 * num1 and num2 are coprime integers.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-substrings-with-fixed-ratio
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



















    public long fixedRatio(String s, int num1, int num2) {
        // 遍历 s 记录 每个位置 之前 ones 个数 和 zero个数 并且计算 zero * num2 - one * num1 值 个数 找到之前的个数求和
        long oneCount = 0;
        long zeroCount = 0;
        long totalCount = 0;
        Map<Long, Long> appearCountMap = new HashMap<>();
        appearCountMap.put(0L, 1L);
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                oneCount++;
            } else {
                zeroCount++;
            }
            long target = zeroCount * num2 - oneCount * num1;
            long count = appearCountMap.getOrDefault(target, 0L);
            totalCount += count;
            // 计算当前个数
            appearCountMap.put(target, count + 1);
        }
        return totalCount;
    }
}
