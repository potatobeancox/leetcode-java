package com.potato.study.leetcodecn.p00250.t001;

import java.util.Map;
import java.util.TreeMap;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 250. 统计同值子树
 *
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 *
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 *
 * 示例：
 *
 * 输入: root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-univalue-subtrees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int sameTreeCount;

    public int countUnivalSubtrees(TreeNode root) {
        this.sameTreeCount = 0;
        dfs(root);
        return this.sameTreeCount;
    }

    /**
     *
     * @param root
     * @return true 这个树是 相同的树
     */
    private boolean dfs(TreeNode root) {
        if (null == root) {
            return true;
        }
        // 只有叶子肯定是 相同的树
        if (root.left == null && root.right == null) {
            this.sameTreeCount++;
            return true;
        }
        boolean leftSame = dfs(root.left);
        boolean rightSame = dfs(root.right);
        if (!leftSame || !rightSame) {
            return false;
        }
        // 子树都是相同 判断 当前根是不是相同
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        this.sameTreeCount++;
        return true;
    }


}
