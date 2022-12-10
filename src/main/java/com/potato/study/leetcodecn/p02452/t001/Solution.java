package com.potato.study.leetcodecn.p02452.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2452. 距离字典两次编辑以内的单词
 *
 * 给你两个字符串数组 queries 和 dictionary 。数组中所有单词都只包含小写英文字母，且长度都相同。
 *
 * 一次 编辑 中，你可以从 queries 中选择一个单词，将任意一个字母修改成任何其他字母。从 queries 中找到所有满足以下条件的字符串：不超过 两次编辑内，字符串与 dictionary 中某个字符串相同。
 *
 * 请你返回 queries 中的单词列表，这些单词距离 dictionary 中的单词 编辑次数 不超过 两次 。单词返回的顺序需要与 queries 中原本顺序相同。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * 输出：["word","note","wood"]
 * 解释：
 * - 将 "word" 中的 'r' 换成 'o' ，得到 dictionary 中的单词 "wood" 。
 * - 将 "note" 中的 'n' 换成 'j' 且将 't' 换成 'k' ，得到 "joke" 。
 * - "ants" 需要超过 2 次编辑才能得到 dictionary 中的单词。
 * - "wood" 不需要修改（0 次编辑），就得到 dictionary 中相同的单词。
 * 所以我们返回 ["word","note","wood"] 。
 * 示例 2：
 *
 * 输入：queries = ["yes"], dictionary = ["not"]
 * 输出：[]
 * 解释：
 * "yes" 需要超过 2 次编辑才能得到 "not" 。
 * 所以我们返回空数组。
 *  
 *
 * 提示：
 *
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * 所有 queries[i] 和 dictionary[j] 都只包含小写英文字母。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/words-within-two-edits-of-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

















    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        // 纯暴力
        List<String> resultList = new ArrayList<>();
        for (String word : queries) {
            int len = word.length();
            boolean isValid = false;
            for (String dic: dictionary) {
                int diff = 0;
                for (int i = 0; i < len; i++) {
                    if (dic.charAt(i) != word.charAt(i)) {
                        diff++;
                    }
                    if (diff > 2) {
                        break;
                    }
                }
                // 找到了一个
                if (diff <= 2) {
                    isValid = true;
                    break;
                }
            }
            if (isValid) {
                resultList.add(word);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] queries = new String[] {
                "word","note","ants","wood"
        };
        String[] dictionary = new String[] {
                "wood","joke","moat"
        };
        List<String> strings = solution.twoEditWords(queries, dictionary);
        System.out.println(strings);
    }


}
