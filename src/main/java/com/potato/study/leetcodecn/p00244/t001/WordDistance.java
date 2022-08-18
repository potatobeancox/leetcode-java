package com.potato.study.leetcodecn.p00244.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. 最短单词距离 II
 *
 * 请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词，并返回列表中这两个单词之间的最短距离。
 *
 * 实现 WordDistanc 类:
 *
 * WordDistance(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 worddict 中 word1 和 word2 之间的最短距离。
 *  
 *
 * 示例 1:
 *
 * 输入:
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * 输出:
 * [null, 3, 1]
 *
 * 解释：
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // 返回 3
 * wordDistance.shortest("makes", "coding");    // 返回 1
 *  
 *
 * 注意:
 *
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在数组 wordsDict 中
 * word1 != word2
 *  shortest 操作次数不大于 5000 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-word-distance-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class WordDistance {

    private Map<String, List<Integer>> wordIndexListMap;


    public WordDistance(String[] wordsDict) {
        // 使用 map word list 出现index 记录 出现位置
        this.wordIndexListMap = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            List<Integer> list = wordIndexListMap.getOrDefault(wordsDict[i], new ArrayList<>());
            list.add(i);
            wordIndexListMap.put(wordsDict[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        // 遍历 word1 和 word2 找到两个 list n*n 比较abs 最小值
        List<Integer> list1 = wordIndexListMap.getOrDefault(word1, new ArrayList<>());
        List<Integer> list2 = wordIndexListMap.getOrDefault(word2, new ArrayList<>());
        int min = Integer.MAX_VALUE;
        for (int index1 : list1) {
            for (int index2 : list2) {
                min = Math.min(min, Math.abs(index2 - index1));
            }
        }
        return min;
    }
}
