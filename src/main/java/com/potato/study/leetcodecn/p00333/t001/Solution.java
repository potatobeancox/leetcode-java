package com.potato.study.leetcodecn.p00333.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.*;

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
        // 如果有孩子 找到孩子的信息
        int[] leftChildInfo = dfs(root.left);
        int[] rightChildInfo = dfs(root.right);

        // 当前节点信息
        int rootValue = root.val;
        int[] rootInfo = new int[3];
        rootInfo[0] = rootValue;
        rootInfo[1] = rootValue;
        rootInfo[2] = 1;
        // 大于 左孩子的最大值
        if (leftChildInfo != null) {
            rootInfo[0] = Math.min(leftChildInfo[0], rootInfo[0]);
            rootInfo[1] = Math.max(leftChildInfo[1], rootInfo[1]);
            rootInfo[2] += leftChildInfo[2];
        }
        // 小于 右孩子的最小值
        if (rightChildInfo != null) {
            rootInfo[0] = Math.min(rightChildInfo[0], rootInfo[0]);
            rootInfo[1] = Math.max(rightChildInfo[1], rootInfo[1]);
            rootInfo[2] += rightChildInfo[2];
        }
        // 记录历史最大值
        if (leftChildInfo != null && rightChildInfo != null && rootValue > leftChildInfo[0] && rootValue < rightChildInfo[1]) {
            this.maxSubtreeCount = Math.max(this.maxSubtreeCount, rootInfo[2]);
        } else if (leftChildInfo != null && rootValue > leftChildInfo[0] && rightChildInfo == null) {
            this.maxSubtreeCount = Math.max(this.maxSubtreeCount, rootInfo[2]);
        } else if (rightChildInfo != null && rootValue < rightChildInfo[1] && leftChildInfo == null) {
            this.maxSubtreeCount = Math.max(this.maxSubtreeCount, rootInfo[2]);
        }
        return rootInfo;
    }
}
