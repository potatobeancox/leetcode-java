package com.potato.study.leetcodecn.p01772.t001;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.*;

/**
 * 1772. 按受欢迎程度排列功能
 *
 * 给定一个字符串数组 features ，其中 features[i] 是一个单词，描述你最近参与开发的项目中一个功能的名称。你调查了用户喜欢哪些功能。另给定一个字符串数组 responses，其中 responses[i] 是一个包含以空格分隔的一系列单词的字符串。
 *
 * 你想要按照受欢迎程度排列这些功能。 严格地说，令 appearances(word) 是满足 responses[i] 中包含单词 word 的 i 的个数，则当 appearances(features[x]) > appearances(features[y]) 时，第 x 个功能比第 y 个功能更受欢迎。
 *
 * 返回一个数组 sortedFeatures ，包含按受欢迎程度排列的功能名称。当第 x  个功能和第 y 个功能的受欢迎程度相同且 x < y 时，你应当将第 x 个功能放在第 y 个功能之前。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：features = ["cooler","lock","touch"], responses = ["i like cooler cooler","lock touch cool","locker like touch"]
 * 输出：["touch","cooler","lock"]
 * 解释：appearances("cooler") = 1，appearances("lock") = 1，appearances("touch") = 2。由于 "cooler" 和 "lock" 都出现了 1 次，且 "cooler" 在原数组的前面，所以 "cooler" 也应该在结果数组的前面。
 * 示例 2：
 *
 * 输入：features = ["a","aa","b","c"], responses = ["a","a aa","a a a a a","b a"]
 * 输出：["a","aa","b","c"]
 *  
 *
 * 提示：
 *
 * 1 <= features.length <= 104
 * 1 <= features[i].length <= 10
 * features 不包含重复项。
 * features[i] 由小写字母构成。
 * 1 <= responses.length <= 102
 * 1 <= responses[i].length <= 103
 * responses[i] 由小写字母和空格组成。
 * responses[i] 不包含两个连续的空格。
 * responses[i] 没有前置或后置空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-features-by-popularity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String[] sortFeatures(String[] features, String[] responses) {
        // 将 features 存储到 countMap 中
        Map<String, Integer> countMap = new HashMap<>();
        for (String feature : features) {
            countMap.put(feature, 0);
        }
        // 遍历 responses 进行数字计算
        for (String response : responses) {
            String[] words = response.split(" ");
            if (words != null && words.length > 0) {
                for (String word : words) {
                    if (!countMap.containsKey(word)) {
                        continue;
                    }
                    Integer count = countMap.get(word);
                    count++;
                    countMap.put(word, count);
                }
            }
        }
        // 遍历 countMap 进行堆排序
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) ->
                Integer.compare(countMap.getOrDefault(o2, 0), countMap.getOrDefault(o1, 0)));
        for (String word : countMap.keySet()) {
            priorityQueue.add(word);
        }
        return null;
    }
}
