package com.potato.study.leetcodecn.other.lcr.p0064.t001;

/**
 * 剑指 Offer II 064. 神奇的字典
 *
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 *
 * 实现 MagicDictionary 类：
 *
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *  
 *
 * 示例：
 *
 * 输入
 * inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *  
 *
 * 提示：
 *
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 *  
 *
 * 注意：本题与主站 676 题相同： https://leetcode-cn.com/problems/implement-magic-dictionary/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/US1pGT
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MagicDictionary {
    // 064

    private Dictionary root;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        this.root = new Dictionary();
    }

    /**
     * 构造字段数
     * @param dictionary
     */
    public void buildDict(String[] dictionary) {
        for (String dic : dictionary) {
            Dictionary current = this.root;
            for (char ch : dic.toCharArray()) {
                int index = ch - 'a';
                if (current.next[index] == null) {
                    current.next[index] = new Dictionary();
                }
                current = current.next[index];
            }
            current.isEnd = true;
        }
    }

    /**
     * 从根开始搜索 如果 记录 一个标志 当前已经换了多少个单词
     * @param searchWord
     * @return
     */
    public boolean search(String searchWord) {
        return searchWord(searchWord, this.root, 0, 0);
    }

    /**
     * 当前搜索
     * @param searchWord
     * @param root
     * @param replaceCount
     * @return
     */
    private boolean searchWord(String searchWord, Dictionary root, int replaceCount, int charIndex) {
        // 找到最后了
        if (replaceCount > 1) {
            return false;
        }
        if (charIndex == searchWord.length()) {
            // 判断当前找到最后
            if (root != null && root.isEnd) {
                return true;
            }
            return false;
        }
        // 还没找到最后一个点 找下一个点
        char ch = searchWord.charAt(charIndex);
        int index = ch - 'a';
        Dictionary[] child = root.next;
        for (int i = 0; i < child.length; i++) {
            boolean hasFind;
            if (i != index) {
                hasFind = searchWord(searchWord, child[i], replaceCount + 1, charIndex + 1);
            } else {
                hasFind = searchWord(searchWord, child[i], replaceCount, charIndex + 1);
            }
            if (hasFind) {
                return true;
            }
        }
        return false;
    }


    class Dictionary {
        public Dictionary[] next;
        public boolean isEnd;

        public Dictionary() {
            this.next = new Dictionary[26];
        }
    }
}
