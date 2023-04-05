package com.potato.study.leetcodecn.other.Interview.p0004.p0012;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            // 到了终点 不需要统计
            return 0;
        }
        // 从这个节点开始 找路径 保证这个节点是起点
        int path = rootPathSum(root, sum, 0);

        // 从孩子节点找
        if (root.left != null) {
            path += pathSum(root.left, sum);
        }
        if (root.right != null) {
            path += pathSum(root.right, sum);
        }

        return path;
    }

    /**
     * 返回从这个节点 到某个孩子 路径值为 sum 的条数
     * @param root
     * @param sum
     * @param current   走到当前节点已经有了多少积累
     * @return
     */
    private int rootPathSum(TreeNode root, int sum, int current) {
        if (root == null) {
            // 到了终点 不需要统计
            return 0;
        }
        // 如果走到这个节点 current 已经等于 sum
        current += root.val;
        int pathCount = 0;
        // 当前这个路径已经找到了 计数
        if (sum == current) {
            pathCount++;
        }
        // 沿着之前的路往前走 有正数也有负数
        if (root.left != null) {
            pathCount += rootPathSum(root.left, sum, current);
        }
        if (root.right != null) {
            pathCount += rootPathSum(root.right, sum, current);
        }
        return pathCount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        int i = solution.pathSum(root, 1);
        System.out.println(i);
        Assert.assertEquals(1, i);


        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);

        i = solution.pathSum(root, 3);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
