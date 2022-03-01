package com.potato.study.leetcodecn.p02182.t001;

import java.util.PriorityQueue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.ListNode;

/**
 * 2182. 构造限制重复的字符串
 *
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
 *
 * 返回 字典序最大的 repeatLimitedString 。
 *
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length)
 * 个字符都相同，那么较长的字符串字典序更大。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 * 示例 2：
 *
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 *  
 *
 * 提示：
 *
 * 1 <= repeatLimit <= s.length <= 105
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-string-with-repeat-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 2182 构造限制重复字符串
    public String repeatLimitedString(String s, int repeatLimit) {
        // 统计 s中各个字符出现的次数
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // index1 是 对应字母 对应的index ，index2 是目前有的数量
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((arr1, arr2) -> {
            return Integer.compare(arr2[0], arr1[0]);
        });
        // 贪心构建 如果 当前 字符出现超多 repeatLimit 往后找一个字符
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            priorityQueue.add(new int[] {
                    i, count[i]
            });
        }
        // 生成结果
        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            // 当前最大
            int[] poll = priorityQueue.poll();
            if (poll[1] > repeatLimit && priorityQueue.isEmpty()) {
                for (int i = 0; i < repeatLimit; i++) {
                    builder.append((char) ('a' + poll[0]));
                }
                return builder.toString();
            }
            if (poll[1] <= repeatLimit) {
                for (int i = 0; i < poll[1]; i++) {
                    builder.append((char) ('a' + poll[0]));
                }
                continue;
            }
            // 有多于 repeatLimit 个字符
            for (int i = 0; i < repeatLimit; i++) {
                builder.append((char) ('a' + poll[0]));
            }
            poll[1] -= repeatLimit;
            if (priorityQueue.isEmpty()) {
                return builder.toString();
            }
            int[] other = priorityQueue.poll();
            builder.append((char) ('a' + other[0]));
            other[1]--;
            priorityQueue.add(poll);
            if (other[1] > 0) {
                priorityQueue.add(other);
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "cczazcc";
        int repeatLimit = 3;
        String s = solution.repeatLimitedString(str, repeatLimit);
        System.out.println(s);
        Assert.assertEquals("zzcccac", s);

        // 不必使用完 所有的字符串
        str = "aababab";
        repeatLimit = 2;
        s = solution.repeatLimitedString(str, repeatLimit);
        System.out.println(s);
        Assert.assertEquals("bbabaa", s);
    }



}
