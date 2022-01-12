package com.potato.study.leetcodecn.p00623.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 623. 在二叉树中增加一行
 *
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。

 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。

 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。

 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。

 示例 1:

 输入:
 二叉树如下所示:
 4
 /   \
 2     6
 / \   /
 3   1 5

 v = 1

 d = 2

 输出:
 4
 / \
 1   1
 /     \
 2       6
 / \     /
 3   1   5

 示例 2:

 输入:
 二叉树如下所示:
 4
 /
 2
 / \
 3   1

 v = 1

 d = 3

 输出:
 4
 /
 2
 / \
 1   1
 /     \
 3       1
 注意:

 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 输入的二叉树至少有一个节点。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-one-row-to-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (null == root) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int layerIndex = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (layerIndex == depth - 1) {
                    TreeNode left = new TreeNode(val);
                    left.left = poll.left;
                    TreeNode right = new TreeNode(val);
                    right.right = poll.right;
                    poll.left = left;
                    poll.right = right;
                } else {
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                }
            }
            if (layerIndex == depth - 1) {
                return root;
            }
            layerIndex++;
        }
        // 高度没有那么高
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }

}
