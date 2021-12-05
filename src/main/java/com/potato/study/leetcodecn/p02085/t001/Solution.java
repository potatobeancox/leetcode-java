package com.potato.study.leetcodecn.p02085.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2085. 统计出现过一次的公共字符串
 *
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。

  

 示例 1：

 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 输出：2
 解释：
 - "leetcode" 在两个数组中都恰好出现一次，计入答案。
 - "amazing" 在两个数组中都恰好出现一次，计入答案。
 - "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
 - "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
 所以，有 2 个字符串在两个数组中都恰好出现了一次。
 示例 2：

 输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 输出：0
 解释：没有字符串在两个数组中都恰好出现一次。
 示例 3：

 输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 输出：1
 解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
  

 提示：

 1 <= words1.length, words2.length <= 1000
 1 <= words1[i].length, words2[j].length <= 30
 words1[i] 和 words2[j] 都只包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-common-words-with-one-occurrence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> countMap1 = new HashMap<>();
        for (String word : words1) {
            Integer count = countMap1.getOrDefault(word, 0);
            count++;
            countMap1.put(word, count);
        }

        Map<String, Integer> countMap2 = new HashMap<>();
        for (String word : words2) {
            Integer count = countMap2.getOrDefault(word, 0);
            count++;
            countMap2.put(word, count);
        }

        // 遍历 coutmap1
        int singleCount = 0;
        for (Map.Entry<String, Integer> entry : countMap1.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }
            String key = entry.getKey();
            if (countMap2.getOrDefault(key, 0) == 1) {
                singleCount++;
            }
        }
        return singleCount;
    }
}
