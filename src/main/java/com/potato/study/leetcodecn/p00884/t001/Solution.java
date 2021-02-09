package com.potato.study.leetcodecn.p00884.t001;

import java.util.HashSet;
import java.util.Set;

/**
 * 884. 两句话中的不常见单词
 *
 * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）

 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。

 返回所有不常用单词的列表。

 您可以按任何顺序返回列表。

  

 示例 1：

 输入：A = "this apple is sweet", B = "this apple is sour"
 输出：["sweet","sour"]
 示例 2：

 输入：A = "apple apple", B = "banana"
 输出：["banana"]
  

 提示：

 0 <= A.length <= 200
 0 <= B.length <= 200
 A 和 B 都只包含空格和小写字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String[] uncommonFromSentences(String a, String b) {
        if (null == a && null == b) {
            return null;
        }
        if (null == a) {
            return b.split(" ");
        }
        if (null == b) {
            return a.split(" ");
        }
        Set<String> set1 = new HashSet<>();
        for (String word : a.split(" ")) {
            set1.add(word);
        }
        Set<String> set2 = new HashSet<>();
        for (String word : b.split(" ")) {
            set2.add(word);
        }
        // 分别遍历 set1 和set2 找不常见次
        Set<String> result = new HashSet<>();
        for (String word : set1) {
            if (!set2.contains(word)) {
                result.add(word);
            }
        }
        for (String word : set2) {
            if (!set1.contains(word)) {
                result.add(word);
            }
        }
        String[] array = new String[result.size()];
        int index = 0;
        for (String word :result) {
            array[index++] = word;
        }
        return array;
    }
}
