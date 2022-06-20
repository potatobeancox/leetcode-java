package com.potato.study.leetcodecn.p00839.t001;

import org.junit.Assert;

/**
 * 839. 相似字符串组
 *
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？

  

 示例 1：

 输入：strs = ["tars","rats","arts","star"]
 输出：2
 示例 2：

 输入：strs = ["omv","ovm"]
 输出：1
  

 提示：

 1 <= strs.length <= 300
 1 <= strs[i].length <= 300
 strs[i] 只包含小写字母。
 strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/similar-string-groups
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }


    private boolean check(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
            if (diff > 2) {
                return false;
            }
        }
        return diff == 0 || diff == 2;
    }


    class UnionFind {

        private int[] parent;
        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.count = n;
        }

        public void union(int target1, int target2) {
            int p1 = find(target1);
            int p2 = find(target2);
            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
            count--;
        }


        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return target;
        }

    }
}
