package com.potato.study.leetcodecn.p00439.t001;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 439. 三元表达式解析器
 *
 * 给定一个表示任意嵌套三元表达式的字符串 expression ，求值并返回其结果。

 你可以总是假设给定的表达式是有效的，并且只包含数字， '?' ，  ':' ，  'T' 和 'F' ，其中 'T' 为真， 'F' 为假。表达式中的所有数字都是 一位 数(即在 [0,9] 范围内)。

 条件表达式从右到左分组(大多数语言中都是这样)，表达式的结果总是为数字 'T' 或 'F' 。

  

 示例 1：

 输入： expression = "T?2:3"
 输出： "2"
 解释： 如果条件为真，结果为 2；否则，结果为 3。
 示例 2：

 输入： expression = "F?1:T?4:5"
 输出： "4"
 解释： 条件表达式自右向左结合。使用括号的话，相当于：
 "(F ? 1 : (T ? 4 : 5))" --> "(F ? 1 : 4)" --> "4"
 or "(F ? 1 : (T ? 4 : 5))" --> "(T ? 4 : 5)" --> "4"
 示例 3：

 输入： expression = "T?T?F:5:3"
 输出： "F"
 解释： 条件表达式自右向左结合。使用括号的话，相当于：
 "(T ? (T ? F : 5) : 3)" --> "(T ? F : 3)" --> "F"
 "(T ? (T ? F : 5) : 3)" --> "(T ? F : 5)" --> "F"
  

 提示:

 5 <= expression.length <= 104
 expression 由数字, 'T', 'F', '?' 和 ':' 组成
 保证 了表达式是一个有效的三元表达式，并且每个数字都是 一位数 

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/ternary-expression-parser
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public String parseTernary(String expression) {
        // 用一个栈 存没有用到的数字
        Stack<Character> stack = new Stack<>();
        // 从后往前遍历 字符串
        char[] chars = expression.toCharArray();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char ch = chars[i];
            // 冒号直接 下一个字符
            if (':' == ch) {
                continue;
            }
            // 数字的话 题目包整只有一个数字 直接进展
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                stack.add(ch);
                continue;
            }
            if ('?' == ch) {
                // 问号 说明 已经 stack 中有个两个操作数 往前判断 字母是 tf 决定 使用哪个继续进展
                char condition = chars[i-1];
                i--;
                Character op1 = stack.pop();
                Character op2 = stack.pop();
                if (condition == 'F') {
                    stack.add(op2);
                } else if (condition == 'T') {
                    stack.add(op1);
                } else {
                    // error
                }
            } else {
                // err
            }

        }
        return String.valueOf(stack.peek());
    }

}
