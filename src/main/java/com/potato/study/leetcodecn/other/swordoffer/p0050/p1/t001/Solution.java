package com.potato.study.leetcodecn.other.swordoffer.p0050.p1.t001;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 *
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

 示例:

 s = "abaccdeff"
 返回 "b"

 s = ""
 返回 " "
  

 限制：

 0 <= s 的长度 <= 50000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 从后往前遍历，使用set 记录出现的字符
     * 如果当前字符 没有出现 那么记录当前字符为可能最开始的字符 入栈，否则直接丢弃
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            if (count[ch - 'a'] == 1) {
                return ch;
            }
        }
        return ' ';
    }

}
