package com.potato.study.leetcodecn.p01858.t001;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

/**
 * 1858. 包含所有前缀的最长单词
 *
 * 给定一个字符串数组 words，找出 words 中所有的前缀都在 words 中的最长字符串。
 *
 * 例如，令 words = ["a", "app", "ap"]。字符串 "app" 含前缀 "ap" 和 "a" ，都在 words 中。
 * 返回符合上述要求的字符串。如果存在多个（符合条件的）相同长度的字符串，返回字典序中最小的字符串，如果这样的字符串不存在，返回 ""。
 *
 *  
 *
 * 示例 1:
 *
 * 输入： words = ["k","ki","kir","kira", "kiran"]
 * 输出： "kiran"
 * 解释： "kiran" 含前缀 "kira"、 "kir"、 "ki"、 和 "k"，这些前缀都出现在 words 中。
 * 示例 2:
 *
 * 输入： words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出： "apple"
 * 解释： "apple" 和 "apply" 都在 words 中含有各自的所有前缀。
 * 然而，"apple" 在字典序中更小，所以我们返回之。
 * 示例 3:
 *
 * 输入： words = ["abc", "bc", "ab", "qwe"]
 * 输出： ""
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 105
 * 1 <= words[i].length <= 105
 * 1 <= sum(words[i].length) <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-word-with-all-prefixes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode.cn/problems/longest-word-with-all-prefixes/solution/java-qian-zhui-shu-by-regan-hoo-tmvd/
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        // 将 words 按照长度排序
        Arrays.sort(words, (w1, w2) -> {
            int compare = Integer.compare(w1.length(), w2.length());
            if (compare != 0) {
                return compare;
            }
            return w1.compareTo(w2);
        });
        // 遍历 每个 word 将其 依次插入 到字典树中，
        TrieNode root = new TrieNode();
        // 插入过程中记录 本次插入是不是已经有了 字母站位 记录最长的字符串
        String maxWord = "";
        for (String word : words) {
            TrieNode temp = root;
            boolean isAllContain = true;
            // 遍历 word 每个字母 插入
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                TrieNode node = temp.nexts[index];
                // 不存在申请 一个新的
                if (node == null) {
                    temp.nexts[index] = new TrieNode();
                    node = temp.nexts[index];
                }
                temp = node;
                // 不是结尾判断 前缀是不是 里边的字符串
                if (i != word.length() - 1 && !node.isEnd) {
                    isAllContain = false;
                }
            }
            // 插入结尾标志
            temp.isEnd = true;
            // 处理前缀是不是都在里边
            if ((word.length() == 1 || isAllContain) && maxWord.length() < word.length()) {
                maxWord = word;
            }
        }
        return maxWord;
    }

    class TrieNode {
        public TrieNode[] nexts;
        public boolean isEnd;

        public TrieNode() {
            this.nexts = new TrieNode[26];
            this.isEnd = false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "k","ki","kir","kira", "kiran"
        };
        String s = solution.longestWord(words);
        System.out.println(s);
        Assert.assertEquals("kiran", s);

    }
}
