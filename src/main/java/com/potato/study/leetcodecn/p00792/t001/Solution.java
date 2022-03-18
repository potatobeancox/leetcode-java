package com.potato.study.leetcodecn.p00792.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * 792. 匹配子序列的单词数
 *
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 *
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 * ​​​​
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-matching-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int numMatchingSubseq(String s, String[] words) {
        // 使用map 记录重复次数 进行剪枝
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        // 遍历 s 计算 子序列个数 返回个数
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            if (countMap.get(words[i]) > 0) {
                boolean res = isSubseg(s, words[i]);
                if (res) {
                    result += countMap.get(words[i]);
                }
                countMap.put(words[i], 0);
            }
        }
        return result;
    }

    /**
     * 判断是够子序列 word 是否是 s的
     * @param s
     * @param word
     * @return
     */
    private boolean isSubseg(String s, String word) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < s.length() && index2 < word.length()) {
            char ch1 = s.charAt(index1);
            char ch2 = word.charAt(index2);
            if (ch1 != ch2) {
                index1++;
                continue;
            }
            index1++;
            index2++;
        }
        return index2 == word.length();
    }

    public static void main(String[] args) {
        //
        //[]
        Solution solution = new Solution();
        String s = "abcde";
        String[] word = new String[] {
                "a","bb","acd","ace"
        };
        int i = solution.numMatchingSubseq(s, word);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
