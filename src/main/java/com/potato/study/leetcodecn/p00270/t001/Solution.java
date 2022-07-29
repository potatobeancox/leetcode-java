package com.potato.study.leetcodecn.p00270.t001;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 270. 最接近的二叉搜索树值
 *
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。
 *
 * 注意：
 *
 * 给定的目标值 target 是一个浮点数
 * 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 * 示例：
 *
 * 输入: root = [4,2,5,1,3]，目标值 target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/closest-binary-search-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {



    private int num;

    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        this.num = root.val;
        getClosestValue(root, target);
        return num;
    }

    private void getClosestValue(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < Math.abs(num - target)) {
            num = root.val;
        }
        // 往做孩子找
        if (root.val > target) {
            getClosestValue(root.left, target);
        } else if (root.val < target) {
            getClosestValue(root.right, target);
        }
    }
}

