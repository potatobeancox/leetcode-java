package com.potato.study.leetcodecn.p00524.t001;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 *
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 *
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 *
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String findLongestWord(String s, List<String> dictionary) {
        if (null == dictionary) {
            return "";
        }
        Collections.sort(dictionary, (word1, word2) -> {
            int score = Integer.compare(word2.length(), word1.length());
            if (score != 0) {
                return score;
            }
            // 字典序排序 字符串比较 string.compareTo
            return word1.compareTo(word2);
        });

        for (String word : dictionary) {
            if (ifCanConstitute(s, word)) {
                return word;
            }
        }
        return "";
    }


    /**
     * 判断 s 能不能组成 target
     * @param s
     * @param target
     * @return
     */
    private boolean ifCanConstitute(String s, String target) {
        // s 的比较位置
        int index1 = 0;
        // target 的比较位置
        int index2 = 0;
        while (index1 < s.length() && index2 < target.length()) {
            if (s.charAt(index1) == target.charAt(index2)) {
                index1++;
                index2++;
            } else {
                index1++;
            }
        }
        // 所有字符都可以找到 // s.length() == index1
        return target.length() == index2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new ArrayList<>();
        list.add("ale"); // "ale","apple","monkey","plea"
        list.add("apple");
        list.add("monkey");
        list.add("plea");

        String s = "abpcplea";
        String longestWord = solution.findLongestWord(s, list);
        System.out.println(longestWord);
        Assert.assertEquals("apple", longestWord);

        //        int[] nums = new int[]{23,2,4,6,7};
//        int k = 6;
//        boolean s = solution.checkSubarraySum(nums, k);
//        System.out.println(s);
//        Assert.assertEquals(true, s);
//
//        nums = new int[]{23,2,6,4,7};
//        k = 0;
//        s = solution.checkSubarraySum(nums, k);
//        System.out.println(s);
//        Assert.assertEquals(false, s);
    }

}
