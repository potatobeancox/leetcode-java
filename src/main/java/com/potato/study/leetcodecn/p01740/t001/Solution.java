package com.potato.study.leetcodecn.p01740.t001;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1740. 找到二叉树中的距离
 *
 * 给定一棵二叉树的根节点 root 以及两个整数 p 和 q ，返回该二叉树中值为 p 的结点与值为 q 的结点间的 距离 。
 *
 * 两个结点间的 距离 就是从一个结点到另一个结点的路径上边的数目。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
 * 输出：3
 * 解释：在 5 和 0 之间有 3 条边：5-3-1-0
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
 * 输出：2
 * 解释：在 5 和 7 之间有 2 条边：5-2-7
 * 示例 3：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
 * 输出：0
 * 解释：一个结点与它本身之间的距离为 0
 *  
 *
 * 提示：
 *
 * 树中结点个数的范围在 [1, 104].
 * 0 <= Node.val <= 109
 * 树中所有结点的值都是唯一的.
 * p 和q 是树中结点的值.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-distance-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public int findDistance(TreeNode root, int p, int q) {
        // 找到 共同的父亲
        TreeNode parent = getCommonParent(root, p, q);
        // 求 父亲到这两个点 距离多少
        int path1 = getPath(parent, p);
        int path2 = getPath(parent, q);
        return path1 + path2;
    }

    /**
     * 距离
     * @param parent
     * @param p
     * @return
     */
    private int getPath(TreeNode parent, int p) {
        if (parent == null) {
            return -1;
        }
        if (parent.val == p) {
            return 0;
        }
        int path1 = getPath(parent.left, p);
        int path2 = getPath(parent.right, p);

        if (path1 >= 0 && path2 >= 0) {
            return Math.min(path1, path2) + 1;
        } else if (path1 >= 0) {
            return path1 + 1;
        } else if (path2 >= 0) {
            return path2 + 1;
        } else {
            return -1;
        }
    }

    /**
     * 找到共同的父亲
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode getCommonParent(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        // 这个节点不是
        TreeNode left = getCommonParent(root.left, p, q);
        TreeNode right = getCommonParent(root.right, p, q);

        if (left != null && right != null) {
            // 一遍一个
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
}
