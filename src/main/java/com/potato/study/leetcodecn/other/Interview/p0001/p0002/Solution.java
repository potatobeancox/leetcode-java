package com.potato.study.leetcodecn.other.Interview.p0001.p0002;


/**
 * 面试题 01.02. 判定是否互为字符重排
 *
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。

 示例 1：

 输入: s1 = "abc", s2 = "bca"
 输出: true
 示例 2：

 输入: s1 = "abc", s2 = "bad"
 输出: false
 说明：

 0 <= len(s1) <= 100
 0 <= len(s2) <= 100

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/check-permutation-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 计数比较
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] charCount1 = getCharCount(s1);
        int[] charCount2 = getCharCount(s2);
        return isSameArr(charCount1, charCount2);
    }

    /**
     *
     * @param word
     * @return
     */
    private int[] getCharCount(String word) {
        int[] count = new int[26];
        for (char ch : word.toCharArray()) {
            count[ch - 'a']++;
        }
        return count;
    }

    /**
     *
     * @param ch1
     * @param ch2
     * @return
     */
    private boolean isSameArr(int[] ch1, int[] ch2) {
        for (int i = 0; i < 26; i++) {
            if (ch1[i] != ch2[i]) {
                return false;
            }
        }
        return true;
    }
}
