package com.potato.study.leetcodecn.p01973.t001;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1973. 值等于子节点值之和的节点数量
 *
 * 给定一颗二叉树的根节点 root ，返回满足条件：节点的值等于该节点所有子节点的值之和 的节点的数量。
 *
 * 一个节点 x 的 子节点 是指从节点 x 出发，到所有叶子节点路径上的节点。没有子节点的节点的子节点和视为 0 。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: root = [10,3,4,2,1]
 * 输出: 2
 * 解释:
 * 对于值为10的节点: 其子节点之和为： 3+4+2+1 = 10。
 * 对于值为3的节点：其子节点之和为： 2+1 = 3。
 * 示例 2:
 *
 *
 * 输入: root = [2,3,null,2,null]
 * 输出: 0
 * 解释:
 * 没有节点满足其值等于子节点之和。
 * 示例 3:
 *
 *
 * 输入: root = [0]
 * 输出: 1
 * 解释:
 * 对于值为0的节点：因为它没有子节点，所以自己点之和为0。
 *  
 *
 * 提示：
 *
 * 树中节点的数量范围： [1, 105]
 * 0 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-nodes-equal-to-sum-of-descendants
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int num;

    public int equalToDescendants(TreeNode root) {
        this.num = 0;
        int rootTreeSum = getEqualToDescendants(root);
        return this.num;
    }

    /**
     * 获取 root 子树每个节点的和 并过程中 比较相同的 root 和 root子树 sum
     * @param root
     * @return 以 root 为根的树 每个节点值的和
     */
    private int getEqualToDescendants(TreeNode root) {
        // 如果是 null
        if (root == null) {
            return 0;
        }
        // 如果是叶子
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                this.num++;
            }
            return root.val;
        }
        // 非叶子 递归获取 左孩子和右边孩子 求和 和当前节点对比
        int leftSum = getEqualToDescendants(root.left);
        int rightSum = getEqualToDescendants(root.right);
        if (root.val == leftSum + rightSum) {
            this.num++;
        }
        return leftSum + rightSum + root.val;
    }

}
