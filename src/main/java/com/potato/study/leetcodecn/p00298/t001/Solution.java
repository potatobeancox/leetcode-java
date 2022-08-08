package com.potato.study.leetcodecn.p00298.t001;


import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 298. 二叉树最长连续序列
 *
 * 给你一棵指定的二叉树的根节点 root ，请你计算其中 最长连续序列路径 的长度。
 *
 * 最长连续序列路径 是依次递增 1 的路径。该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父节点到子节点，反过来是不可以的。
 *
 *  
 * 示例 1：
 *
 *
 * 输入：root = [1,null,3,2,4,null,null,null,5]
 * 输出：3
 * 解释：当中，最长连续序列是 3-4-5 ，所以返回结果为 3 。
 * 示例 2：
 *
 *
 * 输入：root = [2,null,3,2,null,1]
 * 输出：2
 * 解释：当中，最长连续序列是 2-3 。注意，不是 3-2-1，所以返回 2 。
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 3 * 104] 内
 * -3 * 104 <= Node.val <= 3 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int max;

    public int longestConsecutive(TreeNode root) {
        this.max = 0;
        longestConsecutiveDfs(root);
        return max;
    }

    public int longestConsecutiveDfs(TreeNode root) {
        // 终止条件
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            this.max = Math.max(1, max);
            return 1;
        }
        // dfs 判断 是否与 孩子是 递增 + 1的关系
        int leftHeight = longestConsecutiveDfs(root.left);
        int rightHeight = longestConsecutiveDfs(root.right);

        int height = 1;
        // 是的话 返回
        if (leftHeight > 0 && root.left.val == root.val + 1) {
            height = Math.max(height, leftHeight + 1);
        }
        if (rightHeight > 0 && root.right.val == root.val + 1) {
            height = Math.max(height, rightHeight + 1);
        }
        this.max = Math.max(max, height);
        return height;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        int i = solution.longestConsecutive(root);
        System.out.println(i);
        Assert.assertEquals(3, i);


        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        i = solution.longestConsecutive(root);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
