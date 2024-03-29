package com.potato.study.leetcodecn.other.lcr.p0054.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 *
 * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 *
 *  
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 *
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * 示例 3：
 *
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * 示例 4：
 *
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 *  
 *
 * 提示：
 *
 * 树中的节点数介于 0 和 104 之间。
 * 每个节点的值介于 -104 和 104 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 *  
 *
 * 注意：
 *
 * 本题与主站 538 题相同： https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 * 本题与主站 1038 题相同：https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/w6cpku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    private long sum = 0;

    /**
     * 右 中左 遍历 记录当前sum 修改
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        // 对于每一个节点 先序遍历 如果是叶子直接返回
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            sum += root.val;
            root.val = (int)sum;
            return root;
        }
        if (root.right != null) {
            // 对于当前节点 获取右子树节点的值，加上当前节点
            convertBST(root.right);
        }
        sum += root.val;
        root.val = (int)sum;
        if (root.left != null) {
            convertBST(root.left);
        }
        return root;
    }
}
