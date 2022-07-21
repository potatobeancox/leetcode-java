package com.potato.study.leetcodecn.p00266.t001;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 266. 回文排列

 *
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 *
 * 示例 1：
 *
 * 输入: "code"
 * 输出: false
 * 示例 2：
 *
 * 输入: "aab"
 * 输出: true
 * 示例 3：
 *
 * 输入: "carerac"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean canPermutePalindrome(String s) {
        // 统计数量
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 超过1个 奇数个数
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }


}
