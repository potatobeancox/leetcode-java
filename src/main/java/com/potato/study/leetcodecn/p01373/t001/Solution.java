package com.potato.study.leetcodecn.p01373.t001;


import com.potato.study.leetcode.domain.TreeNode;
import org.junit.Assert;

/**
 * 1373. 二叉搜索子树的最大键值和
 *
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 *
 * 二叉搜索树的定义如下：
 *
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 * 示例 2：
 *
 *
 *
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 * 示例 3：
 *
 * 输入：root = [-4,-2,-5]
 * 输出：0
 * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 * 示例 4：
 *
 * 输入：root = [2,1,3]
 * 输出：6
 * 示例 5：
 *
 * 输入：root = [5,4,8,3,null,6,3]
 * 输出：7
 *  
 *
 * 提示：
 *
 * 每棵树有 1 到 40000 个节点。
 * 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    private int maxSum;
    // 1373
    public int maxSumBST(TreeNode root) {
        this.maxSum = 0;
        findMaxSumBST(root);
        return maxSum;
    }

    private FindBstResult findMaxSumBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        FindBstResult leftRes = findMaxSumBST(root.left);
        FindBstResult rightRes = findMaxSumBST(root.right);
        // 左右孩子都是空 当前就是
        FindBstResult result = new FindBstResult();
        result.isBst = true;
        result.sum = root.val;
        result.maxVal = root.val;
        result.minVal = root.val;
        // 左右孩子至少有一个不是0
        if (leftRes != null) {
            result.isBst = result.isBst && leftRes.isBst && root.val > leftRes.maxVal;
            result.sum += leftRes.sum;
            result.maxVal = Math.max(result.maxVal, leftRes.maxVal);
            result.minVal = Math.min(result.minVal, leftRes.minVal);

        }
        if (rightRes != null) {
            result.isBst = result.isBst && rightRes.isBst  && root.val < rightRes.minVal;
            result.sum += rightRes.sum;
            result.maxVal = Math.max(result.maxVal, rightRes.maxVal);
            result.minVal = Math.min(result.minVal, rightRes.minVal);
        }

        if (result.isBst) {
            this.maxSum = Math.max(this.maxSum, result.sum);
        }
        return result;
    }


    /**
     * 递归找bst 子树的统计结果
     */
    class FindBstResult {
        // true 为是bst
        public boolean isBst;
        public int minVal;
        public int maxVal;
        // 当前节点为跟的子树上的节点和
        public int sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
//
//        int i = solution.maxSumBST();
//        System.out.println(i);
//        Assert.assertEquals(20, i);
    }

}
