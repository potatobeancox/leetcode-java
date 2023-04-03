package com.potato.study.leetcodecn.other.Interview.p0016.p0026;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 面试题 16.26. 计算器
 *
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/calculator-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 16.26
    public int calculate(String s) {
        // 遍历 s 正整数、加(+)、减(-)、乘(*)、除(/)
        Stack<Character> opStack = new Stack<>();
        // 一个操作数栈 一个 符号栈 如果当前是
        Stack<Integer> numStack = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            // 遍历 s 如果当前字母是数字 那就循环往后解析提取数字 否则 当前是符号
            char c = s.charAt(index);
            // 看看是不是数字
            boolean isDigit = Character.isDigit(c);
            if (isDigit) {
                int num = (c - '0');
                while (index < s.length() - 1 && Character.isDigit(s.charAt(index + 1))) {
                    num *= 10;
                    num += (c - '0');
                    index++;
                }
                numStack.add(num);
                continue;
            }
            // 这个分支是 符号 加(+)、减(-)、乘(*)、除(/) 如果是加减 之前的运算符号非空 那么就要进行之前的运算 如果是乘除要看之前符号 只有乘除的时候 之前才运算
            if (c == '+' || c == '-'
                    || ((c == '*' || c == '/') && (opStack.peek() == '*' || opStack.peek() == '/'))) {
                // 计算之前的
                if (!opStack.isEmpty()) {
                    Character pop = opStack.pop();
                    Integer num2 = numStack.pop();
                    Integer num1 = numStack.pop();
                    int target;
                    switch (pop) {
                        case '+':
                            target = num1 + num2;
                            numStack.push(target);
                            break;
                        case '-':
                            target = num1 - num2;
                            numStack.push(target);
                            break;
                        case '*':
                            target = num1 * num2;
                            numStack.push(target);
                            break;
                        case '/':
                            target = num1 / num2;
                            numStack.push(target);
                            break;
                    }
                }
                opStack.add(c);
                index++;
            }
        }

        // 符号栈如果非空 那么就循环pop
        while (!opStack.isEmpty()) {
            Character pop = opStack.pop();

            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();

            int target;
            switch (pop) {
                case '+':
                    target = num1 + num2;
                    numStack.push(target);
                    break;
                case '-':
                    target = num1 - num2;
                    numStack.push(target);
                    break;
                case '*':
                    target = num1 * num2;
                    numStack.push(target);
                    break;
                case '/':
                    target = num1 / num2;
                    numStack.push(target);
                    break;
            }
        }
        return numStack.pop();
    }
}
