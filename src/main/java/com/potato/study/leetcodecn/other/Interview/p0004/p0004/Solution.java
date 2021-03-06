package com.potato.study.leetcodecn.other.Interview.p0004.p0004;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 04.04. 检查平衡性
 *
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 *
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 * 返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-balance-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 找到两边的差
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        // 两边都平衡 递归找 孩子么
        return isBalanced(root.left) && isBalanced(root.right);
    }


    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height1 = getHeight(root.left);
        int height2 = getHeight(root.right);
        return 1 + Math.max(height1, height2);
    }
}
