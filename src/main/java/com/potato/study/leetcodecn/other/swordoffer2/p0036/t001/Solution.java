package com.potato.study.leetcodecn.other.swordoffer2.p0036.t001;

import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 剑指 Offer II 036. 后缀表达式
 *
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 *  
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *  
 *
 * 示例 1：
 *
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *  
 *
 * 提示：
 *
 * 1 <= tokens.length <= 104
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 *  
 *
 * 逆波兰表达式：
 *
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 *
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 *
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 *  
 *
 * 注意：本题与主站 150 题相同： https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/8Zf90G
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 036
    public int evalRPN(String[] tokens) {
        // 遍历 token  +、-、*、/
        Stack<Integer> numStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        for (String token : tokens) {
            // 数字直接入栈
            if (null == token || token.length() == 0) {
                continue;
            }
            // 符号的好 与栈peek 比较下优先级
            if (Character.isDigit(token.charAt(0))) {
                // 数字
                int i = Integer.parseInt(token);
                numStack.add(i);
            } else {
                // 符号
                if (opStack.isEmpty()) {
                    opStack.add(token);
                    continue;
                }
                String peek = opStack.peek();
                int priority = getPriority(peek, token);
                // 如果peek 优先级大 需要计算peek
                if (priority >= 0) {
                    Integer num2 = numStack.pop();
                    Integer num1 = numStack.pop();
                    opStack.pop();

                    int res = compute(num1, num2, peek);

                    opStack.push(token);
                    numStack.push(res);
                } else {
                    opStack.add(token);
                }
            }
        }
        if (numStack.isEmpty()) {
            return 0;
        }
        return numStack.peek();
    }

    private int compute(Integer num1, Integer num2, String op) {
        int res = 0;
        switch (op) {
            case "+" :
                res = num1 + num2;
                break;
            case "-" :
                res = num1 - num2;
                break;
            case "*" :
                res = num1 * num2;
                break;
            case "/" :
                res = num1 / num2;
                break;
        }
        return res;
    }


    /**
     * 比较 op1 和 op2 之间优先级
     * @param op1
     * @param op2
     * @return
     */
    private int getPriority(String op1, String op2) {
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);

        return Integer.compare(priorityMap.getOrDefault(op1, 0), priorityMap.getOrDefault(op2, 0));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] tokens = new String[] {
                "2","1","+","3","*"
        };
        int i = solution.evalRPN(tokens);
        System.out.println(i);
        Assert.assertEquals(9, i);
    }
}
