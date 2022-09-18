package com.potato.study.leetcodecn.p01804.t001;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1804. 实现 Trie （前缀树） II
 *
 * 前缀树（trie ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。

 实现前缀树 Trie 类：

 Trie() 初始化前缀树对象。
 void insert(String word) 将字符串 word 插入前缀树中。
 int countWordsEqualTo(String word) 返回前缀树中字符串 word 的实例个数。
 int countWordsStartingWith(String prefix) 返回前缀树中以 prefix 为前缀的字符串个数。
 void erase(String word) 从前缀树中移除字符串 word 。
  

 示例 1:

 输入
 ["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
 [[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
 输出
 [null, null, null, 2, 2, null, 1, 1, null, 0]

 解释
 Trie trie = new Trie();
 trie.insert("apple");               // 插入 "apple"。
 trie.insert("apple");               // 插入另一个 "apple"。
 trie.countWordsEqualTo("apple");    // 有两个 "apple" 实例，所以返回 2。
 trie.countWordsStartingWith("app"); // "app" 是 "apple" 的前缀，所以返回 2。
 trie.erase("apple");                // 移除一个 "apple"。
 trie.countWordsEqualTo("apple");    // 现在只有一个 "apple" 实例，所以返回 1。
 trie.countWordsStartingWith("app"); // 返回 1
 trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
 trie.countWordsStartingWith("app"); // 返回 0
  

 提示：

 1 <= word.length, prefix.length <= 2000
 word 和 prefix 只包含小写英文字母。
 insert、 countWordsEqualTo、 countWordsStartingWith 和 erase 总共调用最多 3 * 104 次。
 保证每次调用 erase 时，字符串 word 总是存在于前缀树中。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/implement-trie-ii-prefix-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Trie {

    private TrieNode root;


    public Trie() {
        // 初始化根节点
        this.root = new TrieNode();
    }

    public void insert(String word) {
        // 查找
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode temp = this.root;
        TrieNode node = null;
        // 遍历 word 对于每个字母 i 找到 idnex 对应的 TrieNode
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            node = temp.nexts[index];
            // 判断是否为空 空的话 设置 不空的话 增加参数
            if (node == null) {
                TrieNode trieNode = new TrieNode();
                // prefix
                temp.nexts[index] = trieNode;
                node = trieNode;
            }
            node.prefixCount++;

            temp = node;
        }
        // 全部设置完了 之后 将当前 的idsend 修改
        node.wordCount++;
        node.isEnd = true;
    }

    public int countWordsEqualTo(String word) {
        // 查找
        if (word == null || word.length() == 0) {
            return -1;
        }
        TrieNode temp = this.root;
        TrieNode node = null;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            node = temp.nexts[index];
            // 没有说明不是
            if (node == null) {
                return 0;
            }

            temp = node;
        }
        return node.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        // 查找
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        TrieNode temp = this.root;
        TrieNode node = null;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            node = temp.nexts[index];
            // 没有说明不是
            if (node == null) {
                return 0;
            }

            temp = node;
        }
        return node.prefixCount;
    }

    public void erase(String word) {
        // 查找
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode temp = this.root;
        TrieNode node = null;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            node = temp.nexts[index];
            // 没有说明不是
            if (node == null) {
                return;
            }
            // prefix
            node.prefixCount--;
            if (node.prefixCount <= 0) {
                temp.nexts[index] = null;
                return;
            }

            temp = node;
        }
        node.wordCount--;
    }


    class TrieNode {
        public TrieNode[] nexts;
        public boolean isEnd;
        // 以这个作为前缀的单词数量
        public int prefixCount;
        // 单词数量
        public int wordCount;

        public TrieNode() {
            this.nexts = new TrieNode[26];
            this.isEnd = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apple");
        System.out.println(trie.countWordsEqualTo("apple"));
        System.out.println(trie.countWordsStartingWith("app"));
    }
}
