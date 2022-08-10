package com.potato.study.leetcodecn.p00285.t001;


import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreeNodeUtil;

import java.util.Iterator;

/**
 * 285. 二叉搜索树中的中序后继
 *
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。

 节点 p 的后继是值比 p.val 大的节点中键值最小的节点。

  

 示例 1：



 输入：root = [2,1,3], p = 1
 输出：2
 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 示例 2：



 输入：root = [5,3,6,2,4,null,null,1], p = 6
 输出：null
 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
  

 提示：

 树中节点的数目在范围 [1, 104] 内。
 -105 <= Node.val <= 105
 树中各节点的值均保证唯一。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/inorder-successor-in-bst
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private TreeNode pre;
    private TreeNode target;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        this.pre = null;
        this.target = null;

        inorder(root, p);

        return this.target;
    }

    private void inorder(TreeNode root, TreeNode p) {
        if (root.left != null) {
            inorder(root.left, p);
        }
        // 处理当前节点
        if (this.target != null) {
            return;
        }
        // 找到了后继
        if (p == pre) {
            this.target = root;
            return;
        }
        this.pre = root;
        if (root.right != null) {
            inorder(root.right, p);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        TreeNode p = root.left;
        TreeNode treeNode = solution.inorderSuccessor(root, p);
        System.out.println(treeNode);

    }
}
