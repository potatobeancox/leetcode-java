package com.potato.study.leetcodecn.p01080.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1080. 根到叶路径上的不足节点
 *
 * 给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）

 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为「不足节点」，需要被删除。

 请你删除所有不足节点，并返回生成的二叉树的根。

  

 示例 1：


 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1

 输出：[1,2,3,4,null,null,7,8,9,null,14]
 示例 2：


 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22

 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 示例 3：


 输入：root = [5,-6,-6], limit = 0
 输出：[]
  

 提示：

 给定的树有 1 到 5000 个节点
 -10^5 <= node.val <= 10^5
 -10^9 <= limit <= 10^9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insufficient-nodes-in-root-to-leaf-paths
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param root
     * @param limit
     * @return
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // dfs 后序便利 每个孩子返回是否可以删除 如果当前节点满足 左右孩子都可以删除，且 当前节点也满足 就删除
        boolean canDeleteNodeRoot = canDeleteNode(root, limit, 0);
        // 否则
        if (canDeleteNodeRoot) {
            return null;
        }
        return root;
    }

    private boolean canDeleteNode(TreeNode root, int limit, int current) {
        if (root == null) {
            return true;
        }
        // 当前是叶子节点 判断当前是否需要删除
        if (root.left == null && root.right == null) {
            if (current + root.val < limit) {
                return true;
            } else {
                return false;
            }
        }
        // 有孩子
        boolean canDeleteNodeLeft = true;
        if (root.left != null) {
            canDeleteNodeLeft = canDeleteNode(root.left, limit, root.val + current);
        }
        boolean canDeleteNodeRight = true;
        if (root.right != null) {
            canDeleteNodeRight = canDeleteNode(root.right, limit, root.val + current);
        }
        // 删除孩子
        if (canDeleteNodeLeft) {
            root.left = null;
        }
        if (canDeleteNodeRight) {
            root.right = null;
        }
        return canDeleteNodeLeft && canDeleteNodeRight && current + root.val < limit;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(-5);
        root.left.right = null;

        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(4);
        root.right.right = null;
        TreeNode node = solution.sufficientSubset(root, -1);

    }
}
