package com.potato.study.leetcodecn.other.swordoffer.p0007.p1.t001;

import java.util.Arrays;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer 07. 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *  
 *
 * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     * 前序遍历和中序遍历
     * 递归求解
     * 1. preorder 找到 0 作为 当前节点
     * 2. 将 inorder 使用 index 0 分割成两个子数组
     * 3. 再利用 2 的数量 顺序 将 preorder 分割成2个数组
     * 4. 利用23 数组 递归求解
     * 5. 返回当前点
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 0. 终止条件
        if (null == preorder || preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        // 1. preorder 找到 0 作为 当前节点
        TreeNode root = new TreeNode(preorder[0]);
        // 2. 将 inorder 使用 index 0 分割成两个子数组
        int[][] childInorder = getChildArr(inorder, preorder[0]);
        // 3. 再利用 2 的数量 顺序 将 preorder 分割成2个数组
        int[][] childPreorder = getChildArr(preorder, childInorder[0].length, childInorder[1].length);
        // 4. 利用23 数组 递归求解
        root.left = buildTree(childPreorder[0], childInorder[0]);
        root.right = buildTree(childPreorder[1], childInorder[1]);
        // 5. 返回当前点
        return root;
    }


    /**
     * 根据 target 将 数组分割成两部分
     * @param inorder
     * @param target
     * @return
     */
    private int[][] getChildArr(int[] inorder, int target) {
        int[][] result = new int[2][];
        // find the indes of the target
        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (target == inorder[i]) {
                index = i;
                break;
            }
        }
        if (index < 0 || index >= inorder.length) {
            result[0] = new int[0];
            result[1] = new int[0];
            return result;
        }
        result[0] = Arrays.copyOfRange(inorder, 0, index);
        result[1] = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        return result;
    }

    /**
     * 根据 target 将 数组分割成两部分
     * @param preorder
     * @param len1
     * @param len2
     * @return
     */
    private int[][] getChildArr(int[] preorder, int len1, int len2) {
        int[][] result = new int[2][];
        result[0] = Arrays.copyOfRange(preorder, 1, 1 + len1);
        result[1] = Arrays.copyOfRange(preorder, 1 + len1, len1 + len2 + 1);
        return result;
    }


}
