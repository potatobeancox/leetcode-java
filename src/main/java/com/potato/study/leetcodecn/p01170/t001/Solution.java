package com.potato.study.leetcodecn.p01170.t001;


/**
 * 1170. 比较字符串最小字母出现频次
 *
 * 定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
 *
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
 *
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W
 * 表示词汇表 words 中的每个词。
 *
 * 请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 * 示例 2：
 *
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 *  
 *
 * 提示：
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j]、words[i][j] 都由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-strings-by-frequency-of-the-smallest-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    // 1170
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        // 初始化 对 word 进行计算
        int[] wordCount = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordCount[i] = getCount(words[i]);
        }
        // 遍历 queries 对每个值进行计算 数量 每次 遍历 words 计数
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryCount = getCount(queries[i]);
            // 遍历计数
            int totalCount = 0;
            for (int eachWordCount : wordCount) {
                if (queryCount < eachWordCount) {
                    totalCount++;
                }
            }
            result[i] = totalCount;
        }
        return result;
    }

    private int getCount(String word) {
        int[] count = new int[26];
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            count[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                return count[i];
            }
        }
        return 0;
    }



}
