package com.potato.study.leetcodecn.other.swordoffer.p0068.p2.t001;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]



  

 示例 1:

 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 输出: 3
 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 示例 2:

 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 输出: 5
 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
  

 说明:

 所有节点的值都是唯一的。
 p、q 为不同节点且均存在于给定的二叉树中。
 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归 终止 条件 当前 root 为空 或者就是 pq 中的一个点
        if (null == root || root == p || root == q) {
            return root;
        }
        // 递归在 左子树和柚子树 中找pq
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 通过结果判定在哪个子树 根据题目含义一边一个
        if (left != null && right != null) {
            return root;
        } else if (left == null && right == null) {
            return null;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }


}
