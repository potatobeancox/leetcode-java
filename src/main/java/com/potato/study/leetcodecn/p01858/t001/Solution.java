package com.potato.study.leetcodecn.p01858.t001;

import java.util.Stack;

import org.junit.Assert;

/**
 * 1858. 包含所有前缀的最长单词
 *
 * 给定一个字符串数组 words，找出 words 中所有的前缀都在 words 中的最长字符串。
 *
 * 例如，令 words = ["a", "app", "ap"]。字符串 "app" 含前缀 "ap" 和 "a" ，都在 words 中。
 * 返回符合上述要求的字符串。如果存在多个（符合条件的）相同长度的字符串，返回字典序中最小的字符串，如果这样的字符串不存在，返回 ""。
 *
 *  
 *
 * 示例 1:
 *
 * 输入： words = ["k","ki","kir","kira", "kiran"]
 * 输出： "kiran"
 * 解释： "kiran" 含前缀 "kira"、 "kir"、 "ki"、 和 "k"，这些前缀都出现在 words 中。
 * 示例 2:
 *
 * 输入： words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出： "apple"
 * 解释： "apple" 和 "apply" 都在 words 中含有各自的所有前缀。
 * 然而，"apple" 在字典序中更小，所以我们返回之。
 * 示例 3:
 *
 * 输入： words = ["abc", "bc", "ab", "qwe"]
 * 输出： ""
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 105
 * 1 <= words[i].length <= 105
 * 1 <= sum(words[i].length) <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-word-with-all-prefixes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String longestWord(String[] words) {
        return null;
    }
}
