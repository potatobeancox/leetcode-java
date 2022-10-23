package com.potato.study.leetcodecn.p00549.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 549. 二叉树中最长的连续序列
 *
 * 给定二叉树的根 root ，返回树中最长连续路径的长度。
 * 连续路径是路径中相邻节点的值相差 1 的路径。此路径可以是增加或减少。
 *
 * 例如， [1,2,3,4] 和 [4,3,2,1] 都被认为有效，但路径 [1,2,4,3] 无效。
 * 另一方面，路径可以是子-父-子顺序，不一定是父子顺序。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [1,2,3]
 * 输出: 2
 * 解释: 最长的连续路径是 [1, 2] 或者 [2, 1]。
 *  
 *
 * 示例 2:
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: 3
 * 解释: 最长的连续路径是 [1, 2, 3] 或者 [3, 2, 1]。
 *  
 *
 * 提示：
 *
 * 树上所有节点的值都在 [1, 3 * 104] 范围内。
 * -3 * 104 <= Node.val <= 3 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int max;

    /**
     *
     * @param root
     * @return
     */
    public int longestConsecutive(TreeNode root) {
        // dfs 记录 当前预期的下一个是增大还是见效
        this.max = 0;
        dfs(root);
        return max;
    }

    /**
     *
     * @param root
     * @return index0 代表以root为开始的连续降序 4321 的长度，index1 连续升序的长度
     */
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        // 先求左右孩子的长度
        int[] leftChild = dfs(root.left);
        int[] rightChild = dfs(root.right);


        // 初始化结果
        int[] res = new int[] {1, 1};
        // 如果没有左右孩子只能返回当前节点
        if (root.left == null && root.right == null) {
            max = Math.max(1, max);
            return res;
        }
        // 判断下 当前roo val 和 左孩子是否差1
        if (root.left != null && Math.abs(root.val - root.left.val) == 1) {
            // index0 代表以root为开始的连续降序 4321 的长度，index1 连续升序的长度
            if (root.val - root.left.val == 1) {
                res[0] = Math.max(res[0], leftChild[0] + 1);
                // 更新max
                max = Math.max(max, res[0]);

            }
            // 1234
            if (root.val - root.left.val == -1) {
                res[1] = Math.max(res[1], leftChild[1] + 1);
                // 更新max
                max = Math.max(max, res[1]);
            }
        }
        // 判断右孩子是否差1
        if (root.right != null && Math.abs(root.val - root.right.val) == 1) {
            // index0 代表以root为开始的连续降序 4321 的长度，index1 连续升序的长度
            if (root.val - root.right.val == 1) {
                res[0] = Math.max(res[0], rightChild[0] + 1);

                // 更新max 左边升序 右边降序
                max = Math.max(max, res[1] + rightChild[0]);
            }
            // 1234
            if (root.val - root.right.val == -1) {
                res[1] = Math.max(res[1], rightChild[1] + 1);

                // 更新max 左边升序 右边升序
                max = Math.max(max, res[0] + rightChild[1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.left.right = new TreeNode(2);


        int i = solution.longestConsecutive(root);
        System.out.println(i);
        Assert.assertEquals(3, i);

    }

}
