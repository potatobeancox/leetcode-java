package com.potato.study.leetcodecn.p00425.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 425. 单词方块
 *
 *
 * 给定一个单词集合 words （没有重复），找出并返回其中所有的 单词方块 。 words 中的同一个单词可以被 多次 使用。你可以按 任意顺序 返回答案。

 一个单词序列形成了一个有效的 单词方块 的意思是指从第 k 行和第 k 列  0 <= k < max(numRows, numColumns) 来看都是相同的字符串。

 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
  

 示例 1：

 输入：words = ["area","lead","wall","lady","ball"]
 输出: [["ball","area","lead","lady"],
 ["wall","area","lead","lady"]]
 解释：
 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
 示例 2：

 输入：words = ["abat","baba","atan","atal"]
 输出：[["baba","abat","baba","atal"],
 ["baba","abat","baba","atan"]]
 解释：
 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
  

 提示:

 1 <= words.length <= 1000
 1 <= words[i].length <= 4
 words[i] 长度相同
 words[i] 只由小写英文字母组成
 words[i] 都 各不相同

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/word-squares
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/word-squares/solution/dfs-trie-xiang-xi-si-lu-by-jerry_nju/
     * @param words
     * @return
     */
    public List<List<String>> wordSquares(String[] words) {
        // 遍历 words 将每个单词放入 trie中
        Trie trie = new Trie(words);
        // 遍历 words 每个单词都作为开始 dfs 遍历
        int length = words[0].length();
        List<List<String>> resultList = new ArrayList<>();
        for (String word : words) {
            List<String> list = new ArrayList<>();
            list.add(word);
            dfs(trie, length, list, resultList);
        }
        return resultList;
    }

    private void dfs(Trie trie, int length, List<String> list, List<List<String>> resultList) {
        // dfs 找到当前单词是不是到了最后
        if (length == list.size()) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        // 找到目前已经确定的要找单词的index
        int index = list.size();
        StringBuilder builder = new StringBuilder();
        for (String confirmedWord: list) {
            builder.append(confirmedWord.charAt(index));
        }
        String prefixString = builder.toString();
        List<String> prefixList = trie.getPrefixList(prefixString);
        if (prefixList.size() == 0) {
            return;
        }
        for (String next : prefixList) {
            list.add(next);
            // 不是最后 找一下 目前单对应的已有单词的列 组合成前缀 使用trie 找到下一个 要找的单词 递归
            dfs(trie, length, list, resultList);
            list.remove(list.size() - 1);
        }
    }


    class Trie {


        public TrieNode root;

        public Trie(String[] words) {
            this.root = new TrieNode();
            for (String word : words) {
                this.insert(word);
            }
        }

        // 往里边插入
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new TrieNode();
                }
                // 放前缀单词
                node.child[index].samePrefixList.add(word);
                node = node.child[index];
            }
        }

        /**
         * 查找
         * @return
         */
        public List<String> getPrefixList(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.child[index] == null) {
                    return new ArrayList<>();
                }
                // 返回
                node = node.child[index];
            }
            return node.samePrefixList;
        }
    }

    class TrieNode {
        // 数组
        public TrieNode[] child;
        public List<String> samePrefixList;

        // list 所有以当前作为prefix的 list
        public TrieNode() {
            this.child = new TrieNode[26];
            this.samePrefixList = new ArrayList<>();
        }

    }


    public static void main(String[] args) {
        String[] words = new String[] {
                "area","lead","wall","lady","ball"
        };
        Solution solution = new Solution();
        // ["wall","area","lead","lady"]]
        List<List<String>> lists = solution.wordSquares(words);
        System.out.println(lists);
    }
}



