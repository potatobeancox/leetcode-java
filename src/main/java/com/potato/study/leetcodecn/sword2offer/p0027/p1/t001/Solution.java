package com.potato.study.leetcodecn.sword2offer.p0027.p1.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。

 例如输入：

      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 镜像输出：

      4
    /   \
   7     2
  / \   / \
 9   6 3   1

  

 示例 1：

 输入：root = [4,2,7,1,3,6,9]
 输出：[4,7,2,9,6,3,1]
  

 限制：

 0 <= 节点个数 <= 1000

 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode node1 = mirrorTree(root.right);
        TreeNode node2 = mirrorTree(root.left);
        root.left = node1;
        root.right = node2;
        return root;
    }

}
