package com.potato.study.leetcodecn.p00159.t001;

/**
 * 159. 至多包含两个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // 滑动窗口 使用 count【】 数组 判断 当前 字符数量
        int left = 0;
        int[] count = new int[256];
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = ch;
            // 统计当前 index 有多少个 字符
            count[index]++;
            while (bitCount(count) > 2 && left <= i) {
                count[s.charAt(left)]--;
                left++;
            }
            maxLen = Math.max(maxLen, i - left + 1);

        }
        return maxLen;
    }


    private int bitCount(int[] count) {
        int bitCount = 0;
        for (int i = 0; i < 256; i++) {
            if (count[i] > 0) {
                bitCount++;
            }
        }
        return bitCount;
    }
}
