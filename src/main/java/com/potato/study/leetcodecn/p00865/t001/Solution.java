package com.potato.study.leetcodecn.p00865.t001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 865. 具有所有最深节点的最小子树
 *
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 *
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 *
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 *
 * 返回能满足 以该节点为根的子树中包含所有最深的节点 这一条件的具有最大深度的节点。
 *
 *  
 *
 * 注意：本题与力扣 1123 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 示例 3：
 *
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 *  
 *
 * 提示：
 *
 * 树中节点的数量介于 1 和 500 之间。
 * 0 <= Node.val <= 500
 * 每个节点的值都是独一无二的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private Map<TreeNode, Integer> depthMap;

    private int maxDepth;

    /**
     * https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/solution/ju-you-suo-you-zui-shen-jie-dian-de-zui-xiao-zi-sh/
     *
     *
     *                                                                                                                                        E#
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // map 存 节点对应深度
        this.depthMap = new HashMap<>();
        this.maxDepth = 0;
        // dfs 一遍 记录所有深度并记录 最深
        buildDepthMap(root, null);
        // 再进行一次dfs 找到当前前左右孩子是否有 最深点 都有 递归找到 父亲节点
        TreeNode node = dfsGetDeepRoot(root);
        return node;
    }


    /**
     * dfs 获取最深的公共节点
     * @param root
     * @return
     */
    private TreeNode dfsGetDeepRoot(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (depthMap.get(root) == maxDepth) {
            return root;
        }
        // 当前不是叶子
        TreeNode left = dfsGetDeepRoot(root.left);
        TreeNode right = dfsGetDeepRoot(root.right);
        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            // 两边都有depth 当前点可以作为 返回
            return root;
        }
    }

    /**
     * 递归找到当前点的深度
     *
     * @param node
     * @param parent
     */
    private void buildDepthMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        // 获取到 父亲的深度 ++
        Integer depthParent = depthMap.getOrDefault(parent, 0);
        depthMap.put(node, depthParent + 1);
        maxDepth = Math.max(maxDepth, depthParent +1);
        if (node.left != null) {
            buildDepthMap(node.left, node);
        }
        if (node.right != null) {
            buildDepthMap(node.right, node);
        }
    }



}
