package com.potato.study.leetcodecn.p00965.t001;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 965. 单值二叉树
 *
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

 只有给定的树是单值二叉树时，才返回 true；否则返回 false。

  

 示例 1：



 输入：[1,1,1,1,1,null,1]
 输出：true
 示例 2：



 输入：[2,2,2,5,2]
 输出：false
  

 提示：

 给定树的节点数范围是 [1, 100]。
 每个节点的值都是整数，范围为 [0, 99] 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/univalued-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {


    /**
     * 递归找
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isUnivalTree(root, root.val);
    }

    private boolean isUnivalTree(TreeNode root, int val) {
        if (null == root) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }


}
