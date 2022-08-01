package com.potato.study.leetcodecn.p00156.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.Stack;

/**
 * 156. 上下翻转二叉树
 *
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。

 你可以按下面的步骤翻转一棵二叉树：

 原来的左子节点变成新的根节点
 原来的根节点变成新的右子节点
 原来的右子节点变成新的左子节点

 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。

  

 示例 1：


 输入：root = [1,2,3,4,5]
 输出：[4,5,2,null,null,3,1]
 示例 2：

 输入：root = []
 输出：[]
 示例 3：

 输入：root = [1]
 输出：[1]
  

 提示：

 树中节点数目在范围 [0, 10] 内
 1 <= Node.val <= 10
 树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）
 树中的每个右节点都没有子节点

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/binary-tree-upside-down
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    /**
     * 画个图
     * https://leetcode.cn/problems/binary-tree-upside-down/solution/zui-you-ya-ban-ben-an-ceng-cong-shang-wa-ipic/
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {

        TreeNode parent = null;
        TreeNode right = null;

        while (root != null) {

            TreeNode left = root.left;
            // 第一个为空
            root.left = right;
            right = root.right;
            root.right = parent;
            // 新的 parent 新的root
            parent = root;
            root = left;

        }
        return parent;
    }
}
