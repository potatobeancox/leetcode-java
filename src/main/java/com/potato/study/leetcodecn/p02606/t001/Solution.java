package com.potato.study.leetcodecn.p02606.t001;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *
 * 2606. 找到最大开销的子字符串
 *
 * 给你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
 *
 * 子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
 *
 * 字符的价值 定义如下：
 *
 * 如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。
 * 比方说，'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
 * 否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
 * 请你返回字符串 s 的所有子字符串中的最大开销。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "adaa", chars = "d", vals = [-1000]
 * 输出：2
 * 解释：字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
 * 最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
 * 2 是最大开销。
 * 示例 2：
 *
 * 输入：s = "abc", chars = "abc", vals = [-1,-1,-1]
 * 输出：0
 * 解释：字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
 * 最大开销子字符串是 "" ，它的开销为 0 。
 * 0 是最大开销。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含小写英文字母。
 * 1 <= chars.length <= 26
 * chars 只包含小写英文字母，且 互不相同 。
 * vals.length == chars.length
 * -1000 <= vals[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-substring-with-maximum-cost
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2606
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        // 如果 s中字符出现在 chars 中 index为 i 那么开销就是 val i 否则就是 字母表的位置
        Map<Character, Integer> charsValueMap = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            charsValueMap.put(chars.charAt(i), vals[i]);
        }
        // 因为 val i 可能小于 0
        int[] charsValues = new int[26];
        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            if (charsValueMap.containsKey(ch)) {
                charsValues[i] = charsValueMap.get(ch);
            } else {
                charsValues[i] = i + 1;
            }
        }
        // 遍历 s 求前缀和的 valSum 过程中记录之前的最小值 小跟堆 过程中减去最小值 记录max
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        smallHeap.add(0);
        int currentSum = 0;
        int max = Integer.MIN_VALUE;
        for (char current : s.toCharArray()) {
            currentSum += charsValues[current - 'a'];
            int smallest = smallHeap.peek();
            max = Math.max(max, currentSum - smallest);
        }
        return max;
    }


}
