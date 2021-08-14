package com.potato.study.leetcodecn.other.swordoffer.p0055.p2.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

  

 示例 1:

 给定二叉树 [3,9,20,null,null,15,7]

 3
 / \
 9  20
 /  \
 15   7
 返回 true 。

 示例 2:

 给定二叉树 [1,2,2,3,3,null,null,4,4]

 1
 / \
 2   2
 / \
 3   3
 / \
 4   4
 返回 false 。

  

 限制：

 1 <= 树的结点个数 <= 10000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 当前节点 左子树和 右子树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        if (null == root) {
            return true;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 返回当前节点的高度
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

}
