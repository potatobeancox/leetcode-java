package com.potato.study.leetcodecn.p01268.t001;


import com.potato.study.leetcode.domain.duplicate.name.Node;

import java.util.*;

/**
 * 1268. 搜索推荐系统
 *
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。

 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。

 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。

  

 示例 1：

 输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 输出：[
 ["mobile","moneypot","monitor"],
 ["mobile","moneypot","monitor"],
 ["mouse","mousepad"],
 ["mouse","mousepad"],
 ["mouse","mousepad"]
 ]
 解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]
 输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
 示例 2：

 输入：products = ["havana"], searchWord = "havana"
 输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 示例 3：

 输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 示例 4：

 输入：products = ["havana"], searchWord = "tatiana"
 输出：[[],[],[],[],[],[],[]]
  

 提示：

 1 <= products.length <= 1000
 1 <= Σ products[i].length <= 2 * 10^4
 products[i] 中所有的字符都是小写英文字母。
 1 <= searchWord.length <= 1000
 searchWord 中所有字符都是小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-suggestions-system
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private Trie trie;
    /**
     *
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        this.trie = new Trie();
        for (String word : products) {
            insert(word);
        }
        // 遍历 searchWord 每个字符 生成结果
        List<List<String>> resultList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        Trie node = this.trie;
        for (char ch : searchWord.toCharArray()) {

            List<String> tmp = new ArrayList<>();

            if (node == null) {
                resultList.add(tmp);
                continue;
            }

            Trie child = node.child[ch - 'a'];
            node = child;

            if (node == null) {
                resultList.add(tmp);
                continue;
            }



            while (!node.priorityQueue.isEmpty()) {
                tmp.add(0, node.priorityQueue.poll());
            }

            if (tmp.size() > 3) {
                tmp = tmp.subList(0, 3);
            }
            resultList.add(tmp);
        }
        return resultList;
    }


    private void insert(String word) {
        Trie node = this.trie;
        for (char ch : word.toCharArray()) {
            Trie child = node.child[ch - 'a'];
            if (null == child) {
                child = new Trie();
                child.ch = ch;
                node.child[ch - 'a'] = child;
            }
            node = child;
            node.priorityQueue.add(word);
            while (node.priorityQueue.size() > 3) {
                node.priorityQueue.poll();
            }

        }
    }

    class Trie {
        public Trie[] child;
        public PriorityQueue<String> priorityQueue;
        public char ch;

        public Trie() {
            this.child = new Trie[26];
            this.priorityQueue = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] products = new String[] {
                "mobile","mouse","moneypot","monitor","mousepad"
        };
        String searchWord = "mouse";
        List<List<String>> lists = solution.suggestedProducts(products, searchWord);
        System.out.println(lists);
    }


}
