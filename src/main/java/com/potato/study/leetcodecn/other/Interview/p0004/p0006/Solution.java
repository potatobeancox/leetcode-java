package com.potato.study.leetcodecn.other.Interview.p0004.p0006;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 04.06. 后继者
 *
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/successor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private TreeNode prev;
    /**
     * 中序 遍历 找到下一个节点
     * 从 root 这个树中找到下一个节点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode leftResult = inorderSuccessor(root.left, p);
        if (leftResult != null) {
            return leftResult;
        }
        // visit 修改 prev 修改之前判断一下
        if (prev == p) {
            return root;
        }
        this.prev = root;
        // 往有孩子找一找
        return inorderSuccessor(root.right, p);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode p = left;
        TreeNode node = solution.inorderSuccessor(root, p);
        System.out.println(node == null ? "null" : node.val);
    }
}
