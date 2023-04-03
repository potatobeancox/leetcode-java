package com.potato.study.leetcodecn.other.Interview.p0004.p0012;


import com.potato.study.leetcode.domain.TreeNode;

/**
 * 面试题 04.12. 求和路径
 *
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * 提示：
 *
 * 节点总数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/paths-with-sum-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    // 04 12 求路径 一直向下
    private int pathCount;
    public int pathSum(TreeNode root, int sum) {
        this.pathCount = 0;
        dfs(root, sum, 0);
        return pathCount;
    }

    private void dfs(TreeNode root, int sum, int current) {
        if (root == null) {
            // 到了终点 不需要统计
            return;
        }
        // 如果走到这个节点 current 已经等于 sum
        current += root.val;
        // 当前这个路径已经找到了 计数
        if (sum == current) {
            this.pathCount++;
        }
        // 当前节点作为起点且为终点
        if (root.val == sum) {
            this.pathCount++;
        }
        // 沿着之前的路往前走
        dfs(root.left, sum, current);
        dfs(root.right, sum, current);

        // 从孩子开始走
        dfs(root.left, sum, 0);
        dfs(root.right, sum, 0);
    }
}
