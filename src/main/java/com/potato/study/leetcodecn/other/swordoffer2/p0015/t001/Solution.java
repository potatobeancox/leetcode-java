package com.potato.study.leetcodecn.other.swordoffer2.p0015.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 变位词 指字母相同，但排列不同的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 *  示例 2：
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 *  
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *  
 *
 * 注意：本题与主站 438 题相同： https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/VabMRr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // ii 015
    public List<Integer> findAnagrams(String s, String p) {
        // 对 p计数 得到窗口要得到的数量
        int[] count = new int[26];
        for (char ch : p.toCharArray()) {
            count[ch - 'a']++;
        }
        List<Integer> indexList = new ArrayList<>();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 窗口内的 == 窗口外边的++
            count[ch-'a']--;
            if (i - left + 1 > p.length()) {
                count[s.charAt(left) - 'a']++;
                left++;
            }
            // check 是不是计数都归0了
            if (checkCount(count)) {
                indexList.add(left);
            }
        }
        return indexList;
    }

    private boolean checkCount(int[] count) {
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
