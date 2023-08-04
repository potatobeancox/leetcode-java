package com.potato.study.leetcodecn.other.lcr.p0033.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LCR 033. 字母异位词分组
 *
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 *
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *  
 *
 * 注意：本题与主站 49 题相同： https://leetcode-cn.com/problems/group-anagrams/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sfvd7V
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        if (null == strs || strs.length == 0) {
            return new ArrayList<>();
        }
        for (String word : strs) {
            String anagramsKey = getAnagramsKey(word);
            List<String> orDefault = map.getOrDefault(anagramsKey, new ArrayList<>());
            orDefault.add(word);
            map.put(anagramsKey, orDefault);
        }
        // 生成结果
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    /**
     * 获取 变相词的key
     * @param word
     * @return
     */
    private String getAnagramsKey(String word) {
        int[] count = new int[26];
        for (char ch : word.toCharArray()) {
            count[ch - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
            builder.append(count[i]);
            builder.append("_");
        }
        return builder.toString();
    }
}
