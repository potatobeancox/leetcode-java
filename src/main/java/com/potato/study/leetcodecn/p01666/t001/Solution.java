package com.potato.study.leetcodecn.p01666.t001;

import com.potato.study.leetcode.domain.node.val.left.right.parent.Node;

import java.util.Arrays;

/**
 * 1666. 改变二叉树的根节点
 *
 * 给定一棵二叉树的根节点 root 和一个叶节点 leaf ，更改二叉树，使得 leaf 为新的根节点。
 *
 * 你可以按照下列步骤修改从 leaf 到 root 的路径中除 root 外的每个节点 cur ：
 *
 * 如果 cur 有左子节点，则该子节点变为 cur 的右子节点。注意我们保证 cur 至多有一个子节点。
 * cur 的原父节点变为 cur 的左子节点。
 * 返回修改后新树的根节点。
 *
 * 注意：确保你的答案在操作后正确地设定了 Node.parent （父节点）指针，否则会被判为错误答案。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 7
 * 输出: [7,2,null,5,4,3,6,null,null,null,1,null,null,0,8]
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 0
 * 输出: [0,1,null,3,8,5,null,null,null,6,2,null,null,7,4]
 *  
 *
 * 提示:
 *
 * 树中节点的个数在范围 [2, 100] 内。
 * -109 <= Node.val <= 109
 * 所有的 Node.val 都是唯一的。
 * leaf 存在于树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/change-the-root-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private Node root;

    public Node flipBinaryTree(Node root, Node leaf) {
        this.root = root;
        flipBinaryTreeHelper(leaf);
        leaf.parent = null;
        return leaf;
    }

    private void flipBinaryTreeHelper(Node current) {
        if (this.root == current) {
            return;
        }
        Node left = current.left;
        Node parent = current.parent;
        // 记录各种节点
        if (left != null) {
            current.right = left;
        }
        current.left = parent;
        if (parent.left == current) {
            parent.left = null;
        }
        if (parent.right == current) {
            parent.right = null;
        }
        flipBinaryTreeHelper(parent);
        parent.parent = current;
    }
}
