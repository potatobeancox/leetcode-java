package com.potato.study.leetcodecn.p01120.t001;


import com.potato.study.leetcode.domain.TreeNode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1120. 子树的最大平均值
 *
 * 给你一棵二叉树的根节点 root，找出这棵树的 每一棵 子树的 平均值 中的 最大 值。

 子树是树中的任意节点和它的所有后代构成的集合。

 树的平均值是树中节点值的总和除以节点数。

  

 示例：



 输入：[5,6,1]
 输出：6.00000
 解释：
 以 value = 5 的节点作为子树的根节点，得到的平均值为 (5 + 6 + 1) / 3 = 4。
 以 value = 6 的节点作为子树的根节点，得到的平均值为 6 / 1 = 6。
 以 value = 1 的节点作为子树的根节点，得到的平均值为 1 / 1 = 1。
 所以答案取最大值 6。
  

 提示：

 树中的节点数介于 1 到 5000之间。
 每个节点的值介于 0 到 100000 之间。
 如果结果与标准答案的误差不超过 10^-5，那么该结果将被视为正确答案。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/maximum-average-subtree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private double max;

    public double maximumAverageSubtree(TreeNode root) {
        this.max = 0;
        if (root == null) {
            return max;
        }
        // dfs 返回 sum 和 个数 两个量
        int[] rootInfo = dfs(root);
        max = Math.max(max, ave(rootInfo));
        return max;
    }

    /**
     * 输入保证不能为 null
     * @param root
     * @return
     */
    private int[] dfs(TreeNode root) {
        // 终止条件 叶子
        int[] res = new int[] {
                root.val, 1
        };
        if (root.left == null && root.right == null) {
            // 当前子树平均
            max = Math.max(max, ave(res));
            return res;
        }
        // 拿到孩子的
        if (root.left != null) {
            int[] leftChild = dfs(root.left);
            res[0] += leftChild[0];
            res[1] += leftChild[1];
        }
        if (root.right != null) {
            int[] rightChild = dfs(root.right);
            res[0] += rightChild[0];
            res[1] += rightChild[1];
        }
        max = Math.max(max, ave(res));
        return res;
    }

    private double ave(int[] rootInfo) {
        return (double) rootInfo[0] / rootInfo[1];
    }
}
