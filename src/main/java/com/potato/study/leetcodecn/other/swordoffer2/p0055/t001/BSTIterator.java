package com.potato.study.leetcodecn.other.swordoffer2.p0055.t001;

import java.util.Stack;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer II 055. 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 *
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST
 * 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * 可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入
 * inputs = ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * inputs = [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 105] 内
 * 0 <= Node.val <= 106
 * 最多调用 105 次 hasNext 和 next 操作
 *  
 *
 * 进阶：
 *
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kTOapQ
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class BSTIterator {

    private TreeNode root;
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.stack = new Stack<>();
        // 往stack 中放入节点 放入左子树的左边节点
        TreeNode current = root;
        while (current != null) {
            stack.add(current);
            current = current.left;
        }
    }

    public int next() {
        // stack pop 就是当前节点
        if (!hasNext()) {
            return -1;
        }
        TreeNode pop = stack.pop();
        TreeNode right = pop.right;
        if (right != null) {
            TreeNode current = right;
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
        }
        return pop.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
