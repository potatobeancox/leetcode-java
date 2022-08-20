package com.potato.study.leetcodecn.p00269.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 269. 火星词典
 *
 * 现有一种使用英语字母的火星语言，这门语言的字母顺序与英语顺序不同。

 给你一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。

 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。

 字符串 s 字典顺序小于 字符串 t 有两种情况：

 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
  

 示例 1：

 输入：words = ["wrt","wrf","er","ett","rftt"]
 输出："wertf"
 示例 2：

 输入：words = ["z","x"]
 输出："zx"
 示例 3：

 输入：words = ["z","x","z"]
 输出：""
 解释：不存在合法字母顺序，因此返回 "" 。
  

 提示：

 1 <= words.length <= 100
 1 <= words[i].length <= 100
 words[i] 仅由小写英文字母组成

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/alien-dictionary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String alienOrder(String[] words) {
        // 遍历 word 每个位置 生成一个 parent list child 的 list list
        List<List<Character>> graph = new ArrayList<>();
        // 生成孩子
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        // 用一个 map 记录 目前每个字母的入度
        Map<Character, Integer> indegreeMap = new HashMap<>();
        // 初始化
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                indegreeMap.put(chars[i], 0);
            }
        }


        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                // 从第一个位置开始
                int index = 0;
                while (index < words[i].length() && index < words[j].length()
                        && words[i].charAt(index) == words[j].charAt(index)) {
                    index++;
                }
                // 达到了 某一个的终点
                if (index >= words[i].length() || index >= words[j].length()) {
                    // 出现了 长的在短的前面
                    if (words[i].length() > words[j].length()) {
                        return "";
                    }
                    break;
                }
                // 第一个不相同位置
                char parent = words[i].charAt(index);
                char child = words[j].charAt(index);

                // 如果当前 child 出现过 前一个位置 相同那么就不
                if (j > i+1 && words[j-1].length() > index
                        && words[j-1].charAt(index) == child) {
                    // 记录过关系
                    continue;
                }
                // 计算对应关系
                int parentIndex = parent - 'a';
                graph.get(parentIndex).add(child);

                // 计算入度
                int indegree = indegreeMap.getOrDefault(child, 0);
                indegree++;
                indegreeMap.put(child, indegree);
            }
        }


        // 将 map 入入度为 0的 插入 queue中
        Queue<Character> queue = new LinkedList<>();
        // 用 set 记录已经用过的字母 再次出现 直接返回false
        Set<Character> usedSet = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : indegreeMap.entrySet()) {
            int indegree = entry.getValue();
            char ch = entry.getKey();
            if (indegree == 0) {
                queue.add(ch);
                usedSet.add(ch);
            }
        }
        // 每次从 queue 中 pop 出一个 字母 作为当前字母，修改 入度，如果修改之后 入度为 0 加入 queue
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            char poll = queue.poll();
            // 记录bulid
            builder.append(poll);
            // 修改 入度
            int pollIndex = poll - 'a';
            List<Character> childList = graph.get(pollIndex);
            for (char child : childList) {
                // 已经用过了的 child 说明 出现了环
                if (usedSet.contains(child)) {
                    return "";
                }
                // 修改入度
                if (!indegreeMap.containsKey(child)
                        || indegreeMap.get(child) <= 0) {
                    return "";
                }
                indegreeMap.put(child, indegreeMap.get(child) - 1);
                // 判断是否 进去queue 修改 set
                if (indegreeMap.get(child) == 0) {
                    queue.add(child);
                    usedSet.add(child);
                }
            }
        }
        // 有些字符没法确定
        if (builder.length() < indegreeMap.size()) {
            return "";
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[] {
                "wrt","wrf","er","ett","rftt"
        };
        String s = solution.alienOrder(words);
        System.out.println(s);
        Assert.assertEquals("wertf", s);


        words = new String[] {
                "z","z"
        };
        s = solution.alienOrder(words);
        System.out.println(s);
        Assert.assertEquals("z", s);

        words = new String[] {
                "ab","adc"
        };
        s = solution.alienOrder(words);
        System.out.println(s);
        Assert.assertEquals("abcd", s);


        words = new String[] {
                "abc","ab"
        };
        s = solution.alienOrder(words);
        System.out.println(s);
        Assert.assertEquals("", s);


        words = new String[] {
                "z","x","a","zb","zx"
        };
        s = solution.alienOrder(words);
        System.out.println(s);
        Assert.assertEquals("", s);
    }


}
