package com.potato.study.leetcodecn.p00687.t001;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 687. 最长同值路径
 *
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int maxLength;

    public int longestUnivaluePath(TreeNode root) {
        this.maxLength = 0;
        // 递归 函数作用 找到当前点为根的 两个相同点的最长路径
        if (null == root) {
            return 0;
        }
        longestUnivaluePathEach(root);
        return maxLength - 1;
    }


    private int longestUnivaluePathEach(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int len = 1;
        int leftLen = 0;
        int sameLen = 1;
        if (root.left != null) {
            leftLen = longestUnivaluePathEach(root.left);
            if (root.val == root.left.val) {
                len = Math.max(len, leftLen + 1);
                sameLen += leftLen;
            }
        }
        int rightLen = 0;
        if (root.right != null) {
            rightLen = longestUnivaluePathEach(root.right);
            if (root.val == root.right.val) {
                len = Math.max(len, rightLen + 1);
                sameLen += rightLen;
            }
        }
        this.maxLength = Math.max(maxLength, sameLen);
        return len;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);

        root.right.left.left = new TreeNode(1);
        root.right.left.right = new TreeNode(1);

        root.right.right.left = new TreeNode(1);
//        root.right.right = new TreeNode(5);
        int i = solution.longestUnivaluePath(root);
        System.out.println(i);
        Assert.assertEquals(4, i);



        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(5);
//        root.right.right = new TreeNode(5);
        i = solution.longestUnivaluePath(root);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }



}
