package com.potato.study.leetcodecn.p00701.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 *
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。

 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

  

 例如, 

 给定二叉搜索树:

 4
 / \
 2   7
 / \
 1   3

 和 插入的值: 5
 你可以返回这个二叉搜索树:

 4
 /   \
 2     7
 / \   /
 1   3 5
 或者这个树也是有效的:

 5
 /   \
 2     7
 / \
 1   3
 \
 4
  

 提示：

 给定的树上的节点数介于 0 和 10^4 之间
 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 -10^8 <= val <= 10^8
 新值和原始二叉搜索树中的任意节点值都不同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 找到位置 然后插入
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // root 与 val 不可能相等
        if (root.left == null && root.val > val) {
            root.left = new TreeNode(val);
            return root;
        }

        if (root.right == null && root.val < val) {
            root.right = new TreeNode(val);
            return root;
        }

        // 左右 孩子有存在的
        if (root.val > val) {
            insertIntoBST(root.left, val);
        } else if (root.val < val) {
            insertIntoBST(root.right, val);
        } else {
            throw new RuntimeException("不可能存在的情况");
        }
        return root;
    }
}
