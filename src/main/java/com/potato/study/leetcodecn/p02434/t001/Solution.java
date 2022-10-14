package com.potato.study.leetcodecn.p02434.t001;

import java.util.Stack;

/**
 * 2434. 使用机器人打印字典序最小的字符串
 *
 * 给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。执行以下操作之一，直到 s 和 t 都变成空字符串：
 *
 * 删除字符串 s 的 第一个 字符，并将该字符给机器人。机器人把这个字符添加到 t 的尾部。
 * 删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
 * 请你返回纸上能写出的字典序最小的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "zza"
 * 输出："azz"
 * 解释：用 p 表示写出来的字符串。
 * 一开始，p="" ，s="zza" ，t="" 。
 * 执行第一个操作三次，得到 p="" ，s="" ，t="zza" 。
 * 执行第二个操作三次，得到 p="azz" ，s="" ，t="" 。
 * 示例 2：
 *
 * 输入：s = "bac"
 * 输出："abc"
 * 解释：用 p 表示写出来的字符串。
 * 执行第一个操作两次，得到 p="" ，s="c" ，t="ba" 。
 * 执行第二个操作两次，得到 p="ab" ，s="c" ，t="" 。
 * 执行第一个操作，得到 p="ab" ，s="" ，t="c" 。
 * 执行第二个操作，得到 p="abc" ，s="" ，t="" 。
 * 示例 3：
 *
 * 输入：s = "bdda"
 * 输出："addb"
 * 解释：用 p 表示写出来的字符串。
 * 一开始，p="" ，s="bdda" ，t="" 。
 * 执行第一个操作四次，得到 p="" ，s="" ，t="bdda" 。
 * 执行第二个操作四次，得到 p="addb" ，s="" ，t="" 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public String robotWithString(String s) {
        // 遍历字母 计数
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 使用一个栈 记录当前没有使用的字母
        Stack<Character> stack = new Stack<>();
        // 先进栈
        int minCharIndex = 0;
        StringBuilder builder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            count[ch - 'a']--;
            // 找到 剩余的最小字母
            while (minCharIndex <= 25 && count[minCharIndex] == 0) {
                minCharIndex++;
            }
            stack.add(ch);
            // 判断当前栈顶是否小于等于 minCharIndex
            while (!stack.isEmpty() && stack.peek() - 'a' <= minCharIndex) {
                builder.append(stack.pop());
            }
        }
        // 如果栈顶小于等于 剩余最小 弹出 放入结集合
        return builder.toString();
    }

}
