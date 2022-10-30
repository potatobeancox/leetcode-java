package com.potato.study.leetcodecn.p00737.t001;

import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 737. 句子相似性 II
 *
 * 我们可以将一个句子表示为一个单词数组，例如，句子 I am happy with leetcode"可以表示为 arr = ["I","am",happy","with","leetcode"]
 *
 * 给定两个句子 sentence1 和 sentence2 分别表示为一个字符串数组，并给定一个字符串对 similarPairs ，其中 similarPairs[i] = [xi, yi] 表示两个单词 xi 和 yi 是相似的。
 *
 * 如果 sentence1 和 sentence2 相似则返回 true ，如果不相似则返回 false 。
 *
 * 两个句子是相似的，如果:
 *
 * 它们具有 相同的长度 (即相同的字数)
 * sentence1[i] 和 sentence2[i] 是相似的
 * 请注意，一个词总是与它自己相似，也请注意，相似关系是可传递的。例如，如果单词 a 和 b 是相似的，单词 b 和 c 也是相似的，那么 a 和 c 也是 相似 的。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
 * 输出: true
 * 解释: 这两个句子长度相同，每个单词都相似。
 * 示例 2:
 *
 * 输入: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * 输出: true
 * 解释: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
 * 因为“leetcode”和“onepiece”相似，而且前两个单词是相同的，所以这两句话是相似的。
 * 示例 3:
 *
 * 输入: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * 输出: false
 * 解释: “leetcode”和“onepiece”不相似。
 *  
 *
 * 提示:
 *
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] 和 sentence2[i] 只包含大小写英文字母
 * 0 <= similarPairs.length <= 2000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi 和 yi 只含英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sentence-similarity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2,
                                          List<List<String>> similarPairs) {
        // 将 similarPairs 单词转换成数字
        Map<String, Integer> wordIndexMap = new HashMap<>();
        UnionFind unionFind = new UnionFind(similarPairs.size() * 2);
        for (List<String> similarPair : similarPairs) {
            String word1 = similarPair.get(0);
            String word2 = similarPair.get(1);

            // 两个字符串相似
            int wordIndex1;
            if (!wordIndexMap.containsKey(word1)) {
                wordIndex1 = wordIndexMap.size();
                wordIndexMap.put(word1, wordIndexMap.size());
            } else {
                wordIndex1 = wordIndexMap.get(word1);
            }

            int wordIndex2;
            if (!wordIndexMap.containsKey(word2)) {
                wordIndex2 = wordIndexMap.size();
                wordIndexMap.put(word2, wordIndexMap.size());
            } else {
                wordIndex2 = wordIndexMap.get(word2);
            }

            unionFind.union(wordIndex1, wordIndex2);
        }
        // 分别对 sentence1 和 sentence2 每个单词进行find 找到对应结果
        if (sentence1.length != sentence2.length) {
            return false;
        }
        int n = sentence1.length;
        for (int i = 0; i < n; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];


            if (word1.equals(word2)) {
                continue;
            }

            // 如果 map 中不包含某一个那就是不相似
            if (!wordIndexMap.containsKey(word1)
                    || !wordIndexMap.containsKey(word2)) {
                return false;
            }

            int p1 = unionFind.find(wordIndexMap.get(word1));
            int p2 = unionFind.find(wordIndexMap.get(word2));
            // 没有相同父亲就是不相似
            if (p1 != p2) {
                return false;
            }

        }
        return true;
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
            parent[p2] = p1;
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] sentence1 = LeetcodeInputUtils.inputString2StringArray("[\"I\",\"love\",\"leetcode\"]");
        String[] sentence2 = LeetcodeInputUtils.inputString2StringArray("[\"I\",\"love\",\"onepiece\"]");
        String input = "[[\"manga\",\"onepiece\"],[\"platform\",\"anime\"],[\"leetcode\",\"platform\"],[\"anime\",\"manga\"]]";
        List<List<String>> similarPairs = LeetcodeInputUtils.inputString2StringListList(input);

        boolean b = solution.areSentencesSimilarTwo(sentence1, sentence2, similarPairs);
        System.out.println(b);
        Assert.assertEquals(true, b);
    }
}
