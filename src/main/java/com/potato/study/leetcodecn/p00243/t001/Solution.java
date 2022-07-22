package com.potato.study.leetcodecn.p00243.t001;

/**
 * 243. 最短单词距离
 *
 * 给定一个字符串数组 wordDict 和两个已经存在于该数组中的不同的字符串 word1 和 word2 。返回列表中这两个单词之间的最短距离。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * 输出: 3
 * 示例 2:
 *
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出: 1
 *  
 *
 * 提示:
 *
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在 wordsDict 中
 * word1 != word2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-word-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        // 遍历 wordsDict 记录最近 的 word1 和 word2 出现的index 每次2个都券 求查
        int shortest = Integer.MAX_VALUE;
        int wordIndex1 = -1;
        int wordIndex2 = -1;
        for (int i = 0; i < wordsDict.length; i++) {
            if (word1.equals(wordsDict[i])) {
                wordIndex1 = i;
            } else if (word2.equals(wordsDict[i])) {
                wordIndex2 = i;
            }

            if (wordIndex1 >= 0 && wordIndex2 >= 0) {
                shortest = Math.min(shortest, Math.abs(wordIndex2 - wordIndex1));
            }
        }
        return shortest;
    }


}
