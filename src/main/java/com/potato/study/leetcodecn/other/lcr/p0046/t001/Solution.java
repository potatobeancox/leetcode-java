package com.potato.study.leetcodecn.other.lcr.p0046.t001;

import com.potato.study.leetcode.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 *
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

  

 示例 1:



 输入: [1,2,3,null,5,null,4]
 输出: [1,3,4]
 示例 2:

 输入: [1,null,3]
 输出: [1,3]
 示例 3:

 输入: []
 输出: []
  

 提示:

 二叉树的节点个数的范围是 [0,100]
 -100 <= Node.val <= 100 


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/WNC0Lk
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 层序遍历
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == size - 1) {
                    result.add(poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return result;
    }
}
