package com.potato.study.leetcodecn.p00648.t001;

import org.junit.Assert;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * 648. 单词替换
 *
 * 在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

 你需要输出替换之后的句子。

  

 示例 1：

 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 输出："the cat was rat by the bat"
 示例 2：

 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 输出："a a b c"
 示例 3：

 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 输出："a a a a a a a a bbb baba a"
 示例 4：

 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 输出："the cat was rat by the bat"
 示例 5：

 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 输出："it is ab that this solution is ac"
  

 提示：

 1 <= dictionary.length <= 1000
 1 <= dictionary[i].length <= 100
 dictionary[i] 仅由小写字母组成。
 1 <= sentence.length <= 10^6
 sentence 仅由小写字母和空格组成。
 sentence 中单词的总量在范围 [1, 1000] 内。
 sentence 中每个单词的长度在范围 [1, 1000] 内。
 sentence 中单词之间由一个空格隔开。
 sentence 没有前导或尾随空格。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/replace-words
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String replaceWords(List<String> dictionary, String sentence) {
        // 生成 trie 树 并将 dictionary 全部插入
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        // 遍历 sentence 找到 空格和之后组成的单词 判断是否可以有词根替换
        int index = 0;
        StringBuilder builder = new StringBuilder();
        StringBuilder current = new StringBuilder();
        while (index < sentence.length()) {
            // 找到单词
            char ch = sentence.charAt(index++);
            if (Character.isAlphabetic(ch)) {
                current.append(ch);
                continue;
            }
            // 找到空格
            String currentWord = current.toString();
            String search = trie.search(currentWord);
            if (search != null) {
                builder.append(search).append(" ");
            } else {
                builder.append(currentWord).append(" ");
            }
            current = new StringBuilder();
        }
        if (current.length() != 0) {
            String currentWord = current.toString();
            String search = trie.search(currentWord);
            if (search != null) {
                builder.append(search).append(" ");
            } else {
                builder.append(currentWord).append(" ");
            }
        }

        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length()-1);
        }

        return builder.toString();
    }



    class Trie {
        public Trie[] child;
        public boolean isEnd;
        public String word;

        public Trie() {
            this.child = new Trie[26];
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Trie child = node.child[ch - 'a'];
                if (child == null) {
                    child = new Trie();
                    node.child[ch - 'a'] = child;
                }
                node = child;
            }
            node.isEnd = true;
            node.word = word;
        }

        public String search(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Trie child = node.child[ch - 'a'];
                if (child == null) {
                    child = new Trie();
                    node.child[ch - 'a'] = child;
                }
                node = child;
                if (node.isEnd) {
                    return node.word;
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
        Assert.assertEquals("the cat was rat by the bat", s);

    }
}
