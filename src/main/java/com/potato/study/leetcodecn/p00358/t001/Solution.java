package com.potato.study.leetcodecn.p00358.t001;

import org.junit.Assert;

import java.util.*;

/**
 * 358. K 距离间隔重排字符串
 *
 * 给你一个非空的字符串 s 和一个整数 k ，你要将这个字符串 s 中的字母进行重新排列，使得重排后的字符串中相同字母的位置间隔距离 至少 为 k 。如果无法做到，请返回一个空字符串 ""。

  

 示例 1：

 输入: s = "aabbcc", k = 3
 输出: "abcabc"
 解释: 相同的字母在新的字符串中间隔至少 3 个单位距离。
 示例 2:

 输入: s = "aaabc", k = 3
 输出: ""
 解释: 没有办法找到可能的重排结果。
 示例 3:

 输入: s = "aaadbbcc", k = 2
 输出: "abacabcd"
 解释: 相同的字母在新的字符串中间隔至少 2 个单位距离。
  

 提示：

 1 <= s.length <= 3 * 105
 s 仅由小写英文字母组成
 0 <= k <= s.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/rearrange-string-k-distance-apart
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        // 统计 s 中的字母出现次数
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            int count = countMap.getOrDefault(ch, 0);
            count++;
            countMap.put(ch, count);
        }
        // 将出现次数 放入频次大根堆
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(countMap.get(o2), countMap.get(o1));
        });
        for (char ch : countMap.keySet()) {
            priorityQueue.add(ch);
        }
        // 依次判断 是否已经达到了 最大出现的次数 queue 达到了 循环 pop 加入堆中
        Queue<Character> usedQueue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty() || usedQueue.size() >= k) {
            // queue 是不是已经ok了
            while (usedQueue.size() >= k) {
                Character poll = usedQueue.poll();
                if (countMap.get(poll) > 0) {
                    priorityQueue.add(poll);
                }
            }
            if (priorityQueue.isEmpty()) {
                break;
            }
            // 依次从堆中获取 最大的字母 进行pop 生成单词
            Character ch = priorityQueue.poll();
            builder.append(ch);
            countMap.put(ch, countMap.get(ch) - 1);
            // 使用一个 queue 记录还没有到达k 次数先后顺序都有哪些字母
            usedQueue.add(ch);
        }
        // 使用 生成单词个数判定
        if (builder.length() != s.length()) {
            return "";
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "aabbcc";
        int k = 3;
        String word = solution.rearrangeString(s, k);
        System.out.println(word);
        Assert.assertEquals("acbacb", word);

    }
}
