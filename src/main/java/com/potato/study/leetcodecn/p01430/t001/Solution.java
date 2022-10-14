package com.potato.study.leetcodecn.p01430.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1430. 判断给定的序列是否是二叉树从根到叶的路径
 *
 * 定一个二叉树，我们称从根节点到任意叶节点的任意路径中的节点值所构成的序列为该二叉树的一个 “有效序列” 。检查一个给定的序列是否是给定二叉树的一个 “有效序列” 。
 *
 * 我们以整数数组 arr 的形式给出这个序列。从根节点到任意叶节点的任意路径中的节点值所构成的序列都是这个二叉树的 “有效序列” 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
 * 输出：true
 * 解释：
 * 路径 0 -> 1 -> 0 -> 1 是一个“有效序列”（图中的绿色节点）。
 * 其他的“有效序列”是：
 * 0 -> 1 -> 1 -> 0
 * 0 -> 0 -> 0
 * 示例 2：
 *
 *
 *
 * 输入：root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
 * 输出：false
 * 解释：路径 0 -> 0 -> 1 不存在，所以这不是一个“序列”。
 * 示例 3：
 *
 *
 *
 * 输入：root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
 * 输出：false
 * 解释：路径 0 -> 1 -> 1 是一个序列，但不是一个“有效序列”（译者注：因为序列的终点不是叶节点）。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 5000
 * 0 <= arr[i] <= 9
 * 每个节点的值的取值范围是 [0 - 9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {





















    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int index) {
        // 终止条件 到了最后一个index
        if (index == arr.length - 1) {
            // root 是否是 叶子
            if (root == null || (root.left != null || root.right != null || root.val != arr[index])) {
                return false;
            }
            // 叶子节点 且值相同
            return true;
        }
        // 不是最后一个点 当前 root 是null gg
        if (null == root) {
            return false;
        }
        if (root.val != arr[index]) {
            return false;
        }
        // 递归往下
        return dfs(root.left, arr, index + 1) || dfs(root.right, arr, index + 1);
    }
}
