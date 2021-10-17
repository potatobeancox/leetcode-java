package com.potato.study.leetcodecn.p01457.t001;

import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 1457. 二叉树中的伪回文路径
 *
 * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 *
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [2,3,1,3,1,null,1]
 * 输出：2
 * 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
 *      在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,1,1,3,null,null,null,null,null,1]
 * 输出：1
 * 解释：上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
 *      这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 3：
 *
 * 输入：root = [9]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 给定二叉树的节点数目在 1 到 10^5 之间。
 * 节点值在 1 到 9 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private int count;

    public int pseudoPalindromicPaths (TreeNode root) {
        if (null == root) {
            return 0;
        }
        this.count = 0;
        // dfs 遍历 root 生成 结果
        int bit = 0;
        dfs(bit, root);
        return count;
    }


    /**
     * 判断的使用 将状态压缩到一个 int形中 每个位置做 异或 代表数字是否是奇数
     * @param bit 状态
     * @param node
     */
    private void dfs(int bit, TreeNode node) {
        // 当前节点放入builder 中 dfs 往后找 左右孩子
        bit ^= (1 << node.val);
        // 终止条件 node == null
        if (node.left == null && node.right == null) {
            if (isPalindrome(bit)) {
                this.count++;
            }
            return;
        }
        if (node.left != null) {
            dfs(bit, node.left);
        }
        if (node.right != null) {
            dfs(bit, node.right);
        }
    }



    /**
     * 判断单词是不是 伪回文 数字
     * @param bit
     * @return
     */
    private boolean isPalindrome(int bit) {
        // 偶数
        if (bit == 0) {
            return true;
        }
        // 是不是只有一个位置是1
        if ((bit & (bit - 1)) == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);

        int i = solution.pseudoPalindromicPaths(root);
        System.out.println(i);
        Assert.assertEquals(2, i);
    }
}
