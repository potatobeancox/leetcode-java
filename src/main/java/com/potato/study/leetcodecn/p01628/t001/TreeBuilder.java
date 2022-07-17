package com.potato.study.leetcodecn.p01628.t001;

import java.util.Stack;

/**
 * 1628. 设计带解析函数的表达式树
 *
 * 给定一个算术表达式的后缀表示法的标记（token） postfix ，构造并返回该表达式对应的二叉表达式树。

 后缀表示法是一种将操作数写在运算符之前的表示法。例如，表达式 4*(5-(2+7)) 的后缀表示法表示为数组 postfix = ["4","5","7","2","+","-","*"] 。

 抽象类 Node 需要用于实现二叉表达式树。我们将通过 evaluate 函数来测试返回的树是否能够解析树中的值。你不可以移除 Node 类，但你可以按需修改此类，也可以定义其他类来实现它。

 二叉表达式树是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符： '+' （加）、 '-' （减）、 '*' （乘）和 '/' （除）。

 我们保证任何子树对应值的绝对值不超过 109 ，且所有操作都是有效的（即没有除以零的操作）

 进阶： 你可以将表达式树设计得更模块化吗？例如，你的设计能够不修改现有的 evaluate 的实现就能支持更多的操作符吗？

  

 示例 1:



 输入： s = ["3","4","+","2","*","7","/"]
 输出： 2
 解释： 此表达式可解析为上述二叉树，其对应表达式为 ((3+4)*2)/7) = 14/7 = 2.
 示例 2:



 输入: s = ["4","5","7","2","+","-","*"]
 输出: -16
 解释: 此表达式可解析为上述二叉树，其对应表达式为 4*(5-(2+7)) = 4*(-4) = -16.
  

 提示:

 1 <= s.length < 100
 s.length 是奇数。
 s 包含数字和字符 '+' 、 '-' 、 '*' 以及 '/' 。
 如果 s[i] 是数，则对应的整数不超过 105 。
 s 保证是一个有效的表达式。
 结果值和所有过程值的绝对值均不超过 109 。
 保证表达式不包含除以零的操作。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/design-an-expression-tree-with-evaluate-function
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TreeBuilder {


    /**
     * 判断 str 是否是 运算符
     * @param str
     * @return
     */
    private boolean isOp(String str) {
        return "+".equals(str) || "-".equals(str)
                || "*".equals(str) || "/".equals(str);
    }



    Node buildTree(String[] postfix) {
        // 遍历 postfix 用stack 存孩子节点 如果遇到运算符号 生成
        Stack<Node> stack = new Stack<>();
        ExpressionNode expressionNode = null;
        for (String str : postfix) {
            // 当前节点
            boolean op = isOp(str);
            expressionNode = new ExpressionNode();
            if (op) {
                expressionNode.op = str.charAt(0);
                // 左右孩子 连接
                if (stack.isEmpty()) {
                    continue;
                }
                Node rightChild = stack.pop();
                Node leftChild = stack.pop();
                expressionNode.left = leftChild;
                expressionNode.right = rightChild;
            } else {
                expressionNode.val = Integer.parseInt(str);
            }
            stack.add(expressionNode);


        }
        return expressionNode;
    }
}

class ExpressionNode extends Node {

    public Node left;
    public Node right;
    public int val;
    public char op;

    public int evaluate() {
        // 如果当前是叶子节点直接返回 val
        if (this.left == null && this.right == null) {
            return this.val;
        }
        // 否则 计算左右孩子 返回计算结果
        int leftVal = this.left.evaluate();
        int rightVal = this.right.evaluate();

        if ('+' == this.op) {
            return leftVal + rightVal;
        } else if ('-' == this.op) {
            return leftVal - rightVal;
        } else if ('*' == this.op) {
            return leftVal * rightVal;
        } else {
            return leftVal / rightVal;
        }
    }
}


abstract class Node {
    public abstract int evaluate();
    // define your fields here
}
