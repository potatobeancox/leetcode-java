package com.potato.study.leetcodecn.p00472.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 472. 连接词
 *
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。

 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。

  

 示例 1：

 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 示例 2：

 输入：words = ["cat","dog","catdog"]
 输出：["catdog"]
  

 提示：

 1 <= words.length <= 104
 0 <= words[i].length <= 30
 words[i] 仅由小写字母组成
 0 <= sum(words[i].length) <= 105

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/concatenated-words
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/concatenated-words/solution/tong-ge-lai-shua-ti-la-zi-dian-shu-dfs-b-dk9c/
     * @param words
     * @return
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // 字典树 将 words 排序 按照大小顺序
        Arrays.sort(words, Comparator.comparingInt(String::length));
        // 遍历 dfs 枚举每个字串位置看看能不能 枚举结束位置看看能不能被分割
        List<String> resultList = new ArrayList<>();
        DicNode root = new DicNode();
        for (String word : words) {
            boolean canConcat = dfs(word, 0, root);
            if (canConcat) {
                resultList.add(word);
            } else {
                // 剪枝 如果是能表达出来 就不用插入了 将 word 插入 字典树
                root.insert(word);
            }
        }
        return resultList;
    }

    private boolean dfs(String word, int i, DicNode root) {
        if (i == word.length()) {
            return true;
        }
        DicNode p = root;
        for (int j = i; j < word.length(); j++) {
            char c = word.charAt(j);
            int index = c - 'a';
            if (p.next[index] == null) {
                return false;
            }
            p = p.next[index];
            if (p.isEnd && dfs(word, j+1, root)) {
                return true;
            }
        }
        // 从 i 开始遍历 word 将起进行字典树的查找 如果找到 再看看能不能 递归找
        return p.isEnd;
    }

    /**
     * 字典树的类
     */
    class DicNode {
        // 下一个点
        public DicNode[] next;
        // 是否到达某个单词的末尾
        public boolean isEnd;

        public DicNode() {
            this.next = new DicNode[26];
        }

        /**
         * 将word 插入字典树
         * @param word
         */
        public void insert(String word) {
            DicNode p = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (p.next[index] == null) {
                    p.next[index] = new DicNode();
                }
                p = p.next[index];
            }
            p.isEnd = true;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
            "dog", "cats", "catsdogcats"
        };
//        String[] words = LeetcodeInputUtils.inputString2StringArray("[\"cat\",\"cats\",\"catsdogcats\",\"dog\",\"dogcatsdog\",\"hippopotamuses\",\"rat\",\"ratcatdogcat\"]");
        List<String> allConcatenatedWordsInADict = solution.findAllConcatenatedWordsInADict(words);
        System.out.println(allConcatenatedWordsInADict);
    }



}
