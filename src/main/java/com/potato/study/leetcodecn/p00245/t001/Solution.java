package com.potato.study.leetcodecn.p00245.t001;

import org.junit.Assert;

/**
 * 245. 最短单词距离 III
 *
 * 给定一个字符串数组 wordsDict 和两个字符串 word1 和 word2 ，返回列表中这两个单词之间的最短距离。
 *
 * 注意：word1 和 word2 是有可能相同的，并且它们将分别表示为列表中 两个独立的单词 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出：1
 * 示例 2：
 *
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= wordsDict.length <= 105
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 都在 wordsDict 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-word-distance-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        // 遍历 wordsDict
        int min = Integer.MAX_VALUE;
        if (!word1.equals(word2)) {
            // 直接遍历一遍找到 两个 index1 index2
            int index1 = -1;
            int index2 = -1;
            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word1)) {
                    index1 = i;
                } else if (wordsDict[i].equals(word2)) {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    min = Math.min(min, Math.abs(index2 - index1));
                }
            }
        } else {
            int lastIndex = -1;
            for (int i = 0; i < wordsDict.length; i++) {
                if (word1.equals(wordsDict[i])) {
                    if (lastIndex != -1) {
                        min = Math.min(min, i - lastIndex);
                    }
                    lastIndex = i;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] wordsDict = new String[] {
                "practice", "makes", "perfect", "coding", "makes"
        };

        String word1 = "makes";
        String word2 = "makes";
        int distance = solution.shortestWordDistance(wordsDict, word1, word2);
        System.out.println(distance);
        Assert.assertEquals(3, distance);
    }

}
