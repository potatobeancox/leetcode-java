package com.potato.study.leetcodecn.p01609.t001;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

import com.potato.study.leetcode.domain.TreeNode;

/**
 * 1609. 奇偶树
 *
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：

 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。

  

 示例 1：



 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 输出：true
 解释：每一层的节点值分别是：
 0 层：[1]
 1 层：[10,4]
 2 层：[3,7,9]
 3 层：[12,8,6,2]
 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 示例 2：



 输入：root = [5,4,2,3,3,7]
 输出：false
 解释：每一层的节点值分别是：
 0 层：[5]
 1 层：[4,2]
 2 层：[3,3,7]
 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 示例 3：



 输入：root = [5,9,1,3,5,7]
 输出：false
 解释：1 层上的节点值应为偶数。
 示例 4：

 输入：root = [1]
 输出：true
 示例 5：

 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 输出：true
  

 提示：

 树中节点数在范围 [1, 105] 内
 1 <= Node.val <= 106

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/even-odd-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    // 1609
    public boolean isEvenOddTree(TreeNode root) {
        // 层序遍历
        int layerIndex = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int last = -1;

        while (!queue.isEmpty()) {
            int layerSize = queue.size();
            boolean isFirst = true;
            for (int i = 0; i < layerSize; i++) {
                TreeNode poll = queue.poll();
                // 奇偶性
                if (layerIndex % 2 == 0) {
                    // 0 层
                    if (poll.val % 2 == 0) {
                        return false;
                    }
                } else {
                    // 1 层
                    if (poll.val % 2 == 1) {
                        return false;
                    }
                }
                // 递增性质 偶数递增 奇数递减
                if (isFirst) {
                    isFirst = false;
                    last = poll.val;

                    // 左右孩子添加
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }

                    continue;
                }
                // 递增性质 偶数递增 奇数递减
                if (layerIndex % 2 == 0  && last >= poll.val) {
                    return false;
                }
                if (layerIndex % 2 == 1 && last <= poll.val) {
                    return false;
                }
                last = poll.val;

                // 左右孩子添加
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            layerIndex++;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(2);
        root.right.left = new TreeNode(7);

        boolean evenOddTree = solution.isEvenOddTree(root);
        System.out.println(evenOddTree);
        Assert.assertEquals(false, evenOddTree);
    }


}
