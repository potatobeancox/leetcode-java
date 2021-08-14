package com.potato.study.leetcodecn.other.Interview.p0001.p0004;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 01.04. 回文排列
 *
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。

 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。

 回文串不一定是字典当中的单词。

  

 示例1：

 输入："tactcoa"
 输出：true（排列有"tacocat"、"atcocta"，等等）

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 直接统计字母出现次数，判定是够有多余 1个的奇数个数
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = countMap.getOrDefault(s.charAt(i), 0);
            count++;
            countMap.put(s.charAt(i), count);
        }
        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                oddCount++;
            }
            if (oddCount > 1) {
                return false;
            }
        }
        return true;
    }
}
