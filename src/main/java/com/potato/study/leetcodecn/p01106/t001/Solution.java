package com.potato.study.leetcodecn.p01106.t001;


import com.potato.study.leetcode.util.LeetcodeInputUtils;
import org.junit.Assert;

import java.util.Stack;

/**
 * 1106. 解析布尔表达式
 *
 * 布尔表达式 是计算结果不是 true 就是 false 的表达式。有效的表达式需遵循以下约定：
 *
 * 't'，运算结果为 true
 * 'f'，运算结果为 false
 * '!(subExpr)'，运算过程为对内部表达式 subExpr 进行 逻辑非（NOT）运算
 * '&(subExpr1, subExpr2, ..., subExprn)'，运算过程为对 2 个或以上内部表达式 subExpr1, subExpr2, ..., subExprn 进行 逻辑与（AND）运算
 * '|(subExpr1, subExpr2, ..., subExprn)'，运算过程为对 2 个或以上内部表达式 subExpr1, subExpr2, ..., subExprn 进行 逻辑或（OR）运算
 * 给你一个以字符串形式表述的 布尔表达式 expression，返回该式的运算结果。
 *
 * 题目测试用例所给出的表达式均为有效的布尔表达式，遵循上述约定。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：expression = "&(|(f))"
 * 输出：false
 * 解释：
 * 首先，计算 |(f) --> f ，表达式变为 "&(f)" 。
 * 接着，计算 &(f) --> f ，表达式变为 "f" 。
 * 最后，返回 false 。
 * 示例 2：
 *
 * 输入：expression = "|(f,f,f,t)"
 * 输出：true
 * 解释：计算 (false OR false OR false OR true) ，结果为 true 。
 * 示例 3：
 *
 * 输入：expression = "!(&(f,t))"
 * 输出：true
 * 解释：
 * 首先，计算 &(f,t) --> (false AND true) --> false --> f ，表达式变为 "!(f)" 。
 * 接着，计算 !(f) --> NOT false --> true ，返回 true 。
 *  
 *
 * 提示：
 *
 * 1 <= expression.length <= 2 * 104
 * expression[i] 为 '('、')'、'&'、'|'、'!'、't'、'f' 和 ',' 之一
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/parsing-a-boolean-expression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 1106
    public boolean parseBoolExpr(String expression) {
        // expression = "&(|(f))" 两个栈 一个存放操作符号
        // ! & | 遇到 （ 往栈里边 放一个 标识 #
        Stack<Character> opStack = new Stack<>();
        Stack<Character> numStack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '!' || ch == '&' || ch == '|') {
                opStack.add(ch);
            } else if (ch == 'f' || ch == 't') {
                numStack.add(ch);
            } else if (ch == '(') {
                numStack.add('#');
            } else if (ch == ')') {
                // 获取操作符号
                Character pop = numStack.pop();
                boolean res = pop == 't';
                Character op = opStack.pop();
                if (op == '!') {
                    res = !res;
                } else {
                    while (numStack.peek() != '#') {
                        Character num = numStack.pop();
                        if (op == '&') {
                            res &= (num == 't');
                        } else {
                            // 或
                            res |= (num == 't');
                        }
                    }
                }
                if (numStack.peek() == '#') {
                    numStack.pop();
                }
                // 将结果 放入num中
                numStack.add(res?'t':'f');
            }
            // , 掠过
        }
        // num 出来结果
        if (numStack.isEmpty()) {
            return false;
        }
        return numStack.peek() == 't';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "&(|(f))";
        boolean b = solution.parseBoolExpr(expression);
        System.out.println(b);
        Assert.assertEquals(false, b);


        expression = "!(&(f,t))";
        b = solution.parseBoolExpr(expression);
        System.out.println(b);
        Assert.assertEquals(true, b);



    }
}
