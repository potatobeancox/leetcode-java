package com.potato.study.leetcodecn.other.Interview.p0017.p0011;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 17.11. 单词距离
 *
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?

 示例：

 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 输出：1
 提示：

 words.length <= 100000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/find-closest-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    public int findClosest(String[] words, String word1, String word2) {
        // 遍历 words 分别记录 上一次出现 的 word1index 和 word2 index 每次计算最小值
        int index1 = -1;
        int index2 = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            if (current.equals(word1)) {
                if (index2 != -1) {
                    distance = Math.min(i - index2, distance);
                }
                index1 = i;
            } else if (current.equals(word2)) {
                if (index1 != -1) {
                    distance = Math.min(i - index1, distance);
                }
                index2 = i;
            }
        }
        return distance;
    }


}
