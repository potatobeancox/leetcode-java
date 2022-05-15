package com.potato.study.leetcodecn.p01717.t001;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import org.junit.Assert;

/**
 * 1717. 删除子字符串的最大得分
 *
 * 给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
 *
 * 删除子字符串 "ab" 并得到 x 分。
 * 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
 * 删除子字符串"ba" 并得到 y 分。
 * 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
 * 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "cdbcbbaaabab", x = 4, y = 5
 * 输出：19
 * 解释：
 * - 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
 * - 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
 * - 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
 * - 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
 * 总得分为 5 + 4 + 5 + 5 = 19 。
 * 示例 2：
 *
 * 输入：s = "aabbaaxybbaabb", x = 5, y = 4
 * 输出：20
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * 1 <= x, y <= 104
 * s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-score-from-removing-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int maximumGain(String s, int x, int y) {
        // 直接遍历 s 使用一个 栈记录 上一个 a 出现的 如果出现了 y大 就互换 xy
        if (y > x) {
            int tmp = y;
            y = x;
            x = tmp;
            s = new StringBuilder(s).reverse().toString();
        }
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int score = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch != 'b') {
                stack.addLast(ch);
                continue;
            }
            //  b
            if (!stack.isEmpty() && stack.peekLast() == 'a') {
                stack.pollLast();
                score += x;
            } else {
                // b 没有被消耗
                stack.addLast(ch);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pollFirst());
        }
        if (builder.length() == 0) {
            return score;
        }
        chars = builder.toString().toCharArray();

        // 搞ba
        stack = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch != 'a') {
                stack.addLast(ch);
                continue;
            }
            //  b
            if (!stack.isEmpty() && stack.peekLast() == 'b') {
                stack.pollLast();
                score += y;
            } else {
                // b 没有被消耗
                stack.addLast(ch);
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "cdbcbbaaabab";
        int x = 4;
        int y = 5;
        int i = solution.maximumGain(s, x, y);
        System.out.println(i);
        Assert.assertEquals(19, i);
    }
}
