package com.potato.study.leetcodecn.p01597.t001;


import com.potato.study.leetcode.domain.node.val.left.right.ch.Node;

import java.util.Stack;

/**
 * 1597. 根据中缀表达式构造二叉表达式树
 *
 * 二叉表达式树 是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符： '+' （加）、 '-' （减）、 '*' （乘）和 '/' （除）。
 *
 * 对于每一个运算符为 o 的非叶节点，对应的 中缀表达式 为 (A o B)，其中 A 是左子树所表达的表达式， B 是右子树所表达的表达式。
 *
 * 给定一个 中缀表达式 字符串 s，其中包含操作数、上面提到的运算符，以及括号 '(' 与 ')' 。
 *
 * 返回一个有效的 二叉表达式树，其 中序遍历 序列对应表达式 s 消除括号后的序列（详情参见下面的示例）。
 *
 * 注意，表达式的一般解析顺序适用于 s，即优先解析括号内的表达式，然后解析乘除法，最后解析加减法。
 *
 * 同时，操作数在 s 和树的中序遍历中 出现顺序相同 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：s = "3*4-2*5"
 * 输出：[-,*,*,3,4,2,5]
 * 解释：上面是唯一一种有效的二叉表达式树，其中序遍历生成 s 。
 * 示例 2：
 *
 *
 * 输入：s = "2-3/(5*2)+1"
 * 输出：[+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
 * 解释：上面的树的中序遍历为 2-3/5*2+1 ，与 s 消除括号后相同。该树还会生成正确的结果，其操作数的顺序与 s 中出现的顺序相同。
 * 下面的树也是一个有效的二叉表达式树，具有与 s 相同的中序遍历，但它不是一个有效的答案，因为它的求值结果不同。
 *
 * 下面的树也是无效的。尽管它的计算结果相等并与上述树等效，但其中序遍历不会产生 s ，并且其操作数与 s 中的顺序也不相同。
 *
 * 示例 3：
 *
 * 输入：s = "1+2+3+4+5"
 * 输出：[+,+,5,+,4,null,null,+,3,null,null,1,2]
 * 解释：二叉树 [+,+,5,+,+,null,null,1,2,3,4] 也是诸多有效的二叉表达式树之一。
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 100
 * s 中包含数字和字符 '+'、 '-'、 '*'、 '/' 。
 * s 中的操作数 恰好 是一位数字。
 * 题目数据保证 s 是一个有效的表达式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/build-binary-expression-tree-from-infix-expression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public Node expTree(String s) {
        // 遍历 s 变成后缀表达式
        String postfixExpression = getEvaluatePostfixExpression(s);
        // 遍历后缀表达式 遇到操作符合 从操作数栈里边获取两个节点 连接
        Stack<Node> stack = new Stack<>();
        char[] chars = postfixExpression.toCharArray();
        for (char ch : chars) {
            // 数字
            if (Character.isDigit(ch)) {
                Node node = new Node();
                node.val = ch;
                // 插入代计算的列表
                stack.add(node);
            } else {
                // 操作符
                Node node = new Node();
                node.val = ch;
                node.right = stack.pop();
                node.left = stack.pop();
                // 将组成好的 放入栈
                stack.add(node);
            }
        }
        return stack.peek();
    }

    private String getEvaluatePostfixExpression(String s) {
        // 遍历 s 中字符 如果是 数字或者 （ 入栈 如果是 数字栈和符号栈 如果当前是符号
        StringBuilder builder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.add(ch);
                continue;
            }
            if (ch == '(') {
                stack.add(ch);
                continue;
            }
            if (ch == '+' || ch == '-') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    builder.append(stack.pop());
                }
                stack.add(ch);
                continue;
            }
            if (ch == '*' || ch == '/') {
                while (!stack.isEmpty() && stack.peek() != '('
                        && stack.peek() != '+' && stack.peek() != '-') {
                    builder.append(stack.pop());
                }
                stack.add(ch);
                continue;
            }
            if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    builder.append(stack.pop());
                }
                // 将 （ pop掉
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node = solution.expTree("3*4-2*5");
        System.out.println(node);
    }
}
