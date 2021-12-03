package com.potato.study.leetcodecn.p00211.t001;

import org.junit.Assert;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

 实现词典类 WordDictionary ：

 WordDictionary() 初始化词典对象
 void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
  

 示例：

 输入：
 ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 输出：
 [null,null,null,null,false,true,true,true]

 解释：
 WordDictionary wordDictionary = new WordDictionary();
 wordDictionary.addWord("bad");
 wordDictionary.addWord("dad");
 wordDictionary.addWord("mad");
 wordDictionary.search("pad"); // return False
 wordDictionary.search("bad"); // return True
 wordDictionary.search(".ad"); // return True
 wordDictionary.search("b.."); // return True
  

 提示：

 1 <= word.length <= 500
 addWord 中的 word 由小写英文字母组成
 search 中的 word 由 '.' 或小写英文字母组成
 最多调用 50000 次 addWord 和 search

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class WordDictionary {

    class WordDictionaryNode {
        // 当前节点 child
        public WordDictionaryNode[] child;
        public boolean isEnd;


        public WordDictionaryNode() {
            this.child = new WordDictionaryNode[26];
        }
    }

    private WordDictionaryNode head;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.head = new WordDictionaryNode();
        head.child = new WordDictionaryNode[26];
    }

    /**
     * 添加单词 递归添加
     * @param word
     */
    public void addWord(String word) {
        WordDictionaryNode node = this.head;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new WordDictionaryNode();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }



    /**
     * 也得递归查找
     * 需要注意 .  可以匹配任意字符
     * @param word
     * @return
     */
    public boolean search(String word) {
        return searchIteration(word, 0, head);
    }

    /**
     * 递归查找
     * @param word
     * @param index
     * @param root
     */
    private boolean searchIteration(String word, int index, WordDictionaryNode root) {
        // 找到最后一个节点
        if (root == null || root.child == null) {
            return false;
        }
        if (index == word.length()) {
            return root.isEnd;
        }
        char c = word.charAt(index);
        if ('.' == c) {
            for (WordDictionaryNode child : root.child) {
                if (searchIteration(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            int childIndex = c - 'a';
            WordDictionaryNode childNode = root.child[childIndex];
            if (childNode == null) {
                return false;
            }
            return searchIteration(word, index + 1, root.child[childIndex]);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));// return False
        System.out.println(wordDictionary.search("bad"));// return True
        System.out.println(wordDictionary.search(".ad"));// return True
        System.out.println(wordDictionary.search("b.."));// return True


        wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));// return False
//        System.out.println(wordDictionary.search("bad"));// return True
//        System.out.println(wordDictionary.search(".ad"));// return True
//        System.out.println(wordDictionary.search("b.."));// return True

    }


}
