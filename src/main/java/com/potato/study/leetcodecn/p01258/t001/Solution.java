package com.potato.study.leetcodecn.p01258.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.*;

/**
 * 1258. 近义词句子
 *
 * 给你一个近义词表 synonyms 和一个句子 text ， synonyms 表中是一些近义词对 ，你可以将句子 text 中每个单词用它的近义词来替换。
 *
 * 请你找出所有用近义词替换后的句子，按 字典序排序 后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
 * text = "I am happy today but was sad yesterday"
 * 输出：
 * ["I am cheerful today but was sad yesterday",
 * "I am cheerful today but was sorrow yesterday",
 * "I am happy today but was sad yesterday",
 * "I am happy today but was sorrow yesterday",
 * "I am joy today but was sad yesterday",
 * "I am joy today but was sorrow yesterday"]
 *  
 *
 * 提示：
 *
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * synonyms[0] != synonyms[1]
 * 所有单词仅包含英文字母，且长度最多为 10 。
 * text 最多包含 10 个单词，且单词间用单个空格分隔开。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/synonymous-sentences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        // 将单词变成 index
        Map<String, Integer> wordIndexMap = new HashMap<>();
        int index = 0;
        for (List<String> synonym : synonyms) {
            String word1 = synonym.get(0);
            String word2 = synonym.get(1);

            if (!wordIndexMap.containsKey(word1)) {
                wordIndexMap.put(word1, index++);
            }

            if (!wordIndexMap.containsKey(word2)) {
                wordIndexMap.put(word2, index++);
            }
        }
        // 构造并查集 遍历 synonyms 对应 单词 生成并查集关系
        UnionFind unionFind = new UnionFind(wordIndexMap.size());
        for (List<String> synonym : synonyms) {
            String word1 = synonym.get(0);
            String word2 = synonym.get(1);

            int index1 = wordIndexMap.get(word1);
            int index2 = wordIndexMap.get(word2);
            // union
            unionFind.union(index1, index2);
        }
        // 对 text 进行 split dfs 生成结果
        String[] split = text.split(" ");
        // 对每个word 判断 他们是不是在 并查集范围之内，在的话 一次找到 对应在并查集的字符串 进行替换并 递归dfs
        List<String> result = new ArrayList<>();
        // dfs
        dfs(split, result, wordIndexMap, unionFind, 0);
        Collections.sort(result);
        return result;
    }

    private void dfs(String[] split, List<String> result, Map<String, Integer> wordIndexMap, UnionFind unionFind,
                     int index) {
        // 终止条件 index 已经到了 最大
        if (index == split.length) {
            String res = String.join(" ", split);
            result.add(res);
            return;
        }
        // 当前点是不是可以替换
        String oldWord = split[index];
        if (!wordIndexMap.containsKey(oldWord)) {
            // 这个单词没法替换 ，往后找吧
            dfs(split.clone(), result, wordIndexMap, unionFind, index + 1);
        } else {
            int oldIndex = wordIndexMap.get(oldWord);
            // 当前可以替换 可以替换成哪些 一个一个换
            for (Map.Entry<String, Integer> entry : wordIndexMap.entrySet()) {
                String newWord = entry.getKey();
//                if (oldWord.equals(newWord)) {
//                    continue;
//                }
                int newIndex = entry.getValue();
                // 可以替换
                if (unionFind.find(newIndex) == unionFind.find(oldIndex)) {
                    String[] clone = split.clone();
                    clone[index] = newWord;
                    dfs(clone, result, wordIndexMap, unionFind, index + 1);
                }
            }
        }
    }


    class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);

            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String text = "I am happy today but was sad yesterday";
        List<List<String>> synonyms = LeetcodeInputUtils.inputString2StringListList("[[\"happy\",\"joy\"],[\"sad\",\"sorrow\"],[\"joy\",\"cheerful\"]]");
        List<String> strings = solution.generateSentences(synonyms, text);

        System.out.println(strings);
    }
}
