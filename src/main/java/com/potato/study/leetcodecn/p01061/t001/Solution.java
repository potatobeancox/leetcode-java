package com.potato.study.leetcodecn.p01061.t001;

/**
 * 1061. 按字典序排列最小的等效字符串
 *
 * 给出长度相同的两个字符串s1 和 s2 ，还有一个字符串 baseStr 。

 其中  s1[i] 和 s2[i]  是一组等价字符。

 举个例子，如果 s1 = "abc" 且 s2 = "cde"，那么就有 'a' == 'c', 'b' == 'd', 'c' == 'e'。
 等价字符遵循任何等价关系的一般规则：

  自反性 ：'a' == 'a'
  对称性 ：'a' == 'b' 则必定有 'b' == 'a'
  传递性 ：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
 例如， s1 = "abc" 和 s2 = "cde" 的等价信息和之前的例子一样，那么 baseStr = "eed" , "acd" 或 "aab"，这三个字符串都是等价的，而 "aab" 是 baseStr 的按字典序最小的等价字符串

 利用 s1 和 s2 的等价信息，找出并返回 baseStr 的按字典序排列最小的等价字符串。

  

 示例 1：

 输入：s1 = "parker", s2 = "morris", baseStr = "parser"
 输出："makkek"
 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [m,p], [a,o], [k,r,s], [e,i] 共 4 组。每组中的字符都是等价的，并按字典序排列。所以答案是 "makkek"。
 示例 2：

 输入：s1 = "hello", s2 = "world", baseStr = "hold"
 输出："hdld"
 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [h,w], [d,e,o], [l,r] 共 3 组。所以只有 S 中的第二个字符 'o' 变成 'd'，最后答案为 "hdld"。
 示例 3：

 输入：s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 输出："aauaaaaada"
 解释：我们可以把 A 和 B 中的等价字符分为 [a,o,e,r,s,c], [l,p], [g,t] 和 [d,m] 共 4 组，因此 S 中除了 'u' 和 'd' 之外的所有字母都转化成了 'a'，最后答案为 "aauaaaaada"。
  

 提示：

 1 <= s1.length, s2.length, baseStr <= 1000
 s1.length == s2.length
 字符串s1, s2, and baseStr 仅由从 'a' 到 'z' 的小写英文字母组成。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/lexicographically-smallest-equivalent-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // 生成并查集 s1 s2
        UnionFind unionFind = new UnionFind(26);
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            int index1 = s1.charAt(i) - 'a';
            int index2 = s2.charAt(i) - 'a';

            unionFind.union(index1, index2);
        }
        // 遍历 base 使用 find 找到字符替换
        StringBuilder builder = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            int temp = unionFind.find(ch - 'a');
            char targetCh = (char) ('a' + temp);
            builder.append(targetCh);
        }
        return builder.toString();
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
            } else if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }

        public int find(int target) {
            while (target != parent[target]) {
                target = parent[target];
            }
            return target;
        }
    }

}
