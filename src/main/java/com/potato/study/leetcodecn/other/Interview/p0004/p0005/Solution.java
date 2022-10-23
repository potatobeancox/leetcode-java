package com.potato.study.leetcodecn.other.Interview.p0004.p0005;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 面试题 04.05. 合法二叉搜索树
 *
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。

 示例 1:
 输入:
 2
 / \
 1   3
 输出: true
 示例 2:
 输入:
 5
 / \
 1   4
      / \
     3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
      根节点的值为 5 ，但是其右子节点值为 4 。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/legal-binary-search-tree-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    public boolean isValidBST(TreeNode root) {
        // 左边得是 true 且右边也是 且
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (!isValidBST(root.left) || !isValidBST(root.right)) {
            return false;
        }
        // 当前值大于左子树素有孩子 小于右子树所有孩子
        if (root.left != null) {
            int leftMax = getMax(root.left);
            if (root.val <= leftMax) {
                return false;
            }

        }
        if (root.right != null) {
            int rightMin = getMin(root.right);
            if (root.val >= rightMin) {
                return false;
            }
        }
        return true;
    }

    private int getMax(TreeNode root) {
        int max = root.val;
        if (root.left != null) {
            max = Math.max(max, getMax(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, getMax(root.right));
        }
        return max;

    }

    private int getMin(TreeNode root) {
        int min = root.val;
        if (root.left != null) {
            min = Math.min(min, getMin(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, getMin(root.right));
        }
        return min;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);

        boolean validBST = solution.isValidBST(root);
        System.out.println(validBST);
        Assert.assertEquals(false, validBST);
    }



}
