package com.potato.study.leetcodecn.other.swordoffer2.p0063.t001;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 063. 替换单词
 *
 * 在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 需要输出替换之后的句子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 *
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * 示例 3：
 *
 * 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * 输出："a a a a a a a a bbb baba a"
 * 示例 4：
 *
 * 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 5：
 *
 * 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * 输出："it is ab that this solution is ac"
 *  
 *
 * 提示：
 *
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 *  
 *
 * 注意：本题与主站 648 题相同： https://leetcode-cn.com/problems/replace-words/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/UhWRSj
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private DictTreeNode root;

    // 字典树
    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new DictTreeNode();
        // 遍历 dictionary 往root 中添加单词
        for (String word : dictionary) {
            root.addWord(word);
        }
        // split sentence 在 root 中查找前缀 拼接成 builder 最终返回
        StringBuilder builder = new StringBuilder();
        String[] split = sentence.split(" ");
        for (String word : split) {
            String prefix = root.prefix(word);
            if (prefix == null) {
                builder.append(word);
            } else {
                builder.append(prefix);
            }
            builder.append(" ");
        }

        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }


    /**
     * 字典树 节点数据结构
     */
    class DictTreeNode {
        public DictTreeNode[] child;
        public boolean isEnd;

        public DictTreeNode() {
            this.child = new DictTreeNode[26];
        }


        /**
         * 添加单词
         * @param word
         */
        public void addWord(String word) {
            DictTreeNode node = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new DictTreeNode();
                }
                node = node.child[index];
            }
            node.isEnd = true;
        }

        public String prefix(String word) {
            DictTreeNode node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.child[index] == null) {
                    return null;
                }
                node = node.child[index];
                if (node.isEnd) {
                    return word.substring(0, i+1);
                }
            }
            return null;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");
        String sentence = "the cattle was rattled by the battery";
        String s = solution.replaceWords(dictionary, sentence);
        System.out.println(s);
//        Assert.assertEquals("", s);
    }

}
