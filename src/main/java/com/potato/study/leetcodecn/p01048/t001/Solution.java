package com.potato.study.leetcodecn.p01048.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 1048. 最长字符串链
 *
 * 给出一个单词列表，其中每个单词都由小写英文字母组成。

 如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。

 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。

 从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
  

 示例：

 输入：["a","b","ba","bca","bda","bdca"]
 输出：4
 解释：最长单词链之一为 "a","ba","bda","bdca"。
  

 提示：

 1 <= words.length <= 1000
 1 <= words[i].length <= 16
 words[i] 仅由小写英文字母组成。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-string-chain
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/longest-string-chain/solution/mapji-lu-zui-chang-lian-xu-zi-xu-lie-11x-32vs/
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {
        // 将 word 按照 长度 升序排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        // 将word 每个位置 删除 查看是否在 map 中 ，数量++
        Map<String, Integer> previosCount = new HashMap<>();
        int totalMax = 0;
        for (String word : words) {
            // 找到前缀 每个位置
            int max = 0;
            for (int i = 0; i < word.length(); i++) {
                String prevous = word.substring(0, i) + word.substring(i+1);
                Integer count = previosCount.getOrDefault(prevous, 0);
                count++;
                // 每次记录数量 max
                max = Math.max(max, count);
            }
            previosCount.put(word, max);
            totalMax = Math.max(totalMax, max);
        }
        return totalMax;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = new String[] {
                "a","b","ba","bca","bda","bdca"
        };
        int i = solution.longestStrChain(arr);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}
