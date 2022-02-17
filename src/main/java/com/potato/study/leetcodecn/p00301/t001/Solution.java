package com.potato.study.leetcodecn.p00301.t001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Assert;

/**
 * 301. 删除无效的括号
 *
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * 示例 2：
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * 示例 3：
 *
 * 输入：s = ")("
 * 输出：[""]
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 结果集合
    private Set<String> resultSet;
    // 当前结合中元素的长度
    private int length;
    // 当前最大的分数
    private int maxScore; // 结果集合

    public List<String> removeInvalidParentheses(String s) {
        // 遍历 s 如果 是 （ +1 如果是 ） -1 统计一下 两种类型符号的个数
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                left++;
            } else if (')' == chars[i]) {
                right++;
            }
        }
        int maxScore = Math.min(left, right);
        if (left == 0 && right == 0) {
            List<String> res = new ArrayList<>();
            res.add(s);
            return res;
        }
        // max 就是 最小值 说明 只能有最小个 这样的数字
        this.resultSet = new HashSet<>();
        resultSet.add("");
        this.length = 0;
        this.maxScore = maxScore;
        // dfs 使用 一个 set 记录 当前最长长度的 结果集合
        dfs(s, 0, "", 0);
        return new ArrayList<>(resultSet);
    }


    private void dfs(String s, int currentIndex, String currentWord, int currentScore) {
        if (currentScore < 0) {
            return;
        }
        if (currentIndex >= s.length()) {
            return;
        }
        // 不使用这个字符
        if (currentScore == 0 && currentWord.length() > 0) {
            if (currentWord.length() == this.length) {
                this.resultSet.add(currentWord);
            } else if (currentWord.length() > this.length) {
                this.length = currentWord.length();
                this.resultSet = new HashSet<>();
                this.resultSet.add(currentWord);
            }
        }
        dfs(s, currentIndex + 1, currentWord, currentScore);
        // 使用这个字符
        char charAt = s.charAt(currentIndex);
        if (charAt == '(') {
            currentScore++;
        } else if (charAt == ')') {
            currentScore--;
        }
        // 分数边界判断
        if (currentScore < 0 || currentScore > this.maxScore) {
            return;
        }
        if (currentScore == 0) {
            if (currentWord.length() + 1 == this.length) {
                this.resultSet.add(currentWord + charAt);
            } else if (currentWord.length() + 1 > this.length) {
                this.length = currentWord.length() + 1;
                this.resultSet = new HashSet<>();
                this.resultSet.add(currentWord + charAt);
            }
        }
        dfs(s, currentIndex + 1, currentWord + charAt, currentScore);
    }

    public static void main(String[] args) {
        String s = "()())()";
        Solution solution = new Solution();
        List<String> list = solution.removeInvalidParentheses(s);
        System.out.println(list);


        s = "n";
        list = solution.removeInvalidParentheses(s);
        System.out.println(list);


        s = ")(n";
        list = solution.removeInvalidParentheses(s);
        System.out.println(list);
    }
}
