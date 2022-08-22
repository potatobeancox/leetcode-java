package com.potato.study.leetcodecn.p00333.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

import org.junit.Assert;

/**
 * 333. 最大 BST 子树
 *
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。

 二叉搜索树（BST）中的所有节点都具备以下属性：

 左子树的值小于其父（根）节点的值。

 右子树的值大于其父（根）节点的值。

 注意：子树必须包含其所有后代。

  

 示例 1：



 输入：root = [10,5,15,1,8,null,7]
 输出：3
 解释：本例中最大的 BST 子树是高亮显示的子树。返回值是子树的大小，即 3 。
 示例 2：

 输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 输出：2
  

 提示：

 树上节点数目的范围是 [0, 104]
 -104 <= Node.val <= 104
  

 进阶:  你能想出 O(n) 时间复杂度的解法吗？

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/largest-bst-subtree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int maxSubtreeCount;

    public int largestBSTSubtree(TreeNode root) {
        this.maxSubtreeCount = 0;
        // dfs 返回子树的 max min 和个数
        dfs(root);
        return maxSubtreeCount;
    }

    /**
     * 0 max
     * 1 min
     * 2 个数
     * @param root
     * @return
     */
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 终止条件叶子
        int rootValue = root.val;
        int[] rootInfo = new int[3];
        rootInfo[0] = rootValue;
        rootInfo[1] = rootValue;
        rootInfo[2] = 1;
        // 叶子节点
        if (root.left == null && root.right == null) {
            this.maxSubtreeCount = Math.max(this.maxSubtreeCount, rootInfo[2]);
            return rootInfo;
        }
        // 只有 左子树
        if (root.left != null && root.right == null) {
            int[] leftChildInfo = dfs(root.left);
            // 左子树无法构成 那这个数就废了 小于等于 左子树的最大值
            if (leftChildInfo == null || rootValue <= leftChildInfo[0]) {
                return null;
            }
            rootInfo[0] = Math.max(leftChildInfo[0], rootInfo[0]);
            rootInfo[1] = Math.min(leftChildInfo[1], rootInfo[1]);
            rootInfo[2] += leftChildInfo[2];
        } else if (root.right != null && root.left == null) {
            // 只有 右子树
            int[] rightChildInfo = dfs(root.right);
            if (rightChildInfo == null || rootValue >= rightChildInfo[1]) {
                return null;
            }
            rootInfo[0] = Math.max(rightChildInfo[0], rootInfo[0]);
            rootInfo[1] = Math.min(rightChildInfo[1], rootInfo[1]);
            rootInfo[2] += rightChildInfo[2];

        } else {
            // 如果有孩子 找到孩子的信息
            int[] leftChildInfo = dfs(root.left);
            int[] rightChildInfo = dfs(root.right);

            if (leftChildInfo == null || rightChildInfo == null) {
                return null;
            }
            if (rootValue <= leftChildInfo[0] || rootValue >= rightChildInfo[1]) {
                return null;
            }

            rootInfo[1] = Math.min(leftChildInfo[1], rootInfo[1]);
            rootInfo[2] += leftChildInfo[2];

            rootInfo[0] = Math.max(rightChildInfo[0], rootInfo[0]);
            rootInfo[2] += rightChildInfo[2];
        }
        this.maxSubtreeCount = Math.max(this.maxSubtreeCount, rootInfo[2]);
        return rootInfo;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);

        root.right = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int i = solution.largestBSTSubtree(root);
        System.out.println(i);
        Assert.assertEquals(3, i);
    }
}
