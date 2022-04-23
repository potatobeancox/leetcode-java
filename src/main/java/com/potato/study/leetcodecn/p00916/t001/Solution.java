package com.potato.study.leetcodecn.p00916.t001;

import java.util.ArrayList;
import java.util.List;

/**
 * 916. 单词子集
 *
 * 给你两个字符串数组 words1 和 words2。

 现在，如果 b 中的每个字母都出现在 a 中，包括重复出现的字母，那么称字符串 b 是字符串 a 的 子集 。

 例如，"wrr" 是 "warrior" 的子集，但不是 "world" 的子集。
 如果对 words2 中的每一个单词 b，b 都是 a 的子集，那么我们称 words1 中的单词 a 是 通用单词 。

 以数组形式返回 words1 中所有的通用单词。你可以按 任意顺序 返回答案。

  

 示例 1：

 输入：words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 输出：["facebook","google","leetcode"]
 示例 2：

 输入：words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 输出：["apple","google","leetcode"]
 示例 3：

 输入：words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","oo"]
 输出：["facebook","google"]
 示例 4：

 输入：words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lo","eo"]
 输出：["google","leetcode"]
 示例 5：

 输入：words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["ec","oc","ceo"]
 输出：["facebook","leetcode"]
  

 提示：

 1 <= words1.length, words2.length <= 104
 1 <= words1[i].length, words2[i].length <= 10
 words1[i] 和 words2[i] 仅由小写英文字母组成
 words1 中的所有字符串 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-subsets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public List<String> wordSubsets(String[] words1, String[] words2) {
        // 遍历 word 2 记录每个 字母在每个单词中出现的最大次数
        int[] maxCount = new int[26];
        for (String w2 : words2) {
            int[] countMap = getCountMap(w2);
            for (int i = 0; i < 26; i++) {
                maxCount[i] = Math.max(maxCount[i], countMap[i]);
            }
        }
        List<String> result = new ArrayList<>();
        for (String w1 : words1) {
            int[] countMap = getCountMap(w1);
            boolean isValid = true;
            for (int i = 0; i < 26; i++) {
                if (countMap[i] < maxCount[i]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                result.add(w1);
            }
        }
        return result;
    }

    private int[] getCountMap(String word) {
        char[] chars = word.toCharArray();
        int[] count = new int[26];
        for (char ch : chars) {
            count[ch - 'a']++;
        }
        return count;
    }


}
