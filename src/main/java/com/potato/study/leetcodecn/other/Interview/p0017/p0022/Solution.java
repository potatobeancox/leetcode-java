package com.potato.study.leetcodecn.other.Interview.p0017.p0022;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 面试题 17.22. 单词转换
 *
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 *
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 *
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 *
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-transformer-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 17.22
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 边界 条件
        Set<String> set = new HashSet<>(wordList);
        // 没有任何路径
        if (!set.contains(endWord)) {
            return new ArrayList<>();
        }
        // bfs queue 中使用 List<String> 记录当前走到这个单词位置 路径的内容 遍历过程中 遇到 end 直接返回
        Queue<List<String>> queue = new LinkedList<>();
        // 初始放入一个单词 begin
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.add(path);

        Set<String> visit = new HashSet<>();
        visit.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<String> poll = queue.poll();
                String lastWord = poll.get(poll.size() - 1);
                // 替换单词
                for (int j = 0; j < lastWord.length(); j++) {
                    char[] chars = lastWord.toCharArray();
                    // 1-26
                    for (int k = 0; k < 26; k++) {
                        if (k == lastWord.charAt(j) - 'a') {
                            continue;
                        }
                        chars[j] = (char) ('a' + k);
                        String targetWord = new String(chars);
                        if (!set.contains(targetWord)) {
                            // set 中没有
                            continue;
                        }
                        // 访问 过 剪枝
                        if (visit.contains(targetWord)) {
                            continue;
                        }
                        visit.add(targetWord);
                        List<String> newPath = new ArrayList<>(poll);
                        newPath.add(targetWord);
                        // 已经到终点了
                        if (targetWord.equals(endWord)) {
                            return newPath;
                        }
                        // 放入 queue中继续访问
                        queue.add(newPath);
                    }
                }
            }
        }
        // 没找到
        return new ArrayList<>();
    }
}
