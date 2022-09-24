package com.potato.study.leetcodecn.p00776.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 776. 拆分二叉搜索树
 *
 * 给你一棵二叉搜索树（BST）的根结点 root 和一个整数 target 。请将该树按要求拆分为两个子树：其中一个子树结点的值都必须小于等于给定的目标值；另一个子树结点的值都必须大于目标值；树中并非一定要存在值为 target 的结点。
 *
 * 除此之外，树中大部分结构都需要保留，也就是说原始树中父节点 p 的任意子节点 c ，假如拆分后它们仍在同一个子树中，那么结点 p 应仍为 c 的父结点。
 *
 * 返回 两个子树的根结点的数组 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,6,1,3,5,7], target = 2
 * 输出：[[2,1],[4,3,6,null,null,5,7]]
 * 示例 2:
 *
 * 输入: root = [1], target = 1
 * 输出: [[1],[]]
 *  
 *
 * 提示：
 *
 * 二叉搜索树节点个数在 [1, 50] 范围内
 * 0 <= Node.val, target <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/split-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public TreeNode[] splitBST(TreeNode root, int target) {
        // 如果当前节点 是null 终止条件 直接返回
        if (root == null) {
            return new TreeNode[] {null, null};
        }
        if (root.val <= target) {
            // 比较 当前 root 值和 target 如果 root 值小于等于 target 分割点在 右孩子
            TreeNode[] treeNodes = splitBST(root.right, target);
            root.right = treeNodes[0];
            treeNodes[0] = root;
            return treeNodes;
        } else {
            // 否则分割点子在左孩子
            TreeNode[] treeNodes = splitBST(root.left, target);
            root.left = treeNodes[1];
            treeNodes[1] = root;
            return treeNodes;
        }
    }
}
