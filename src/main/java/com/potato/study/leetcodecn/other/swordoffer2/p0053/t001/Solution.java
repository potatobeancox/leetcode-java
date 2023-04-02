package com.potato.study.leetcodecn.other.swordoffer2.p0053.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 *
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 *  
 *
 * 注意：本题与主站 285 题相同： https://leetcode-cn.com/problems/inorder-successor-in-bst/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/P5rCT8
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private TreeNode prev;
    private TreeNode target;
    // ii 053
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 中序 遍历 root 过程中 记录 上一个节点 prev
        this.prev = null;
        this.target = null;
        doInorderSuccessorFind(root, p);
        return target;
    }

    /**
     * 中序遍历 如果当前遍历到的节点 时，prev等于 p 那么当前节点就是 后继 返回即可
     * @param root
     * @param p
     */
    private void doInorderSuccessorFind(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        // 剪枝
        if (target != null) {
            return;
        }
        // do left child
        doInorderSuccessorFind(root.left, p);
        // 处理如果prev 就是p 那么当前节点就是 后继
        // 剪枝
        if (target == null && p != null && prev != null && p.val == prev.val) {
            target = root;
            return;
        }
        prev = root;
        doInorderSuccessorFind(root.right, p);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        TreeNode p = new TreeNode(1);
        root.left = p;
        root.right = new TreeNode(3);

        Solution solution = new Solution();
        TreeNode treeNode = solution.inorderSuccessor(root, p);
        System.out.println(treeNode);


        root = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        p = new TreeNode(1);
        root.left.left.left = p;

        treeNode = solution.inorderSuccessor(root, p);
        // 2
        System.out.println(treeNode);

    }


}
