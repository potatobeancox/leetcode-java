package com.potato.study.leetcodecn.p00140.t001;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 140. 单词拆分 II
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

 说明：

 分隔时可以重复使用字典中的单词。
 你可以假设字典中没有重复的单词。
 示例 1：

 输入:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 输出:
 [
   "cats and dog",
   "cat sand dog"
 ]
 示例 2：

 输入:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 输出:
 [
   "pine apple pen apple",
   "pineapple pen apple",
   "pine applepen apple"
 ]
 解释: 注意你可以重复使用字典中的单词。
 示例 3：

 输入:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 输出:
 []

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/word-break-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 其实也是 动态规划 方法和 139 类似 只是 记录结果的矩阵由bool型 变成String 类型
     * List<List<String> result i 记录 从 0 到 i 能被 wordDict 中串构造出来的 可能结果
     *
     * result i =  wordDict 中每个单词 word i- word.len  result[i- word.len] 并集
     *
     * 返回 result.get(s.len -1 )
     *
     *
     * https://leetcode-cn.com/problems/word-break-ii/solution/dan-ci-chai-fen-ii-by-leetcode-solution/
     *
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 递归找 从每个位置开始往后找 找到对应的 这个位置开始的对应前缀 如果前缀在word dic 中 递归往后找
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Map<Integer, List<List<String>>> indexResultMap = new HashMap<>();
        getWordBreak(s, wordDictSet, 0, indexResultMap);
        List<List<String>> lists = indexResultMap.get(0);

        List<String> resultList = new ArrayList<>();
        for (List<String> eachResult : lists) {
            resultList.add(String.join(" ", eachResult));
        }
        return resultList;
    }

    /**
     *
     * @param s
     * @param wordDictSet
     * @param startIndex 当前开始位置
     * @param indexResultMap
     * @return
     */
    private List<List<String>> getWordBreak(String s, Set<String> wordDictSet, int startIndex,
                                            Map<Integer, List<List<String>>> indexResultMap) {
        if (startIndex == s.length()) {
            return new ArrayList<>();
        }
        // 如果从index开始找的结果之前找过 直接返回
        if (indexResultMap.containsKey(startIndex)) {
            return indexResultMap.get(startIndex);
        }

        List<List<String>> currentResultList = new ArrayList<>();

        // 不存在 startIndex 的位置 遍历 s 中从 startIndex 开始一直到结尾的每种可能
        for (int endIndex = startIndex + 1; endIndex <= s.length(); endIndex++) {
            // 找到当前单词
            String prefix = s.substring(startIndex, endIndex);
            // 判断是否在 wordDict 中
            if (!wordDictSet.contains(prefix)) {
                continue;
            }
            // 获取之后的情况
            List<List<String>> nextWordBreak = getWordBreak(s, wordDictSet, endIndex, indexResultMap);
            // 如果之后有结果那就直接拼接 没有结果就拉倒
            if (nextWordBreak.size() == 0) {
                if (endIndex == s.length()) {
                    List<String> res = new ArrayList<>();
                    res.add(prefix);
                    currentResultList.add(res);
                }
            } else {
                // 生成新结果
                for (List<String> result : nextWordBreak) {
                    List<String> tmp = new ArrayList<>(result);
                    tmp.add(0, prefix);
                    currentResultList.add(tmp);
                }
            }
        }
        indexResultMap.put(startIndex, currentResultList);
        return currentResultList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "catsanddog";
        List<String> wordDict = Lists.newArrayList("cat", "cats", "and", "sand", "dog");
        List<String> list = solution.wordBreak(s, wordDict);
        System.out.println(list);
        //  "cats and dog"
        //  "cat sand dog"


        s = "pineapplepenapple";
        wordDict = Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple");
        list = solution.wordBreak(s, wordDict);
        System.out.println(list);
    }
}
