package com.potato.study.leetcodecn.p00126.t001;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.*;

/**
 * 126. 单词接龙 II
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

 每次转换只能改变一个字母。
 转换后得到的单词必须是字典中的单词。
 说明:

 如果不存在这样的转换序列，返回一个空列表。
 所有单词具有相同的长度。
 所有单词只由小写字母组成。
 字典中不存在重复的单词。
 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 示例 1:

 输入:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 输出:
 [
 ["hit","hot","dot","dog","cog"],
   ["hit","hot","lot","log","cog"]
 ]
 示例 2:

 输入:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 输出: []

 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-ladder-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/word-ladder-ii/solution/dan-ci-jie-long-ii-by-leetcode-solution/
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 首先使用 set 缓存 wordList
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);
        wordSet.remove(beginWord);
        // 先对极端情况短路处理
        if (!wordSet.contains(endWord)) {
            return new ArrayList<>();
        }
        // bfs 找到 最近的那一层  生成的时候 需要记录 当前 string 第一次遍历在第几层 每个单词可以由于那个单词生成
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Map<String, Integer> firstAppearLayerMap = new HashMap<>();
        int layerIndex = 0;
        Map<String, Set<String>> fromWordMap = new HashMap<>();
        boolean hasFoundEnd = false;
        while (!queue.isEmpty()) {
            // 当前层 遍历的数量 当前层的层号
            int layerLength = queue.size();
            // 对于当前层的每个单词都要获取下一个可以遍历的单词并放入 queue中
            for (int i = 0; i < layerLength; i++) {
                String wordParent = queue.poll();
                // 遍历 wordParent 每一个位置 生成 下一个单词
                char[] wordParentChars = wordParent.toCharArray();
                for (int j = 0; j < wordParent.length(); j++) {
                    char wordParentCharOriginal = wordParentChars[j];
                    for (int k = 0; k < 26; k++) {
                        char targetCh = (char) ('a' + k);
                        // 相同
                        if (targetCh == wordParentCharOriginal) {
                            continue;
                        }
                        char[] tmp = wordParentChars.clone();
                        tmp[j] = targetCh;
                        String targetWord = new String(tmp);
                        if (!wordSet.contains(targetWord)) {
                            continue;
                        }
                        // 是候选单词时 需要判断下是否出现过 没出现过 或者 就是本行出现了
                        if (firstAppearLayerMap.containsKey(targetWord) && firstAppearLayerMap.get(targetWord) < layerIndex) {
                            continue;
                        }

                        // 生成关系map 并生成首次出现map
                        firstAppearLayerMap.put(targetWord, layerIndex);

                        Set<String> fromWord = fromWordMap.getOrDefault(targetWord, new HashSet<>());
                        fromWord.add(wordParent);
                        fromWordMap.put(targetWord, fromWord);

                        // 放入队列 继续访问
                        queue.add(targetWord);

                        if (endWord.equals(targetWord)) {
                            hasFoundEnd = true;
                        }

                    }
                }
            }
            // 本层找到结果了，就不用再找下一层了
            if (hasFoundEnd) {
                break;
            }
            layerIndex++;
        }
        // 生成结果后 使用 dfs 从结果反推出 到开始点的位置
        List<List<String>> resultList = new ArrayList<>();
        dfsGetResult(resultList, new ArrayList<>(), endWord, beginWord, fromWordMap);
        return resultList;
    }


    private void dfsGetResult(List<List<String>> resultList, List<String> currentPath, String current, String target,
                              Map<String, Set<String>> fromWordMap) {
        // 终止条件 当前就是 开始的单词
        List<String> path = new ArrayList<>(currentPath);
        path.add(0, current);
        if (target.equals(current)) {
            resultList.add(path);
            return;
        }
        // 父亲都有谁
        Set<String> parents = fromWordMap.getOrDefault(current, new HashSet<>());
        for (String parent : parents) {
            dfsGetResult(resultList, path, parent, target, fromWordMap);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Lists.newArrayList("hot","dot","dog","lot","log","cog");
        List<List<String>> ladders = solution.findLadders(beginWord, endWord, wordList);
        // ["hit","hot","dot","dog","cog"],
        // ["hit","hot","lot","log","cog"]
        System.out.println(ladders);


//        beginWord = "red";
//        endWord = "tax";
//        wordList = Lists.newArrayList("ted","tex","red","tax","tad","den","rex","pee");
//        ladders = solution.findLadders(beginWord, endWord, wordList);
//        // [["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
//        System.out.println(ladders);
    }
}
