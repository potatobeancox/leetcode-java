package com.potato.study.leetcodecn.p00545.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.*;

/**
 * 545. 二叉树的边界
 *
 * 二叉树的 边界 是由 根节点 、左边界 、按从左到右顺序的 叶节点 和 逆序的右边界 ，按顺序依次连接组成。

 左边界 是满足下述定义的节点集合：

 根节点的左子节点在左边界中。如果根节点不含左子节点，那么左边界就为 空 。
 如果一个节点在左边界中，并且该节点有左子节点，那么它的左子节点也在左边界中。
 如果一个节点在左边界中，并且该节点 不含 左子节点，那么它的右子节点就在左边界中。
 最左侧的叶节点 不在 左边界中。
 右边界 定义方式与 左边界 相同，只是将左替换成右。即，右边界是根节点右子树的右侧部分；叶节点 不是 右边界的组成部分；如果根节点不含右子节点，那么右边界为 空 。

 叶节点 是没有任何子节点的节点。对于此问题，根节点 不是 叶节点。

 给你一棵二叉树的根节点 root ，按顺序返回组成二叉树 边界 的这些值。

  

 示例 1：


 输入：root = [1,null,2,3,4]
 输出：[1,3,4,2]
 解释：
 - 左边界为空，因为二叉树不含左子节点。
 - 右边界是 [2] 。从根节点的右子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以右边界只有 2 。
 - 叶节点从左到右是 [3,4] 。
 按题目要求依序连接得到结果 [1] + [] + [3,4] + [2] = [1,3,4,2] 。
 示例 2：


 输入：root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
 输出：[1,2,4,7,8,9,10,6,3]
 解释：
 - 左边界为 [2] 。从根节点的左子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以左边界只有 2 。
 - 右边界是 [3,6] ，逆序为 [6,3] 。从根节点的右子节点开始的路径为 3 -> 6 -> 10 ，但 10 是叶节点。
 - 叶节点从左到右是 [4,7,8,9,10]
 按题目要求依序连接得到结果 [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3] 。
  

 提示：

 树中节点的数目在范围 [1, 104] 内
 -1000 <= Node.val <= 1000

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/boundary-of-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // bfs
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<Integer> left = new ArrayList<>();
        dfsGetLeft(root.left, left);

        List<Integer> right = new LinkedList<>();
        dfsGetRight(root.right, right);

        List<Integer> leaves = new ArrayList<>();
        dfsGetLeaves(root.left, leaves);
        dfsGetLeaves(root.right, leaves);

        res.add(root.val);
        // 左边界
        res.addAll(left);
        // 叶子
        if (left.size() > 0 && leaves.size() > 0) {
            leaves = leaves.subList(1, leaves.size());
        }
        res.addAll(leaves);
        // 右边界 逆序
        if (leaves.size() > 0 && right.size() > 0) {
            right = right.subList(1, right.size());
        }
        res.addAll(right);
        return res;
    }

    private void dfsGetRight(TreeNode root, List<Integer> right) {
        if (root == null) {
            return;
        }
        right.add(0, root.val);
        if (root.right != null) {
            dfsGetRight(root.right, right);
        } else if (root.left != null) {
            dfsGetRight(root.left, right);
        }
    }

    private void dfsGetLeft(TreeNode root, List<Integer> left) {
        if (root == null) {
            return;
        }
        left.add(root.val);
        if (root.left != null) {
            dfsGetLeft(root.left, left);
        } else if (root.right != null) {
            dfsGetLeft(root.right, left);
        }
    }


    /**
     * 递归找叶子
     * @param root
     * @param leaves
     */
    private void dfsGetLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        dfsGetLeaves(root.left, leaves);
        dfsGetLeaves(root.right, leaves);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);


        List<Integer> list = solution.boundaryOfBinaryTree(root);
        // [1,3,4,2]
        System.out.println(list);
    }


}
