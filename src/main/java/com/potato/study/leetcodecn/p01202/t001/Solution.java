package com.potato.study.leetcodecn.p01202.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.*;

/**
 * 1202. 交换字符串中的元素
 *
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。

 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。

 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

  

 示例 1:

 输入：s = "dcab", pairs = [[0,3],[1,2]]
 输出："bacd"
 解释：
 交换 s[0] 和 s[3], s = "bcad"
 交换 s[1] 和 s[2], s = "bacd"
 示例 2：

 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 输出："abcd"
 解释：
 交换 s[0] 和 s[3], s = "bcad"
 交换 s[0] 和 s[2], s = "acbd"
 交换 s[1] 和 s[2], s = "abcd"
 示例 3：

 输入：s = "cba", pairs = [[0,1],[1,2]]
 输出："abc"
 解释：
 交换 s[0] 和 s[1], s = "bca"
 交换 s[1] 和 s[2], s = "bac"
 交换 s[0] 和 s[1], s = "abc"
  

 提示：

 1 <= s.length <= 10^5
 0 <= pairs.length <= 10^5
 0 <= pairs[i][0], pairs[i][1] < s.length
 s 中只含有小写英文字母

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // 并查集 遍历找到相同的联通分量 对于相同联通分量数字进行排序
        UnionFind unionFind = new UnionFind(s.length());
        for (int i = 0; i < pairs.size(); i++) {
            List<Integer> pair = pairs.get(i);
            int target1 = pair.get(0);
            int target2 = pair.get(1);
            unionFind.union(target1, target2);
        }
        // 获取 parent 同一个 放入一个 map parentValue PriorityQueue 字典序升序
        int[] parent = unionFind.getParent();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int key = unionFind.find(parent[i]);
            PriorityQueue<Character> priorityQueue = map.getOrDefault(key, new PriorityQueue<>(new Comparator<Character>() {
                @Override
                public int compare(Character w1, Character w2) {
                    return Character.compare(w1, w2);
                }
            }));
            priorityQueue.add(s.charAt(i));
            map.put(key, priorityQueue);

        }
        char[] chars = s.toCharArray();
        // 对于 map 按照 相同的 parent 进行排序
        for (int i = 0; i < parent.length; i++) {
            int key = unionFind.find(parent[i]);
            PriorityQueue<Character> priorityQueue = map.get(key);
            chars[i] = priorityQueue.poll();
        }
        return new String(chars);
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
            parent[p1] = p2;
        }

        public int find(int target) {
            while (parent[target] != target) {
                target = parent[target];
            }
            return parent[target];
        }

        public int[] getParent() {
            return parent;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "dcab";
        List<List<Integer>> pairs = LeetcodeInputUtils.inputString2IntListTwoDimensional("[[0,3],[1,2]]");
        String word = solution.smallestStringWithSwaps(s, pairs);
        System.out.println(word);
        Assert.assertEquals("bacd", word);

        s = "dcab";
        pairs = LeetcodeInputUtils.inputString2IntListTwoDimensional("[[0,3],[1,2],[0,2]]");
        word = solution.smallestStringWithSwaps(s, pairs);
        System.out.println(word);
        Assert.assertEquals("abcd", word);
    }
}



