package com.potato.study.leetcodecn.p00450.t001;

import com.potato.study.leetcode.domain.TreeNode;
import com.potato.study.leetcode.util.TreeNodeUtil;

/**
 * 450. 删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

 一般来说，删除节点可分为两个步骤：

 首先找到需要删除的节点；
 如果找到了，删除它。
 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

 示例:

 root = [5,3,6,2,4,null,7]
 key = 3

 5
 / \
 3   6
 / \   \
 2   4   7

 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

 5
 / \
 4   6
 /     \
 2       7

 另一个正确答案是 [5,2,6,null,4,null,7]。

 5
 / \
 2   6
 \   \
 4   7

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-dian-by-l/
     *
     * 递归删除 指定节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (null == root) {
            return root;
        }
        // 往两侧找寻删除
        if (root.val < key) {
            // 左子树里边操作删除
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            // 右子树里边操作删除
            root.left = deleteNode(root.left, key);
        } else {
            // 相等 找到了 待删除的节点
            if (root.left == null && root.right == null) {
                // 叶子 直接深处
                return null;
            } else if (root.left != null) {
                // 不是叶子 有左孩子，找到左孩子的最右的孩子，再递归删除
                root.val = getPrecursorValue(root);
                root.left = deleteNode(root.left, root.val);
            } else {
                // 不是叶子，有右孩子，找到右孩子最左边的孩子，再递归删除 (root.right != null)
                root.val = getSuccessorValue(root);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }


    /**
     * 返回当前root 之前的那个节点
     * @return
     */
    private int getPrecursorValue(TreeNode root) {
        TreeNode left = root.left;
        while (null != left.right) {
            left = left.right;
        }
        return left.val;
    }


    /**
     * 找到当前root 后继节点的值
     * @return
     */
    private int getSuccessorValue(TreeNode root) {
        TreeNode right = root.right;
        while (null != right.left) {
            right = right.left;
        }
        return right.val;
    }


    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.generateTreeByArrayString("[5,3,6,2,4,null,7]");

        Solution solution = new Solution();
        TreeNode node = solution.deleteNode(root, 3);
        TreeNodeUtil.printBSTTreeNodeInArrayString(node, 3);
    }
}
